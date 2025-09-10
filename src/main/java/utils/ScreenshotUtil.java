package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {
    public static String captureScreenshot(String testName) throws IOException {
        File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + testName + ".png";
        String relPath="../Screenshots" + File.separator + testName + ".png";

        File finalDest = new File(path);
        Files.copy(src.toPath(), finalDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return relPath;
    }
}
