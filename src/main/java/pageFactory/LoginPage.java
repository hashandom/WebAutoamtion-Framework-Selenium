package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameInputField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;


    @FindBy(xpath = "//h4[normalize-space()='Accepted usernames are:']")
    private WebElement Accepted_usernames_are;

    @FindBy(xpath = "//h4[normalize-space()='Password for all users:']")
    private WebElement Password_for_all_users;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement popupErrorMessage;


    public WebElement usernameInputField() {
        return usernameInputField;
    }

    public WebElement passwordInputField() {
        return passwordInputField;
    }

    public WebElement loginButton() {
        return loginButton;
    }

    public WebElement Accepted_usernames_are() {

        return Accepted_usernames_are;
    }

    public WebElement Password_for_all_users() {
        return Password_for_all_users;
    }

    public WebElement popup_error_message() {
        return popupErrorMessage;
    }


}
