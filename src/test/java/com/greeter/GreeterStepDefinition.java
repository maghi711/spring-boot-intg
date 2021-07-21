package com.greeter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GreeterStepDefinition {
    String name;

    @Given("^given the name of the person (.*)$")
    public void personsName(String name) throws Throwable {
        System.out.println("Provided name is " + name);
        this.name = name;
    }

    @When("^if its morning$")
    public void weHaveAUsualPassenger() {
    }

    @Then("^greet him with morning salutation$")
    public void youCanAddAndRemoveHimFromAnEconomyFlight() {
        Assert.assertEquals("", "Good Morning Ramesh", "Good Morning " + name);
    }
}
