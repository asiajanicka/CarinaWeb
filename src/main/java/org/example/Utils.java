package org.example;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static boolean isElementOnTheLeft(ExtendedWebElement el, WebDriver driver) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        long windowWidth = (long) je.executeScript("return window.innerWidth");

        int elX = el.getLocation().getX();
        int elWidth = el.getSize().getWidth();

        return ((elX + elWidth) <= (windowWidth / 2));
    }

    public static boolean isCurrentPageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        return wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }
}
