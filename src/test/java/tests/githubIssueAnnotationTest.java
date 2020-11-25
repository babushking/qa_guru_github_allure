package tests;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class githubIssueAnnotationTest {
    private final static String REPOSITORY = "Babosso/qa_guru_github_allure";
    private final static String USER = "Babosso";
    private final static int ISSUE_NUMBER = 1;

    @Test
    @DisplayName("Наш любимый тест с аннотацией")
    @Feature("Issues")
    @Story("User should see issues in existing repository")
    public void searchForIssue(){
        final BaseSteps steps = new BaseSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepository(REPOSITORY);
        steps.goToIssue();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
    public static class  BaseSteps{

        @Step("Открываем главную страницу")
        public void openMainPage() {
            open("https://github.com");
        }

        @Step("Ищем репозиторий ${name}")
        public void searchForRepository(final String name) {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(name);
            $(".header-search-input").submit();
        }

        @Step("Переходим в  репозиторий ${name}")
        public void goToRepository(final String name) {
            $(By.linkText(name)).click();
        }

        @Step("Переходим в  раздел Issue")
        public void goToIssue() {
            $(withText("Issues")).click();
        }

        @Step("Проверяем наличие Issue с номером ${number}")
        public void shouldSeeIssueWithNumber(final int number) {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        }
    }
}
