package ge.tbc.testautomation.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MapPage {
   public Locator marker;
   public Locator allFilterBtn;
   public Locator branchesFilterBtn;
   public Locator atmFilterBtn;
   public Locator listContainer;
   public Locator dropDown;
   public Locator openTwentyFourHoursFilter;
   public Locator map;
   public Locator options;
   public Locator listItems;
   public Locator searchInput;


    public MapPage(Page page){
        marker = page.locator("gmp-advanced-marker.yNHHyP-marker-view[role='button']")
                .first();
        allFilterBtn = page.locator(".tbcx-pw-tab-menu .tbcx-pw-tab-menu__item.active");
        branchesFilterBtn = page.locator("//span[text()='ფილიალები']/parent::button");
        atmFilterBtn = page.locator("//span[text()='ბანკომატები']/parent::button");
        listContainer = page.locator("//div[contains(@class, " +
                "'tbcx-pw-atm-branches-section__list-wrapper')]");
        dropDown = page.locator(".tbcx-dropdown-selector");
        openTwentyFourHoursFilter = page.locator("(//label[@class='tbcx-pw-chip'])[1]");
        map = page.locator(".gm-style").first();
        options = page.locator("tbcx-dropdown-popover-item " +
                ".tbcx-dropdown-popover-item__title");
        listItems = page.locator("app-atm-branches-section-list-item");
        searchInput = page.locator("//input[contains(@id, 'tbcx-text-input')]");

    }

    public Locator getItemLabel(Locator item) {
        return item.locator("xpath=.//div[contains(@class," +
                "'tbcx-pw-atm-branches-section__list-item-label')]");
    }

    public Locator getItemTitle(Locator item) {
        return item.locator(".tbcx-pw-atm-branches-section__list-item-title.tbcx-pw-title");
    }

}
