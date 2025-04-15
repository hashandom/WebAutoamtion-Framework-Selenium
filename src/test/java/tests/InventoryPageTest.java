package tests;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageFactory.InventoryItemPage;
import pageFactory.InventoryPage;
import pageFactory.LoginPage;
import resources.Base;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

public class InventoryPageTest extends Base {

    public WebDriver driverInventory;
    public InventoryPage inventoryPage;
    public LoginPageTest loginPageTest;
    public LoginPage loginPage;
    public InventoryItemPage inventoryItemPage;

    @BeforeClass
    public void setUpApplication() throws IOException, InterruptedException {
        System.out.println("setup application.......");
        driverInventory = initializeDriver();
        driverInventory.get(prop.getProperty("url"));
        System.out.println("navigate to the url" + prop.getProperty("url"));
        inventoryPage = new InventoryPage(driverInventory);
//        loginPageTest = new LoginPageTest(driverInventory);
//        loginPage = new LoginPage(driverInventory);
        System.out.println("create the page object.......");
    }


    @Test(priority = 1, groups = "inventory-functionality-regression", description = "Validate That All Selected Product Details Are Displayed Correctly on the Inventory Item Page")
    public void Validate_All_Selected_Product_Details_Are_Displayed_Correctly_on_the_Inventory_Item_Page() throws InterruptedException {
        userLogin(driverInventory);
        Thread.sleep(1000);
        System.out.println("navigate to the inventory page after successful login");

        System.out.println();
        System.out.println();

        //get the inventory page window handler id
        String invnetoryPageWindowHandler = driverInventory.getWindowHandle();
        System.out.println("inventory page window handle :::" + invnetoryPageWindowHandler);

        //get the title the Sauce-labs-backpack red link
        String sauceLabsBackpackClickableLinkTitle = inventoryPage.inventoryPage_sauceLabsBackpackClickableLink().getText();
        System.out.println("sauce Labs Backpack ClickableLink Title:::" + sauceLabsBackpackClickableLinkTitle);

        //get the description of the Sauce-labs-backpack red link
        String sauceLabsBackpackDescription = inventoryPage.inventoryPage_sauceLabsBackpackDescription().getText();
        System.out.println("sauce Labs Backpack Description:::" + sauceLabsBackpackDescription);

        //get the price of the Sauce-labs-backpack red link
        String sauceLabsBackpackPrice = inventoryPage.inventoryPage_sauceLabsBackpackPrice().getText();
        System.out.println("sauce Labs Backpack Price::;" + sauceLabsBackpackPrice);

        inventoryPage.inventoryPage_sauceLabsBackpackClickableLink().click();
        Thread.sleep(1000);
        System.out.println("navigate to the inventor-item page");

        //get all the window handler id & select the relevant id for compression between inventory page product & inventory item  page product
        Set<String> windowHandles = driverInventory.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(invnetoryPageWindowHandler)) {
                driverInventory.switchTo().window(handle);
                break;
            }
        }

        System.out.println("inventoryItemPage url" + driverInventory.getCurrentUrl());

        //initialize the driverInventory to inventItem page
        inventoryItemPage = new InventoryItemPage(driverInventory);

        //get title , description , price for sauce labs backpack in inventory-item page
        String expect_sauceLabsBackpackClickableLinkTitle = inventoryItemPage.inventoryItemPage_sauceLabsBackpackTitle().getText();
        System.out.println("expect_sauceLabsBackpackClickableLinkTitle::: " + expect_sauceLabsBackpackClickableLinkTitle);
        String expect_sauceLabsBackpackDescription = inventoryItemPage.inventoryItemPage_sauceLabsBackpackDescription().getText();
        System.out.println("expect_sauceLabsBackpackDescription :::" + expect_sauceLabsBackpackDescription);
        String expect_sauceLabsBackpackPrice = inventoryItemPage.inventoryItemPage_sauceLabsBackpackPrice().getText();
        System.out.println("expect_sauceLabsBackpackPrice :::" + expect_sauceLabsBackpackPrice);

        //compare with inventory page sauce-labs-backpack title with inventory-item page  sauce-labs-backpack title
        Assert.assertEquals(sauceLabsBackpackClickableLinkTitle, expect_sauceLabsBackpackClickableLinkTitle, "sauce labs backpack title mismatch");
        //compare with inventory page sauce-labs-backpack description with inventory-item page  sauce-labs-backpack description
        Assert.assertEquals(sauceLabsBackpackDescription, expect_sauceLabsBackpackDescription, "sauce labs backpack description mismatch");
        //compare with inventory page sauce-labs-backpack price with inventory-item page  sauce-labs-backpack price
        Assert.assertEquals(sauceLabsBackpackPrice, expect_sauceLabsBackpackPrice, "sauce labs backpack description mismatch");

        System.out.println("finish Validate That All Selected Product Details Are Displayed Correctly ..............");
    }

    /**
     * SUPPORTIVE METHODS
     */
    public void userLogin(WebDriver driverInventory) throws InterruptedException {
        loginPageTest = new LoginPageTest(driverInventory);
        loginPageTest.Validate_the_Login_function_by_providing_valid_username_valid_password();
        Thread.sleep(10000);
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

