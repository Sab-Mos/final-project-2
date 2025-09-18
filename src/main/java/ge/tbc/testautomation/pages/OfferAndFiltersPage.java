package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OfferAndFiltersPage {
    Page page;

    public Locator creditCardOffersBtn;
    public Locator offerListItems;
    public Locator filterList;
    public Locator filterListMobile;
    public Locator mobileFilterBtn;
    public Locator acceptCookiesBtn;
    public Locator clearFilterBtnDesktop;
    public Locator clearFilterBtnMobile;


    public OfferAndFiltersPage(Page page){
        this.page = page;
        creditCardOffersBtn = page.locator("(//button[contains(text(), 'ვრცლად')]/parent::a)[1]");
        offerListItems = page.locator("app-marketing-list a.ng-star-inserted");
        acceptCookiesBtn = page.locator("//button[contains(text(), 'თანხმობა')]");
        clearFilterBtnMobile = page.locator("//div[contains(@class, 'filters__buttons ng-star-inserted')]//button[contains(text(), 'გასუფთავება')]");
        clearFilterBtnDesktop = page.locator("(//div[contains(@class, 'filter ng-star-inserted')])[1]//button");
        filterList = page.locator("(//ul[contains(@class, 'filter__list')])[1]");
        filterListMobile = page.locator("(//div[contains(text(), 'მოგზაურობა')])[2]/ancestor::ul");
        mobileFilterBtn = page.locator("div.marketing__filter-chip.marketing__filter-chip--active");
    }

    public Locator targetCategoryFilter(Boolean isMobile, String string){
        if(isMobile){
            return page.locator("(//div[contains(text(),'" + string + "')" +
                    "]/input)[2]");
        }
        else{
            return page.locator("//div[contains(text(),'" + string + "')]/input");
        }
    }


}
