package org.mule.munit.runner.mule.result;

import org.mule.munit.runner.mule.result.notification.Notification;

/**
 * <p>The result of each test in Munit</p>
 *
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class TestResult implements MunitResult {

	private String name;
	private Notification failure;
	private Notification error;
    private float time;
    private boolean skipped;


    public TestResult(String name) {
		this.name = name;
	}

	public String getTestName() {
		return name;
	}

	public boolean hasSucceeded() {
	 return ( error == null && failure == null );
	}

	public int getNumberOfFailures() {
		return failure != null ? 1 : 0;
	}

	public int getNumberOfErrors() {
		return error != null ? 1 : 0;
	}

    @Override
    public int getNumberOfTests() {
        return 1;
    }

    @Override
    public float getTime() {
        return time;
    }

    @Override
    public int getNumberOfSkipped() {
       return skipped ? 1 : 0;
    }

    public Notification getFailure() {
		return failure;
	}

	public void setFailure(Notification failure) {
		this.failure = failure;
	}

	public Notification getError() {
		return error;
	}

	public void setError(Notification error) {
		this.error = error;
	}

    public void setTime(float time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public boolean isSkipped() {
        return skipped;
    }
}
