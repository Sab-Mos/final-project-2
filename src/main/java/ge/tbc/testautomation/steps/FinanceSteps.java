package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.FinancePage;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;

public class FinanceSteps {
    FinancePage financePage = new FinancePage();

    public FinanceSteps validateBreadCrumb(boolean isMobile) {
        financePage.breadCrumbContainer.shouldBe(Condition.visible);

        financePage.breadcrumbLinks.shouldHave(size(3));

        if (isMobile) {
            financePage.breadcrumbLinks.get(0).should(Condition.exist).shouldNotBe(Condition.visible);

            financePage.breadcrumbLinks.get(2).should(Condition.exist).shouldNotBe(Condition.visible);

            financePage.breadcrumbLinks.get(1)
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text(Constants.FINANCE_TEXT));

            Assert.assertTrue(financePage.breadcrumbLinks.get(1).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[1]));
        } else {
            financePage.breadcrumbLinks.get(0)
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text(Constants.MY_BUSINESS_TEXT));

            financePage.breadcrumbLinks.get(1)
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text(Constants.FINANCE_TEXT));

            financePage.breadcrumbLinks.get(2)
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text(Constants.BUSINESS_LOANS_TEXT));

            Assert.assertTrue(financePage.breadcrumbLinks.get(0).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[0]));
            Assert.assertTrue(financePage.breadcrumbLinks.get(1).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[1]));
            Assert.assertTrue(financePage.breadcrumbLinks.get(2).getAttribute(
                    "href").contains(Constants.BREADCRUMB_PATHS[2]));

        }
        return this;
    }

    public void validateActiveBreadCrumb(Boolean isMobile) {
        if(!isMobile){
            String breadCrumbCurrentPageUrl =
                    financePage.breadcrumbLinks.get(2).shouldBe(Condition.visible).getAttribute("href");

            String currentUrl = WebDriverRunner.url();

            Assert.assertEquals(breadCrumbCurrentPageUrl, currentUrl);
        }

    }


}
