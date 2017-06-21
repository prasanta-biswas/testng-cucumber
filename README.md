## testng-cucumber
The original cucumber-jvm supports running each feature as a testng test.Testng-Cucumber has an added support for running each scenario as a separate testng.


#### Examples

- To run cucumber feature as a separate testng test case we can use the following approach
```java
@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/resources/cucumber/runtime/testng",
        glue = "cucumber.stepdefinition",
        plugin = {"pretty", "html:target/cucumber-html-report" },
        tags={"@FA-test,@FA-test2"})//features to run - can also specify at run time with -Dcucumber.options
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

    @Test(dataProvider = "features",description = "Run each feature as testng test",groups = {"cucumber"})
    public void feature(CucumberFeatureWrapper cucumberFeature) 
    {
            this.testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider(name = "features")
    public Object[][] getFeatures()
    {
        return this.testNGCucumberRunner.provideFeatures();//this method provides all the features
    }
}
```
###### The @Test method will get all the features from the data provider and run them as a separate testng test

- If we want to run each scenario of every feature as a separate testng test case which is basically the case,
we can also do this here. Here is the example

```java
@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/resources/cucumber/runtime/testng",
        glue = "cucumber.stepdefinition",
        plugin = {"pretty", "html:target/cucumber-html-report" },
        tags={"@FA-test,@FA-test2"})//features to run - can also specify at run time with -Dcucumber.options
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
        return this.testNGCucumberRunner.provideScenarios();//this method provides scenarios of all the features
    }
}
```
###### The above example illustrates how we can provide scenarios as a data provider to the @Test method and run them

- Now from the above example, we will not be able to know which scenario belongs to which feature. We also have a solution to that.
Here is the example

```java
@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/resources/cucumber/runtime/testng",
        glue = "cucumber.stepdefinition",
        plugin = {"pretty", "html:target/cucumber-html-report" },
        tags={"@FA-test,@FA-test2"})//features to run - can also specify at run time with -Dcucumber.options
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

    @Test(dataProvider = "scenariosWithFeature",description = "Run each scenarios as testng test with feature info.",groups = {"cucumber"})
    public void runScenario(CucumberTagStatementWrapper cucumberTagStatementWrapper) throws Throwable
    {
        System.out.println("Cucumber Feature: "+cucumberTagStatementWrapper.getCucumberFeature()); //This displays the feature name when a scenario is run
        this.testNGCucumberRunner.runCucumberScenario(cucumberTagStatementWrapper.getCucumberScenario());
    }

    @DataProvider(name = "scenariosWithFeature")
    public Object[][] getScenariosWithFeature()
    {
        return this.testNGCucumberRunner.provideScenariosWithFeature();//this method provides scenarios of all the features
    }
}
```
###### So in the above example we will able to know which scenario belongs to which feature