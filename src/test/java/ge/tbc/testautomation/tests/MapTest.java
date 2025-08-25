package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import org.testng.annotations.Test;

public class MapTest extends BaseTest {
    HomeSteps homeSteps = new HomeSteps();

    @Test(description = "Main Page → Locations map with pins, panning/zooming" +
            " [MSP-T25]")
    public void testMapState(){
        homeSteps
                .openPage(Constants.TBC_URL)
                .navigateToMap()
                .mapContentLoaded()
                .zoomIn()
                .zoomOut()
                .panBy(100, 200);

    }
}
