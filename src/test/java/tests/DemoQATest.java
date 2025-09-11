
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ElementsPage;
import pages.HomePage;
import reports.ExtentReportManager;
import utils.LogUtil;

public class DemoQATest extends BaseTest {


//    @Test
//    public void verifyDemoPageTitle() {
//
//        ExtentReportManager.getTest().info("Navigated to demoqa.com");
//        ExtentReportManager.getTest().info("Page title is: " + title);
//        LogUtil.info("Title of the page: "+title);
//        Assert.assertTrue(title.contains("DEMOQA"), "Title does not contain expected text.");
//        LogUtil.debug("Title of the page: "+title);
//
//    }
//    @Test
//    public void verifyElementsPage(){
//        ExtentReportManager.getTest().info("Navigated to demoqa.com");
//        homePage.clickElementsCard();
//        Assert.assertTrue(title.contains("DEMOQA"), "Title does not contain expected text.");
//
//    }

    @Test
    public void testUserNameSubmission() throws InterruptedException {
        homePage.clickElementsCard();
        Assert.assertTrue(homePage.checkElementsCardDisplayed());
//        Thread.sleep(10000);
//        elementsPage.enterUserName("Test User");
//        elementsPage.clickSubmit();

        // Add assertions here
    }

}
