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
    		 "html:target/cucumber-reports.html" 
     },tags = "@Checkout and @CT05"
 )
 public class Runner {
 }