package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import org.testng.annotations.Test;

public class ExchangeTest extends BaseTest {
    HomeSteps homeSteps = new HomeSteps();


    @Test(description = "Currency Exchange & Converter — rates table visible," +
            " swap work [MSP-T29]")
    public void testExchangePage() {
        homeSteps
                .openPage(Constants.TBC_URL)
                .acceptCookies()
                .navigateToCurrency()
                .validateTable()
                .writeTheCurrency()
                .swapTheCurrencies();

    }
}
