package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import org.testng.annotations.Test;

public class MapTest extends BaseTest {

    @Test(description = "Main Page → Locations map with pins, panning/zooming" +
            " [CRM-T5]")
    public void testMapState(){
        homeSteps
                .openPage(Constants.TBC_URL)
                .assertMapLinkPresent()
                .scrollMapLinkIntoView()
                .clickMapLink()
                .mapContentLoaded()
                .zoomIn()
                .zoomOut()
                .panBy(100, 200);

    }
}
