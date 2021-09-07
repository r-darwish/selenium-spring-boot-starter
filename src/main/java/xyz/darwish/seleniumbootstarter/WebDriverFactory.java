package xyz.darwish.seleniumbootstarter;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public interface WebDriverFactory {
    WebDriver webDriver();
}
