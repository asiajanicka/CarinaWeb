package org.example.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import lombok.Getter;
import org.example.pages.HomePage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static org.example.Utils.isElementOnTheLeft;

@Getter
public class Header extends AbstractUIObject {

    @FindBy(css = ".md-logo")
    private ExtendedWebElement logo;

    @FindBy(css = ".md-header-nav__topic")
    private ExtendedWebElement carinaLabel;

    @FindBy(css = "a[href*='github']")
    private ExtendedWebElement githubLink;

    @FindBy(css = ".md-search")
    private Search searchComponent;

    public Header(WebDriver driver) {
        super(driver);
    }

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public HomePage clickLogo() {
        logo.click();
        return new HomePage(driver);
    }

    public boolean isLogoOnTheLeft() {
        return isElementOnTheLeft(logo, driver);
    }

    public String getCarinaLabelText() {
        return carinaLabel.getText().strip();
    }

    public void clickGithubLink() {
        githubLink.click();
    }
}
