
package org.mule.munit.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.NestedProcessor;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;
import org.mule.munit.MClient;
import org.mule.munit.adapters.MClientProcessAdapter;
import org.mule.munit.process.NestedProcessorChain;


/**
 * CallMessageProcessor invokes the {@link org.mule.munit.MClient#call(java.lang.String, java.util.Map, java.lang.Object, java.util.List)} method in {@link MClient }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.1", date = "2012-09-24T08:54:01-03:00", comments = "Build UNNAMED.1297.150f2c9")
public class CallMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor
{

    protected Object path;
    protected String _pathType;
    protected Object parameters;
    protected Map<String, Object> _parametersType;
    protected Object payload;
    protected Object _payloadType;
    protected Object responseProcessing;
    protected List<NestedProcessor> _responseProcessingType;

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
        if (responseProcessing instanceof List) {
            for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                if (messageProcessor instanceof Initialisable) {
                    ((Initialisable) messageProcessor).initialise();
                }
            }
        }
    }

    public void start()
        throws MuleException
    {
        if (responseProcessing instanceof List) {
            for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                if (messageProcessor instanceof Startable) {
                    ((Startable) messageProcessor).start();
                }
            }
        }
    }

    public void stop()
        throws MuleException
    {
        if (responseProcessing instanceof List) {
            for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                if (messageProcessor instanceof Stoppable) {
                    ((Stoppable) messageProcessor).stop();
                }
            }
        }
    }

    public void dispose() {
        if (responseProcessing instanceof List) {
            for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                if (messageProcessor instanceof Disposable) {
                    ((Disposable) messageProcessor).dispose();
                }
            }
        }
    }

    /**
     * Set the Mule context
     * 
     * @param context Mule context to set
     */
    public void setMuleContext(MuleContext context) {
        super.setMuleContext(context);
        if (responseProcessing instanceof List) {
            for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                if (messageProcessor instanceof MuleContextAware) {
                    ((MuleContextAware) messageProcessor).setMuleContext(context);
                }
            }
        }
    }

    /**
     * Sets flow construct
     * 
     * @param flowConstruct Flow construct to set
     */
    public void setFlowConstruct(FlowConstruct flowConstruct) {
        super.setFlowConstruct(flowConstruct);
        if (responseProcessing instanceof List) {
            for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                if (messageProcessor instanceof FlowConstructAware) {
                    ((FlowConstructAware) messageProcessor).setFlowConstruct(flowConstruct);
                }
            }
        }
    }

    /**
     * Sets path
     * 
     * @param value Value to set
     */
    public void setPath(Object value) {
        this.path = value;
    }

    /**
     * Sets payload
     * 
     * @param value Value to set
     */
    public void setPayload(Object value) {
        this.payload = value;
    }

    /**
     * Sets responseProcessing
     * 
     * @param value Value to set
     */
    public void setResponseProcessing(Object value) {
        this.responseProcessing = value;
    }

    /**
     * Sets parameters
     * 
     * @param value Value to set
     */
    public void setParameters(Object value) {
        this.parameters = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(final MuleEvent event)
        throws MuleException
    {
        try {
            findOrCreate(MClientProcessAdapter.class, true, event);
            final String _transformedPath = ((String) evaluateAndTransform(getMuleContext(), event, CallMessageProcessor.class.getDeclaredField("_pathType").getGenericType(), null, path));
            final Map<String, Object> _transformedParameters = ((Map<String, Object> ) evaluateAndTransform(getMuleContext(), event, CallMessageProcessor.class.getDeclaredField("_parametersType").getGenericType(), null, parameters));
            final Object _transformedPayload = ((Object) evaluateAndTransform(getMuleContext(), event, CallMessageProcessor.class.getDeclaredField("_payloadType").getGenericType(), null, payload));
            final List<NestedProcessor> _transformedResponseProcessing = new ArrayList<NestedProcessor>();
            if (responseProcessing!= null) {
                for (MessageProcessor messageProcessor: ((List<MessageProcessor> ) responseProcessing)) {
                    _transformedResponseProcessing.add(new NestedProcessorChain(event, getMuleContext(), messageProcessor));
                }
            }
            Object resultPayload;
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) getModuleObject()).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((MClient) object).call(_transformedPath, _transformedParameters, _transformedPayload, _transformedResponseProcessing);
                }

            }
            , this, event);
            overwritePayload(event, resultPayload);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("call"), event, e);
        }
    }

}
