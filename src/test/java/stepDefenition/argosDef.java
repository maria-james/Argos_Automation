package stepDefenition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.SearchResultPage;
import pages.BasketPage;

public class argosDef {
	
	WebDriver driver;
	String searchItem;
	HomePage home_obj;
	SearchResultPage search_obj;
	BasketPage basket_obj;
	
	private String url = "https://www.argos.co.uk/";

	@Given("I navigate to Argos website")
	public void i_navigate_to_Argos_website() {
	     ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // maximize window
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
	    driver.get(url);
	    home_obj = new HomePage(driver);
		search_obj = new SearchResultPage(driver);
		 home_obj.acceptCookiesIfPresent();
	}

	@When("I search for {string}")
	public void i_search_for_item(String product) {
	    searchItem = product;
	    home_obj.search_Input(product);
		home_obj.search_Button();
	    
	}	
	 @Then("I should see search results for {string}")
    public void i_should_see_search_results_for(String product) {
	   Assert.assertTrue(search_obj.isProductInSearchResults(product),
            "Search results do not match expected product");
    }

    @When("I add the first product to the basket")
    public void i_add_the_first_product_to_the_basket() {
		search_obj.addFirstProductToTrolley();	
    }

    @Then("the product should be visible in the basket")
     public void the_product_should_be_visible_in_the_basket() throws InterruptedException {
		  basket_obj = new BasketPage(driver);
             Assert.assertTrue(basket_obj.getProductNameInBasket().contains("Lenovo IdeaPad"),
                 "Search results do not match expected product");
			
			
		}

	@When("I increase the product quantity to 2")
	public void i_increase_the_product_quantity_to() {
		basket_obj = new BasketPage(driver);
		basket_obj.incrementProductQuantity();
	}

	@Then("the basket subtotal should be updated correctly")
	public void the_basket_subtotal_should_be_updated_correctly() {

		double unitPrice = basket_obj.getUnitPrice();
	    double expectedSubtotal = unitPrice * 2;
	    double actualSubtotal = basket_obj.getSubtotal();
	    Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01, "Subtotal did not update correctly");
	}
	@After
	public void after_test() {
		driver.quit();
	}
}
