package org.example;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static boolean isElementOnTheLeft(ExtendedWebElement el, WebDriver driver) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        long windowWidth = (long) je.executeScript("return window.innerWidth");

        int elX = el.getLocation().getX();
        int elWidth = el.getSize().getWidth();

        return ((elX + elWidth) <= (windowWidth / 2));
    }
}
