package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage {
    WebDriver driver;

    public InventoryItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
    private WebElement  inventoryItemPage_sauceLabsBackpackTitle;

    @FindBy(xpath = "//div[normalize-space()='carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.']")
    private WebElement   inventoryItemPage_sauceLabsBackpackDescription;

    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement   inventoryItemPage_sauceLabsBackpackPrice;


    public WebElement inventoryItemPage_sauceLabsBackpackTitle() {
        return inventoryItemPage_sauceLabsBackpackTitle;
    }

    public WebElement inventoryItemPage_sauceLabsBackpackDescription() {
        return inventoryItemPage_sauceLabsBackpackDescription;
    }
    public WebElement   inventoryItemPage_sauceLabsBackpackPrice() {
        return   inventoryItemPage_sauceLabsBackpackPrice;
    }

}
