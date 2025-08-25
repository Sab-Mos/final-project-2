package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public SelenideElement
            currencyPageLink = $x("(//a[contains(@href, '/ka/treasury-products')])[1]"),

    mapLink = $x("//a[contains(text(), 'მისამართები')]"),

    cookieContainer = $("tbcx-pw-cookie-consent.ng-star-inserted div" +
            ".tbcx-pw-cookie-consent"),

    acceptCookiesBtn = $x("//button[text()=' თანხმობა ']");


}
