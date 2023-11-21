package ru.yandex.praktikum.mainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.orderPage.OrderPage;


public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Логотип Яндекс
    private final By logoYandex = By.xpath(".//*[@alt='Yandex']");
    //Логотип Самокат
    private final By logoSamokat = By.xpath(".//*[@alt='Scooter']");
    //Кнопка "заказать" вверху страницы
    //private final By orderButtonAbove = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private final By orderButtonAbove = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Кнопка "заказать" внизу страницы
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //Окно для заказа
    private final By orderModalCheck = By.xpath(".//*[text()='Для кого самокат']");
    //faq: вопросы
    private final By question = By.xpath(".//div[contains(@id,'accordion__heading')]");
    //faq: ответы
    private final By answer = By.xpath(".//div[contains(@id,'accordion__panel')]/p");
    //всплывающее окно куки
    private final By cookie = By.xpath(".//*[@id='rcc-confirm-button']");
    public void openSite(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    public void cookieSubmit(){
        WebElement cookieElement = driver.findElement(cookie);
        if (cookieElement.isDisplayed()){
            cookieElement.click();
        }
    }
    public String getQuestion(int index){
        WebElement questionElement = driver.findElements(question).get(index);
        return questionElement.getText();
    }
   public String getAnswer(int index){
       WebElement answerElement = driver.findElements(answer).get(index);
       return answerElement.getText();
    }
    public void openQuestionInFaq(int index){
       WebElement questionElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(question));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        WebElement questionElementWithIndex = driver.findElements(question).get(index);
        new WebDriverWait(driver,3).until(ExpectedConditions.elementToBeClickable(questionElementWithIndex));
        questionElementWithIndex.click();
    }

    public void clickOrderButtonAbove(){
        WebElement orderButtonElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(orderButtonAbove));
        orderButtonElement.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(orderModalCheck));
    }
    public void clickOrderButtonBottom(){
        WebElement orderButtonElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(orderButtonBottom));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderButtonElement);
        WebElement orderButtonElementToClick = new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
        orderButtonElementToClick.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(orderModalCheck));
    }
}
