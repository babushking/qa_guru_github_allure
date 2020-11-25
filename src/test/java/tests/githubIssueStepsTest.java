package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class githubIssueStepsTest {
    private final static String REPOSITORY = "Babosso/qa_guru_github_allure";
    private final static String USER = "Babosso";
    private final static String PASSWORD = "";
    private final static int ISSUE_NUMBER = 1;

    @Test
    public void searchForIssueSteps() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Проходим авторизацию", () -> {
            $("a[href='/login']").click();
            $("#login_field").sendKeys(USER);
            $("#password").sendKeys(PASSWORD);
            $(".btn.btn-primary.btn-block").click();
        });

        step("Открываем репозиторий" + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переходим в раздел Issues", () -> {
            $("span[data-content='Issues']").click();
        });

        step("Проверяем наличие Issue c номером" + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        });

        step("Создаем New Issue", () -> {
            $("a.btn.btn-primary").click();
            $("#issue_title").setValue("Issue");
            $("#issue_body").setValue("My first Issue");
            $(withText("Assignees")).click();
            $$(".js-username").find(text("Babosso")).click();
            $(withText("Assignees")).click();
            $(withText("Labels")).click();
            $$(".name").find(text("bug")).click();
            $(withText("Labels")).click();
            $$(".btn.btn-primary").find(text("Submit new issue")).click();
        });
    }
}
