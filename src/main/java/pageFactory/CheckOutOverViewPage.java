package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOverViewPage {

    WebDriver driver;

    public CheckOutOverViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemPrice;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    private WebElement tax;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    private WebElement totalPrice;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    private WebElement finish;

    public WebElement itemTotal() {
        return itemPrice;
    }

    public WebElement tax() {
        return tax;
    }

    public WebElement totalPrice() {
        return totalPrice;
    }

    public WebElement finish() {
        return finish;
    }


}
