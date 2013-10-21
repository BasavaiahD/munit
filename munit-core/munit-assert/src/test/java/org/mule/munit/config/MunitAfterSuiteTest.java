package org.mule.munit.config;

import org.junit.Test;

import org.mule.api.MuleContext;

import static org.mockito.Mockito.mock;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitAfterSuiteTest
{

    MuleContext muleContext = mock(MuleContext.class);

    @Test
    public void testConstructor()
    {
        new MunitAfterSuite("name", muleContext);
    }
}
