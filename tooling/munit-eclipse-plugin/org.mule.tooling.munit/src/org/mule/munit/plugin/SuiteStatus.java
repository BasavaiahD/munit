/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SuiteStatus
{

    private Map<String, TestStatus> tests = new HashMap<String, TestStatus>();
    private int numberOfTests;
    private String suitePath;


    public void add(String testName, TestStatus testStatus)
    {
        tests.put(testName, testStatus);
    }

    public int getProcessedTests()
    {
        return tests.size();
    }

    public int getFailures()
    {
        int failures = 0;
        for (TestStatus testStatus : tests.values())
        {
            if (testStatus.hasFailed())
            {
                failures++;
            }
        }
        return failures;
    }

    public int getErrors()
    {
        int errors = 0;
        for (TestStatus testStatus : tests.values())
        {
            if (testStatus.hasError())
            {
                errors++;
            }
        }
        return errors;
    }

    public int getNumberOfTests()
    {
        return numberOfTests;
    }

    public void setNumberOfTests(int valueOf)
    {
        numberOfTests = valueOf;

    }

    public TestStatus getTest(String string)
    {
        return tests.get(string);

    }

    public TestStatus getRunningTest()
    {
        for (TestStatus testStatus : tests.values())
        {
            if (!testStatus.isFinished())
            {
                return testStatus;
            }
        }
        return null;
    }

    public Collection<TestStatus> getTests()
    {
        return tests.values();

    }

    public String getName()
    {
        return suitePath.substring(suitePath.lastIndexOf("/"));
    }

    public String getSuitePath()
    {
        return suitePath;
    }

    public void setSuitePath(String suitePath)
    {
        this.suitePath = suitePath;

    }


}
