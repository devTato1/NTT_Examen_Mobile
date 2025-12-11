package com.test.mobile.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.test.mobile.glue", "com.test.mobile.hooks", "com.test.mobile.step"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@Regresion"
)
public class cucumberRunner {
}