package ge.tbc.testautomation.tests;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class TabsFiltersTest extends BaseTest {

    @Test(description = "Tabs & Filters Functionality (ATMs, Branches, 24/7, " +
            "Open Now [CRM-T2]")
    public void testFilters() {
        homeSteps
                .openPage(Constants.TBC_URL)
                .navigateToMap()
                .allFilterActive()
                .listDisplayed()
                .filterCityAndValidateListSize(isMobile, Constants.TBILISI)
                .switchToBranchesTab()
                .validateFilteredBranches(isMobile)
                .applySubFilters()
                .removeFilters()
                .switchToAtmFilter();
    }
}
