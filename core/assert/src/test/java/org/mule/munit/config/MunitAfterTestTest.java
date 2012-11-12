package org.mule.munit.config;

import org.junit.Test;
import org.mule.api.MuleContext;

import static org.mockito.Mockito.mock;

/**
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class MunitAfterTestTest {

    MuleContext muleContext = mock(MuleContext.class);

    @Test
    public void testConstructor(){
        new MunitAfterTest("name",muleContext);
    }
}
