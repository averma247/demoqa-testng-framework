package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElementsPage {
    WebDriver driver;

    @FindBy(id = "userName")
    WebElement userNameInput;

    @FindBy(id = "submit")
    WebElement submitButton;

    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String name) {
        userNameInput.sendKeys(name);
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
