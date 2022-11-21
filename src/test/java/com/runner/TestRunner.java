package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
                 features="src/main/resources/Features/LabcorpSearch.feature",
                 glue={"com.stepdefinitions"},
                  plugin = {"pretty",
                           "html:target/HtmlReports/report.html","json:target/JsonReports/report.json"},
                  tags = "@Smoke"
)

public class TestRunner extends AbstractTestNGCucumberTests {


}
