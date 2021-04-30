package testProjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class SixpmTest {

    @Test
    public void testSearchResult() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Milena\\IdeaProjects\\MavennProject\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.6pm.com");
        Thread.sleep(10000);
        WebElement inputField = driver.findElement(By.id("searchAll"));
        inputField.sendKeys("dresses" + Keys.ENTER);
        List<WebElement> allProducts = driver.findElements(By.cssSelector("#searchPage article"));
        WebElement randomElem = allProducts.get(new Random().nextInt(allProducts.size()));
        String randomProductBrand = randomElem.findElement(By.cssSelector("#searchPage [itemprop=brand]")).getText();
        String randomProductName = randomElem.findElement(By.cssSelector("dd[itemprop=name]")).getText();
        String randomProductPrice = randomElem.findElement(By.cssSelector("dd>span[itemprop=price]")).getText();

        System.out.println("Randomly picked product brand is" + " " + randomProductBrand + ",name is " + " " + randomProductName + " " + "and price is" + " " + randomProductPrice);
        Thread.sleep(5000);
        randomElem.click();
        Thread.sleep(10000);
        String productBrandFromDetailsPage = driver.findElement(By.cssSelector("#overview div span[itemprop=name]")).getText();
        String productNameFromDetailsPage = driver.findElement(By.cssSelector("#overview div span:nth-child(2)")).getText();
        List<WebElement> productPrices = driver.findElements(By.cssSelector("#productRecap div span[aria-hidden=true]"));
        WebElement elem = productPrices.get(1);
        String productPriceFromDetailsPage = elem.getText();
        System.out.println("Price:" + productPriceFromDetailsPage);


        System.out.println("Brand:" + productBrandFromDetailsPage);
        System.out.println("Name:" + productNameFromDetailsPage);


        Assert.assertEquals(productBrandFromDetailsPage, randomProductBrand, "The Brand taken from Details page doesn't match with the Brand of randomly clicked product");
        Assert.assertEquals(productNameFromDetailsPage, randomProductName, "The Name taken from Details page doesn't match with the Name of randomly clicked product ");
        Assert.assertEquals(productPriceFromDetailsPage, randomProductPrice, "The Price taken from Details page doesn't match with the Price of randomly clicked product");


        driver.quit();

    }
}
