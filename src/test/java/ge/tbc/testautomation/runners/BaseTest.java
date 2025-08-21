package ge.tbc.testautomation.runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.data.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    @Parameters({Constants.DEVICE})
    public void setUp(@Optional(Constants.DESKTOP) String device) {
        Configuration.browser = Constants.CHROME;
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.pageLoadTimeout = 15000;
    }


}
