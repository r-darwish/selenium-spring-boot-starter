package xyz.darwish.seleniumbootstarter;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;
import xyz.darwish.seleniumbootstarter.configuration.WebDriverConfiguration;

import java.net.URL;

@Component
@AllArgsConstructor
public class WebDriverFactoryImpl implements WebDriverFactory {
    private final WebDriverConfiguration configuration;

    @Override
    public WebDriver webDriver() {
        switch (configuration.getBrowserOrigin()) {
            case Local:
                return getLocalDriver();
            case Remote:
                return getRemoteDriver();
        }
        throw new RuntimeException("Shouldn't get here");
    }

    @SneakyThrows
    private WebDriver getRemoteDriver() {
        return new RemoteWebDriver(new URL(configuration.getRemote().getUrl()), getCapabilities());
    }

    private WebDriver getLocalDriver() {
        switch (configuration.getBrowserType()) {
            case Firefox:
                return new FirefoxDriver((FirefoxOptions) getCapabilities());
            case Chrome:
                return new ChromeDriver((ChromeOptions) getCapabilities());
        }

        throw new RuntimeException("Shouldn't get here");
    }

    @NotNull
    private Capabilities getCapabilities() {
        switch (configuration.getBrowserType()) {
            case Chrome:
                return new ChromeOptions();
            case Firefox:
                return new FirefoxOptions();
        }

        throw new RuntimeException("Shouldn't get here");
    }
}
