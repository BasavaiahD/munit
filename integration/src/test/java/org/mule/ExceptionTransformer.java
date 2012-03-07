package org.mule;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class ExceptionTransformer extends AbstractMessageTransformer{
    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
        throw new TransformerException(null);
    }
}
