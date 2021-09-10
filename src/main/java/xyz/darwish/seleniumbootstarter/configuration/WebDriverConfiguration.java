package xyz.darwish.seleniumbootstarter.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("selenium")
@Data
public class WebDriverConfiguration {
    BrowserOrigin browserOrigin;
    BrowserType browserType;
    RemoteBrowserConfiguration remote;
    LocalBrowserConfiguration local;

    @Data
    public static class RemoteBrowserConfiguration {
        String url;
    }

    @Data
    public static class LocalBrowserConfiguration {
        Boolean installUblock;
    }
}
