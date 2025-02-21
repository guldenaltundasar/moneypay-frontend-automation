package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PetShopPage;

import static org.junit.Assert.assertTrue;


public class PetShopSteps extends BaseTest {
    private PetShopPage petShopPage;

    @Given("Open Migros website with {string}")
    public void openBrowser(String browser) {
        driver = getDriver(browser);
        petShopPage = new PetShopPage(driver);
        driver.get("https://www.migros.com.tr/");
    }

    @When("Accept cookies and close popup")
    public void acceptCookiesAndClosePopup() {
        petShopPage.acceptCookiesAndClosePopup();
    }

    @When("Select Pet Shop from Categories menu")
    public void selectPetShopCategory() {
        petShopPage.navigateToPetShop();
    }

    @Then("Verify Pet Shop page is opened")
    public void verifyPetShopPage() {
        assertTrue("Pet Shop page is not opened", petShopPage.isPetShopPageDisplayed());
    }

    @When("Sort products by low price")
    public void sortProductsByLowPrice() {
        petShopPage.sortByLowPrice();
    }

    @Then("Verify products are sorted by low price")
    public void verifyProductsSortedByLowPrice() {
        assertTrue("Products are not sorted by price", petShopPage.isProductsSortedByPrice());
        System.out.println("Test completed successfully");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario.getName());
        }
        closeDriver();
    }
} 