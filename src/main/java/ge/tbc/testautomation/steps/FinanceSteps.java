package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.FinancePage;
import org.testng.Assert;

import java.net.URL;

public class FinanceSteps {
    Page page;

    FinancePage financePage;

    public FinanceSteps(Page page) {
        this.page = page;
        this.financePage = new FinancePage(page);
    }


    public FinanceSteps validateBreadCrumb(boolean isMobile) {
        PlaywrightAssertions.assertThat(financePage.breadCrumbContainer).isVisible();

        PlaywrightAssertions.assertThat(financePage.breadcrumbLinks).hasCount(3);


        if (isMobile) {
            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(0)).isHidden();

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(2)).isHidden();

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(1)).isVisible();

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(1)).hasText(Constants.FINANCE_TEXT);

            Assert.assertTrue(financePage.breadcrumbLinks.nth(1).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[1]));
        } else {
            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(0)).isVisible();

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(0)).hasText(Constants.MY_BUSINESS_TEXT);

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(1)).isVisible();

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(1)).hasText(Constants.FINANCE_TEXT);

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(2)).isVisible();

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(2)).hasText(Constants.BUSINESS_LOANS_TEXT);

            Assert.assertTrue(financePage.breadcrumbLinks.nth(0).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[0]));
            Assert.assertTrue(financePage.breadcrumbLinks.nth(1).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[1]));
            Assert.assertTrue(financePage.breadcrumbLinks.nth(2).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[2]));

        }
        return this;
    }

    public void validateActiveBreadCrumb(Boolean isMobile) {
        if (!isMobile) {

            PlaywrightAssertions.assertThat(financePage.breadcrumbLinks.nth(2)).isVisible();


            String breadCrumbCurrentPageUrl =
                    financePage.breadcrumbLinks.nth(2).getAttribute("href");

            String currentUrl = page.url();


            Assert.assertTrue(currentUrl.contains(breadCrumbCurrentPageUrl));
        }

    }


}
