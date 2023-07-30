package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.delivery.data.DataGenerator.generateDate;

class DeliveryTest {
    @BeforeAll
    static void setUpAll () {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll () {
        SelenideLogger.removeListener("allure");
    }
    @Test
    void manualInputTestFormPlanAndRePlanMeting () {

        var validUser = DataGenerator.Registration.generateUser("ru");

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $(".calendar-input .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        $(".calendar-input .input__control").setValue(generateDate(4));
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button .button__text").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + generateDate(4))).shouldBe(Condition.visible);
        $(".calendar-input .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        $(".calendar-input .input__control").setValue(generateDate(7));
        $(".button .button__text").click();
        $("[data-test-id= 'replan-notification']").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible);
        $("[data-test-id= 'replan-notification'] .button").click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + generateDate(7))).shouldBe(Condition.visible);





    }
}
