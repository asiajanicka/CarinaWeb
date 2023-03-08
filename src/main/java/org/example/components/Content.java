package org.example.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class Content extends AbstractUIObject {

    @FindBy(tagName = "h1")
    private ExtendedWebElement heading;

    public Content(WebDriver driver) {
        super(driver);
    }

    public Content(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getHeadingText() {
        return heading.getText().strip();
    }
}
