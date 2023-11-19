package ru.yandex.praktikum.mainpage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;
import ru.yandex.praktikum.mainPage.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class MainPageTest {
    private WebDriver driver;
    private final int index;
    private final String expectedQuestion;
    private final String expectedAnswer;
    public MainPageTest(int index, String expectedQuestion, String expectedAnswer) {

        this.index = index;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }
    @Parameterized.Parameters // (name = "{index}, question={1}, answer={2}")
    public static Object [][] data(){
        return new Object [][] {
                {0, "Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {3, "Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {7, "Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    // Закомментировано, т.к. через менеджер chrome всё равно не запускается из-за различий в версиях, а сдавать необходимо на нём.
    // На firefox всё проверено.
    // Запускал на локальном хроме. Оставил инициализацию под него.
  /* @Before
    public void startUpChrome() {
       WebDriverManager.chromedriver().setup();
       //driver = new ChromeDriver();
   }
   public void startUpFirefox() {
      WebDriverManager.firefoxdriver().setup();
    } */
    @Test
        public void checkQuestionsAnswersInFaq(){
        //chrome
       // startUpChrome();
       driver = new ChromeDriver();
       //firefox
       // startUpFirefox();
       // driver = new FirefoxDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openQuestionInFaq(index);
        Assert.assertEquals(expectedQuestion,objMainPage.getQuestion(index));
        Assert.assertEquals(expectedAnswer,objMainPage.getAnswer(index));

        }

    @After
    public void tearDown(){
        driver.quit();
    }
}
