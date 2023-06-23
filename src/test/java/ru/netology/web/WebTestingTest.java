package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTestingTest {
    private static WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);}

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver\\win\\chromedriver.exe");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

   @Test
   public void shouldOpenPage() {
       open("http://localhost:9999");
   }

    @Test
    public void shouldSendBankForm() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Бакулина Наталья");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74952592741");
        $(By.cssSelector(".checkbox__box")).click();
        $(By.cssSelector("button")).click();
        $(By.cssSelector(".paragraph")).shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }


    @Test
    public void shouldEnterIncorrectName() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Bakulina Natalia");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74952592741");
        $(By.cssSelector(".checkbox__box")).click();
        $(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void  shouldEnterIncorrectPhone() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Бакулина Наталья");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7 495 259-27-41");
        $(By.cssSelector(".checkbox__box")).click();
        $(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldDoNotClickCheckbox() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Бакулина Наталья");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74952592741");
        $(By.cssSelector(".checkbox__text")).shouldHave(Condition.cssValue("color", "rgba(11, 31, 53, 0.95)"));
    }


}
