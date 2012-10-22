package org.mule.munit.common.matchers;

/**
 * <p>Dumb matcher that returns the specified value</p>
 *
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class DumbMatcher implements Matcher{
    boolean expectedValue;

    public DumbMatcher(boolean expectedValue) {
        this.expectedValue = expectedValue;
    }


    @Override
    public boolean match(Object o) {
        return expectedValue;
    }
}
