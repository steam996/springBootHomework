package ru.netology.springboothomework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboothomework.DevProfile;
import ru.netology.springboothomework.ProductionProfile;
import ru.netology.springboothomework.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
