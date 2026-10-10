package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// Добавлены импорты для реализации ожидания внутри страницы
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriver driver;
    //локатор в шапке сайта кнопки заказать
    private final By orderButton = By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']");
    //локатор кнопки заказать внизу
    private final By orderButtonLower = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
    //кнопка куки
    private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void clickQuestionButton(String questionId) {
        driver.findElement(By.id(questionId)).click();
    }

    public void openPage() {
        driver.get(BASE_URL);
    }

    public void clickOrderButton(String buttonType) {
        if (buttonType.equals("верхняя")) {
            driver.findElement(orderButton).click();
        } else {
            driver.findElement(orderButtonLower).click();
        }
    }


    public String getAnswerText(String questionId, String panelId) {
        // Кликаем по раскрывающемуся списку с вопросом
        clickQuestionButton(questionId);

        // Ждем до 10 секунд, пока элемент ответа станет видимым на странице
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(panelId)));

        // Получаем и возвращаем итоговый текст
        return driver.findElement(By.id(panelId)).getText();
    }
}