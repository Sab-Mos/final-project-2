package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class GlobalNavigationTest extends BaseTest {


    @Test(description = "Global Navigation — Mega Menu Navigation & Breadcrumb Validation [MSP-T28]")
    public void testGlobalNavigation() {
        commonSteps
                .openPage(Constants.TBC_URL)
                .acceptCookies()
                .hoverOverAndNavigate(isMobile)
                .validateBreadCrumb(isMobile)
                .validateActiveBreadCrumb(isMobile);
    }
}
