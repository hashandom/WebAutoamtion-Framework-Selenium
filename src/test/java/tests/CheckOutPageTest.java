package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.CartPage;
import pageFactory.CheckOutPage;
import pageFactory.LoginPage;
import resources.Base;

import java.io.IOException;
import java.util.Set;

public class CheckOutPageTest extends Base {

    public WebDriver driverCheckOutPage;
    public CartPageTest cartPageTest;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;
    public LoginPageTest loginPageTest;

    public CheckOutPageTest(WebDriver driverCheckOutOverview) {
        this.driverCheckOutPage = driverCheckOutOverview;
        this.checkOutPage = new CheckOutPage(driverCheckOutPage);
        this.cartPage = new CartPage(driverCheckOutPage);
    }

    @BeforeClass
    public void setUpApplication() throws IOException, InterruptedException {
        System.out.println("setup application.......");
        driverCheckOutPage = initializeDriver();
        driver.get(prop.getProperty("url"));
        System.out.println("navigate to the url" + prop.getProperty("url"));
        System.out.println("create the loginPage  object.......");
        cartPage = new CartPage(driverCheckOutPage);
//        cartPageTest = new CartPageTest(driverCheckOutPage);
        checkOutPage = new CheckOutPage(driverCheckOutPage);
//        loginPageTest = new LoginPageTest(driverCheckOutPage);
    }


    @Test(priority = 1, groups = "checkOut-functionality-regression", description = "Validate Checkout Button Functionality")
    public void Validate_Checkout_Button_Functionality() throws InterruptedException {
//      userLogin(driverCheckOutPage);
        navigateCartPage(driverCheckOutPage);
        System.out.println("navigate to the cart page  successfully ...");

        System.out.println();
        System.out.println();

        String cartPageWindowHandleId = driverCheckOutPage.getWindowHandle();
        System.out.println("cart page window handle id>>> " + cartPageWindowHandleId);

        cartPage.checkoutButton().click();
        System.out.println("click the checkout button");

        Set<String> handles = driverCheckOutPage.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(cartPageWindowHandleId)) {
                driverCheckOutPage.switchTo().window(handle);
            }
        }


        String expectCheckOutPageURL = driverCheckOutPage.getCurrentUrl();
        System.out.println("expected checkout page URL:::" + expectCheckOutPageURL);

        String actualCheckOutPageURL = "https://www.saucedemo.com/v1/checkout-step-one.html";

        //comparing the checkout page URL
        Assert.assertEquals(actualCheckOutPageURL, expectCheckOutPageURL, "checkout page URL is mismatch checkout button is not working properly");
        System.out.println("finish validate checkout button functionality ....");
    }


    @Test(priority = 1, groups = "checkOut-functionality-regression", description = "Validate Continue Button with Empty input Fields")
    public void Validate_Continue_Button_with_Empty_Input_Fields() throws InterruptedException {
        Validate_Checkout_Button_Functionality();
        System.out.println("successfully navigate to checkout page");

        //clear all the three input fields if any text is input
        checkOutPage.firstNameInputField().clear();
        checkOutPage.lastNameInputField().clear();
        checkOutPage.postalCodeInputField().clear();

        checkOutPage.continueButton().click();
        System.out.println("click the continue button");

        String actualErrorMsg = "Error: First Name is required";
        String expectedErrorMsg = checkOutPage.errorMsg().getText();
        System.out.println("expected error message " + expectedErrorMsg);

        //compare with actual error message with expected error message
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "mismatch the error message");
        System.out.println("finish Validate Continue Button with Empty input Fields ..... ");
    }


    @Test(priority = 1, groups = "checkOut-functionality-regression", description = "Validate Continue Button with Valid Input")
    public void Validate_Continue_Button_with_Valid_Input() throws InterruptedException {
        Validate_Checkout_Button_Functionality();
        System.out.println("successfully navigate to checkout page *****");

        //clear all the three input fields if any text is input
        checkOutPage.firstNameInputField().sendKeys("hashan");
        checkOutPage.lastNameInputField().sendKeys("sampath");
        checkOutPage.postalCodeInputField().sendKeys("10900");

        String checkOutPageWindowHandleId = driverCheckOutPage.getWindowHandle();
        System.out.println("check out page window handle id" + checkOutPageWindowHandleId);
        checkOutPage.continueButton().click();
        System.out.println("click the continue button");

        Set<String> handles = driverCheckOutPage.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(checkOutPageWindowHandleId)) {
                driverCheckOutPage.switchTo().window(handle);
            }
        }

        String expectedCheckoutOverviewPageURL = driverCheckOutPage.getCurrentUrl();
        System.out.println("expected checkout overview page URL" + expectedCheckoutOverviewPageURL);

        String actualCheckoutOverviewPageURL = "https://www.saucedemo.com/v1/checkout-step-two.html";

        //compare actual URL with expected URL
        Assert.assertEquals(actualCheckoutOverviewPageURL, expectedCheckoutOverviewPageURL, "checkOutOverview url mismatch continue button not working properly");
        System.out.println("finish Validate Continue Button with Valid Input ..... ");


    }


    @Test(priority = 1, groups = "checkOut-functionality-regression", description = "Validate Cancel Button Functionality")
    public void Validate_Cancel_Button_Functionality() throws InterruptedException {
        Validate_Checkout_Button_Functionality();
        System.out.println("successfully navigate to checkout page");

        //clear all the three input fields if any text is input
        checkOutPage.firstNameInputField().sendKeys("hashan");
        checkOutPage.lastNameInputField().sendKeys("sampath");
        checkOutPage.postalCodeInputField().sendKeys("10900");

        String checkOutPageWindowHandleId = driverCheckOutPage.getWindowHandle();
        System.out.println("check out page window handle id" + checkOutPageWindowHandleId);
        checkOutPage.cancelButton().click();
        System.out.println("click the cancel button");

        Set<String> handles = driverCheckOutPage.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(checkOutPageWindowHandleId)) {
                driverCheckOutPage.switchTo().window(handle);
            }
        }
        //get the cart page url
        String expectedCartPageUrl = driverCheckOutPage.getCurrentUrl();
        System.out.println("expect the cart page url" + expectedCartPageUrl);

        String actualCartPageUrl = "https://www.saucedemo.com/v1/cart.html";

        //compare  page URL
        Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "URL mismatch cancel button not working properly");
    }


    /**
     * supportive methods
     */

//    public void userLogin(WebDriver driverInventory) throws InterruptedException {
//        loginPageTest = new LoginPageTest(driverInventory);
//        loginPageTest.Validate_the_Login_function_by_providing_valid_username_valid_password();
//        Thread.sleep(10000);
//    }
    public void navigateCartPage(WebDriver driverCheckOutPage) throws InterruptedException {
        cartPageTest = new CartPageTest(driverCheckOutPage);
        cartPageTest.Validate_Add_to_Cart_Button_Functionality();
        Thread.sleep(1000);
    }


    @AfterClass
    public void driverClose() {
        if (driver != null) {
            driver.quit();
            System.out.println();
            System.out.println();
            System.out.println("driver closed successfully");
        }
    }

}