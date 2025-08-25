package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.HomePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class HomeSteps {
    HomePage homePage = new HomePage();

    public HomeSteps openPage(String url) {
        open(url);
        return this;
    }

    public HomeSteps acceptCookies() {
        homePage.acceptCookiesBtn.shouldBe(Condition.visible,
                Duration.ofSeconds(5)).click();
        return this;
    }

    public MapSteps navigateToMap() {
        homePage.mapLink.should(Condition.exist).scrollIntoView(true).click();
        return new MapSteps();
    }

    public CurrencySteps navigateToCurrency() {
        homePage.currencyPageLink.should(Condition.exist).scrollIntoView(true).click();
        return new CurrencySteps();
    }
}
