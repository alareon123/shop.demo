import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "pretty", "json:target/cucumber-report/report.json"},
        features = "src/test/resources/features",
        glue = "com.demo.test.steps"
)
public class CucumberRunner {
}
