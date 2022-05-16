package com.google.kd20121989;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Owner("kd20121989")
public class HomeworkSelenide {
    private final String repoName = "selenide";
    private final String repoEndpoint = repoName + "/" + repoName;
    private final String softAssertions = "SoftAssertions";
    private final String dragAndDropPage = "https://the-internet.herokuapp.com/drag_and_drop";

    private final SelenideElement headerSearch = $("input.header-search-input");
    private final SelenideElement repositoryResults = $("ul.repo-list");
    private final SelenideElement firstRepoFound = repositoryResults.$("li");
    private final SelenideElement firstRepoFoundLink = firstRepoFound.$("a");
    private final SelenideElement wikiTab = $("a#wiki-tab");
    private final SelenideElement wikiPagesSidebar = $("div.js-wiki-sidebar-toggle-display");
    private final SelenideElement showMorePagesButton = $("button.js-wiki-more-pages-link");
    private final ElementsCollection wikiPages = wikiPagesSidebar.$$("ul li");
    private final SelenideElement wikiBody = $("div#wiki-body");
    private final ElementsCollection examplesHeaders = wikiBody.$$("h4");
    private final SelenideElement rectangleA = $("div#column-a");
    private final SelenideElement rectangleB = $("div#column-b");

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://github.com/";
    }

    @Test
    @DisplayName("Checking that SoftAssertions can be opened from Wiki and have JUnit5 code example")
    void findSelenideAsFirstRepository() {
        step("Open Selenide repo", () -> {
            open(repoEndpoint);
        });
        step("Open Wiki tab", () -> {
            wikiTab.click();
        });
        step("Looking for SoftAssertions and opening it", () -> {
            showMorePagesButton.click();
            wikiPages.findBy(Condition.text(softAssertions)).click();
        });
        step("Checking that JUnit5 among listed examples", () -> {
            examplesHeaders.findBy(Condition.textCaseSensitive("JUnit5"))
                    .shouldHave(Condition.textCaseSensitive("JUnit5"));
        });
    }

    @Test
    @DisplayName("Drag&Drop with Selenide.actions()")
    @Disabled("For some reason Selenide.actions().dragAndDrop not working")
    void dragAndDropSelenideActions() {
        step("Open Drag And Drop page", () -> {
            open(dragAndDropPage);
        });
        step("Assert rectangles in correct position", () -> {
            rectangleA.shouldHave(Condition.text("A"));
            rectangleB.shouldHave(Condition.text("B"));
        });
        step("Drag and Drop rectangle A to rectangle B", () -> {
            Selenide.actions().dragAndDrop(rectangleA, rectangleB);
        });
        step("Assert that drag and drop was successful", () -> {
            rectangleA.shouldHave(Condition.text("B"));
            rectangleB.shouldHave(Condition.text("A"));
        });
    }

    @Test
    @DisplayName("Drag&Drop A to B")
    void dragAndDropAtoB() {
        step("Open Drag And Drop page", () -> {
            open(dragAndDropPage);
        });
        step("Assert rectangles in correct position", () -> {
            rectangleA.shouldHave(Condition.text("A"));
            rectangleB.shouldHave(Condition.text("B"));
        });
        step("Drag and Drop rectangle A to rectangle B", () -> {
            rectangleA.dragAndDropTo(rectangleB);
        });
        step("Assert that drag and drop was successful", () -> {
            rectangleA.shouldHave(Condition.text("B"));
            rectangleB.shouldHave(Condition.text("A"));
        });
    }
}
