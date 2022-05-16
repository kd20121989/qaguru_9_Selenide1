package com.google.kd20121989;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ContributorsTest {

    @Test
    void solntsevFirstContriburot() {
        Configuration.holdBrowserOpen = false;

        open("https://github.com/selenide/selenide");
        $(".Layout-sidebar h2").$(byText("Contributors")).ancestor("div")
                .$("ul li").hover();
        $(".Popover-message").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Andrei Soslntsev"));

    }
}
