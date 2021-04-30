package testProjects;

import jdk.jfr.Timespan;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class testingChrome {

    @Test

    public void TestingYoutubeButton() throws InterruptedException {

        System.setProperty( "webdriver.chrome.driver","C:\\Users\\Milena\\IdeaProjects\\MavennProject\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://youtube.com");
        Thread.sleep(2000);
        driver.findElement(By.id("buttons")).click();
        Thread.sleep(2000);
        driver.quit();







    }
}
