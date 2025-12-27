package com.profession.suggest.configuration.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:enviroment.properties")
public class EnvPropertiesConfig {
    public static final String SECRET_KEY = "app.secret.key";
}
