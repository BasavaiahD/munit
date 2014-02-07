/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.mel.utils;

import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.el.ExpressionLanguageFunction;

/**
 * <p>
 * This is an extension that picks an indicated file, an returns its content in
 * the payload of the message from where the method was called. The type of the
 * returned value can be String, InputStream or byte array, depending of the
 * operation aplicated to the MunitResource returned
 * <p/>
 * <pre>
 *         {@code
 *
 *           <mock:verify-call messageProcessor="jira:create-group" atLeast="1">
 *                   <mock:attributes>
 *                           <mock:attribute name="userName" whereValue-ref='#[getResource(fileName).asString()]'/>
 *                   </mock:attributes>
 *           </mock:verify-call>
 *         }
 *     </pre>
 *
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class GetResourceFunction implements ExpressionLanguageFunction
{

    @Override
    public Object call(final Object[] params, ExpressionLanguageContext context)
    {
        if (params == null || params.length != 1
            || !(params[0] instanceof String))
        {
            throw new IllegalArgumentException(
                    "You must provide one parameter that "
                    + "indicates where the resource file is placed");
        }
        return new MunitResource((String) params[0]);
    }

}
