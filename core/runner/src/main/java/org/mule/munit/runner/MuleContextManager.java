package org.mule.munit.runner;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationBuilder;
import org.mule.api.context.MuleContextBuilder;
import org.mule.config.DefaultMuleConfiguration;
import org.mule.config.builders.SimpleConfigurationBuilder;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.tck.TestingWorkListener;
import org.mule.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Starts and stops mule</p>
 *
 *
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class MuleContextManager {

    public static final String CLASSNAME_ANNOTATIONS_CONFIG_BUILDER = "org.mule.org.mule.munit.config.AnnotationsConfigurationBuilder";


    public MuleContext startMule(String resources) throws Exception {
        MuleContext context = createMule(resources);
        context.start();
        return context;
    }

    public void killMule(MuleContext muleContext) {
        try {
            if ( muleContext != null )
            {
                muleContext.stop();
            }
        } catch (MuleException e1) {

        }
        if (muleContext != null )
        {
            muleContext.dispose();
        }
    }

    private MuleContext createMule(String resources) throws Exception {
        MuleContext context;
        org.mule.api.context.MuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
        List<ConfigurationBuilder> builders = new ArrayList<ConfigurationBuilder>();
        builders.add(new SimpleConfigurationBuilder(null));
        if (ClassUtils.isClassOnPath(CLASSNAME_ANNOTATIONS_CONFIG_BUILDER,
                getClass())) {
            builders.add((ConfigurationBuilder) ClassUtils.instanciateClass(
                    CLASSNAME_ANNOTATIONS_CONFIG_BUILDER, ClassUtils.NO_ARGS,
                    getClass()));
        }
        builders.add(getBuilder(resources));
        MuleContextBuilder contextBuilder = new DefaultMuleContextBuilder();
        configureMuleContext(contextBuilder);
        context = muleContextFactory
                .createMuleContext(builders, contextBuilder);
        ((DefaultMuleConfiguration) context.getConfiguration())
                .setShutdownTimeout(0);
        return context;
    }



    protected ConfigurationBuilder getBuilder(String resources) throws Exception {
        return new SpringXmlConfigurationBuilder(resources);
    }

    protected void configureMuleContext(MuleContextBuilder contextBuilder) {
        contextBuilder.setWorkListener(new TestingWorkListener());
    }

}
