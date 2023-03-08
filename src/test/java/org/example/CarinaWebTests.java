package org.example;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.assertj.core.api.SoftAssertions;
import org.example.pages.CucumberPage;
import org.example.pages.HomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Utils.isCurrentPageLoaded;

public class CarinaWebTests implements IAbstractTest {
    HomePage homePage = null;

    @BeforeTest
    public void startDriver() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void logoOnTheLeftTest() {
        homePage.open();

        assertThat(homePage.getHeader().isLogoOnTheLeft()).isTrue();
    }

    @Test
    public void logoMovesToOverviewPageTest() {
        String expectedHeadingText = "Overview";

        CucumberPage cucumberPage = new CucumberPage(getDriver());
        homePage = cucumberPage.goToPage().getHeader().clickLogo();

        assertThat(homePage.getContentBlock().getHeadingText()).isEqualTo(expectedHeadingText);
    }

    @Test
    public void carinaLabelOnHeader() {
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

        assertThat(isCurrentPageLoaded(getDriver())).isTrue();
        assertThat(getDriver().getCurrentUrl()).isEqualTo(expectedGithubUrl);
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
}
