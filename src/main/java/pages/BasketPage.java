package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage {
    @SuppressWarnings("unused")
	private WebDriver driver;
	
    @FindBy(css = "img[data-test='product-image']")
    WebElement productImage;
    @FindBy(css = "h4[data-test='component-quick-view-content-title']")
    WebElement productTitle;
    @FindBy(css = "button[data-test='component-quick-view-content-button-att-override']")
    WebElement backButton;
    @FindBy(css = "select[data-e2e='product-quantity']")
    WebElement quantityDropdown;
    @FindBy(css = "span[data-e2e='product-line-price']")
    WebElement subtotal;
    @FindBy(css = "span[data-e2e='product-unit-price']")
    WebElement unitPrice;


    public BasketPage(WebDriver driver) {
        this.driver = driver;
        // Initialize @FindBy elements
        PageFactory.initElements(driver, this);
    }
    
    // Get the product name in the basket

      public String getProductNameInBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the image to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(productImage));
       // Click on the image
       productImage.click();
      // Now wait for the product title to be visible
       wait.until(ExpectedConditions.visibilityOf(productTitle));
      // Get the product title text
       String text = productTitle.getText();
      // Click the back button to return to the basket
       backButton.click();
        return text;
    
   }
    // Increase product quantity to 2
    public  void incrementProductQuantity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(quantityDropdown));
       Select select = new Select(quantityDropdown);
      // select quantity 2
      select.selectByValue("2");
    }
    // Get the basket subtotal
    public double getSubtotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(subtotal));
        return Double.parseDouble(subtotal.getText().replace("£", "").trim());
    }
// Get the unit price of the product
    public double getUnitPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(unitPrice));
        return Double.parseDouble(unitPrice.getText().replace("£", "").trim());
    }
}
