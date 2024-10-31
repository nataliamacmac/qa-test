 package runner;

 import org.junit.runner.RunWith;
 import io.cucumber.junit.Cucumber;
 import io.cucumber.junit.CucumberOptions;
 
 import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

 @RunWith(Cucumber.class)
 @CucumberOptions(
     snippets = CAMELCASE,
     publish = false,
     stepNotifications = false,
     useFileNameCompatibleName = false,
     features = "src/test/resources/features",
     glue = "steps",
     plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "message:target/cucumber-reports/cucumber.ndjson"
    },tags = "@Checkout"
 )
 public class Runner {
 }