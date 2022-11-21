package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CareersPage {
    private final WebDriver driver;

    By searchBoxXpath = By.xpath("(//input[@placeholder='Search job title or location'])[2]");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchTextInSearchBox_PressEnter(String jobTitle) {
        driver.findElement(searchBoxXpath).sendKeys(jobTitle);
        driver.findElement(searchBoxXpath).sendKeys(Keys.ENTER);
    }

    public void selectPositionFromSearchedResults(String jobTitle) {
        driver.findElement(By.xpath("(//div[@class='job-title']/span[text()='" + jobTitle + " '])[1]")).click();
    }
}
