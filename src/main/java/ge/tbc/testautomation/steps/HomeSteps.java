package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.HomePage;


import static com.codeborne.selenide.Selenide.open;

public class HomeSteps {
    Page page;

    HomePage homePage;


    public HomeSteps(Page page) {
        this.page = page;
        this.homePage = new HomePage(page);
    }

    public HomeSteps openPage(String url) {
        page.navigate(url);
        return this;
    }

    public HomeSteps acceptCookies() {
        PlaywrightAssertions.assertThat(homePage.acceptCookiesBtn).isVisible();
        homePage.acceptCookiesBtn.click();
        return this;
    }

    public MapSteps navigateToMap() {
        PlaywrightAssertions.assertThat(homePage.mapLink).not().hasCount(0);
        homePage.mapLink.scrollIntoViewIfNeeded();
        homePage.mapLink.click();
        return new MapSteps(page);
    }
    public HomeSteps assertMapLinkPresent() {
        PlaywrightAssertions.assertThat(homePage.mapLink).not().hasCount(0);
        return this;
    }

    public HomeSteps scrollMapLinkIntoView() {
        homePage.mapLink.scrollIntoViewIfNeeded();
        return this;
    }

    public MapSteps clickMapLink() {
        homePage.mapLink.click();
        return new MapSteps(page);
    }

    public HomeSteps assertCurrencyLinkPresent() {
        PlaywrightAssertions.assertThat(homePage.currencyPageLink).not().hasCount(0);
        return this;
    }
    public HomeSteps scrollCurrencyLinkIntoView() {
        homePage.currencyPageLink.scrollIntoViewIfNeeded();
        return this;
    }
    public CurrencySteps clickCurrencyLink() {
        homePage.currencyPageLink.click();
        return new CurrencySteps(page);
    }
}
