package org.example.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.By;
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

    @FindBy(xpath = ".//nav[@aria-label='Automation']/parent::li")
    private ExtendedWebElement automationLink;

    @FindBy(xpath = ".//nav[@aria-label='Advanced']/parent::li")
    private ExtendedWebElement advancedLink;

    @FindBy(xpath = ".//nav[@aria-label='Integration']/parent::li")
    private ExtendedWebElement integrationLink;

    @FindBy(css = ".md-nav__item--nested")
    private List<ExtendedWebElement> linksWithSublinksList;

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

    public NavigationMenu clickOnAutomationLink() {
        automationLink.click();
        return this;
    }

    public NavigationMenu clickOnAdvancedLink() {
        advancedLink.click();
        return this;
    }

    public NavigationMenu clickOnIntegrationLink() {
        integrationLink.click();
        return this;
    }

    public List<ExtendedWebElement> getSubLinksForAutomation() {
        return getSubLinksForLink(automationLink);
    }

    public List<ExtendedWebElement> getSubLinksForAdvanced() {
        return getSubLinksForLink(advancedLink);
    }

    public List<ExtendedWebElement> getSubLinksForIntegration() {
        return getSubLinksForLink(integrationLink);
    }

    private List<ExtendedWebElement> getSubLinksForLink(ExtendedWebElement el) {
        return el.findExtendedWebElements(By.xpath(".//li"));
    }

    public NavigationMenu openAllSubMenus() {
        for (ExtendedWebElement link : linksWithSublinksList) {
            link.click();
        }
        return this;
    }

    public Content clickLink(int i) {
        linksList.get(i).click();
        return new Content(driver);
    }
}
