package wishlist.infrastructure.controller;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "wishlist.infrastructure.controller",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CucumberTest {
}