package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



    public class MainPage {
        private final WebDriver driver;


        //локатор в шапке сайта кнопки заказать
        private final By orderButton = By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']");

       private final By orderButtonLower =  By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
        //локатор   кнопки заказать внизу

        private final By cookieButton = By.id("rcc-confirm-button");
        //кнопка куки


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
            driver.get("https://qa-scooter.praktikum-services.ru/");
        }

        public void clickOrderButton(String buttonType) {
            if (buttonType.equals("верхняя")) {
                driver.findElement(orderButton).click();
            } else {
                driver.findElement(orderButtonLower).click();
            }
        }
    public String getAnswerText(String panelId) {
        return driver.findElement(By.id(panelId)).getText();
    }
}



