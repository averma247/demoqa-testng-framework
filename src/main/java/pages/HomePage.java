
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumActions;

public class HomePage {
    WebDriver driver;
    SeleniumActions actions;

    @FindBy(xpath = "//h5[text()='Elements']")
    private WebElement elementsCard;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new SeleniumActions(driver);
    }

    public void clickElementsCard() {
        actions.scrollToElement(elementsCard);
        actions.click(elementsCard);
    }
    public boolean checkElementsCardDisplayed() {
        return actions.isElementDisplayed(elementsCard);
    }
}
