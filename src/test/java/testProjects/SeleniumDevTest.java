package testProjects;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.List;


public class SeleniumDevTest {


    @Test
    public void testSelenium() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev");
        WebElement downloadsButton = driver.findElement(By.xpath("//nav[@id='navbar']//a[text()='Downloads']"));
        downloadsButton.click();
        Thread.sleep(1000);
        String versionExpected = "3.141.59";
        String versionActual = driver.findElement(By.xpath("//div[@class='right']/p[1]/a")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(versionExpected, versionActual);
        WebElement searchButton = driver.findElement(By.cssSelector("input[name=search]"));
        String expectedWord = "selenium";
        searchButton.sendKeys(expectedWord + Keys.ENTER);
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='gsc-resultsbox-visible']//div[@class='gs-title']"));

        for (WebElement elem : searchResults) {
            String searchResultTitle = elem.getText();
            softAssert.assertTrue(searchResultTitle.toLowerCase().contains(expectedWord.toLowerCase()), "This title doesn't contain the word " + expectedWord);
        }
        driver.quit();
        softAssert.assertAll();


    }
}
