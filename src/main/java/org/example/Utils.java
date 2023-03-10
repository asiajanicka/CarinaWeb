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
        int windowWidth = (Double.valueOf(je.executeScript("return window.innerWidth").toString())).intValue();

        int elX = el.getLocation().getX();
        int elWidth = el.getSize().getWidth();

        return ((elX + elWidth) <= (windowWidth / 2));
    }

    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }
}
