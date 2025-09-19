package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.BoundingBox;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.MapPage;
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


    public MapSteps zoomIn() {
        mapPage.map.hover();
        page.keyboard().down("Control");
        page.mouse().wheel(0, -500);
        page.keyboard().up("Control");
        return this;
    }

    public MapSteps zoomOut() {
        mapPage.map.hover();
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

    public MapSteps mapDisplayed() {
        PlaywrightAssertions.assertThat(mapPage.map).isVisible();
        return this;
    }


    //No dropdown on mobile so this particular step must be skipped
    public MapSteps filterCityAndValidateListSize(boolean isMobile,
                                                  String value) {
        if (!isMobile) {
            int initialSize = mapPage.listItems.count();
            PlaywrightAssertions.assertThat(mapPage.dropDown).isVisible();
            mapPage.dropDown.click();
            mapPage.options.getByText(value,
                    new Locator.GetByTextOptions().setExact(true)).click();
            PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
            int sizeAfter = mapPage.listItems.count();
            Assert.assertTrue(sizeAfter < initialSize);
        }

        return this;
    }

    public MapSteps clickTheCityDropDown() {
        PlaywrightAssertions.assertThat(mapPage.dropDown).isVisible();
        mapPage.dropDown.click();
        return this;
    }

    public MapSteps selectTheCity(String value) {
        mapPage.options.getByText(value,
                new Locator.GetByTextOptions().setExact(true)).click();
        return this;
    }

    public MapSteps switchToBranchesTab() {
        PlaywrightAssertions.assertThat(mapPage.branchesFilterBtn).isVisible();
        mapPage.branchesFilterBtn.click();
        return this;
    }

    public MapSteps validateFilteredBranches(Boolean isMobile) {
        if (!isMobile) {
            PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
            List<Locator> allItems = mapPage.listItems.all();
            for (Locator item : allItems) {
                Locator listLabel = mapPage.getItemLabel(item);
                PlaywrightAssertions.assertThat(listLabel).not().containsText(Constants.ATM);
            }
        }
        return this;
    }

    public MapSteps validateFilteredItemsVisible(Boolean isMobile) {
        if(!isMobile){
            PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        }

        return this;
    }

    public MapSteps validateBranchStreetNameMatches(Boolean isMobile,
                                                    String streetName) {
        if (!isMobile) {
            if (mapPage.listItems.count() > 1) {
                Locator branchTitle = mapPage.getItemTitle(mapPage.listItems.last());
                PlaywrightAssertions.assertThat(branchTitle).containsText(streetName);
            } else {
                Locator branchTitle =
                        mapPage.getItemTitle(mapPage.listItems.first());
                PlaywrightAssertions.assertThat(branchTitle).containsText(streetName);
            }
        }

        return this;
    }

    public void validateBranchStreetNumberMatches(Boolean isMobile,
                                                  String streetNumber) {
        if (!isMobile) {
            if (mapPage.listItems.count() > 1) {
                Locator branchTitle = mapPage.getItemTitle(mapPage.listItems.last());
                PlaywrightAssertions.assertThat(branchTitle).containsText(streetNumber);
            } else {
                Locator branchTitle =
                        mapPage.getItemTitle(mapPage.listItems.first());
                PlaywrightAssertions.assertThat(branchTitle).containsText(streetNumber);
            }
        }
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

    public MapSteps checkIfSearchInputDisplayed() {
        PlaywrightAssertions.assertThat(mapPage.searchInput).isVisible();
        return this;
    }

    public MapSteps searchTheStreetName( String street) {
        mapPage.searchInput.fill(street);
        return this;
    }

    public MapSteps switchToAtmFilter() {
        PlaywrightAssertions.assertThat(mapPage.atmFilterBtn).isVisible();
        mapPage.atmFilterBtn.click();
        PlaywrightAssertions.assertThat(mapPage.listItems).not().hasCount(0);
        List<Locator> listItems = mapPage.listItems.all();

        for (Locator item : listItems) {
            String label = mapPage.getItemLabel(item).innerText();
            Assert.assertEquals(label, Constants.ATM_LABEL);
        }

        return this;
    }


}


