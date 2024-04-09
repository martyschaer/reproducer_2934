package dev.schaer.reproducer_2934.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class ScsConverterConfig {
    @Bean
    public MessageConverter simplePassthroughConverter() {
        return new SimplePassthroughConverter();
    }
}
