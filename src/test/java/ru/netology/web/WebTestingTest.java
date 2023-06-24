package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTestingTest {

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
        $(By.cssSelector("[data-test-id='order-success']")).shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        ;

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
    public void shouldSendWithoutName() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74952592741");
        $(By.cssSelector(".checkbox__box")).click();
        $(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldEnterIncorrectPhone() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Бакулина Наталья");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7 495 259-27-41");
        $(By.cssSelector(".checkbox__box")).click();
        $(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldEnterЦшерщгеPhone() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Бакулина Наталья");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        $(By.cssSelector(".checkbox__box")).click();
        $(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldDoNotClickCheckbox() {
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id=name] input")).sendKeys("Бакулина Наталья");
        $(By.cssSelector("[data-test-id=phone] input")).sendKeys("+74952592741");
        $(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=agreement].input_invalid")).shouldBe(Condition.visible).shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }


}
