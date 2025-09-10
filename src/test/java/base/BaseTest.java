package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.testng.Assert;
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
    protected String title;
    protected HomePage homePage;
    protected ElementsPage elementsPage;
    @Parameters({"browser", "isRemote"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, @Optional("false") boolean isRemote, Method method) throws MalformedURLException {
        DriverManager.initDriver(browser, isRemote);
        homePage = new HomePage(DriverManager.getDriver());
        elementsPage = new ElementsPage(DriverManager.getDriver());
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get("https://demoqa.com");
        title = DriverManager.getDriver().getTitle();
        ExtentReportManager.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(result.getName());
            ExtentReportManager.getTest().fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
        DriverManager.quitDriver();
    }

    @BeforeSuite
    public void beforeSuite() {
        ExtentReportManager.initReports();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportManager.flushReports();
    }
}
