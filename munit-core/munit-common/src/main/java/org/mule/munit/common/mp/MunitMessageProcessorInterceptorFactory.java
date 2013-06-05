package org.mule.munit.common.mp;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.modules.interceptor.spring.BeanFactoryMethodBuilder;
import org.mule.modules.interceptor.spring.MethodInterceptorFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

import java.util.Map;


/**
 * <p>
 *     This is the Message processor interceptor factory.
 * </p>
 *
 * @author Federico, Fernando
 * @since 3.3.2
 */
public class MunitMessageProcessorInterceptorFactory  extends MethodInterceptorFactory {

    /**
     * <p>
     *     The Id in the spring registry of Mule
     * </p>
     */
    public static final String ID = "__messageProcessorEnhancerFactory";

    /**
     * <p>
     *     Util method that creates a @see #BeanFactoryMethodBuilder based on an abstract bean definition
     * </p>
     * <p/>
     * <p>The usage:</p>
     * <p/>
     * <code>
     * addFactoryDefinitionTo(beanDefinition).withConstructorArguments(beanDefinition.getBeanClass());
     * </code>
     *
     * @param beanDefinition
     * <p>
     *     The bean definition that we want to modify
     * </p>
     * @return
     */
    public static BeanFactoryMethodBuilder addFactoryDefinitionTo(AbstractBeanDefinition beanDefinition) {
        return new BeanFactoryMethodBuilder(beanDefinition, "create", ID);
    }

    public Object create(Class realMpClass, MessageProcessorId id, Map<String,String> attributes, String fileName, String lineNumber){
        try {

            Enhancer e = new Enhancer();
            e.setSuperclass(realMpClass);

            MunitMessageProcessorInterceptor callback = new MunitMessageProcessorInterceptor();
            callback.setId(id);
            callback.setAttributes(attributes);
            callback.setFileName(fileName);
            callback.setLineNumber(lineNumber);
            e.setCallback(callback);
            return e.create();

        } catch (Throwable e) {
            throw new Error("The message processor " + id.getFullName() + " could not be mocked", e);
        }
    }

    /**
     * <p>
     *     Actual implementation of the interceptor creation
     * </p>
     *
     * @return
     *      <p>
     *          A {@link MunitMessageProcessorInterceptor} object
     *      </p>
     */
    @Override
    protected MethodInterceptor createInterceptor() {
        return new MunitMessageProcessorInterceptor();
    }
}
