/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.config;

import org.junit.Test;

import org.mule.api.MuleContext;

import static org.mockito.Mockito.mock;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitBeforeTestTest
{

    MuleContext muleContext = mock(MuleContext.class);

    @Test
    public void testConstructor()
    {
        new MunitBeforeTest("name", muleContext);
    }
}
