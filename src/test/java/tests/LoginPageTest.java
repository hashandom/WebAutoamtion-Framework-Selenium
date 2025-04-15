package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageFactory.InventoryPage;
import pageFactory.LoginPage;
import resources.Base;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

public class LoginPageTest extends Base {

    public WebDriver driver;
    public LoginPage loginPage;


    // Pass the shared WebDriver instance to LoginPageTest
    public LoginPageTest(WebDriver driver) {
        this.driver = driver;
     this.loginPage = new LoginPage(driver);
    }


    @BeforeClass
    public void setUpApplication() throws IOException, InterruptedException {
        System.out.println("setup application.......");
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        System.out.println("navigate to the url" + prop.getProperty("url"));
//        loginPage = new LoginPage(driver);
        System.out.println("create the loginPage  object.......");
    }


    @Test(groups = "smoke", priority = 1, description = "Validate the given URL is valid URL")
    public void Validate_the_given_URL_is_valid_URL() {
        System.out.println("Validate the given URL is valid URL is running........");
        String expectedLoginPageUrl = driver.getCurrentUrl();
        System.out.println("expected login page url" + expectedLoginPageUrl);
        String actualLoginPageUrl = "https://www.saucedemo.com/v1/index.html";
        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "invalid url and smoke testing unsuccessful");
        System.out.println("finish the execution of Validate the given URL is valid URL ........");
    }


    @Test(
            groups = "login-functionality-regression",
            dependsOnGroups = "smoke",
            priority = 2,
            description = "Validate the Login function by providing empty username  & empty password")
    public void Validate_the_Login_function_by_providing_empty_username_empty_password() throws InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("Validate the Login function by providing empty username  & empty password is running...............");
        loginPage.usernameInputField().sendKeys("");
        loginPage.passwordInputField().sendKeys("");
        loginPage.loginButton().click();
        String expectedErrorMessage = loginPage.popup_error_message().getText();
        System.out.println("expected error message :::" + expectedErrorMessage);
        String actualErrorMessage = "Epic sadface: Username is required";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch");
        System.out.println("finish the execution Validate the Login function by providing empty username  & empty password...............");
    }


    @Test(
            groups = "login-functionality-regression",
            dependsOnGroups = "smoke",
            priority = 3,
            description = "Validate the Login function by providing empty username  & valid password.......")
    public void Validate_the_Login_function_by_providing_empty_username_valid_password() throws InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("Validate the Login function by providing empty username  & valid password is running");
        loginPage.usernameInputField().sendKeys("");
        Thread.sleep(2000);
        loginPage.passwordInputField().sendKeys("secret_sauce");
        loginPage.loginButton().click();
        String expectedErrorMessage = loginPage.popup_error_message().getText();
        System.out.println("expected error message" + expectedErrorMessage);
        String actualErrorMessage = "Epic sadface: Username is required";
        Thread.sleep(2000);
        loginPage.passwordInputField().clear();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch");
        System.out.println("finish Validate the Login function by providing empty username  & valid password is running.......");
    }


    @Test(
            groups = "login-functionality-regression",
            dependsOnGroups = "smoke",
            priority = 4,
            description = "Validate the Login function by providing valid username  & valid password")
    public void Validate_the_Login_function_by_providing_valid_username_valid_password() throws InterruptedException {
        System.out.println();
        System.out.println();


        System.out.println("validate the Login function by providing valid username  & valid password .......");
        //getting mainWindow handler id
        String loginPageWindowHandle = driver.getWindowHandle();
        System.out.println("loginPageWindowHandle " + loginPageWindowHandle);


        loginPage.usernameInputField().sendKeys("standard_user");
        Thread.sleep(5000);
        loginPage.passwordInputField().clear();
        loginPage.passwordInputField().sendKeys("secret_sauce");
        Thread.sleep(5000);
        loginPage.loginButton().click();
        Thread.sleep(2000);

        System.out.println("navigate to the inventory page url");
        Thread.sleep(2000);

        Set<String> pageWindowHandles = driver.getWindowHandles();
        for (String windowHandle : pageWindowHandles) {
            if (!windowHandle.equals(loginPageWindowHandle)) {
                System.out.println("switching to the window handle" + windowHandle);
                driver.switchTo().window(windowHandle);
            }

        }

        //validate after successful login navigate to the inventory page
        String actualInventoryPageUrl = "https://www.saucedemo.com/v1/inventory.html";
        String expectedInventoryPageUrl = driver.getCurrentUrl();
        System.out.println("expected inventory page url" + expectedInventoryPageUrl);
        Assert.assertEquals(actualInventoryPageUrl, expectedInventoryPageUrl, "unsuccessful navigate to the inventory page");
        System.out.println("finish validate the Login function by providing valid username  & valid password .......");

    }


    @AfterClass
    public void driverClose() throws InterruptedException {
        System.out.println();
        System.out.println();
        if (driver != null) {
            driver.quit();
            System.out.println("driver closed sucessfully");
        }
    }
}
