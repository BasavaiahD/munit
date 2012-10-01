package org.mule.munit.runner;


import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.registry.MuleRegistry;
import org.mule.munit.config.MunitAfterSuite;
import org.mule.munit.config.MunitBeforeSuite;
import org.mule.munit.config.MunitTest;
import org.mule.munit.runner.mule.result.output.TestOutputHandler;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MunitRunnerTest {


    private static final String AFTER_TEST_DESCRIPTION = "After Test Description";
    private static final String AFTER_TEST_NAME = "AfterTestName";

    private static final String BEFORE_TEST_DESCRIPTION = "Before Test Description";
    private static final String BEFORE_TEST_NAME = "BeforeTestName";


    private MuleContext muleContext;
    private MuleRegistry registry;
    private List<MunitAfterSuite> afterTestFlows;
    private List<MunitBeforeSuite> beforeTestFlows;
    private MunitTest munitTest;
    private MunitAfterSuite afterTest;
    private MunitBeforeSuite beforeTest;

    private TestOutputHandler handler;

    private MuleContextManager manager;


    @Before
    public void setUp(){
        muleContext = mock(MuleContext.class);
        registry = mock(MuleRegistry.class);
        munitTest = mock(MunitTest.class);
        manager = mock(MuleContextManager.class);
        afterTest = mock(MunitAfterSuite.class);
        beforeTest = mock(MunitBeforeSuite.class);
        afterTestFlows = Arrays.asList(new MunitAfterSuite[]{afterTest});
        beforeTestFlows = Arrays.asList(new MunitBeforeSuite[]{beforeTest});
        handler = mock(TestOutputHandler.class);

        when(muleContext.getRegistry()).thenReturn(registry);
    }

    /**
     * If it doesn't fail then check that everything is called
     */
    @Test
    public void killMuleMustBeCalled() throws MuleException {
        setBehavior();

        MunitRunner<MockSuiteResult> runner = new MunitRunner<MockSuiteResult>(handler, manager, muleContext){

            @Override
            protected MockSuiteResult runSuite() throws Exception {
                return null;
            }
        };

        runner.run();

        verifyMocks();
    }


    /**
     * If fails after test and kill mule must be called
     */
    @Test(expected = RuntimeException.class)
    public void killMuleMustBeCalledWhenFailed() throws MuleException {
        setBehavior();

        MunitRunner<MockSuiteResult> runner = new MunitRunner<MockSuiteResult>(handler, manager, muleContext){

            @Override
            protected MockSuiteResult runSuite() throws Exception {
                throw new Exception("error");
            }
        };

        runner.run();

        verifyMocks();
    }

    private void verifyMocks() throws MuleException {
        verify(handler, times(1)).print(BEFORE_TEST_NAME, BEFORE_TEST_DESCRIPTION);
        verify(handler, times(1)).print(AFTER_TEST_NAME, AFTER_TEST_DESCRIPTION);
        verify(afterTest, times(1)).process(null);
        verify(beforeTest, times(1)).process(null);
        verify(manager, times(1)).killMule(muleContext);
    }

    private void setBehavior() {
        when(registry.lookupObjects(MunitBeforeSuite.class)).thenReturn(beforeTestFlows);
        when(registry.lookupObjects(MunitAfterSuite.class)).thenReturn(afterTestFlows);
        when(registry.lookupObjects(MunitTest.class)).thenReturn(Arrays.asList(new MunitTest[]{munitTest}));

        when(afterTest.getDescription()).thenReturn(AFTER_TEST_DESCRIPTION);
        when(afterTest.getName()).thenReturn(AFTER_TEST_NAME);
        when(beforeTest.getDescription()).thenReturn(BEFORE_TEST_DESCRIPTION);
        when(beforeTest.getName()).thenReturn(BEFORE_TEST_NAME);
    }

    private class MockSuiteResult {
    }
}
