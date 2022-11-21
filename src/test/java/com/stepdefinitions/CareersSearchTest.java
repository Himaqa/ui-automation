package com.stepdefinitions;

import com.pages.CareersPage;
import com.pages.HomePage;
import com.pages.PositionDetailsPage;
import com.pages.WorkDayPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;


public class CareersSearchTest {
    private WebDriver driver;
    private final String EXPECTED_JOB_DESCRIPTION = "The right candidate for this role will participate in the test automation technology development and best practice models";
    private final String JOB_TITLE = "QA Automation Developer";
    private final String careersUrl = "https://careers.labcorp.com/global/en";
    private final String JOB_ID = "2263932";
    private final String JOB_LOCATION = "Durham, North Carolina, United States of America";
    private HomePage homePage;
    private CareersPage careersPage;
    private PositionDetailsPage positionDetailsPage;
    private WorkDayPage workDayPage;


    @Before
    public void browserSetUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        workDayPage = new WorkDayPage(driver);
        positionDetailsPage = new PositionDetailsPage(driver);
    }

    @Given("^Open LabCorp website$")
    public void open_LabCorpWebsite() {
        driver.navigate().to("https://www.labcorp.com/");
        homePage.isLabCorpImageDisplayed();
        driver.manage().window().maximize();
    }


    @When("^User clicks LabCorp careers link and go to careers page$")
    public void userClicksCareersLink_goTo_CareersPage() {
        homePage.clickCareersLink();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String actualCurrentPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualCurrentPageUrl, careersUrl);
        waitForVisibilityByXpath("//span[text()='Get tailored job recommendations based on your interests.']", 20);
    }

    @And("^User enters search text in careers search box$")
    public void userEnters_searchText_inCareerSearchBox() {
        careersPage.enterSearchTextInSearchBox_PressEnter(JOB_TITLE);
        waitForVisibilityByCssSelector("span.result-count", 20);
    }

    @And("^User selects and browse the position from searched results$")
    public void selectAndBrowse_Position_fromSearchedResults() {
        careersPage.selectPositionFromSearchedResults(JOB_TITLE);
        waitForVisibilityByXpath("//ppc-content[contains(text(),'Labcorp Employee? Apply Here')]", 10);
    }

    @Then("^Validate Job title$")
    public void validate_jobTitle() {
        String actualJobTitle = positionDetailsPage.getJobTitle();
        Assert.assertEquals(actualJobTitle, JOB_TITLE);
    }

    @Then("^Validate Job Id$")
    public void validate_jobId() {
        String actualJobId = positionDetailsPage.getJobId();
        Assert.assertEquals(actualJobId, JOB_ID);
    }

    @Then("^Validate Job Location$")
    public void validate_jobLocation() {
        String actualJobLocation = positionDetailsPage.getJobLocation();
        Assert.assertEquals(actualJobLocation, JOB_LOCATION);
    }

    @Then("^Validate first sentence of Job Description$")
    public void validate_firstSentenceOfJobDescription() {
        String actualFirstSentenceOfJobDescription = positionDetailsPage.getFirstSentenceOfJobDescription();
        Assert.assertEquals(actualFirstSentenceOfJobDescription, EXPECTED_JOB_DESCRIPTION);
    }

    @Then("^Validate second bullet point under Management Support Description$")
    public void validate_secondBulletPointUnderManagementSupportDescription() {
        String actualSecondBulletPointUnderManagementSupportDescription = positionDetailsPage.getSecondBulletUnderUnderManagementSupportDescription();
        Assert.assertEquals(actualSecondBulletPointUnderManagementSupportDescription, "Prepare test plans, budgets, and schedules.");
    }

    @Then("^Validate third Requirement Description$")
    public void validate_thirdRequirementDescription() {
        String actualThirdRequirementDescription = positionDetailsPage.getThirdRequirementDescription();
        Assert.assertEquals(actualThirdRequirementDescription, "5+ years of experience in QA automation development and scripting.");
    }

    @When("^User clicks Apply Now$")
    public void Apply_now() {
        positionDetailsPage.clickApply();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        waitForVisibilityByCssSelector("div[aria-label='Sign In']", 20);
    }

    @And("^Enter (.*) and (.*) to Login to WorkDay site$")
    public void login_to_workdayWebsite(String username, String password) {
        workDayPage.loginValidUser(username, password);
        waitForVisibilityByCssSelector("div>h3", 20);
    }

    @Then("^Validate job title in workday/apply page$")
    public void Assert_job_title() {
        String workDayJobTitle = workDayPage.getJobTitleFromWorkDaySite();
        Assert.assertEquals(workDayJobTitle, JOB_TITLE);
    }

    @Then("^User returns to Careers Job search page$")
    public void returnTo_job_search() {
        workDayPage.clickCareersHome();
        waitForVisibilityByXpath("//span[text()='Get tailored job recommendations based on your interests.']", 10);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public void waitForVisibilityByXpath(String xpath, int secs) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitForVisibilityByCssSelector(String cssSelector, int secs) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }
}
