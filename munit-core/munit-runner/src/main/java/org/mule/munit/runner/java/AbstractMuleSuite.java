package org.mule.munit.runner.java;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;

/**
 * <p>Junit Runner to run Munit from Junit (Java)</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
@RunWith(MuleSuiteRunner.class)
public abstract class AbstractMuleSuite extends TestSuite
{

    /**
     * @return The path to the MUnit config.
     */
    public abstract String getConfigResources();

}
