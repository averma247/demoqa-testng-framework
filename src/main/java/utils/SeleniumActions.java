package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SeleniumActions {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    /**
     * Enterprise-grade click method
     * Handles:
     * - waits
     * - headless mode
     * - intercepted clicks
     * - scrolling issues
     */
    public void click(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

        scrollToElement(element);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            element.click();

        } catch (ElementNotInteractableException e) {

            javascriptClick(element);

        } catch (StaleElementReferenceException e) {

            wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(element)));

            javascriptClick(element);
        }
    }

    /**
     * Safe click wrapper
     */
    public void safeClick(WebElement element) {

        try {
            click(element);

        } catch (Exception e) {

            scrollToElement(element);

            wait.until(ExpectedConditions.elementToBeClickable(element));

            javascriptClick(element);
        }
    }

    /**
     * Send text to input field
     */
    public void sendKeys(WebElement element, String text) {

        wait.until(ExpectedConditions.visibilityOf(element));

        scrollToElement(element);

        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element
     */
    public String getText(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

        return element.getText();
    }

    /**
     * Wait until element is visible
     */
    public void waitForVisibility(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait until element is clickable
     */
    public WebElement waitForElementClickable(WebElement element) {

        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Scroll element to center of viewport
     */
    public void scrollToElement(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                element
        );
    }

    /**
     * JavaScript click fallback
     */
    public void javascriptClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    /**
     * Hover on element
     */
    public void hoverOverElement(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

        actions.moveToElement(element).perform();
    }

    /**
     * Double click
     */
    public void doubleClick(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        actions.doubleClick(element).perform();
    }

    /**
     * Right click
     */
    public void rightClick(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        actions.contextClick(element).perform();
    }

    /**
     * Check element visibility
     */
    public boolean isElementDisplayed(WebElement element) {

        try {
            return element.isDisplayed();

        } catch (NoSuchElementException |
                 StaleElementReferenceException e) {

            return false;
        }
    }

    /**
     * Select dropdown using visible text
     */
    public void selectDropdownByText(WebElement dropdownElement,
                                     String visibleText) {

        wait.until(ExpectedConditions.visibilityOf(dropdownElement));

        Select select = new Select(dropdownElement);

        select.selectByVisibleText(visibleText);
    }

    /**
     * Select dropdown using value
     */
    public void selectDropdownByValue(WebElement dropdownElement,
                                      String value) {

        wait.until(ExpectedConditions.visibilityOf(dropdownElement));

        Select select = new Select(dropdownElement);

        select.selectByValue(value);
    }

    /**
     * Select dropdown using index
     */
    public void selectDropdownByIndex(WebElement dropdownElement,
                                      int index) {

        wait.until(ExpectedConditions.visibilityOf(dropdownElement));

        Select select = new Select(dropdownElement);

        select.selectByIndex(index);
    }

    /**
     * Accept alert
     */
    public void acceptAlert() {

        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
    }

    /**
     * Dismiss alert
     */
    public void dismissAlert() {

        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().dismiss();
    }

    /**
     * Get alert text
     */
    public String getAlertText() {

        wait.until(ExpectedConditions.alertIsPresent());

        return driver.switchTo().alert().getText();
    }

    /**
     * Switch to frame using WebElement
     */
    public void switchToFrame(WebElement frameElement) {

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    /**
     * Switch back to default content
     */
    public void switchToDefaultContent() {

        driver.switchTo().defaultContent();
    }

    /**
     * Static wait (avoid using frequently)
     */
    public void hardWait(int seconds) {

        try {

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}