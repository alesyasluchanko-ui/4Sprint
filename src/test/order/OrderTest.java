package order;

import pageObjects.DriverFactory;
import pageObjects.MainPage;
import pageObjects.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String orderDate;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;
    private final String buttonType;

    public OrderTest(String name, String surname, String address, String metroStation, String phoneNumber, String orderDate, String rentalPeriod, String colour, String comment, String buttonType) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
        this.buttonType = buttonType;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                // Изменены даты на будущие, убраны плюсы из номеров, заменена "ё" на "е" в названии цвета
                {"Алеся", "Случанко", "Москва, ул. Тверская 14", "Пушкинская", "375292221133", "12.10.2026", "сутки", "чёрный жемчуг", "Нет комментариев", "верхняя"},
                {"Никита", "Случанко", "Москва, ул. Русаковская 8", "Сокольники", "79686143737", "15.10.2026", "сутки", "серая безысходность", "позвонить заранее", "нижняя"},
        };
    }

    @Before
    public void setup() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Logger.getLogger(org.openqa.selenium.devtools.CdpVersionFinder.class.getName()).setLevel(Level.OFF);
        driver = DriverFactory.getDriver();
    }

    @Test
    public void orderSamokat() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickCookieButton();
        mainPage.clickOrderButton(buttonType);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstLoginForm(name, surname, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillSecondLoginForm(orderDate, rentalPeriod, colour, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();

        assertTrue("Окно успешного оформления заказа не появилось!", orderPage.isOrderModalDisplayed());
    }

    @After
    public void quit() {
        driver.quit();
    }
}
