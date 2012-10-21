package org.mule.munit.common.mocking;


import org.mule.api.MuleContext;
import org.mule.api.processor.MessageProcessor;
import org.mule.munit.common.mp.SpyAssertion;

import java.util.List;

public class MunitSpier extends MunitTool{

    public MunitSpier(MuleContext muleContext) {
        super(muleContext);
    }

    public MunitSpier spyMessageProcessor(String name) {
        this.messageProcessorName = name;
        return this;
    }


    public MunitSpier ofNamespace(String namespace) {
        this.messageProcessorNamespace = namespace;
        return this;
    }

    public void running(List<MessageProcessor> beforeCall, List<MessageProcessor> afterCall){
        getManager().addSpyAssertion(getFullName(),
                new SpyAssertion(beforeCall,afterCall));
    }


}
