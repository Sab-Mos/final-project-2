package ge.tbc.testautomation.tests.visual;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;

import org.testng.annotations.Test;

public class VisualBreadcrumbSnapshotTest extends BaseTest {

    @Test(description = "Visual Regression — Breadcrumb (Business Loans, KA) " +
            "[CRM-T7]")
    public void breadcrumb_visual_snapshot() {
        commonSteps
                .openLoansPage(Constants.BUSINESS_LOANS_KA_URL)
                .acceptCookiesLoansPage()
                .stabilize()
                .assertBreadcrumbMatchesBaseline();
    }
}
