package com.google.kd20121989;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Owner("kd20121989")
public class SelenideRepositoryTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void findSelenideAsFirstRepository() {
        open("https://github.com");
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        $$(".repo-list li ").first().$("a").click();
        $("h2").shouldHave(Condition.text("selenide / selenide"));

    }


}
