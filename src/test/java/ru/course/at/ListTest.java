package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {

        String input = "Selenium.dev";
        String searchurl = "https://www.selenium.dev/";
        WebElement seacrhField = driver.findElement(By.cssSelector("input[class=\"sb_form_q\"]"));
        seacrhField.sendKeys(input);

        WebElement buttonField = driver.findElement(By.cssSelector("svg[class=\"search_svg\"]"));
        buttonField.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(By.cssSelector("h2 > a[href]"), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector("h2 > a[href]"))
        ));
        List results = driver.findElements(By.cssSelector("h2 > a[href]"));

        assertEquals(searchurl, driver.getCurrentUrl(), "Открылась не верная вкладка");

        List<WebElement> result = driver.findElements(By.cssSelector("a[class=\"Selenium\"]"));
        clickElement(result, 0);
        driver.getCurrentUrl();

    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(0).click();
    }
}