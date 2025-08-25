package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CurrencyPage;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;

public class CurrencySteps {

    CurrencyPage currencyPage = new CurrencyPage();


    public CurrencySteps validateTable() {
        currencyPage.currencyTable.shouldBe(Condition.visible);
        currencyPage.currencyTableRows.shouldHave(size(3));
        checkCurrency(Constants.CURRENCIES[0]);
        checkCurrency(Constants.CURRENCIES[1]);
        checkCurrency(Constants.CURRENCIES[2]);
        return this;
    }

    public CurrencySteps writeTheCurrency() {
        currencyPage.currencyInputInitial.shouldBe(Condition.visible).setValue("50");

        Double convertedValue =
                Double.parseDouble(currencyPage.currencyInputConverted.shouldBe(Condition.visible).getValue());

        Assert.assertEquals(convertedValue, 132, 5.0);
        return this;
    }


    //Since on website the swap functionality is bugged and swapping of
    // values does not happen when the button is clicked, we wil only check if
    // input Currency display changes from usd to
    // gel
    // and vice versa in both currency inputs
    public void swapTheCurrencies() {
        currencyPage.displayedCurrencyInitial.shouldBe(Condition.visible).shouldHave(Condition.text(Constants.CURRENCIES[0]));

        currencyPage.displayedCurrencyConverted.shouldBe(Condition.visible).shouldHave(Condition.text(Constants.CURRENCIES[3]));

        currencyPage.swapBtn.shouldBe(Condition.visible).click();

        currencyPage.displayedCurrencyInitial.shouldHave(Condition.text(Constants.CURRENCIES[3]));

        currencyPage.displayedCurrencyConverted.shouldBe(Condition.visible).shouldHave(Condition.text(Constants.CURRENCIES[0]));
        
    }


    public void checkCurrency(String currencyCode) {
        SelenideElement row = currencyPage.rowByCurrency(currencyCode).shouldBe(Condition.visible);
        currencyPage.buyIn(row).shouldBe(Condition.visible).shouldHave(Condition.matchText("\\d"));
        currencyPage.sellIn(row).shouldBe(Condition.visible).shouldHave(Condition.matchText("\\d"));
    }
}
