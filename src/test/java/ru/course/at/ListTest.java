package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
        driver.get("https://duckduckgo.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        searchInput();
        expectationsDownloadPage();
        clickFirstSeleniumResult();
        checkSeleniumUrl();
    }

    public void searchInput() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("input[id=\"searchbox_input\"]"));
        searchField.sendKeys(input);
        searchField.submit();
    }

    public void expectationsDownloadPage() {
        String locator = "a[target=_self]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(locator), "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector(locator))
        ));
    }

    public void clickFirstSeleniumResult() {
        String locator = "a[target=_self]";
        List<WebElement> results = driver.findElements(By.cssSelector(locator));
        clickElement(results, 0);
    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(num).click();
        System.out.println("Произведён клик по" + num);
    }

    public void checkSeleniumUrl() {
        String seleniumUrl = "https://www.selenium.dev/";
        String url = driver.getCurrentUrl();
        assertEquals(seleniumUrl, url, "Открылась нужная вкладка");
    }
}
