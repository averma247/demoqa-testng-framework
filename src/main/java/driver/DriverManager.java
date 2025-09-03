package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser, boolean isRemote) throws MalformedURLException {
        if (driver.get() == null) {
            WebDriver webDriver;
            if (isRemote) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(browser);
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } else {
                if (browser.equalsIgnoreCase("chrome")) {
                    webDriver = new ChromeDriver();
                } else {
                    webDriver = new FirefoxDriver();
                }
            }
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}