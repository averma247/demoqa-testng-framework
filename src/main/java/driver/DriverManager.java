package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverManager {

    private DriverManager() {
        // Prevent object creation
    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser, boolean isRemote) throws MalformedURLException {

        if (driver.get() != null) {
            return;
        }

        WebDriver webDriver;

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");

                // Required for GitHub Actions/Linux execution

                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");


                if (isRemote) {
                    webDriver = new RemoteWebDriver(
                            URI.create("http://localhost:4444/wd/hub").toURL(),
                            chromeOptions
                    );
                } else {
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(chromeOptions);
                }
                break;

            case "edge":

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");

                if (isRemote) {
                    webDriver = new RemoteWebDriver(
                            URI.create("http://localhost:4444/wd/hub").toURL(),
                            edgeOptions
                    );
                } else {

                   webDriver = new EdgeDriver(edgeOptions);
                }
                break;

            case "firefox":

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (isRemote) {
                    webDriver = new RemoteWebDriver(
                            URI.create("http://localhost:4444/wd/hub").toURL(),
                            firefoxOptions
                    );
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver(firefoxOptions);
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}