package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CommonPage;

public class CommonSteps {
    Page page;

    CommonPage commonPage;

    public CommonSteps(Page page) {
        this.page = page;
        this.commonPage = new CommonPage(page);
    }


    public CommonSteps openPage(String url) {
        page.navigate(url);
        return this;
    }

    public CommonSteps acceptCookies() {
        PlaywrightAssertions.assertThat(commonPage.acceptCookiesBtn).isVisible();
        commonPage.acceptCookiesBtn.click();
        return this;
    }

    public CommonSteps openSearchInput() {
        PlaywrightAssertions.assertThat(commonPage.searchBtn).isVisible();
        commonPage.searchBtn.click();
        return this;
    }

    public CommonSteps writeValidSearchWord(String value) {
        PlaywrightAssertions.assertThat(commonPage.searchInput).isVisible();
        commonPage.searchInput.fill(value);
        return this;
    }

    public CommonSteps validateResults() {
        PlaywrightAssertions.assertThat(commonPage.searchContainer).isVisible();
        PlaywrightAssertions.assertThat(commonPage.searchResults).not().hasCount(0);
        return this;
    }

    public CommonSteps searchInvalidWord(String value) {
        PlaywrightAssertions.assertThat(commonPage.searchInput).isVisible();
        commonPage.searchInput.fill(value);
        return this;
    }

    public CommonSteps validateEmptyResults() {
        PlaywrightAssertions.assertThat(commonPage.searchContainer).isVisible();
        PlaywrightAssertions.assertThat(commonPage.noResultsFoundElement).isVisible();
        PlaywrightAssertions.assertThat(commonPage.noResultsFoundElement).hasText(Constants.NOTHING_FOUND);
        return this;
    }

    public FinanceSteps hoverOverAndNavigate(Boolean isMobile) {
        if (isMobile) {
            PlaywrightAssertions.assertThat(commonPage.hamburgerIcon).isVisible();
            commonPage.hamburgerIcon.click();
            commonPage.myBusinessBtnMobile.scrollIntoViewIfNeeded();
            page.waitForTimeout(300);
            commonPage.myBusinessBtnMobile.click();
            PlaywrightAssertions.assertThat(commonPage.dropDownButton).isVisible();
            commonPage.dropDownButton.click();
            PlaywrightAssertions.assertThat(commonPage.businessLoansLink).isVisible();
            commonPage.businessLoansLink.click();

        } else {
            PlaywrightAssertions.assertThat(commonPage.myBusinessLink).isVisible();
            commonPage.myBusinessLink.hover();
            page.waitForTimeout(500);
            PlaywrightAssertions.assertThat(commonPage.businessLoansLink).isVisible();
            commonPage.businessLoansLink.click();
        }

        return new FinanceSteps(page);
    }

    public void exitSearch() {
        PlaywrightAssertions.assertThat(commonPage.closeSearchBtn).isVisible();
        commonPage.closeSearchBtn.click();
        PlaywrightAssertions.assertThat(commonPage.searchWindow).not().isVisible();
    }

}

