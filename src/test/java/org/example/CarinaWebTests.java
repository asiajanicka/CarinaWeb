package org.example;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.example.pages.CucumberPage;
import org.example.pages.HomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
