package org.example.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import lombok.Getter;
import org.example.components.Content;
import org.example.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends AbstractPage {

    @FindBy(className = "md-header")
    private Header header;

    @FindBy(css = ".md-content")
    private Content contentBlock;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
