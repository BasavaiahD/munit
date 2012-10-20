
package org.mule.munit.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.munit.MockModule;


/**
 * A <code>MockModuleProcessAdapter</code> is a wrapper around {@link MockModule } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.1", date = "2012-10-20T04:33:25-03:00", comments = "Build UNNAMED.1297.150f2c9")
public class MockModuleProcessAdapter
    extends MockModuleLifecycleAdapter
    implements ProcessAdapter<MockModuleCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, MockModuleCapabilitiesAdapter> getProcessTemplate() {
        final MockModuleCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,MockModuleCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, MockModuleCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
