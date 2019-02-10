package io.github.opensanca.archvalidator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.github.opensanca.archvalidator")
public class ArchValidatorAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchValidatorAutoConfiguration.class);

    @PostConstruct
    public void log() {
        LOGGER.info("**** ArchValidatorAutoConfiguration ****");
    }
}
