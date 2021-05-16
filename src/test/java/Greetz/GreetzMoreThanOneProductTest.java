package Greetz;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;
import java.util.Random;

public class GreetzMoreThanOneProductTest {
    private WebDriver driver;
    private static WebDriverWait wait;
    Random rand = new Random();


        @Test
        public void CheckingPriceChangesTest() throws InterruptedException {

            System.setProperty("webdriver.chrome.driver",
                    "src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 30);
            driver.manage().window().maximize();
            driver.get("https://www.greetz.nl/ballonnen ");
            By acceptCookiesElemLoc = By.cssSelector(".b-buttonbar--button.b-button.cookieBar-old-style__button");
            WebElement acceptCookieElem = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesElemLoc));
            acceptCookieElem.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(acceptCookiesElemLoc));
            By productsElems = By.xpath("//div[@class='b-products-grid__item']//*[@data-qa-ref='product-wall-item']");
            List<WebElement> productsElements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productsElems, 15));
            WebElement elementRandom = getRandomElement(productsElements);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(elementRandom));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            By prodAmount = By.cssSelector("[data-qa-ref=product-amount]");
            WebElement productAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(prodAmount));
            By priceNormal = By.cssSelector("[data-qa-ref=normal-price]");
            WebElement productPriceElement = driver.findElement(priceNormal);
            String prodPrice = productPriceElement.getText();
            String prodPriceForDouble = prodPrice.replace(",", ".").replaceAll("[^0-9.]", "");
            double prodNumberPrice = Double.valueOf(prodPriceForDouble);
            productAmountElement.clear();
            String randomA = String.valueOf(getRandomNumber());
            productAmountElement.sendKeys(randomA);
            String valueInput = productAmountElement.getAttribute("value");
            wait.until(ExpectedConditions.invisibilityOfElementWithText(priceNormal, prodPrice));
            String prodPriceUpdated = productPriceElement.getText().replace(",", ".").replaceAll("[^0-9.]", "");
            double prodNumberPriceUpdated = Double.valueOf(prodPriceUpdated);
            double totalAmount = (Double.valueOf(valueInput) * prodNumberPrice) * 100 / 100.0;
            Assert.assertEquals(totalAmount, prodNumberPriceUpdated, "The total amount " + totalAmount + " for quantity: " + randomA + " does not match with total amount of " + prodNumberPriceUpdated + " presented on the site.");
            driver.quit();
        }
        public WebElement getRandomElement(List<WebElement> list) {
            return list.get(rand.nextInt(list.size()));
        }
        public int getRandomNumber() {
            return rand.nextInt(4) + 2;
        }

    }



