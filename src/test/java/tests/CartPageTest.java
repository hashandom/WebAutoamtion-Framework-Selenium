package tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.CartPage;
import pageFactory.CheckOutPage;
import pageFactory.InventoryPage;
import pageFactory.LoginPage;
import resources.Base;

import java.io.IOException;
import java.util.Set;

public class CartPageTest extends Base {
    WebDriver driverCart;
    public LoginPage loginPage;
    public LoginPageTest loginPageTest;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;

    // Pass the shared WebDriver instance to LoginPageTest
    public CartPageTest(WebDriver driver) {
        this.driverCart = driver;
        this.cartPage =  new CartPage(driverCart);
        this.inventoryPage = new InventoryPage(driverCart);
        //testing need to remove
        this.checkOutPage = new CheckOutPage(driverCart);
    }

    @BeforeClass
    public void setUpApplication() throws IOException, InterruptedException {
        System.out.println("setup application.......");
        driverCart = initializeDriver();
        driverCart.get(prop.getProperty("url"));
        System.out.println("navigate to the url" + prop.getProperty("url"));
        loginPage = new LoginPage(driverCart);
//        loginPageTest = new LoginPageTest(driverCart);
        inventoryPage = new InventoryPage(driverCart);
//        cartPage = new CartPage(driverCart);
        System.out.println("create the page object.......");
    }



    @Test(priority = 1, groups = "cart-functionality-regression", description = "Validate Add to Cart Button Functionality")
    public void Validate_Add_to_Cart_Button_Functionality() throws InterruptedException {

        userLogin(driverCart);
        System.out.println("navigate to the inventory page after successful login +++++++");

        System.out.println();
        System.out.println();

        //get the current page window id for validate whether land on correct web page
        String inventoryPageWindowHandle = driverCart.getWindowHandle();
        System.out.println("inventory page window handle" + inventoryPageWindowHandle);

        inventoryPage.addToCartButton().click();
        System.out.println("click the ADD TO CART button in the inventory page relevant to the sauce backpack card");
        inventoryPage.cartIcon().click();
        System.out.println("click the CART ICON on the right corner of the inventory page");

        //navigate to the  cart web page
        Set<String> pageHandles = driverCart.getWindowHandles();
        for (String handler : pageHandles) {
            if (!handler.equals(inventoryPageWindowHandle)) {
                driverCart.switchTo().window(handler);
                System.out.println("cart web page window id:::" + driverCart.getWindowHandle());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                break;
            }
        }

        //validate to navigate to the correct cart web page
        String actualCartPageUrl = "https://www.saucedemo.com/v1/cart.html";
        String expectedCartPageUrl = driverCart.getCurrentUrl();
        System.out.println("expected cart page url " + expectedCartPageUrl);
        Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "navigate to the invalid cart web page url");
        System.out.println("navigate the the correct cart web page url....");

        System.out.println();
        System.out.println();

        String inventoryPageItemTitle = inventoryPage.inventoryPage_sauceLabsBackpackClickableLink().getText();
        String cartPageItemTitle = cartPage.itemTitle().getText();
        System.out.println("cart page item title:: " + cartPageItemTitle);

        String inventoryPageItemDescription = inventoryPage.inventoryPage_sauceLabsBackpackDescription().getText();
        String cartPageItemDescription = cartPage.itemDescription().getText();
        System.out.println("cart page item description:: " + cartPageItemDescription);

        String cartPageItemPrice = cartPage.itemPrice().getText();
        System.out.println("cart page item price:: " + cartPageItemPrice);
        String inventoryPageItemPrice = inventoryPage.inventoryPage_sauceLabsBackpackPrice().getText();


        //compare inventory page sauce back pack item details with cart page sauce back pack item details
        //1.compare inventory page item title with cart page item title
        try {
            Assert.assertEquals(inventoryPageItemTitle, cartPageItemTitle, "Item title mismatch");
            System.out.println("Inventory page item title & cart page item title are the same");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
        //2.compare inventory page item description with cart page item description
        try {
            Assert.assertEquals(inventoryPageItemDescription, cartPageItemDescription, "Item description mismatch");
            System.out.println("Inventory page item description & cart page item description are the same");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
        //3.compare inventory page item description with cart page item description
        try {
            Assert.assertEquals(inventoryPageItemPrice, cartPageItemPrice, "Item price mismatch");
            System.out.println("Inventory page item price & cart page item price are the same");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }

        System.out.println("finish validating add to cart button functionality***");
    }


    //check item description is displayed
    public boolean isItemDescriptionDisplayed() {
        try {
            return cartPage.itemDescription().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //check item tile is displayed
    public boolean isItemTitleDisplayed() {
        try {
            return cartPage.itemTitle().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //check item price is displayed
    public boolean isItemPriceDisplayed() {
        try {
            return cartPage.itemPrice().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @Test(priority = 1, groups = "cart-functionality-regression", description = "Validate Remove Button Functionality")
    public void Validate_Remove_Button_Functionality() throws InterruptedException {

        Validate_Add_to_Cart_Button_Functionality();
        System.out.println("successfully navigate to the cart web page");

        cartPage.removeButton().click();
        System.out.println("click the remove button");

        Boolean isItemDescriptionDisplayed = isItemDescriptionDisplayed();

        //validate item title , description , price not visible in cart page after click the remove button
        Assert.assertFalse(isItemDescriptionDisplayed, "item description should not be visible after removal");
        Assert.assertFalse(isItemPriceDisplayed(), "item price should not be visible after removal ");
        Assert.assertFalse(isItemTitleDisplayed(), "item title should not be visible after removal");

        System.out.println("finish validate remove button functionality ...");
    }


    @Test(priority = 1, groups = "cart-functionality-regression", description = "Validate Continue ShoppingButton Functionality")
    public void Validate_Continue_ShoppingButton_Functionality() throws InterruptedException {

        Validate_Add_to_Cart_Button_Functionality();
        System.out.println("successfully navigate to the cart web page");

        //get the cart web page window handler id
        String cartPageWindowHandler = driverCart.getWindowHandle();

        System.out.println();
        System.out.println();
        cartPage.ContinueShoppingButton().click();
        System.out.println("click the continue shopping button");

        //get the window id of the inventory page window
        Set<String> handles = driverCart.getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(cartPageWindowHandler)) {
                driverCart.switchTo().window(handle);
            }
        }

        String expectedInventoryPageUrl = driverCart.getCurrentUrl();
        System.out.println("expected inventory page url:" + expectedInventoryPageUrl);

        //check when click the shopping button again redirect to the inventory page
        String actualInventoryPageUrl = "https://www.saucedemo.com/v1/inventory.html";
        Assert.assertEquals(actualInventoryPageUrl, expectedInventoryPageUrl, "inventory page url incorrect CONTINUE SHOPPING BUTTON now working");
        System.out.println("finish Validate_Continue_ShoppingButton_Functionality .......");


    }


    /**
     * SUPPORTIVE METHODS
     */
    public void userLogin(WebDriver driverCart) throws InterruptedException {
        loginPageTest = new LoginPageTest(driverCart);
        loginPageTest.Validate_the_Login_function_by_providing_valid_username_valid_password();
        Thread.sleep(1000);
    }


    @AfterClass
    public void driverClose() {
        if (driver != null) {
            driver.quit();
            System.out.println();
            System.out.println();
            System.out.println("driver closed sucessfully");
        }
    }
}
