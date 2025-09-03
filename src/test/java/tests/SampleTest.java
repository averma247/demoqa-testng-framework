
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import driver.DriverManager;
import reports.ExtentReportManager;
import utils.LogUtil;

public class SampleTest extends BaseTest {

    @Test
    public void verifyDemoPageTitle() {

        ExtentReportManager.getTest().info("Navigated to demoqa.com");
        ExtentReportManager.getTest().info("Page title is: " + title);
        LogUtil.info("Title of the page: "+title);
        Assert.assertTrue(title.contains("DEMOQA"), "Title does not contain expected text.");
        LogUtil.debug("Title of the page: "+title);

    }
}
