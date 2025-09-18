package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.OfferAndFiltersPage;
import org.testng.Assert;

public class OfferAndFilterSteps {
    Page page;
    OfferAndFiltersPage offerAndFiltersPage;

    public OfferAndFilterSteps(Page page) {
        this.page = page;
        this.offerAndFiltersPage = new OfferAndFiltersPage(page);
    }


    public OfferAndFilterSteps navigateToOffers(String value) {
        page.navigate(value);
        return this;
    }

    public OfferAndFilterSteps acceptCookies() {
        if (offerAndFiltersPage.acceptCookiesBtn.isVisible()) {
            offerAndFiltersPage.acceptCookiesBtn.click();
        }
        return this;
    }

    public OfferAndFilterSteps validateFilterSectionBtnVisible() {
        PlaywrightAssertions.assertThat(offerAndFiltersPage.creditCardOffersBtn).isVisible();
        return this;
    }

    public OfferAndFilterSteps navigateToFiltersPage() {
        offerAndFiltersPage.creditCardOffersBtn.click();
        return this;
    }

    public OfferAndFilterSteps validateOfferListDisplayed() {
        PlaywrightAssertions.assertThat(offerAndFiltersPage.offerListItems).not().hasCount(0);
        return this;
    }

    public OfferAndFilterSteps categoryFilterListDisplayed(Boolean isMobile) {
        if (isMobile) {
            offerAndFiltersPage.mobileFilterBtn.click();
            PlaywrightAssertions.assertThat(offerAndFiltersPage.filterListMobile).isVisible();
        } else {
            PlaywrightAssertions.assertThat(offerAndFiltersPage.filterList).isVisible();
        }

        return this;
    }

    public OfferAndFilterSteps applyFilter(Boolean isMobile, String category) {
        offerAndFiltersPage.targetCategoryFilter(isMobile, category).click();
        return this;
    }

    public OfferAndFilterSteps checkClearFilterBtnVisible(Boolean isMobile) {
        if (isMobile) {
            PlaywrightAssertions.assertThat(offerAndFiltersPage.clearFilterBtnMobile).isVisible();
        } else {
            PlaywrightAssertions.assertThat(offerAndFiltersPage.clearFilterBtnDesktop).isVisible();
        }
        return this;
    }

    public void checkClearFilterBtnInvisible(Boolean isMobile) {
        if (isMobile) {
            offerAndFiltersPage.clearFilterBtnMobile.click();
            PlaywrightAssertions.assertThat(offerAndFiltersPage.clearFilterBtnMobile).isHidden();
        } else {
            PlaywrightAssertions.assertThat(offerAndFiltersPage.clearFilterBtnDesktop).isHidden();
        }
    }

    public OfferAndFilterSteps validateThatFilterAppliedInUrl(String urlToken) {
        String siteUrl = page.url();
        Assert.assertTrue(siteUrl.contains(urlToken));
        return this;
    }

    public OfferAndFilterSteps validateThatFilterRemovedFromUrl(String urlToken) {
        System.out.println(page.url());
        String siteUrl = page.url();
        Assert.assertFalse(siteUrl.contains(urlToken));
        return this;
    }

    public OfferAndFilterSteps removeCategoryFilter(Boolean isMobile, String category) {
        offerAndFiltersPage.targetCategoryFilter(isMobile, category).click();
        return this;
    }

}
