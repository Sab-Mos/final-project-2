package ge.tbc.testautomation.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class FinancePage {
    public Locator breadCrumbContainer;
    public Locator breadcrumbLinks;

    public FinancePage(Page page) {
        this.breadCrumbContainer = page.locator("ul.tbcx-pw" +
                "-breadcrumbs__items");
        this.breadcrumbLinks = page.locator("ul.tbcx-pw-breadcrumbs__items > li.tbcx-pw-breadcrumbs__item a");
    }

}
