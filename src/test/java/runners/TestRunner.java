package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    plugin = {
        "html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/report.json",
        "rerun:target/failed_scenarios.txt"
    }
)
public class TestRunner {
} 