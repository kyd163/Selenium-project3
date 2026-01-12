package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.get("https://habr.com/ru/feed/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Отображение статьи Компании")
    public void articles() {
        WebElement articlesButton = driver.findElement(By.cssSelector("a[class=footer-menu__item-link]"));
        articlesButton.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), Компании)]")).isDisplayed(),
                "Статья Компании не найдена");
    }

    @Test
    @DisplayName("Изменение иконки Бургера, на Крестик")
    public void burger(){
        WebElement burgerButton = driver.findElement(By.cssSelector("button.tm-header__button"));
        burgerButton.click();

        assertTrue(driver.findElement(By.cssSelector("button[aria-expanded=true]")).isDisplayed(),
                "Кнопка Крестика не найдена");
    }

}

