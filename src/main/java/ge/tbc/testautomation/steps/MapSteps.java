package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.BoundingBox;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.MapPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;

import java.util.List;

public class MapSteps {
    Page page;
    MapPage mapPage;

    public MapSteps(Page page) {
        this.page = page;
        this.mapPage = new MapPage(page);
    }


    public MapSteps mapContentLoaded() {
        PlaywrightAssertions.assertThat(mapPage.map).isVisible();
        PlaywrightAssertions.assertThat(mapPage.marker).isVisible();
        mapPage.marker.scrollIntoViewIfNeeded();
        return this;
    }

//    public MapSteps zoomIn() {
//        actions()
//                .moveToElement(mapPage.map)
//                .keyDown(Keys.CONTROL)
//                .scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(mapPage.map), 0, -500)
//                .keyUp(Keys.CONTROL)
//                .perform();
//        return this;
//    }
public MapSteps zoomIn() {
    mapPage.map.hover();  // Move to element center
    page.keyboard().down("Control");
    page.mouse().wheel(0, -500);
    page.keyboard().up("Control");
    return this;
}

    public MapSteps zoomOut() {
        mapPage.map.hover();  // Move to element center
        page.keyboard().down("Control");
        page.mouse().wheel(0, 250);
        page.keyboard().up("Control");
        return this;
    }

public MapSteps panBy(int dx, int dy) {
    BoundingBox mapBox = mapPage.map.boundingBox();
    double startX = mapBox.x + mapBox.width / 2;
    double startY = mapBox.y + mapBox.height / 2;

    page.mouse().move(startX, startY);
    page.mouse().down();
    page.waitForTimeout(100);

    page.mouse().move(startX + dx / 4, startY + dy / 4);
    page.waitForTimeout(50);
    page.mouse().move(startX + dx / 2, startY + dy / 2);
    page.waitForTimeout(50);
    page.mouse().move(startX + 3 * dx / 4, startY + 3 * dy / 4);
    page.waitForTimeout(50);
    page.mouse().move(startX + dx, startY + dy);
    page.waitForTimeout(100);

    page.mouse().up();

    return this;
}

    public MapSteps allFilterActive() {
        PlaywrightAssertions.assertThat(mapPage.allFilterBtn).isVisible();
        PlaywrightAssertions.assertThat(mapPage.allFilterBtn).hasText(Constants.ALL);
        return this;
    }

    public MapSteps listDisplayed() {
        PlaywrightAssertions.assertThat(mapPage.listContainer).isVisible();
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        return this;
    }


    //No dropdown on mobile so this particular step must be skipped
    public MapSteps filterCityAndValidateListSize(boolean isMobile) {

        if (!isMobile) {
            int initialSize = mapPage.listItems.count();
            PlaywrightAssertions.assertThat(mapPage.dropDown).isVisible();
            mapPage.dropDown.click();
            mapPage.options.getByText(Constants.TBILISI, new Locator.GetByTextOptions().setExact(true)).click();
            PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
            int sizeAfter = mapPage.listItems.count();
            Assert.assertTrue(sizeAfter < initialSize);
        }

        return this;
    }

    public MapSteps switchToBranchesTab() {
        PlaywrightAssertions.assertThat(mapPage.branchesFilterBtn).isVisible();
        mapPage.branchesFilterBtn.click();
        return this;
    }

    public MapSteps validateFilteredBranches() {
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        List<Locator> allItems = mapPage.listItems.all();
        for (Locator item : allItems) {
            Locator listLabel = mapPage.getItemLabel(item);
            PlaywrightAssertions.assertThat(listLabel).not().containsText(Constants.ATM);
        }
        return this;
    }

    public MapSteps applySubFilters() {
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        int sizeBefore = mapPage.listItems.count();
        PlaywrightAssertions.assertThat(mapPage.openTwentyFourHoursFilter).isVisible();
        mapPage.openTwentyFourHoursFilter.click();
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        int sizeAfter = mapPage.listItems.count();
        Assert.assertTrue(sizeAfter < sizeBefore);
        return this;
    }

    public MapSteps removeFilters() {
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        int sizeBefore = mapPage.listItems.count();
        PlaywrightAssertions.assertThat(mapPage.openTwentyFourHoursFilter).isVisible();
        mapPage.openTwentyFourHoursFilter.click();
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        int sizeAfterFilterRemoval = mapPage.listItems.count();
        Assert.assertTrue(sizeAfterFilterRemoval > sizeBefore);
        return this;
    }

    public MapSteps switchToAtmFilter() {
        PlaywrightAssertions.assertThat(mapPage.atmFilterBtn).isVisible();
        mapPage.atmFilterBtn.click();
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        List<Locator> listItems =mapPage.listItems.all();

        for(Locator item : listItems){
            String label = mapPage.getItemLabel(item).innerText();
            Assert.assertEquals(label, Constants.ATM_LABEL);
        }

        return this;
    }


}


