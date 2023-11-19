package ru.yandex.praktikum.mainPage;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Логотип Яндекс
    private By logoYandex = By.xpath(".//*[@alt='Yandex']");
    //Логотип Самокат
    private By logoSamokat = By.xpath(".//*[@alt='Scooter']");
    //Кнопка "заказать" вверху страницы
    private By topOrder = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //Кнопка "заказать" внизу страницы
    private By bottomOrder = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //Кнопка статус заказа
    private By orderStatus = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Статус заказа']");
    //faq: вопросы
    private final By question = By.xpath(".//div[contains(@id,'accordion__heading')]");
    //faq: ответы
    private final By answer = By.xpath(".//div[contains(@id,'accordion__panel')]/p");
    public String getQuestion(int index){
        WebElement questionElement = driver.findElements(question).get(index);
        return questionElement.getText();
    }
   public String getAnswer(int index){
       WebElement answerElement = driver.findElements(answer).get(index);
       return answerElement.getText();
    }
    public void openQuestionInFaq(int index){

       // WebElement questionElementToScroll = driver.findElement(question);
       WebElement questionElement = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(question));
        //WebElement questionElement = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(question)).get(index);

        //new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(questionElement));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        WebElement questionElementWithIndex = driver.findElements(question).get(index);
        //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionElementToScroll);
        questionElementWithIndex.click();

    }
}
