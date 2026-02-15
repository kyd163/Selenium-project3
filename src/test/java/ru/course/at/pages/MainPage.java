package ru.course.at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public MainPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[id=\"searchbox_input\"]")
    private WebElement seacrhField;

    public void sendText(String text){
        seacrhField.sendKeys(text);
        seacrhField.submit();
    }

}
