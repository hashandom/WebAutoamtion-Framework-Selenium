package pageFactory;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCodeInputField;

    @FindBy(xpath = "//a[@class='cart_cancel_link btn_secondary']")
    private WebElement cancelButton;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    private WebElement continueButton;

    @FindBy(xpath = " //h3[@data-test='error']")
    private WebElement errorMsg;



    public WebElement firstNameInputField() {
        return firstNameInputField;
    }

    public WebElement lastNameInputField() {
        return lastNameInputField;
    }

    public WebElement postalCodeInputField() {return postalCodeInputField;}

    public WebElement cancelButton() {return cancelButton;}

    public WebElement continueButton() {return continueButton;}

    public WebElement errorMsg() {return errorMsg;}
}
