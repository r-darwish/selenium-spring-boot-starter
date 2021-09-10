package com.github.rdarwish.seleniumbootstarter;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WebDriverFactory.class)
public class WebDriverStarterConfiguration {
    @Bean
    WebDriver webDriver(WebDriverFactory factory) {
        return factory.webDriver();
    }
}
