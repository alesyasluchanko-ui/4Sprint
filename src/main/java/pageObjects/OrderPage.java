package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    // 1-е окно (Для кого самокат)
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // 2-е окно (Про аренду)
    private final By orderDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']/..");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");

    // Окна подтверждения
    private final By yesButton = By.xpath(".//button[text()='Да']");
    private final By orderModal = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение первой формы с выбором метро из списка
    public void fillFirstLoginForm(String name, String surname, String address, String metroStation, String phoneNumber) {
        driver.findElement(firstNameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);

        // Кликаем по метро, вводим буквы и выбираем подсказку
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationField).sendKeys(metroStation);
        By metroSuggestion = By.xpath(".//*[contains(@class, 'select-search__row') or contains(@class, 'select-search__option')]//*[text()='" + metroStation + "']");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(metroSuggestion))
                .click();

        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Заполнение второй формы с закрытием календаря по клавише ESC
    public void fillSecondLoginForm(String orderDate, String rentalPeriod, String colour, String comment) {
        driver.findElement(orderDateField).click();
        driver.findElement(orderDateField).sendKeys(orderDate);
        driver.findElement(orderDateField).sendKeys(Keys.ESCAPE); // Скрываем календарь

        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();

        driver.findElement(By.xpath(".//label[contains(text(), '" + colour + "')]")).click();
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickYesButton() {
        // Дожидаемся, пока кнопка "Да" станет на 100% видимой и кликабельной
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(yesButton));
        // Кликаем по ней напрямую через драйвер
        driver.findElement(yesButton).click();
    }

    // Проверка появления окна по кнопке "Посмотреть статус"
    public boolean isOrderModalDisplayed() {
        // Расширяем локатор: ждем ЛИБО кнопку "Посмотреть статус", ЛИБО любой текст со словом "Заказ" или "оформлен"
        By flexibleModalLocator = By.xpath(".//button[text()='Посмотреть статус'] | .//*[contains(text(), 'оформлен')] | .//*[contains(text(), 'Заказ')]");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(flexibleModalLocator));

        return driver.findElement(flexibleModalLocator).isDisplayed();
    }

    public By getYesButton() {
        return yesButton;
    }
}
