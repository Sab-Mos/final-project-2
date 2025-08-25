package ge.tbc.testautomation.steps;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.MapPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.actions;

public class MapSteps {

    MapPage mapPage = new MapPage();


    public MapSteps mapContentLoaded() {
        mapPage.map.shouldBe(Condition.visible);
        mapPage.marker.shouldBe(Condition.visible).scrollIntoView(true);
        return this;
    }

    public MapSteps zoomIn() {
        actions()
                .moveToElement(mapPage.map)
                .keyDown(Keys.CONTROL)
                .scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(mapPage.map), 0, -500)
                .keyUp(Keys.CONTROL)
                .perform();
        return this;
    }


    public MapSteps zoomOut() {
        actions()
                .moveToElement(mapPage.map)
                .keyDown(Keys.CONTROL)
                .scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(mapPage.map), 0, 250)
                .keyUp(Keys.CONTROL)
                .perform();
        return this;
    }

    public MapSteps panBy(int dx, int dy) {

        actions()
                .moveToElement(mapPage.map)
                .clickAndHold()
                .pause(Duration.ofMillis(100))
                .moveByOffset(dx / 4, dy / 4)
                .pause(Duration.ofMillis(50))
                .moveByOffset(dx / 4, dy / 4)
                .pause(Duration.ofMillis(50))
                .moveByOffset(dx / 4, dy / 4)
                .pause(Duration.ofMillis(50))
                .moveByOffset(dx / 4, dy / 4)
                .pause(Duration.ofMillis(100))
                .release()
                .perform();
        return this;
    }

    public MapSteps allFilterActive() {
        mapPage.allFilterBtn.shouldBe(Condition.visible)
                .shouldHave(Condition.text(Constants.ALL));
        return this;
    }

    public MapSteps listDisplayed() {
        mapPage.listContainer.shouldBe(Condition.visible);
        mapPage.listItems.shouldHave(sizeGreaterThan(0));
        return this;
    }


    //No dropdown on mobile so this particular step must be skipped
    public MapSteps filterCityAndValidateListSize(boolean isMobile) {

        if(!isMobile){
            int initialSize = mapPage.listItems.size();
            mapPage.dropDown.shouldBe(Condition.visible).click();
            mapPage.options.findBy(Condition.exactText(Constants.TBILISI)).click();
            int sizeAfter = mapPage.listItems.shouldHave(sizeGreaterThan(0)).size();
            Assert.assertTrue(sizeAfter < initialSize);
        }

        return this;
    }

    public MapSteps switchToBranchesTab() {
        mapPage.branchesFilterBtn.shouldBe(Condition.visible).click();
        return this;
    }

    public MapSteps validateFilteredBranches() {
        mapPage.listItems.shouldHave(sizeGreaterThan(0)).forEach(item -> {
            SelenideElement listLabel = mapPage.getItemLabel(item);
            listLabel.shouldNotHave(Condition.text(Constants.ATM));
        });
        return this;
    }

    public MapSteps applySubFilters() {
        int sizeBefore = mapPage.listItems.shouldHave(sizeGreaterThan(0)).size();
        mapPage.openTwentyFourHoursFilter.shouldBe(Condition.visible).click();
        int sizeAfter = mapPage.listItems.shouldHave(sizeGreaterThan(0)).size();
        Assert.assertTrue(sizeAfter < sizeBefore);
        return this;
    }

    public MapSteps removeFilters() {
        int sizeBefore = mapPage.listItems.shouldHave(sizeGreaterThan(0)).size();
        mapPage.openTwentyFourHoursFilter.shouldBe(Condition.visible).click();
        int sizeAfterFilterRemoval =
                mapPage.listItems.shouldHave(sizeGreaterThan(0)).size();
        Assert.assertTrue(sizeAfterFilterRemoval > sizeBefore);
        return this;
    }

    public MapSteps switchToAtmFilter() {
        mapPage.atmFilterBtn.shouldBe(Condition.visible).click();
        mapPage.listItems.shouldHave(sizeGreaterThan(0)).forEach(item -> {
            String label = mapPage.getItemLabel(item).getText();
            Assert.assertEquals(label, Constants.ATM_LABEL);
        });
        return this;
    }


}


