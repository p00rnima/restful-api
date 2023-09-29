package com.restful.runners.api;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin = {"pretty", "json:build/cucumber/cucumber-json-report-restful-api-test.json"},
        monochrome = true,
        tags = "@restful-api",
        features = "src/test/resources/feature_files",
        glue = {""})
@Test
public class RestfulApiTestRunner extends AbstractTestNGCucumberTests {
}
