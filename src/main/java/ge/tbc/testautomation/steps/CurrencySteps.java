package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.CurrencyPage;
import java.util.regex.Pattern;


public class CurrencySteps {

    Page page;
    CurrencyPage currencyPage;

    public CurrencySteps(Page page) {
        this.page = page;
        this.currencyPage = new CurrencyPage(page);
    }



    public CurrencySteps checkCurrency(String currencyCode) {
        PlaywrightAssertions.assertThat(currencyPage.rowByCurrency(currencyCode)).isVisible();
        Locator row =
                currencyPage.rowByCurrency(currencyCode);
        PlaywrightAssertions.assertThat(currencyPage.buyIn(row)).isVisible();
        PlaywrightAssertions.assertThat(currencyPage.buyIn(row)).hasText(Pattern.compile("\\d"));

        PlaywrightAssertions.assertThat(currencyPage.sellIn(row)).isVisible();
        PlaywrightAssertions.assertThat(currencyPage.sellIn(row)).hasText(Pattern.compile("\\d"));
        return this;
    }

    public CurrencySteps assertCurrencyTableVisible() {
        PlaywrightAssertions.assertThat(currencyPage.currencyTable).isVisible();
        return this;
    }
    public CurrencySteps assertPopularRowsCount(int expected) {
        PlaywrightAssertions.assertThat(currencyPage.currencyTableRows).hasCount(expected);
        return this;
    }

    public CurrencySteps enterInitialAmount(String value) {
        PlaywrightAssertions.assertThat(currencyPage.currencyInputInitial).isVisible();
        currencyPage.currencyInputInitial.fill(value);
        return this;
    }
    public double getConvertedAmount() {
        PlaywrightAssertions.assertThat(currencyPage.currencyInputConverted).isVisible();
        return Double.parseDouble(currencyPage.currencyInputConverted.inputValue());
    }
    public CurrencySteps clickSwap() {
        PlaywrightAssertions.assertThat(currencyPage.swapBtn).isVisible();
        currencyPage.swapBtn.click();
        return this;
    }

    public CurrencySteps assertConvertedApproximately(double expected, double delta) {
        org.testng.Assert.assertEquals(getConvertedAmount(), expected, delta);
        return this;
    }
    public CurrencySteps assertFromCurrencyIs(String code) {
        PlaywrightAssertions.assertThat(currencyPage.displayedCurrencyInitial).hasText(code);
        return this;
    }
    public CurrencySteps assertToCurrencyIs(String code) {
        PlaywrightAssertions.assertThat(currencyPage.displayedCurrencyConverted).hasText(code);
        return this;
    }

}
