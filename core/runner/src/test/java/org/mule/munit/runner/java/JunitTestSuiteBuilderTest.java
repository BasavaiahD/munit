package org.mule.munit.runner.java;


import org.junit.Before;
import org.junit.Test;
import org.mule.munit.config.MunitFlow;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class JunitTestSuiteBuilderTest {

    MunitFlow flow;

    @Before
    public void setUp(){
        flow = mock(MunitFlow.class);
    }


    @Test
    public void createTestMustNotBeNull(){
        JunitTestSuiteBuilder builder = new JunitTestSuiteBuilder(null);
        assertNotNull(builder.test(null,flow,null));
    }

    @Test
    public void createTSuiteMustNotBeNull(){
        JunitTestSuiteBuilder builder = new JunitTestSuiteBuilder(null);
        assertNotNull(builder.createSuite("test"));
    }
}
