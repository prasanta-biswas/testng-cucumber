package cucumber.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by prasantabiswas on 28 May 2017 12:09 AM.
 */

public class FA
{
    @Given("^G&A$")
    public void faGiven()
    {
        System.out.println("Given FA");
    }
    @When("^G<A$")
    public void faWhen()
    {
        System.out.println("When FA");
    }
    @Then("^T>A$")
    public void faThen()
    {
        System.out.println("Then FA");
    }

    @Given("^N&A$")
    public void faGiven2()
    {
        System.out.println("Given2 FA");
    }
    @When("^O<A$")
    public void faWhen2()
    {
        System.out.println("When2 FA");
    }
    @Then("^Q>A$")
    public void faThen2()
    {
        System.out.println("Then2 FA");
    }
}
