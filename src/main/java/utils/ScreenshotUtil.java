package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String captureScreenshot(String testName) throws IOException {
        File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        //String path = "Screenshots/" + testName + ".png";
        String path = "src/../images/screenshots_"+System.currentTimeMillis()+ testName + ".png";

        File finalDest = new File(path);
        FileUtils.copyFile(src, finalDest);
        return finalDest.getAbsolutePath();
    }
}
