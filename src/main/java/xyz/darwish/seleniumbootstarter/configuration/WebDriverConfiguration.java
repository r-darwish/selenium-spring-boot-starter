package xyz.darwish.seleniumbootstarter.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
@ConfigurationProperties("selenium")
@Data
public class WebDriverConfiguration {
    BrowserOrigin browserOrigin;
    BrowserType browserType;
    RemoteBrowserConfiguration remote;

    @Data
    public static class RemoteBrowserConfiguration {
        String url;
    }
}
