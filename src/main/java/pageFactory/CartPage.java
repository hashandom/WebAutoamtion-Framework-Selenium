package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//button[@class='btn_secondary cart_button']")
    private WebElement removeButton;

    @FindBy(xpath = "//a[@class='btn_action checkout_button']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//a[@class='btn_secondary']")
    private WebElement ContinueShoppingButton;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private WebElement itemTitle;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    private WebElement itemDescription;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private WebElement itemPrice;

    public WebElement removeButton() {
        return removeButton;
    }

    public WebElement checkoutButton() {
        return checkoutButton;
    }

    public WebElement itemTitle() {
        return itemTitle;
    }

    public WebElement itemDescription() {
        return itemDescription;
    }

    public WebElement itemPrice() {
        return itemPrice;
    }

    public WebElement ContinueShoppingButton() {
        return ContinueShoppingButton;
    }
}
