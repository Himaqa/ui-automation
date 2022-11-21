package com.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PositionDetailsPage {
    private WebDriver driver;

    By jobTitle = By.cssSelector("h1.job-title");
    By jobId = By.className("jobId");
    By jobDescription = By.cssSelector("div[data-ph-at-id='jobdescription-text']>p:nth-of-type(4)");

    public PositionDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getJobTitle() {
        return driver.findElement(jobTitle).getText();
    }

    public String getJobId() {
        return driver.findElement(jobId).getText().substring(9, 16);
    }

    public String getJobLocation() {
        WebElement jobLocationElement = driver.findElement(By.xpath("(//span[text()='Location'])[1]/.."));
        return ((JavascriptExecutor) driver).executeScript("return arguments[0].lastChild.textContent;", jobLocationElement).toString().trim();
    }

    public String getFirstSentenceOfJobDescription() {

        String jobDescriptionText = driver.findElement(jobDescription).getText();
        String[] split = StringUtils.split(jobDescriptionText, ".");
        return split[0];
    }

    public String getSecondBulletUnderUnderManagementSupportDescription() {
        return driver.findElement(By.cssSelector("div[data-ph-at-id='jobdescription-text']>ul:nth-of-type(3)>li:nth-of-type(2)")).getText();
    }

    public String getThirdRequirementDescription() {
        return driver.findElement(By.cssSelector("div[data-ph-at-id='jobdescription-text']>ul:nth-of-type(5)>li:nth-of-type(3)")).getText();
    }
    public void clickApply(){
        driver.findElement(By.cssSelector(".Sub-Actions>a[ph-tevent='apply_click']")).click();
    }
}
