package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "stepDefs",
        plugin = {"pretty", "html:target/cucumber-reports"
        ,"com.epam.reportportal.cucumber.ScenarioReporter"}
)

public class TestRunner {

}
