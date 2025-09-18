package ge.tbc.testautomation.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CurrencyPage {
    Page page;

    public Locator currencyTable;
    public Locator currencyInputInitial;
    public Locator displayedCurrencyInitial;
    public Locator swapBtn;
    public Locator displayedCurrencyConverted;
    public Locator currencyInputConverted;
    public Locator currencyTableRows;

    public CurrencyPage(Page page){
        this.page = page;
        currencyTable = page.locator("//div[contains(@class," +
                "'tbcx-pw-popular-currencies__rows')]");
        currencyInputInitial = page.locator("#tbcx-text-input-1");
        displayedCurrencyInitial = page.locator("(//div[contains(@class, 'tbcx-dropdown-selector__selection-text__slot__container ng-star-inserted')])[1]");
        swapBtn = page.locator("//div[contains(@class, 'tbcx-pw-exchange-rates-calculator__swap')]/button");
        displayedCurrencyConverted = page.locator("(//div[contains(@class, " +
                "'tbcx-dropdown-selector__selection-text__slot__container ng-star-inserted')])[2]");
        currencyInputConverted = page.locator("#tbcx-text-input-2");
        currencyTableRows = page.locator("//div[contains(@class," +
                "'tbcx-pw-popular-currencies__rows')]//div[contains(@class," +
                "'tbcx-pw-popular-currencies__row')]");


    }

public Locator rowByCurrency(String currencyCode) {
    return page.locator(
            ".tbcx-pw-popular-currencies__row:has(.tbcx-pw-currency-badge:has-text('" + currencyCode + "'))"
    );
}

    public Locator buyIn(Locator row) {
        return row.locator("xpath=.//div[div[contains(@class,'__caption')" +
                "][normalize-space()='ყიდვა']]/div[contains(@class,'__body')]");
    }

    public Locator sellIn(Locator row) {
        return row.locator("xpath=.//div[div[contains(@class,'__caption')" +
                "][normalize-space()='გაყიდვა']]/div[contains(@class,'__body')]");
    }
}
