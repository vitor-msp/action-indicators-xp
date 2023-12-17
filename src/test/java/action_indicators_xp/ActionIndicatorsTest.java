package action_indicators_xp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ActionIndicatorsTest {
    @Given("I am in action indicators search page")
    public void scenario1Given() {
    }

    @When("insert {string} in action field")
    public void scenario1When(String action) {
    }

    @And("insert {string} in initial date field")
    public void when(String initialDate) {
    }

    @And("insert {string} in final date field")
    public void scenario1WhenAnd1(String finalDate) {
    }

    @And("click in submit button")
    public void scenario1WhenAnd2() {
    }

    @Then("the fields date, open, high, low, close and rsi should be in main table")
    public void scenario1Then() {
    }

    @And("first table line date should be greater than or equal initial date")
    public void scenario1ThenAnd1() {
    }

    @And("last table line date should be less than or equal final date")
    public void scenario1ThenAnd2() {
    }

    @And("the fields medium return, volatility and var should be in top table")
    public void scenario1ThenAnd3() {
    }

    @Given("I am in action indicators search page 2")
    public void scenario2Given(){
    }

    @When("insert {string} in action field 2")
    public void scenario2When(String action){
    }

    @Then("an error message should be displayed")
    public void scenario2Then(){
    }

    @And("the main table should not be displayed")
    public void scenario2ThenAnd1(){
    }
}
