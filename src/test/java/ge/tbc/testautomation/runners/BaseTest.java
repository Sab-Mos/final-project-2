package ge.tbc.testautomation.runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    public CommonSteps commonSteps;
    public boolean isMobile;

    @BeforeClass(alwaysRun = true)
    @Parameters({Constants.DEVICE})
    public void setUp(@Optional(Constants.DESKTOP) String device) {
        Configuration.browser = Constants.CHROME;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 15000;
        commonSteps = new CommonSteps();

        if(device.equals(Constants.MOBILE)){
            Configuration.browserSize= "375x667";
            isMobile = true;
        } else {
            Configuration.browserSize = "1440x900";
            isMobile = false;
        }


    }


}
