package ge.tbc.testautomation.tests;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class ExchangeTest extends BaseTest {

    @Test(description = "Currency Exchange & Converter — rates table visible," +
            " swap work [CRM-T3]")
    public void testExchangePage() {
        homeSteps
                .openPage(Constants.TBC_URL)
                .acceptCookies()
                .assertCurrencyLinkPresent()
                .scrollCurrencyLinkIntoView()
                .clickCurrencyLink()
                .assertCurrencyTableVisible()
                .assertPopularRowsCount(3)
                .checkCurrency(Constants.CURRENCIES[0])
                .checkCurrency(Constants.CURRENCIES[1])
                .checkCurrency(Constants.CURRENCIES[2])
                .enterInitialAmount(Constants.VALUE_FOR_INPUT)
                .assertConvertedApproximately(132, 5.0)
                .assertFromCurrencyIs(Constants.CURRENCIES[0])
                .assertToCurrencyIs(Constants.CURRENCIES[3])
                .clickSwap()
                .assertFromCurrencyIs(Constants.CURRENCIES[3])
                .assertToCurrencyIs(Constants.CURRENCIES[0]);

    }
}
