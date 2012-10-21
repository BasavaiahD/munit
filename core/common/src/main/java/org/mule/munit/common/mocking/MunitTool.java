package org.mule.munit.common.mocking;


import org.mule.api.MuleContext;
import org.mule.munit.common.mp.MockedMessageProcessorManager;

import java.util.HashMap;
import java.util.Map;

public class MunitTool {

    protected MuleContext muleContext;

    protected String messageProcessorName ;
    protected String messageProcessorNamespace = "mule";
    protected Map<String, Object> messageProcessorAttributes = new HashMap<String, Object>();

    public MunitTool(MuleContext muleContext) {
        this.muleContext = muleContext;
    }

    protected void checkValidQuery() {
        if ( messageProcessorName == null ){
            throw new IllegalArgumentException("You must provide at least the Message processor name");
        }
    }

    protected MockedMessageProcessorManager getManager() {
        return (MockedMessageProcessorManager) muleContext.getRegistry().lookupObject(MockedMessageProcessorManager.ID);
    }

    protected String getFullName() {
        return messageProcessorNamespace + ":" + messageProcessorName;
    }

}
