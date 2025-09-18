package ge.tbc.testautomation.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonPage {

    public Locator searchBtn;
    public Locator searchInput;
    public Locator searchContainer;
    public Locator closeSearchBtn;
    public Locator searchWindow;
    public Locator noResultsFoundElement;
    public Locator hamburgerIcon;
    public Locator dropDownButton;
    public Locator businessLoansLink;
    public Locator myBusinessBtnMobile;
    public Locator acceptCookiesBtn;
    public Locator myBusinessLink;
    public Locator searchResults;
    public Locator cookieContainer;


    public  CommonPage(Page page){
        searchBtn = page.locator("//button[contains(@class, 'search__button')" +
                "]");
        searchInput = page.locator("//input[contains(@id, 'tbcx-text-input')]");

        searchContainer = page.locator("//div[contains(@class, " +
                "'global-search__bottom-content ng-star-inserted')]");
        closeSearchBtn = page.locator("//button[contains(@class, " +
                "'global-search__top-content__heading')]");
        searchWindow = page.locator("//div[@class='global-search__top-content']");
        noResultsFoundElement = page.locator("//p[text()='ვერაფერი ვიპოვეთ']");
        hamburgerIcon = page.locator("//button[contains(@class, 'hamburger-menu')]");
        dropDownButton = page.locator("(//button[contains(@class, 'tbc-accordion__title')])" +
                "[1]");
        businessLoansLink = page.locator("//button[contains(@class, 'tbcx-pw')][span[text()" +
                "='ბიზნეს სესხები']]");
        myBusinessBtnMobile = page.locator("tbcx-pw-navigation-item:has-text('ჩემი ბიზნესისთვის')");
        acceptCookiesBtn = page.locator("//button[contains(text(), 'თანხმობა')]");
        myBusinessLink = page.locator("(//a[@href='/ka/business'])[1]");
        cookieContainer= page.locator("tbcx-pw-cookie-consent.ng-star-inserted div" +
                ".tbcx-pw-cookie-consent");
        searchResults = page.locator("app-search-result-item");
    }

}

