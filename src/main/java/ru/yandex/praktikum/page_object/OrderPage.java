package ru.yandex.praktikum.page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.Support.Support;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {

        this.driver = driver;
    }

    //Имя клиента
    private final By clientName = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    //Фамилия клиента
    private final By clientSurname = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    //Адрес доставки
    private final By clientAddress = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    //Метро
    private final By clientMetro = By.xpath(".//input[contains(@placeholder, 'метро')]");
    //Список станций метро
    private final By listMetroStation = By.xpath(".//*[@role='menuitem']/button");
    //Номер клиента
    private final By clientNumber = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //Кнопка "далее"
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");
    //Дата для заказа
    private final By arriveDate = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    //Шаблон для выбора даты через датапикер
    private final String arriveDateOption = ".//*[contains(@aria-label,'";
    //Срок аренды
    private final By rentalPeriod = By.xpath(".//*[contains(text(),'Срок аренды')]");
    //Срок аренды: опции
    private final By rentalPeriodOptions = By.xpath(".//*[@role='option']");
    //Срок аренды: шаблон для выбора опции
    private final String rentalOption = ".//*[@role='option' and text()='";
    //Цвет самоката: чёрный
    private final By blackSamokat = By.xpath(".//label/input[@id='black']");
    //Цвет самоката: серый
    private final By greySamokat = By.xpath(".//label/input[@id='grey']");
    //Комментарий
    private final By commentForDelivery = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");
    //Кнопка "заказать" в заказе
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Заголовок модального окна для подтверждения заказа
    private final By confirmModal = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    //Кнопка "да" для подтверждения заказа
    private final By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Заголовок модального окна с успешным выполнением заказа
    private final By successOrderModal = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    private final By checkOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");

    public void setName(String name) {
        driver.findElement(clientName).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(clientSurname).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(clientAddress).sendKeys(address);
    }

    public void chooseMetroStationByScroll() {
        driver.findElement(clientMetro).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listMetroStation));
        int amountStations = driver.findElements(listMetroStation).size();
        int randomNumberOfStation = (int) (Math.random() * amountStations - 1);
        WebElement stationToSelect = driver.findElements(listMetroStation).get(randomNumberOfStation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationToSelect);
        stationToSelect.click();
    }

    public void setMetroStationByKeyboard(String metroName) {
        driver.findElement(clientMetro).click();
        driver.findElement(clientMetro).sendKeys(metroName);
        driver.findElement(clientMetro).sendKeys(Keys.DOWN);
        driver.findElement(clientMetro).sendKeys(Keys.ENTER);
    }
    public void setNumber(String number) {
        driver.findElement(clientNumber).sendKeys(number);
    }
    public void setArriveDateByDatePicker() {
        driver.findElement(arriveDate).click();
        WebElement elementToBeClicked = driver.findElement(By.xpath(arriveDateOption + Support.getNextDayDate() + "')]"));
        elementToBeClicked.click();
    }

    public void setArriveDateByManual(String date) {
        WebElement dateElement = driver.findElement(arriveDate);
        dateElement.sendKeys(date);
        dateElement.sendKeys(Keys.ENTER);
    }

    public void chooseRentalPeriod(String periodOption) {
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentalPeriodOptions));
        String rentalOptionValue = rentalOption + periodOption + "']";
        WebElement periodElement = driver.findElement(By.xpath(rentalOptionValue));
        periodElement.click();
    }

    public void chooseSamokatColor(String color) {
        if (color.equals("чёрный")) {
            driver.findElement(blackSamokat).click();
        }
        if (color.equals("серый")) {
            driver.findElement(greySamokat).click();
        }
    }
    public void setCommentForDelivery(String comment) {
        driver.findElement(commentForDelivery).sendKeys(comment);
    }

    public void finishOrder() {
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(confirmModal));
        driver.findElement(yesButton).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(successOrderModal));
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(arriveDate));
    }

    public void makeOrder(String name, String surname, String address, String number, String periodOption, String color, String comment) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        chooseMetroStationByScroll();
        setNumber(number);
        clickNextButton();
        setArriveDateByDatePicker();
        chooseRentalPeriod(periodOption);
        chooseSamokatColor(color);
        setCommentForDelivery(comment);
        finishOrder();
    }

    public void makeOrderAnotherWay(String name, String surname, String address, String station, String number, String date, String periodOption, String color, String comment) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStationByKeyboard(station);
        setNumber(number);
        clickNextButton();
        setArriveDateByManual(date);
        chooseRentalPeriod(periodOption);
        chooseSamokatColor(color);
        setCommentForDelivery(comment);
        finishOrder();
    }
}
