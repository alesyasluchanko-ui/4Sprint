package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // Добавили импорт настроек для Chrome
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equals("firefox")) {
            return new FirefoxDriver();
        } else {

            ChromeOptions options = new ChromeOptions();

            // Защита от блокировок модальных окон в кастомных сборках браузера
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");

            // Запуск драйвера с созданными настройками
            return new ChromeDriver(options);

        }
    }
}