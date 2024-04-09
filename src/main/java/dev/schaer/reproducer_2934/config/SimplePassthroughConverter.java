package dev.schaer.reproducer_2934.config;

import dev.schaer.reproducer_2934.model.ActionBase;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

/**
 * A converter that simply passes the received payload through unchanged.
 */
public class SimplePassthroughConverter extends AbstractMessageConverter {

    public SimplePassthroughConverter() {
        super(new MimeType("application", "x-java-object"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ActionBase[].class.isAssignableFrom(clazz);
    }

    @Override
    protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint) {
        return payload;
    }
}
