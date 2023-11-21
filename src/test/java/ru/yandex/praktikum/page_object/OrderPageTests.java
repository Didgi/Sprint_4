package ru.yandex.praktikum.page_object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        objOrderPage.makeOrder("Тест", "Тестович", "Москва", "89151231122", "трое суток", "серый", "привет, курьер");
    }

    @Test
    public void makeOrderViaOrderButtonBottomShouldBeCreatedWithPositiveData() {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        objMainPage.openSite();
        objMainPage.cookieSubmit();
        objMainPage.clickOrderButtonBottom();
        objOrderPage.makeOrderAnotherWay("Тест", "Второй", "Москва, ул. Большая, д.3, кв. 25", "Измайловск", "+79151239988", "27.11.2023", "сутки", "чёрный", "позвоните заранее");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
