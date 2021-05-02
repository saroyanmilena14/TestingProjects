package testProjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

import java.util.Random;

public class GreetzTest {
    private WebDriver driver;

    @BeforeSuite
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
    }

    @BeforeClass
    public void GeneralStepsBeforeRunningTests() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl");
        Thread.sleep(2000);
        WebElement login = driver.findElement(By.cssSelector("[data-qa-ref=profile-icon]"));
        login.click();
        WebElement loginSteptwo = driver.findElement(By.xpath("//ul[@class='b-list']//span[text()='Inloggen']"));
        loginSteptwo.click();
        Thread.sleep(2000);
        WebElement inputFieldEmail = driver.findElement(By.xpath("//div[@class='emailField']//input[@type='email']"));
        inputFieldEmail.click();
        String email = "milenasaroyan14@gmail.com";
        inputFieldEmail.sendKeys(email);
        WebElement inputFieldPassword = driver.findElement(By.xpath("//div[@class='inputfields']//input[@type='password']"));
        inputFieldPassword.click();
        String password = "johnsmithflowers";
        inputFieldPassword.sendKeys(password);
        WebElement sumbit = driver.findElement(By.cssSelector("#login-cta"));
        sumbit.click();
        Thread.sleep(2000);
    }

    //  Thread.sleep(2000);
    @BeforeMethod
    public void openingThaPageWewant() throws InterruptedException {
        Actions actions = new Actions(driver);
        String mainMenuName = "Bloemen";
        WebElement mainMenu = driver.findElement(By.xpath("//nav[@class='content-menu--items']//a[ text()='" + mainMenuName + "']"));
        actions.moveToElement(mainMenu).build().perform();
        Thread.sleep(5000);
        String subMenuName = "Boeketten";
        WebElement subMenu = driver.findElement(By.xpath("//div[@class='content-menu-list']//a[text()='" + subMenuName + "']"));
        subMenu.click();
        Thread.sleep(3000);

    }


    @Test
    public void CheckingFavouritesPriceAndName() throws InterruptedException {
        List<WebElement> allBouquets = driver.findElements(By.xpath("//div[@class='clearfix' and  @infinite-scroll='main.loadNextPage()']//div[@class='b-products-grid__item']"));
        WebElement randomBouquet = allBouquets.get(new Random().nextInt(allBouquets.size()));
        Thread.sleep(5000);
        WebElement favouriteButton = randomBouquet.findElement(By.cssSelector(".b-favourite"));

        favouriteButton.click();
        String randomBouquetName = randomBouquet.findElement(By.xpath(".//div[@class='b-products-grid__item-title']")).getText();
        Thread.sleep(3000);
        try {
            String randomBouquetPrice = randomBouquet.findElement(By.xpath(".//span[@data-qa-ref='current-price']")).getText();

        } catch (NoSuchElementException e) {
            System.out.println("No element was found by xpath(.//span[@data-qa-ref=current-price']");
        }
        String randomBouquetPrice = randomBouquet.findElement(By.xpath(".//span[@data-qa-ref='normal-price']")).getText();

        WebElement myAccountIcon = driver.findElement(By.xpath("//div[@class='page-header__navigation']//i[@data-qa-ref='profile-icon']"));
        myAccountIcon.click();
        WebElement listOfFavouritesIcon = driver.findElement(By.xpath("//div[@data-qa-ref='humburger-menu']//span[text()='Favorieten']"));
        listOfFavouritesIcon.click();
        Thread.sleep(2000);
        List<WebElement> favourites = driver.findElements(By.xpath("//div[@class='clearfix']//div[@class='favorite-item']"));
        WebElement lastFavourite = favourites.get(0);
        lastFavourite.click();
        Thread.sleep(5000);
        String selectedFavouritesName = driver.findElement(By.xpath("//div[@class='page-detail__sidebar']//h1")).getText();
        Thread.sleep(2000);
        try {
            String selectedFavouritesPrice = driver.findElement(By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='current-price']")).getText();

        } catch (NoSuchElementException e) {
            System.out.println("No element was found by xpath(//span[@data-qa-ref=current-price']");
        }
        String selectedFavouritesPrice = driver.findElement(By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='normal-price']")).getText();
        String selectedFavPriceForCust = selectedFavouritesPrice.substring(1).replace(",", ".");
        Double doublePriceFav = Double.parseDouble(selectedFavPriceForCust);
        Double doublePricerandom = Double.parseDouble(randomBouquetPrice.replace(",", "."));
        Assert.assertEquals(randomBouquetName, selectedFavouritesName, "Randomly selected product's name" + " " + randomBouquetName + "" + "doesn't match with the name " + " " + selectedFavouritesName + ",found from its details page");
        Assert.assertEquals(doublePricerandom, doublePriceFav, "Randomly selected product's price" + " " + randomBouquetPrice + "" + "doesn't match with the price " + " " + selectedFavouritesPrice + ",found from its details page");
        WebElement unFavourite = driver.findElement(By.xpath("//div[@class='page-detail__favorite']//div"));
        unFavourite.click();
    }

    @Test
    public void CheckingNameAndPriceNewFlows() throws InterruptedException {
        List<WebElement> allBouquetstest2 = driver.findElements(By.xpath("//div[@class='clearfix' and  @infinite-scroll='main.loadNextPage()']//div[@class='b-products-grid__item']"));
        WebElement randomBouquetTest2 = allBouquetstest2.get(new Random().nextInt(allBouquetstest2.size()));
        randomBouquetTest2.click();
        Thread.sleep(2000);
        String randomsNameTest2= driver.findElement(By.xpath("//div[@class='page-detail__sidebar']//h1")).getText();
        try {
            String randomsPriceTest2 = driver.findElement(By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='current-price']")).getText();

        } catch (NoSuchElementException e) {
            System.out.println("No element was found by xpath(//span[@data-qa-ref=current-price']");
        }
        String randomsPriceTest2 = driver.findElement(By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='normal-price']")).getText();
        WebElement favouriteButton= driver.findElement(By.xpath("//div[@class='page-detail__favorite']//div"));
        favouriteButton.click();
        Thread.sleep(2000);
        WebElement myAccountIconTest2 = driver.findElement(By.xpath("//div[@class='page-header__navigation']//i[@data-qa-ref='profile-icon']"));
        myAccountIconTest2.click();
        WebElement listOfFavouritesIconTest2 = driver.findElement(By.xpath("//div[@data-qa-ref='humburger-menu']//span[text()='Favorieten']"));
        listOfFavouritesIconTest2.click();
        Thread.sleep(2000);
        List<WebElement> favouritesTest2 = driver.findElements(By.xpath("//div[@class='clearfix']//div[@class='favorite-item']"));
        WebElement lastFavourite = favouritesTest2.get(0);
        lastFavourite.click();
        Thread.sleep(2000);
        String selectedNameTest2 = driver.findElement(By.xpath("//div[@class='page-detail__sidebar']//h1")).getText();
        try {
            String selectedPriceTest2 = driver.findElement(By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='current-price']")).getText();

        } catch (NoSuchElementException e) {
            System.out.println("No element was found by xpath(//span[@data-qa-ref=current-price']");
        }
        String selectedPricetest2 = driver.findElement(By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='normal-price']")).getText();
        String selectedPriceTest2Cust = selectedPricetest2.substring(1).replace(",", ".");
        Double doublePriceFavTest2 = Double.parseDouble(selectedPriceTest2Cust);
        Double doublePricerandomTest2 = Double.parseDouble(randomsPriceTest2.substring(1).replace(",", "."));
        Assert.assertEquals(randomsNameTest2, selectedNameTest2, "Randomly selected product's name" + " " + randomsNameTest2 + "" + "doesn't match with the name " + " " + selectedNameTest2 + ",found from its details page");
        Assert.assertEquals(doublePricerandomTest2, doublePriceFavTest2, "Randomly selected product's price" + " " + randomsPriceTest2 + "" + "doesn't match with the price " + " " + selectedPricetest2+ ",found from its details page");
        WebElement unFavouriteTest2 = driver.findElement(By.xpath("//div[@class='page-detail__favorite']//div"));
        unFavouriteTest2.click();


    }

    @AfterClass
    public void Logout() throws InterruptedException {
        WebElement myAccountIcon = driver.findElement(By.xpath("//div[@class='page-header__navigation']//i[@data-qa-ref='profile-icon']"));
        myAccountIcon.click();
        WebElement logoutIcon=driver.findElement(By.xpath("//div[@data-qa-ref='humburger-menu']//span[text()='Uitloggen']"));
        logoutIcon.click();
        Thread.sleep(1000);
        driver.quit();

    }

}

