package ru.yandex.praktikum.pageobject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class MainPageFaqTests {
    private WebDriver driver;
    private final int index;
    private final String expectedQuestion;
    private final String expectedAnswer;

    public MainPageFaqTests(int index, String expectedQuestion, String expectedAnswer) {

        this.index = index;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Before
    public void startUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    /*public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    } */

    @Test
    public void checkQuestionsAnswersInFaqShouldEquals() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openSite();
        objMainPage.cookieSubmit();
        objMainPage.openQuestionInFaq(index);
        Assert.assertEquals(expectedQuestion, objMainPage.getQuestion(index));
        Assert.assertEquals(expectedAnswer, objMainPage.getAnswer(index));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
