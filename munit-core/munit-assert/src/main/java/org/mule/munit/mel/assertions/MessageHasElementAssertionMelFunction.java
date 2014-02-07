/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.mel.assertions;

import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;


/**
 * <p>
 * MEL function to evaluate the existence of an Element in a Message.
 * <p/>
 * All the #[messageHas*PropertyCalled('something') is implemented with this Object
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class MessageHasElementAssertionMelFunction extends AssertionMelFunction
{

    /**
     * <p>
     * The command that does the assertion
     * </p>
     */
    private MessageHasElementAssertionCommand command;

    public MessageHasElementAssertionMelFunction(MessageHasElementAssertionCommand command)
    {
        this.command = command;
    }


    @Override
    public Object call(Object[] params, ExpressionLanguageContext context)
    {
        if (params != null && params.length > 0 && params[0] instanceof String)
        {
            MuleMessage muleMessage = getMuleMessageFrom(context);

            if (muleMessage == null)
            {
                return false;
            }

            return command.messageHas((String) params[0], muleMessage);
        }
        return false;
    }

    public MessageHasElementAssertionCommand getCommand()
    {
        return command;
    }
}
