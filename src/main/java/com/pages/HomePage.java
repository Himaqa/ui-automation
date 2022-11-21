package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    By careersLinkButton = By.xpath("(//a[@href='https://careers.labcorp.com/global/en'])[1]");
    By labCorpLogo = By.cssSelector("img[src='/themes/custom/labcorp/images/newbrand/Labcorp_Logo.svg']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickCareersLink() {
        driver.findElement(careersLinkButton).click();
    }

    public boolean isLabCorpImageDisplayed() {
        return driver.findElement(labCorpLogo).isDisplayed();
    }
}
