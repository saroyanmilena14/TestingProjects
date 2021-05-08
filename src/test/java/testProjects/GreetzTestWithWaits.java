package testProjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

import java.util.Random;

public class GreetzTestWithWaits {
    private WebDriver driver;
    By allProductsElemsLoc = By.xpath("//div[@class='clearfix' and  @infinite-scroll='main.loadNextPage()']//div[@class='b-products-grid__item' and @ng-class]");
    By favouriteButtonElemLoc = By.cssSelector(".b-favourite");
    By myAccountIconElemLoc = By.xpath("//div[@class='page-header__navigation']//i[@data-qa-ref='profile-icon']");
    By listOfFavsElemLoc = By.xpath("//div[@data-qa-ref='humburger-menu']//span[text()='Favorieten']");
    By FavouritesListPageLoc = By.xpath("//div[@class='clearfix']//div[@class='favorite-item']");
    By selectedFavNameElemLoc = By.xpath("//div[@class='page-detail__sidebar']//h1");
    By productsCurrentPriceFromDetailsPage = By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='current-price']");
    By productsNormalPriceFromDetailsPage = By.xpath("//div[@class='page-detail__price']//span[@data-qa-ref='normal-price']");
    By favouriteButtonFromDetailsPage = By.xpath("//div[@class='page-detail__favorite']//div");


    @BeforeSuite
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
    }

    @BeforeClass
    public void GeneralStepsBeforeRunningTests() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get("https://www.greetz.nl");
        By loginElemLoc = By.cssSelector("[data-qa-ref=profile-icon]");
        By inputFieldEmailElemLoc = By.xpath("//div[@class='emailField']//input[@type='email']");
        By loginSteptwoElemLoc = By.xpath("//ul[@class='b-list']//span[text()='Inloggen']");
        By inputFieldPasswordElemLoc = By.xpath("//div[@class='inputfields']//input[@type='password']");
        WebElement loginElem = wait.until(ExpectedConditions.elementToBeClickable(loginElemLoc));
        loginElem.click();
        WebElement loginSteptwoElem = wait.until(ExpectedConditions.elementToBeClickable(loginSteptwoElemLoc));
        loginSteptwoElem.click();
        WebElement inputFieldEmailElem = wait.until(ExpectedConditions.elementToBeClickable(inputFieldEmailElemLoc));
        inputFieldEmailElem.click();
        String email = "milenasaroyan14@gmail.com";
        inputFieldEmailElem.sendKeys(email);
        WebElement inputFieldPassword = wait.until(ExpectedConditions.elementToBeClickable(inputFieldPasswordElemLoc));
        inputFieldPassword.click();
        String password = "johnsmithflowers";
        inputFieldPassword.sendKeys(password);
        WebElement sumbit = driver.findElement(By.cssSelector("#login-cta"));
        sumbit.click();

    }


    @BeforeMethod
    public void openingThaPageWewant() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions actions = new Actions(driver);
        String mainMenuName = "Bloemen";
        By mainMenuElemLoc = By.xpath("//nav[@class='content-menu--items']//a[ text()='" + mainMenuName + "']");
        WebElement mainMenuElem = wait.until(ExpectedConditions.visibilityOfElementLocated(mainMenuElemLoc));
        actions.moveToElement(mainMenuElem).build().perform();
        String subMenuName = "Boeketten";
        By subMenuElemLoc = By.xpath("//div[@class='content-menu-list']//a[text()='" + subMenuName + "']");
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(subMenuElemLoc));
        subMenu.click();

    }


    @Test
    public void CheckingFavouritesPriceAndName() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(allProductsElemsLoc, 40));
        List<WebElement> allBouquets = driver.findElements(allProductsElemsLoc);
        WebElement randomBouquet = allBouquets.get(new Random().nextInt(allBouquets.size()));
        WebElement favouriteButtonElem = wait.until(ExpectedConditions.elementToBeClickable(randomBouquet.findElement(favouriteButtonElemLoc)));
        favouriteButtonElem.click();
        String randomBouquetName = randomBouquet.findElement(By.xpath(".//div[@class='b-products-grid__item-title']")).getText();
        By randomBouquetCurrentPriceElemLoc = By.xpath(".//span[@data-qa-ref='current-price']");
        By randomBouquetNormalPriceElemLoc = By.xpath(".//span[@data-qa-ref='normal-price']");
        WebElement randomBouquetPriceElem;
        try {
            randomBouquetPriceElem = randomBouquet.findElement(randomBouquetCurrentPriceElemLoc);
            wait.until(ExpectedConditions.visibilityOfElementLocated(randomBouquetCurrentPriceElemLoc));

        } catch (NoSuchElementException e) {
            randomBouquetPriceElem = randomBouquet.findElement(randomBouquetNormalPriceElemLoc);
            wait.until(ExpectedConditions.visibilityOfElementLocated(randomBouquetNormalPriceElemLoc));
        }
        String productPriceText = randomBouquetPriceElem.getText();

        WebElement myAccountIconElem = wait.until(ExpectedConditions.elementToBeClickable(myAccountIconElemLoc));
        myAccountIconElem.click();
        WebElement listOfFavsElem = wait.until(ExpectedConditions.elementToBeClickable(listOfFavsElemLoc));
        listOfFavsElem.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(FavouritesListPageLoc, 1));
        List<WebElement> favourites = driver.findElements(FavouritesListPageLoc);
        WebElement lastFavourite = favourites.get(0);
        lastFavourite.click();
        WebElement selectedFavNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedFavNameElemLoc));
        String selectedFavNameElemText = selectedFavNameElem.getText();

        WebElement selectedFavPriceElem;
        try {
            selectedFavPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(productsCurrentPriceFromDetailsPage));

        } catch (ElementNotVisibleException e) {
            selectedFavPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(productsNormalPriceFromDetailsPage));
        }
        String selectedFavouritesPriceText = selectedFavPriceElem.getText().substring(1).replace(",", ".");

        Double doublePriceFav = Double.parseDouble(selectedFavouritesPriceText);
        Double doublePricerandom = Double.parseDouble(productPriceText.replace(",", "."));
        Assert.assertEquals(randomBouquetName, selectedFavNameElemText, "Randomly selected product's name" + " " + randomBouquetName + "" + "doesn't match with the name " + " " + selectedFavNameElemText + ",found from its details page");
        Assert.assertEquals(doublePricerandom, doublePriceFav, "Randomly selected product's price" + " " + randomBouquetPriceElem + "" + "doesn't match with the price " + " " + selectedFavPriceElem + ",found from its details page");

    }

    @Test
    public void CheckingNameAndPriceNewFlows() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(allProductsElemsLoc, 40));
        List<WebElement> allBouquets = driver.findElements(allProductsElemsLoc);
        WebElement randomBouquet = allBouquets.get(new Random().nextInt(allBouquets.size()));
        randomBouquet.click();

        WebElement randomNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedFavNameElemLoc));
        String randomNameElemText = randomNameElem.getText();
        WebElement randomPriceElem;
        try {
            randomPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(productsCurrentPriceFromDetailsPage));

        } catch (ElementNotVisibleException e) {
            randomPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(productsNormalPriceFromDetailsPage));
        }
        String randomsPriceElemText = randomPriceElem.getText();
        WebElement favouriteButton = wait.until(ExpectedConditions.elementToBeClickable(favouriteButtonFromDetailsPage));
        favouriteButton.click();

        WebElement myAccountIconElem = wait.until(ExpectedConditions.elementToBeClickable(myAccountIconElemLoc));
        myAccountIconElem.click();
        WebElement listOfFavouritesElem = wait.until(ExpectedConditions.elementToBeClickable(listOfFavsElemLoc));
        listOfFavouritesElem.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(FavouritesListPageLoc, 1));
        List<WebElement> favourites = driver.findElements(FavouritesListPageLoc);
        WebElement lastFavourite = favourites.get(0);
        lastFavourite.click();

        WebElement selectedFavNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedFavNameElemLoc));
        String selectedFavNameElemText = selectedFavNameElem.getText();

        WebElement selectedFavPriceElem;
        try {
            selectedFavPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(productsCurrentPriceFromDetailsPage));

        } catch (ElementNotVisibleException e) {
            selectedFavPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(productsNormalPriceFromDetailsPage));
        }
        String selectedFavouritesPriceText = selectedFavPriceElem.getText().substring(1).replace(",", ".");
        Double doublePriceFavTest2 = Double.parseDouble(selectedFavouritesPriceText);
        Double doublePricerandomTest2 = Double.parseDouble(randomsPriceElemText.substring(1).replace(",", "."));
        Assert.assertEquals(randomNameElemText, selectedFavNameElemText, "Randomly selected product's name" + " " + randomNameElemText + "" + "doesn't match with the name " + " " + selectedFavNameElemText + ",found from its details page");
        Assert.assertEquals(doublePricerandomTest2, doublePriceFavTest2, "Randomly selected product's price" + " " + randomPriceElem + "" + "doesn't match with the price " + " " + selectedFavPriceElem + ",found from its details page");
    }

    @AfterMethod
    public void UndoFavourite() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement unFavourite = wait.until(ExpectedConditions.elementToBeClickable(favouriteButtonFromDetailsPage));
        unFavourite.click();
    }


    @AfterClass
    public void Logout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement myAccountIcon = wait.until(ExpectedConditions.elementToBeClickable(myAccountIconElemLoc));
        myAccountIcon.click();
        WebElement logoutIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-qa-ref='humburger-menu']//span[text()='Uitloggen']")));
        logoutIcon.click();
        Thread.sleep(1000);
        driver.quit();

    }

}

