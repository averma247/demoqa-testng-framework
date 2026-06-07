package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import pages.ElementsPage;
import pages.HomePage;
import reports.ExtentReportManager;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {

    protected HomePage homePage;
    protected ElementsPage elementsPage;

    @Parameters({"browser", "isRemote"})
    @BeforeMethod(alwaysRun = true)
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") boolean isRemote,
            Method method
    ) throws MalformedURLException {

        // Initialize Driver
        DriverManager.initDriver(browser, isRemote);

        WebDriver driver = DriverManager.getDriver();

        // Browser Setup
        driver.manage().window().maximize();

        // Launch Application
        driver.get("https://demoqa.com");

        // Initialize Page Objects
        homePage = new HomePage(driver);
        elementsPage = new ElementsPage(driver);

        // Create Extent Test
        ExtentReportManager.createTest(
                method.getName() + " [" + browser.toUpperCase() + "]"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {

        if (ExtentReportManager.getTest() != null) {

            switch (result.getStatus()) {

                case ITestResult.FAILURE:

                    String screenshotPath =
                            ScreenshotUtil.captureScreenshot(result.getName());

                    ExtentReportManager.getTest().fail(
                            result.getThrowable(),
                            MediaEntityBuilder
                                    .createScreenCaptureFromPath(screenshotPath)
                                    .build()
                    );
                    break;

                case ITestResult.SUCCESS:

                    ExtentReportManager.getTest().pass("Test Passed");
                    break;

                case ITestResult.SKIP:

                    ExtentReportManager.getTest().skip("Test Skipped");
                    break;
            }
        }

        DriverManager.quitDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        ExtentReportManager.initReports();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        ExtentReportManager.flushReports();
    }
}