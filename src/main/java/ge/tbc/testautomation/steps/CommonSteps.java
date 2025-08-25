package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CommonPage;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.open;

public class CommonSteps {

    CommonPage commonPage = new CommonPage();

    public CommonSteps openPage(String url) {
        open(url);
        return this;
    }

    public CommonSteps acceptCookies() {
        commonPage.acceptCookiesBtn.shouldBe(Condition.visible,
                Duration.ofSeconds(5)).click();

        return this;
    }

    public CommonSteps openSearchInput() {
        commonPage.searchBtn.shouldBe(Condition.visible).click();
        return this;
    }

    public CommonSteps writeValidSearchWord(String value) {
        commonPage.searchInput.shouldBe(Condition.visible).setValue(value);
        return this;
    }

    public CommonSteps validateResults() {
        commonPage.searchContainer.shouldBe(Condition.visible);
        commonPage.searchResults.shouldHave(sizeGreaterThan(0)).filter(Condition.visible).shouldHave(sizeGreaterThan(0));
        return this;
    }

    public CommonSteps searchInvalidWord(String value) {
        commonPage.searchInput.shouldBe(Condition.visible).setValue(value);
        return this;
    }

    public CommonSteps validateEmptyResults() {
        commonPage.searchContainer.shouldBe(Condition.visible);
        commonPage.noResultsFoundElement.shouldBe(Condition.visible).shouldHave(Condition.text(Constants.NOTHING_FOUND));
        return this;
    }

    public FinanceSteps hoverOverAndNavigate(Boolean isMobile) {
        if (isMobile) {
            commonPage.hamburgerIcon.shouldBe(Condition.visible).click();
            commonPage.myBusinessBtnMobile.shouldBe(Condition.visible).click();
            commonPage.dropDownButton.shouldBe(Condition.visible).click();
            commonPage.businessLoansLink.shouldBe(Condition.visible).click();
        } else {
            commonPage.myBusinessLink.shouldBe(Condition.visible).hover();
            commonPage.businessLoansLink.shouldBe(Condition.visible).click();
        }

        return new FinanceSteps();
    }

    public void exitSearch() {
        commonPage.closeSearchBtn.shouldBe(Condition.visible).click();
        commonPage.searchWindow.shouldNotBe(Condition.visible);
    }

}
