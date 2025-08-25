package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class SearchResultsTest extends BaseTest {


    @Test(description = "Search — Results and Empty State Validation [MSP-T27]")
    public void testSearchResults() {
        commonSteps
                .openPage(Constants.TBC_URL)
                .openSearchInput()
                .writeValidSearchWord(Constants.LOAN)
                .validateResults()
                .searchInvalidWord(Constants.RANDOM_VALUE)
                .validateEmptyResults()
                .exitSearch();


    }
}
