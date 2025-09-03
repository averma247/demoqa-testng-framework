
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import driver.DriverManager;
import reports.ExtentReportManager;

public class SampleTest extends BaseTest {

    @Test
    public void verifyDemoPageTitle() {

        ExtentReportManager.getTest().info("Navigated to demoqa.com");
        ExtentReportManager.getTest().info("Page title is: " + title);
        Assert.assertTrue(title.contains("DEMOQA"), "Title does not contain expected text.");
    }
}
