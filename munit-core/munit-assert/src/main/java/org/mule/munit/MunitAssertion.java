/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit;

import org.mule.api.MuleEvent;

/**
 * <p>Interface that all the custom assertions must implement</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface MunitAssertion
{

    /**
     * <p>Method that asserts a mule event</p>
     *
     * @param muleEvent <p>Mule Event to be asserted</p></p>
     * @return <p>The original Mule Event</p>
     * @throws AssertionError <p>Case the assertion fails</p>
     */
    MuleEvent execute(MuleEvent muleEvent) throws AssertionError;
}
