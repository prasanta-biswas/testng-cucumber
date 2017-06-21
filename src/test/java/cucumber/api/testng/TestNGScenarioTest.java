package cucumber.api.testng;

import cucumber.api.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by prasantabiswas on 27 May 2017 11:52 PM.
 */
@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/resources/cucumber/runtime/testng",
        glue = "cucumber.stepdefinition",
        plugin = {"pretty", "html:target/cucumber-html-report" },
        tags={"@FA-test,@FA-test2"})
public class TestNGScenarioTest
{
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setupCucumberRunner()
    {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void finishCucumberRunner()
    {
        this.testNGCucumberRunner.finish();
    }

    @Test(dataProvider = "scenarios",description = "Run each scenarios as testng test",groups = {"cucumber"})
    public void runScenario(CucumberTagStatementWrapper cucumberTagStatementWrapper) throws Throwable
    {
        this.testNGCucumberRunner.runCucumberScenario(cucumberTagStatementWrapper.getCucumberScenario());
    }

    @DataProvider(name = "scenarios")
    public Object[][] getScenarios()
    {
        return this.testNGCucumberRunner.provideScenarios();
    }

    @Test(dataProvider = "scenariosWithFeature",description = "Run each scenarios as testng test",groups = {"cucumber"})
    public void runScenarioWithFeature(CucumberTagStatementWrapper cucumberTagStatementWrapper) throws Throwable
    {
        System.out.println("Cucumber Feature: "+cucumberTagStatementWrapper.getCucumberFeature());
        this.testNGCucumberRunner.runCucumberScenario(cucumberTagStatementWrapper.getCucumberScenario());
    }

    @DataProvider(name = "scenariosWithFeature")
    public Object[][] getScenariosWithFeature()
    {
        return this.testNGCucumberRunner.provideScenariosWithFeature();
    }
}
