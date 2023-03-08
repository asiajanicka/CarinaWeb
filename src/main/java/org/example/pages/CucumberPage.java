package org.example.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import lombok.Getter;
import org.example.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class CucumberPage extends AbstractPage {

    @FindBy(className = "md-header")
    private Header header;

    public CucumberPage(WebDriver driver) {
        super(driver);
    }

    public CucumberPage goToPage() {
        openURL("http://zebrunner.github.io/carina/cucumber/");
        return this;
    }
}
