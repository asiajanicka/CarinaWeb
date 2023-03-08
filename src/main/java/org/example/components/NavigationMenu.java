package org.example.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class NavigationMenu extends AbstractUIObject {

    @FindBy(css = ":first-child")
    private ExtendedWebElement navMenuLabel;

    @FindBy(xpath = ".//li[contains(@class,'md-nav__item') and not(contains(@class,'--nested'))]")
    private List<ExtendedWebElement> linksList;

    @FindBy(xpath = ".//li[contains(@class,'active') and not(contains(@class,'--nested'))]")
    private ExtendedWebElement highlightedLink;

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    public NavigationMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getNavMenuLabelText() {
        return navMenuLabel.getText().strip();
    }

    public String getHighlightedLinkText() {
        return highlightedLink.getText().strip();
    }
}
