package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumActions;

public class ElementsPage {
    WebDriver driver;
    SeleniumActions actions;

    @FindBy(xpath = "//div[text()='Elements']")
    private WebElement elementsCard;

    @FindBy(id = "userName")
    WebElement userNameInput;

    @FindBy(id = "submit")
    WebElement submitButton;

    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new SeleniumActions(driver);

    }
    public boolean checkElementsCardDisplayed() {
        return actions.isElementDisplayed(elementsCard);
    }

    public void enterUserName(String name) {
        actions.sendKeys(userNameInput,"Ajay");
    }

    public void clickSubmit() {
        actions.click(submitButton);
    }
}
