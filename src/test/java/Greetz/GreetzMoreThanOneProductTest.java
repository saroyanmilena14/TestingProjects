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

    @Test
    public void CheckingPriceChangesTest() {
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl/ballonnen");
        WebDriverWait wait = new WebDriverWait(driver, 25);

        List<WebElement> allBallonnen = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='clearfix' and  @infinite-scroll='main.loadNextPage()']//div[@class='b-products-grid__item' and @ng-class]"), 20));
        WebElement randomBallonnen = allBallonnen.get(new Random().nextInt(allBallonnen.size()));
        randomBallonnen.click();
        WebElement priceBefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa-ref=normal-price]")));
        String priceBeforeText = priceBefore.getText().substring(1).replace(",", ".");
        Double priceBeforeValue = Double.parseDouble(priceBeforeText);
        WebElement inputAmount = driver.findElement(By.xpath("//div[@class='page-detail__select']//input[@data-qa-ref='product-amount']"));
        String counttext = "4";

        Double count = Double.parseDouble(counttext);
        Double expectedPrice = count * priceBeforeValue;
        String expectedPriceText = String.valueOf(expectedPrice).replace(".", ",");
        inputAmount.sendKeys(Keys.BACK_SPACE, counttext);


        WebElement priceAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '" + expectedPriceText + "')]")));
        String priceAfterText = priceAfter.getText().substring(1).replace(",", ".");
        Double priceAfterValue = Double.parseDouble(priceAfterText);
        driver.quit();
        Assert.assertEquals(expectedPrice, priceAfterValue, "The expected price " + expectedPrice + " doesn't equal to " + priceAfterValue);


    }
}
