package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.BranchDataProvider;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class BranchFilterTest extends BaseTest {

//The given test has a primary focus on desktop version since on mobile
// version the list does not get updated accordingly either due to a bug or a
// built-in design feature

    @Test(dataProvider ="branchData" , dataProviderClass =
            BranchDataProvider.class , description = "Branch filtration " +
            "functionality by street name [CRM-T8]")
    public void testBranchFilter(String street, String streetNumber){
        homeSteps
                .openPage(Constants.TBC_URL)
                .acceptCookies()
                .navigateToMap()
                .allFilterActive()
                .mapDisplayed()
                .listDisplayed()
                .clickTheCityDropDown()
                .selectTheCity(Constants.TBILISI)
                .switchToBranchesTab()
                .validateFilteredBranches(isMobile)
                .zoomOut()
                .checkIfSearchInputDisplayed()
                .searchTheStreetName(street)
                .validateFilteredItemsVisible(isMobile)
                .validateBranchStreetNameMatches(isMobile,street)
                .validateBranchStreetNumberMatches(isMobile,streetNumber);


    }
}
