package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WorkDayPage {

    private WebDriver driver;
    By userName = By.cssSelector("input[data-automation-id='email']");
    By pwd = By.cssSelector("input[type='password']");
    By careersNavigation = By.cssSelector("button[data-automation-id='navigationItem-Careers Home']");

    By signInButton = By.cssSelector("div[aria-label='Sign In']");

    public WorkDayPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String username) {
        driver.findElement(userName).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(pwd).sendKeys(password);
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void loginValidUser(String username, String password) {
        driver.findElement(userName).sendKeys(username);
        driver.findElement(pwd).sendKeys(password);
        driver.findElement(signInButton).click();
    }

    public String getJobTitleFromWorkDaySite() {
        return driver.findElement(By.cssSelector("div>h3")).getText();
    }

    public void clickCareersHome() {
        driver.findElement(careersNavigation).click();
    }
}
