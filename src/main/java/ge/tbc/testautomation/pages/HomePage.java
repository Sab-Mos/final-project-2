package ge.tbc.testautomation.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    public Locator currencyPageLink;
    public Locator mapLink;
    public Locator cookieContainer;
    public Locator acceptCookiesBtn;


    public HomePage(Page page){
        currencyPageLink = page.locator("(//a[contains(@href, " +
                "'/ka/treasury-products')])[1]");
        mapLink = page.locator("//a[contains(text(), 'მისამართები')]");
        cookieContainer = page.locator("tbcx-pw-cookie-consent.ng-star-inserted div" +
                ".tbcx-pw-cookie-consent");
        acceptCookiesBtn = page.locator("//button[text()=' თანხმობა ']");

    }


}
