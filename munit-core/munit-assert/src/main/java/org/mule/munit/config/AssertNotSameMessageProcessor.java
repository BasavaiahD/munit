/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.config;

import org.mule.api.MuleMessage;
import org.mule.munit.AssertModule;


/**
 * <p>
 * Assert not Same message processor
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertNotSameMessageProcessor extends MunitMessageProcessor
{

    /**
     * @see AssertModule#assertNotSame(String, Object, Object)
     */
    private String message;

    /**
     * @see AssertModule#assertNotSame(String, Object, Object)
     */
    private Object expected;

    /**
     * @see AssertModule#assertNotSame(String, Object, Object)
     */
    private Object value;

    /**
     * @see MunitMessageProcessor#doProcess(org.mule.api.MuleMessage, org.mule.munit.AssertModule)
     */
    @Override
    protected void doProcess(MuleMessage mulemessage, AssertModule module)
    {
        module.assertNotSame(message, evaluate(mulemessage, expected), evaluate(mulemessage, value));
    }

    /**
     * @see org.mule.munit.config.MunitMessageProcessor#getProcessor()
     */
    @Override
    protected String getProcessor()
    {
        return "assertNotSame";
    }

    public void setMessage(String value)
    {
        this.message = value;
    }

    public void setExpected(Object value)
    {
        this.expected = value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

}
