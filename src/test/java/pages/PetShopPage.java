package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class PetShopPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "accept-all")
    private WebElement acceptCookiesButton;

    @FindBy(css = "#header-wrapper > div.header-middle.ng-star-inserted > div.popover.ng-star-inserted > div > fa-icon > svg")
    private WebElement popupCloseButton;

    @FindBy(xpath = "//span[.='KATEGORİLER']")
    private WebElement categoriesMenu;

    public PetShopPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void acceptCookiesAndClosePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(popupCloseButton)).click();
    }

    public void navigateToPetShop() {
        WebElement categoriesElement = wait.until(ExpectedConditions.elementToBeClickable(categoriesMenu));
        
        Actions actions = new Actions(driver);
        actions.moveToElement(categoriesElement).perform();
        
        WebElement petShopElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("a[href*='pet-shop']")));
        
        wait.until(ExpectedConditions.elementToBeClickable(petShopElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", petShopElement);
        
        WebElement body = driver.findElement(By.tagName("body"));
        actions.moveToElement(body).perform();
        
        wait.until(ExpectedConditions.urlContains("pet-shop"));
    }

    public boolean isPetShopPageDisplayed() {
        return driver.getCurrentUrl().contains("pet-shop");
    }

    public void sortByLowPrice() {
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[@class='mat-mdc-select-min-line ng-tns-c1771602899-4 ng-star-inserted']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sortButton);
        
        WebElement lowestPriceOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[contains(.,'Önce En Düşük Fiyat')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lowestPriceOption);
        
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("sm-list-page-item:nth-of-type(1) .price > [_ngcontent-ng-c3302073957]")));
    }

    public boolean isProductsSortedByPrice() {
        WebElement firstPrice = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("sm-list-page-item:nth-of-type(1) .price > [_ngcontent-ng-c3302073957]")));
        
        WebElement secondPrice = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("sm-list-page-item:nth-of-type(2) .price > [_ngcontent-ng-c3302073957]")));
        
        double price1 = getPriceAsDouble(firstPrice.getText());
        double price2 = getPriceAsDouble(secondPrice.getText());
        
        System.out.println("First product price: " + price1);
        System.out.println("Second product price: " + price2);
        
        boolean isSorted = price1 <= price2;
        if (isSorted) {
            System.out.println("Products are successfully sorted by price");
        } else {
            System.out.println("Products are not sorted by price");
        }
        return isSorted;
    }

    private double getPriceAsDouble(String priceStr) {
        String cleanPrice = priceStr.replaceAll("[^0-9,]", "").replace(",", ".");
        try {
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
} 