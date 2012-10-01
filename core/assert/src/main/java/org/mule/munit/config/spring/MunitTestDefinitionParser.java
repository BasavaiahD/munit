package org.mule.munit.config.spring;

import org.apache.commons.lang.StringUtils;
import org.mule.api.config.MuleProperties;
import org.mule.config.spring.parsers.generic.OrphanDefinitionParser;
import org.mule.config.spring.util.ProcessingStrategyUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * <p>Munit Test Definition Parser</p>
 *
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class MunitTestDefinitionParser extends OrphanDefinitionParser {
    public MunitTestDefinitionParser(Class munitClass) {
        super(munitClass, true);
        addIgnored("abstract");
        addIgnored("name");
        addIgnored("processingStrategy");
    }

    @java.lang.Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        builder.addConstructorArgValue(element.getAttribute(ATTRIBUTE_NAME));
        builder.addConstructorArgReference(MuleProperties.OBJECT_MULE_CONTEXT);
        String ignore = element.getAttribute("ignore");
        if (StringUtils.isEmpty(ignore)) {
            ignore = "false";
        }

        builder.addPropertyValue("ignore", Boolean.getBoolean(ignore));
        ProcessingStrategyUtils.configureProcessingStrategy(element, builder,
                ProcessingStrategyUtils.QUEUED_ASYNC_PROCESSING_STRATEGY);
        super.doParse(element, parserContext, builder);
    }
}
