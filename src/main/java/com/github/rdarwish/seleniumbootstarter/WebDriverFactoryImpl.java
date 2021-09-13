package com.github.rdarwish.seleniumbootstarter;

import com.github.rdarwish.seleniumbootstarter.configuration.WebDriverConfiguration;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

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

    @SneakyThrows
    private WebDriver getLocalDriver() {
        switch (configuration.getBrowserType()) {
            case Firefox:
                var driver = new FirefoxDriver((FirefoxOptions) getCapabilities());
                if (configuration.getLocal().getInstallUblock()) {
                    var is = getClass().getClassLoader().getResourceAsStream("uBlock0_1.37.2.firefox.xpi");
                    var fp = Files.createTempFile("ublock", "xpi");
                    Files.write(fp, Objects.requireNonNull(is).readAllBytes());
                    driver.installExtension(fp);
                    fp.toFile().deleteOnExit();
                }
                return driver;
            case Chrome:
                return new ChromeDriver((ChromeOptions) getCapabilities());
            case Edge:
                return new EdgeDriver((EdgeOptions) getCapabilities());
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
            case Edge:
                return new EdgeOptions();
        }

        throw new RuntimeException("Shouldn't get here");
    }
}
