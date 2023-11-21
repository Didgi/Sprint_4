package ru.yandex.praktikum.orderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.mainPage.MainPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.WebDriver;

public class OrderPageTest {
    private WebDriver driver;

    @Before
    /* public void startUpChrome() {
       // WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
    }*/
    public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void makeOrderViaOrderButtonAboveShouldBeCreatedWithPositiveData() {
        //driver = new ChromeDriver();
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        objMainPage.openSite();
        objMainPage.cookieSubmit();
        objMainPage.clickOrderButtonAbove();
        objOrderPage.makeOrder("Тест", "Тестович", "Москва", "89151231122", "трое суток", "серый", "приветики");
    }

    @Test
    public void makeOrderViaOrderButtonBottomShouldBeCreatedWithPositiveData() {
        //driver = new ChromeDriver();
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        objMainPage.openSite();
        objMainPage.cookieSubmit();
        objMainPage.clickOrderButtonBottom();
        objOrderPage.makeOrderAnotherWay("Тест", "Второй", "Москва, ул. Большая, д.3, кв. 25", "Измайловск", "+79151239988", "25.11.2023", "сутки", "чёрный", "ты - молодец");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
