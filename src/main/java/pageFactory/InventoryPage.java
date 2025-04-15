package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='product_label']")
    private WebElement  inventoryPageTitle;

    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
    private WebElement  inventoryPage_sauceLabsBackpackClickableLink;

    @FindBy(xpath = "//div[normalize-space()='carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.']")
    private WebElement  inventoryPage_sauceLabsBackpackDescription;

    @FindBy(xpath = "//div[@class='inventory_item_price']\n")
    private WebElement  inventoryPage_sauceLabsBackpackPrice;

    @FindBy(xpath = "//div[@class='inventory_list']//div[1]//div[3]//button[1]")
    private WebElement  addToCartButton;

    @FindBy(xpath = "//*[name()='path' and contains(@fill,'currentCol')]")
    private WebElement  cartIcon;

    public WebElement inventoryPageTitle() {
        return inventoryPageTitle;
    }

    public WebElement inventoryPage_sauceLabsBackpackClickableLink() {
        return inventoryPage_sauceLabsBackpackClickableLink;
    }
    public WebElement  inventoryPage_sauceLabsBackpackDescription() {
        return  inventoryPage_sauceLabsBackpackDescription;
    }
    public WebElement  inventoryPage_sauceLabsBackpackPrice() {
        return   inventoryPage_sauceLabsBackpackPrice;
    }

    public WebElement addToCartButton() {
        return addToCartButton;
    }

    public WebElement cartIcon() {
        return cartIcon;
    }

}
