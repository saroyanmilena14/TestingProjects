package SixPm;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FiltringProductsInSixPmTest {
    @Test
    public void testPricesFiltring() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.6pm.com");
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        String menuName = "Accessories";
        WebElement menuAccessories = driver.findElement(By.xpath("//a[text()='" + menuName + "']"));
        actions.moveToElement(menuAccessories).build().perform();
        Thread.sleep(2000);
        String subMenu="Sunglasses";
        WebElement sunglasses = driver.findElement(By.xpath("//a[text()='" + subMenu+ "']"));
        actions.moveToElement(sunglasses);
        sunglasses.click();
        Thread.sleep(3000);
        String priceCriteria = "$25.00 and Under";
        WebElement pricesUnderXXX$ = driver.findElement(By.xpath("//ul[@aria-labelledby='priceFacet']//li//*[contains(text(),'" + priceCriteria + "')]"));
        pricesUnderXXX$.click();
        Thread.sleep(2000);
        List<WebElement> pricesSunglasses = driver.findElements(By.cssSelector("#searchPage article span[itemprop=price]"));

        for (WebElement elem : pricesSunglasses) {
            String price = elem.getText();
            String priceWithout$ = price.substring(1);
            String priceUnderXXX$Without$ = pricesUnderXXX$.getText().substring(1, 7);
            Assert.assertTrue(Double.parseDouble(priceWithout$) <= Double.parseDouble(priceUnderXXX$Without$), "The price $" + priceWithout$ + "isn't equal or under $" + priceUnderXXX$Without$);
        }
        driver.quit();

    }
}

