package org.example.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class Search extends AbstractUIObject {

    @FindBy(tagName = "input")
    private ExtendedWebElement inputField;

    @FindBy(css = "label.md-search__icon")
    private ExtendedWebElement searchIcon;

    public Search(WebDriver driver) {
        super(driver);
    }

    public Search(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getSearchHintText() {
        return inputField.getAttribute("placeholder").strip();
    }
}
