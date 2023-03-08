package org.example;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
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
}
