package ru.yandex.praktikum.pageobject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class OrderPageTests {
    private WebDriver driver;

    @Before
     public void startUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
     /* public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    } */

    @Test
    public void makeOrderViaOrderButtonAboveShouldBeCreatedWithPositiveData() {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        objMainPage.openSite();
        objMainPage.cookieSubmit();
        objMainPage.clickOrderButtonAbove();
        boolean isOrderSuccess = objOrderPage.makeOrder("Тест", "Тестович", "Москва", "89151231122", "трое суток", "серый", "привет, курьер");
        Assert.assertTrue(isOrderSuccess);

    }

    @Test
    public void makeOrderViaOrderButtonBottomShouldBeCreatedWithPositiveData() {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        objMainPage.openSite();
        objMainPage.cookieSubmit();
        objMainPage.clickOrderButtonBottom();
        boolean isOrderSuccess = objOrderPage.makeOrderAnotherWay("Тест", "Второй", "Москва, ул. Большая, д.3, кв. 25", "Измайловск", "+79151239988", "27.11.2023", "сутки", "чёрный", "позвоните заранее");
        Assert.assertTrue(isOrderSuccess);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
