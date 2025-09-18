package ge.tbc.testautomation.tests;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.FilterDataProvider;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class TestOffersFilters extends BaseTest {


    @Test(description = "Validate filtration functionality on offers page " +
            "[CRM-T6]", dataProvider = "categoryFiltersAndUrlTokens",
            dataProviderClass= FilterDataProvider.class)
    public void testOfferFilters(String categoryFilter, String urlToken) {
    offerAndFilterSteps
            .navigateToOffers(Constants.TBC_OFFERS_URL)
            .acceptCookies()
            .validateFilterSectionBtnVisible()
            .navigateToFiltersPage()
            .validateOfferListDisplayed()
            .categoryFilterListDisplayed(isMobile)
            .applyFilter(isMobile, categoryFilter)
            .validateThatFilterAppliedInUrl(urlToken)
            .checkClearFilterBtnVisible(isMobile)
            .removeCategoryFilter(isMobile, categoryFilter)
            .validateThatFilterRemovedFromUrl(urlToken)
            .checkClearFilterBtnInvisible(isMobile);

    }
}
