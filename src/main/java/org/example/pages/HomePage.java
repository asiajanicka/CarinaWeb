package org.example.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import lombok.Getter;
import org.example.components.Content;
import org.example.components.Header;
import org.example.components.NavigationMenu;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends AbstractPage {

    @FindBy(className = "md-header")
    private Header header;

    @FindBy(css = ".md-content")
    private Content contentBlock;

    @FindBy(css = ".md-nav[data-md-level='0']")
    private NavigationMenu navigationMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHeaderSticky() {
        int windowOffsetFromTop = getWindowOffsetFromTop();
        int yOfHeader = header.getRootExtendedElement().getLocation().getY();
        return Math.abs((windowOffsetFromTop - yOfHeader)) <= 1;
    }

    private int getWindowOffsetFromTop() {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        return (Double.valueOf(je.executeScript("return window.pageYOffset").toString())).intValue();
    }
}
