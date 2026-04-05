package com.create.customer.infrastructure.client.viacep;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for ViaCEP Feign Client
 */
@Configuration
public class ViaCepClientConfiguration {

    /**
     * Enable full logging for Feign requests
     * Helps debug API calls to ViaCEP
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}


