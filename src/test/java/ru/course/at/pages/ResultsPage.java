package ru.course.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultsPage {
    String locator = "a[target=_self]";
    String seleniumUrl = "https://www.selenium.dev/";

    @FindBy(css = "a[target=_self]")
    private WebElement results;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void waitDownloadPage(WebDriver driver, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until (ExpectedConditions.and(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(locator), text),
                ExpectedConditions.elementToBeClickable(By.cssSelector(locator))));
    }
    public void clickSeleniumResults(WebDriver driver){
        List<WebElement> results = driver.findElements(By.cssSelector(locator));
        clickListElement(results,0);
    }
    public void clickListElement(List<WebElement> results,int num){
        results.get(num).click();
        System.out.println("Произведён клик по" + num);
    }
    public void checkUrl(WebDriver driver){
        String url = driver.getCurrentUrl();
        assertEquals(seleniumUrl,url,"Открылась нужная вкладка");
    }
}