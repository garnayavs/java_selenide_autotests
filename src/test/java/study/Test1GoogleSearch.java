package study;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;
public class Test1GoogleSearch {
    @Test
    public void userCanSearch() {
        open("https://www.google.com/");

        $(By.name("q")).val("Selenide").pressEnter();
        $(By.id("search")).shouldBe(exist);

        closeWebDriver();
    }
}
