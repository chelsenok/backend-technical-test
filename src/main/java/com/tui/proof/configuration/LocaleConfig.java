package com.tui.proof.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Locale configurations.
 */
@Slf4j
@Configuration
public class LocaleConfig {

    /**
     * Set default timezone to UTC
     */
    @PostConstruct
    private void init() {
        log.info("Set default timezone to UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
