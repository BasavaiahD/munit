/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.java;

import static org.mule.munit.common.MunitCore.buildMuleStackTrace;
import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.munit.common.MunitCore;
import org.mule.munit.config.MunitFlow;
import org.mule.munit.config.MunitTestFlow;
import org.mule.munit.runner.output.DefaultOutputHandler;
import org.mule.munit.runner.output.TestOutputHandler;
import org.mule.tck.MuleTestUtils;

import java.util.List;

import junit.framework.TestCase;


/**
 * <p>
 * This is the java representation of a Munit test written with mule code.
 * </p>
 * <p>
 * The Munit tests can be converted into Junit tests by extending the class {@link AbstractMuleSuite}. When user do
 * that instead of creating a {@link org.mule.munit.runner.mule.MunitTest} Munit core creates this class which
 * extends from {@link TestCase}
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitTest extends TestCase
{

    /**
     * The list of flows to be executed before the test
     */
    private List<MunitFlow> before;

    /**
     * The test flow to be executed
     */
    private MunitTestFlow flow;

    /**
     * The list of flows to be executed after the test
     */
    private List<MunitFlow> after;

    /**
     * The mule context, used to access mule configuration/registry
     */
    private MuleContext muleContext;

    /**
     * Hander of the test results. It manages the way the results are shown.
     */
    private TestOutputHandler outputHandler = new DefaultOutputHandler();


    public MunitTest(List<MunitFlow> before, MunitTestFlow flow, List<MunitFlow> after)
    {
        this.before = before;
        this.flow = flow;
        this.after = after;
        this.muleContext = flow.getMuleContext();
    }

    public String getName()
    {
        return flow.getName();
    }

    @Override
    public int countTestCases()
    {
        return 1;
    }

    /**
     * <p>
     * Runs the munit flow and handles the result. In case of failure it changes the java stack trace to the mule
     * stack trace.
     * </p>
     *
     * @throws Throwable
     */
    @Override
    protected void runTest() throws Throwable
    {
        if (flow.isIgnore())
        {
            return;
        }

        MuleEvent event = muleEvent();
        run(event, before);

        showDescription();

        try
        {
            flow.process(event);
        }
        catch (Throwable t)
        {
            if (!flow.expectException(t, event))
            {
                t.setStackTrace(buildMuleStackTrace(event.getMuleContext())
                                        .toArray(new StackTraceElement[] {}));
                throw t;
            }

        }
        finally
        {
            MunitCore.reset(muleContext);
            run(event, after);
        }
    }

    private void run(MuleEvent event, List<MunitFlow> flows) throws MuleException
    {
        if (flows != null)
        {
            for (MunitFlow flow : flows)
            {
                outputHandler.printDescription(flow.getName(), flow.getDescription());
                flow.process(event);
            }
        }
    }

    private void showDescription()
    {
        outputHandler.printDescription(flow.getName(), flow.getDescription().replaceAll("\\.", "\\.%n"));
    }


    protected MuleEvent muleEvent()
    {
        try
        {
            return new DefaultMuleEvent(new DefaultMuleMessage(null, muleContext), MessageExchangePattern.REQUEST_RESPONSE, MuleTestUtils.getTestFlow(muleContext));
        }
        catch (Exception e)
        {
            return null;
        }
    }

}

