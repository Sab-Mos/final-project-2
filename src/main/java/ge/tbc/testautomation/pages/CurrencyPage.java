package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CurrencyPage {

    public SelenideElement

    currencyTable = $x("//div[contains(@class," +
            "'tbcx-pw-popular-currencies__rows')]"),

    currencyInputInitial = $("#tbcx-text-input-1"),

    displayedCurrencyInitial = $x("(//div[contains(@class, 'tbcx-dropdown-selector__selection-text__slot__container ng-star-inserted')])[1]"),

    swapBtn = $x("//div[contains(@class, 'tbcx-pw-exchange-rates-calculator__swap')]/button"),

    displayedCurrencyConverted = $x("(//div[contains(@class, " +
            "'tbcx-dropdown-selector__selection-text__slot__container ng-star-inserted')])[2]"),

    currencyInputConverted = $("#tbcx-text-input-2");



    public ElementsCollection

     currencyTableRows = $$x("//div[contains(@class," +
            "'tbcx-pw-popular-currencies__rows')]//div[contains(@class," +
            "'tbcx-pw-popular-currencies__row')]");




    public SelenideElement rowByCurrency(String currency) {
        return $x("//div[contains(@class,'tbcx-pw-popular-currencies__row')]"
                + "[.//div[contains(@class,'tbcx-pw-currency-badge')][contains(normalize-space(.),'" + currency + "')]]");
    }

    public SelenideElement buyIn(SelenideElement row) {
        return row.$x(".//div[div[contains(@class,'__caption')][normalize-space()='ყიდვა']]/div[contains(@class,'__body')]");
    }

    public SelenideElement sellIn(SelenideElement row) {
        return row.$x(".//div[div[contains(@class,'__caption')][normalize-space()='გაყიდვა']]/div[contains(@class,'__body')]");
    }
}
