package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CommonPage {
    public SelenideElement
            searchBtn = $x("//button[contains(@class, 'search__button')]"),
            searchInput = $x("//input[contains(@id, 'tbcx-text-input')]"),

    searchContainer = $x("//div[contains(@class, " +
            "'global-search__bottom-content ng-star-inserted')]"),

    closeSearchBtn = $x("//button[contains(@class, " +
            "'global-search__top-content__heading')]"),

    searchWindow = $x("//div[@class='global-search__top-content']"),

    noResultsFoundElement = $x("//p[text()='ვერაფერი ვიპოვეთ']"),

    hamburgerIcon = $x("//button[contains(@class, 'hamburger-menu')]"),

    dropDownButton = $x("(//button[contains(@class, 'tbc-accordion__title')])" +
            "[1]"),

    businessLoansLink = $x("//button[contains(@class, 'tbcx-pw')][span[text()" +
            "='ბიზნეს სესხები']]"),
            myBusinessBtnMobile = $x("//button[text()=' ჩემი ბიზნესისთვის ']"),

    acceptCookiesBtn = $x("//button[contains(text(), 'თანხმობა')]"),

    cookieContainer = $("tbcx-pw-cookie-consent.ng-star-inserted div" +
                    ".tbcx-pw-cookie-consent"),

    myBusinessLink = $x("(//a[@href='/ka/business'])[1]");


    public ElementsCollection
            searchResults = $$("app-search-result-item");


}
