//
//package ge.tbc.testautomation.runners;
//import com.microsoft.playwright.*;
//import ge.tbc.testautomation.data.Constants;
//import ge.tbc.testautomation.steps.CommonSteps;
//import ge.tbc.testautomation.steps.HomeSteps;
//import ge.tbc.testautomation.steps.OfferAndFilterSteps;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//
//import java.util.Arrays;
//
//public class BaseTest {
//    Playwright playwright;
//
//    Browser browser;
//
//    BrowserContext browserContext;
//
//    Page page;
//
//    public CommonSteps commonSteps;
//    public OfferAndFilterSteps offerAndFilterSteps;
//
//    public HomeSteps homeSteps;
//    public boolean isMobile;
//
//    @BeforeClass(alwaysRun = true)
//    @Parameters({Constants.DEVICE, Constants.BROWSER_TYPE})
//    public void setUp(@Optional(Constants.DESKTOP) String device,
//                   @Optional(Constants.CHROME) String browserType) {
//        playwright = Playwright.create();
//
//        BrowserType.LaunchOptions launchOptions =
//                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000).setTimeout(50000).setArgs(Arrays.asList("--disable-gpu", "--disable-extensions",
//                        "--no-sandbox"));
//
//        if(browserType.equals(Constants.CHROME)){
//        browser = playwright.chromium().launch(launchOptions);
//        }else if(browserType.equals(Constants.FIREFOX)){
//            browser = playwright.firefox().launch(launchOptions);
//        }else if(browserType.equals(Constants.SAFARI)){
//            browser = playwright.webkit().launch(launchOptions);
//        }
//
//        browserContext = browser.newContext(
//                new Browser.NewContextOptions()
//                        .setGeolocation(new com.microsoft.playwright.options.Geolocation(41.7151, 44.8271))
//                        .setPermissions(java.util.Arrays.asList("geolocation"))
//        );
//
//        page = browserContext.newPage();
//
//        page.setDefaultTimeout(20000);
//
//        page.setDefaultNavigationTimeout(20000);
//
//        if(device.equals(Constants.MOBILE)){
//            page.setViewportSize(385,667);
//            isMobile = true;
//        } else {
//            page.setViewportSize(1440, 900);
//            isMobile = false;
//        }
//        commonSteps = new CommonSteps(page);
//        homeSteps = new HomeSteps(page);
//        offerAndFilterSteps = new OfferAndFilterSteps(page);
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDown(){
//        browserContext.close();
//        browser.close();
//        playwright.close();
//    }
//
//
//
//}
//
package ge.tbc.testautomation.runners;
import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.BreadcrumbVisualSteps;
import ge.tbc.testautomation.steps.CommonSteps;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.OfferAndFilterSteps;
import org.testng.annotations.*;

import java.util.Arrays;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    public CommonSteps commonSteps;
    public OfferAndFilterSteps offerAndFilterSteps;
    public BreadcrumbVisualSteps breadcrumbVisualSteps;
    public HomeSteps homeSteps;
    public boolean isMobile;

    @BeforeClass(alwaysRun = true)
    @Parameters({Constants.DEVICE, Constants.BROWSER_TYPE})
    public void setUp(@Optional(Constants.MOBILE) String device,
                      @Optional(Constants.CHROME) String browserType) {
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions =
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(1000)
                        .setTimeout(50000)
                        .setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--no-sandbox"));

        if (browserType.equals(Constants.CHROME)) {
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserType.equals(Constants.FIREFOX)) {
            browser = playwright.firefox().launch(launchOptions);
        } else if (browserType.equals(Constants.SAFARI)) {
            browser = playwright.webkit().launch(launchOptions);
        }

        isMobile = device.equals(Constants.MOBILE);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        browserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setGeolocation(new com.microsoft.playwright.options.Geolocation(41.7151, 44.8271))
                        .setPermissions(Arrays.asList("geolocation"))
        );

        page = browserContext.newPage();
        page.setDefaultTimeout(20000);
        page.setDefaultNavigationTimeout(20000);

        if (isMobile) {
            page.setViewportSize(385, 667);
        } else {
            page.setViewportSize(1440, 900);
        }

        commonSteps = new CommonSteps(page);
        homeSteps = new HomeSteps(page);
        offerAndFilterSteps = new OfferAndFilterSteps(page);
        breadcrumbVisualSteps = new BreadcrumbVisualSteps(page);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}

