package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.CheckOutOverViewPage;
import resources.Base;

import java.io.IOException;
import java.util.Set;

public class CheckOutOverviewPageTest extends Base {
    WebDriver driverCheckOutOverview;
    public CheckOutPageTest checkOutPageTest;
    public CheckOutOverViewPage checkOutOverViewPage;

    @BeforeClass
    public void setUpApplication() throws IOException, InterruptedException {
        System.out.println("setup application.......");
        driverCheckOutOverview = initializeDriver();
        driverCheckOutOverview.get(prop.getProperty("url"));
        System.out.println("navigate to the url" + prop.getProperty("url"));
//        loginPage = new LoginPage(driver);
        checkOutOverViewPage = new CheckOutOverViewPage(driverCheckOutOverview);
        System.out.println("create the loginPage  object.......");
    }

    @Test(priority = 1, groups = "checkOutOverview-functionality-regression", description = "Validate Successful Processing of Total Product Calculation")
    public void Validate_Successful_Processing_of_Total_Product_Calculation() throws InterruptedException {
        navigateCheckOutOverviewPage(driverCheckOutOverview);
        System.out.println("successfully navigate to the checkout overview page ........");

        System.out.println();
        System.out.println();
        System.out.println("------------- check the total calculation-------------");

        String expectedItemPrice = checkOutOverViewPage.itemTotal().getText().trim();
        double numericExpectedItemPrice = Double.parseDouble(expectedItemPrice.replaceAll("[^0-9.]", ""));
        System.out.println("Item Price: " + numericExpectedItemPrice);

        String expectedTax = checkOutOverViewPage.tax().getText().trim();
        double numericExpectedTax = Double.parseDouble(expectedTax.replaceAll("[^0-9.]", ""));
        System.out.println("Tax: " + numericExpectedTax);

        String expectedTotalPrice = checkOutOverViewPage.totalPrice().getText().trim();
        double numericExpectedTotalPrice = Double.parseDouble(expectedTotalPrice.replaceAll("[^0-9.]", ""));
        System.out.println("Expected Total Cost: " + numericExpectedTotalPrice);
        System.out.println();

        // Calculate actual total price
        double actualTotalPrice = numericExpectedItemPrice + numericExpectedTax;
        System.out.println("Actual Total Cost: " + actualTotalPrice);

        //compare expected total cost with actual total cost
        Assert.assertEquals(actualTotalPrice, numericExpectedTotalPrice, 0.01, "Error in total price calculation");
        System.out.println("finish total product calculation .......");
    }


    @Test(priority = 1, groups = "checkOutOverview-functionality-regression", description = "Validate Successful Functionality of the FINISH Button", dependsOnMethods = "Validate_Successful_Processing_of_Total_Product_Calculation")
    public void Validate_Successful_Functionality_of_the_FINISH_Button() throws InterruptedException {
//        Validate_Successful_Processing_of_Total_Product_Calculation();
        System.out.println("successfully done calculation");

        String checkOutViewWindowId = driverCheckOutOverview.getWindowHandle();
        System.out.println("checkout view window handle id ::" + checkOutViewWindowId);

        checkOutOverViewPage.finish().click();
        System.out.println("click the finish button");

        //navigate to the finish page
        Set<String> handles = driverCheckOutOverview.getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(checkOutViewWindowId)) {
                driverCheckOutOverview.switchTo().window(handle);
            }
        }

        String expectedFinishPageURL = driverCheckOutOverview.getCurrentUrl();
        System.out.println("expect finish page url" + expectedFinishPageURL);

        String actualFinishPageURL = "https://www.saucedemo.com/v1/checkout-complete.html";
        Assert.assertEquals(expectedFinishPageURL, actualFinishPageURL, "mismatch finish page url & finish button not working properly");
        System.out.println("finish the Functionality of the FINISH Button ......");
    }

    /**
     * Supportive methods
     */

    public void navigateCheckOutOverviewPage(WebDriver driverCheckOutOverview) throws InterruptedException {
        checkOutPageTest = new CheckOutPageTest(driverCheckOutOverview);
        checkOutPageTest.Validate_Continue_Button_with_Valid_Input();
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
