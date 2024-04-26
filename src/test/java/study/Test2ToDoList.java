package study;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;

public class Test2ToDoList {
    @Test
    public void userCanSearch() {
        String link = "https://webdriveruniversity.com/To-Do-List/index.html";
        String newTask = "Water the flowers";

        //открываем сайт
        open(link);

        //добавляем новую таску
        $(By.cssSelector("input[type='text']")).val(newTask).pressEnter();

        //проверяем, что таска добавлена в список
        ElementsCollection tasks = $$("ul li");
        boolean isTaskFound = false;

        for (SelenideElement task : tasks) {
            if (task.text().equals(newTask)) {
                isTaskFound = true;
                break;
            }
        }
        assertTrue(isTaskFound, "Task not found: " + newTask);

        //фокусируемся на элементе и жмем удалить
        SelenideElement addedTask = $(By.cssSelector("ul li:last-child"));
        SelenideElement trashButton = addedTask.$(By.className("fa-trash"));

        actions().moveToElement(addedTask).perform();
        trashButton.click();
        sleep(2000);

        //проверяем, что элемента нет в списке
        boolean isTaskNotFound = true;
        for (SelenideElement task : tasks) {
            if (task.text().equals(newTask)) {
                isTaskNotFound = false;
                break;
            }
        }
        assertTrue(isTaskNotFound, "Task found: " + newTask);

        closeWebDriver();
    }
}
