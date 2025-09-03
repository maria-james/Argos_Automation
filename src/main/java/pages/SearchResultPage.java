package pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class SearchResultPage {

    @SuppressWarnings("unused")
	private WebDriver driver;
	
   @FindBy(xpath = "//h1[contains(@class,'SearchTerm')]")
    WebElement resultsHeader;
     @FindBy(css = "button[data-test='component-att-button']")
    List<WebElement> AddToTrolleyButtons; 
    @FindBy(xpath = "//a[text()='Continue without insurance']")
    WebElement continueButton;

     
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getResultsHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(resultsHeader));
       return resultsHeader.getText();  
    
    }
    

    public void addFirstProductToTrolley() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            if (AddToTrolleyButtons.size() > 0) {
                WebElement AddToTrolley = AddToTrolleyButtons.get(0); // pick the first button
                // Scroll it into view
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", AddToTrolley);
                // Wait until clickable
                wait.until(ExpectedConditions.elementToBeClickable(AddToTrolley));
                // Click it
                AddToTrolley.click();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } 
        // Wait for and click the continue button if it appears
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
         continueButton.click();
    }
}

