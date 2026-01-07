package com.profession.suggest.configuration;

import com.profession.suggest.interceptors.auth.JWTValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JWTValidationInterceptor jwtValidationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtValidationInterceptor)
                .addPathPatterns(
                        "/api/auth/protected-test",
                        "/api/pupils/pupil-data",
                        "/api/pupils/update-pupil-data",
                        "/api/psych-tests/**"
                );
    }
}
