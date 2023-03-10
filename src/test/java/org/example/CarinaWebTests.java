package org.example;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.assertj.core.api.SoftAssertions;
import org.example.components.Content;
import org.example.components.NavigationMenu;
import org.example.components.Search;
import org.example.pages.CucumberPage;
import org.example.pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarinaWebTests implements IAbstractTest {
    HomePage homePage = null;

    @BeforeTest
    public void startDriver() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void logoTest() {
        String expectedHeadingText = "Overview";

        CucumberPage cucumberPage = new CucumberPage(getDriver());
        homePage = cucumberPage.goToPage().getHeader().clickLogo();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(homePage.getHeader().isLogoOnTheLeft()).isTrue();
        soft.assertThat(homePage.getContentBlock().getHeadingText()).isEqualTo(expectedHeadingText);
        soft.assertAll();
    }

    @Test
    public void carinaLabelIsOnHeaderTest() {
        String expectedLabelText = "Carina";

        homePage.open();

        assertThat(homePage.getHeader().getCarinaLabel().isPresent()).isTrue();
        assertThat(homePage.getHeader().getCarinaLabelText()).isEqualTo(expectedLabelText);
    }

    @Test
    public void githubLinkMovesToProjectPageTest() {
        String expectedGithubUrl = "https://github.com/zebrunner/carina/";

        homePage.open();

        assertThat(homePage.getHeader().isUIObjectPresent()).isTrue();

        homePage.getHeader().clickGithubLink();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        Boolean isGithubPageOpened = wait.until(d -> getDriver().getCurrentUrl().equals(expectedGithubUrl));

        assertThat(isGithubPageOpened).isTrue();
    }

    @Test
    public void headerIsStickyTest() {
        homePage.open();

        assertThat(homePage.isHeaderSticky()).isTrue();

        Utils.scrollToBottom(getDriver());

        assertThat(homePage.getHeader().isUIObjectPresent()).isTrue();
        assertThat(homePage.isHeaderSticky()).isTrue();
    }

    @Test
    public void navigationMenuIsVisibleTest() {
        String expectedLabelText = "Carina";

        homePage.open();

        String actualHeadingText = homePage.getContentBlock().getHeadingText();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(homePage.getNavigationMenu().isUIObjectPresent()).isTrue();
        soft.assertThat(homePage.getNavigationMenu().getNavMenuLabelText()).isEqualTo(expectedLabelText);
        soft.assertThat(homePage.getNavigationMenu().getLinksList().size()).isGreaterThan(0);
        soft.assertThat(homePage.getNavigationMenu().getHighlightedLinkText()).isEqualTo(actualHeadingText);
        soft.assertAll();
    }

    @Test
    public void subLinksForAutomationArePresentTest() {
        int expectedNumberOfSubLinks = 4;

        homePage.open();
        List<ExtendedWebElement> subLinksForAutomation = homePage.getNavigationMenu().clickOnAutomationLink().getSubLinksForAutomation();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(subLinksForAutomation.size()).isEqualTo(expectedNumberOfSubLinks);
        for (ExtendedWebElement subLink : subLinksForAutomation) {
            soft.assertThat(subLink.isPresent()).isTrue();
        }
        soft.assertAll();
    }

    @Test
    public void subLinksForAdvancedArePresentTest() {
        int expectedNumberOfSubLinks = 9;

        homePage.open();
        List<ExtendedWebElement> subLinksForAdvanced = homePage.getNavigationMenu().clickOnAdvancedLink().getSubLinksForAdvanced();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(subLinksForAdvanced.size()).isEqualTo(expectedNumberOfSubLinks);
        for (ExtendedWebElement subLink : subLinksForAdvanced) {
            assertThat(subLink.isPresent()).isTrue();
        }
        soft.assertAll();
    }

    @Test
    public void subLinksForIntegrationArePresentTest() {
        int expectedNumberOfSubLinks = 1;

        homePage.open();
        List<ExtendedWebElement> subLinksForIntegration = homePage.getNavigationMenu().clickOnIntegrationLink().getSubLinksForIntegration();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(subLinksForIntegration.size()).isEqualTo(expectedNumberOfSubLinks);
        for (ExtendedWebElement subLink : subLinksForIntegration) {
            soft.assertThat(subLink.isPresent()).isTrue();
        }
        soft.assertAll();
    }

    @Test
    public void navigationElementsMoveToCorrectPagesTest() {
        homePage.open();
        int size = homePage.getNavigationMenu().openAllSubMenus().getLinksList().size();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        SoftAssertions soft = new SoftAssertions();
        for (int i = 0; i < size; i++) {
            homePage.open();
            NavigationMenu navigationMenu = homePage.getNavigationMenu().openAllSubMenus();

            String actualLinkLabel = navigationMenu.getLinksList().get(i).getText().strip();
            Content content = navigationMenu.clickLink(i);

            String highlightedLinkLabel = navigationMenu.getHighlightedLinkText();
            String headingText = content.getHeadingText();

            Boolean isActualLinkHighlighted = wait.until(d -> highlightedLinkLabel.equals(actualLinkLabel));

            soft.assertThat(isActualLinkHighlighted).isTrue();
            soft.assertThat(headingText).contains(actualLinkLabel);
        }
        soft.assertAll();
    }

    @Test
    public void searchComponentsArePresentTest() {
        String expectedSearchHintText = "Search";

        homePage.open();
        Search search = homePage.getHeader().getSearchComponent();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(search.isUIObjectPresent()).isTrue();
        soft.assertThat(search.getSearchIcon().isPresent()).isTrue();
        soft.assertThat(search.getInputField().isPresent()).isTrue();
        soft.assertThat(search.getSearchHintText()).isEqualTo(expectedSearchHintText);
        soft.assertAll();
    }
}
