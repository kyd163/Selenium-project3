package ru.course.at.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.course.at.pages.MainPage;
import ru.course.at.pages.ResultsPage;

import java.time.Duration;

public class ListTest {
    private WebDriver driver;
    MainPage mp;
    ResultsPage rp;
    String input = "Selenium";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://duckduckgo.com/");
        mp = new MainPage(driver);
        rp = new ResultsPage(driver);
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
    }

    public void searchInput() {
        String input = "Selenium";
        mp.sendText(input);
    }

    public void expectationsDownloadPage() {
        rp.waitDownloadPage(driver, input);
    }

    public void clickFirstSeleniumResult() {
        rp.clickSeleniumResults();
        rp.checkUrl(driver);
    }
}
