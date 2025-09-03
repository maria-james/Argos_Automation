package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage {
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(id = "searchTerm")
	private WebElement searchInput;
	@FindBy(xpath = "//button[@data-test='search-button']")
	private WebElement search;
	@FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesBtn;

	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

   // Accept cookies if the popup appears   
   public void acceptCookiesIfPresent() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement acceptBtn = wait.until(
            ExpectedConditions.elementToBeClickable(acceptCookiesBtn)
        );
        acceptBtn.click();
    } catch (TimeoutException e) {
        // No cookie popup appeared, safe to continue
    }
   }

    // Enter product name in search input field and click search button	
	public void search_Input(String product) {
		searchInput.sendKeys(product);
	}

	public void search_Button() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.elementToBeClickable(search));
		search.click();

	}
	
}
