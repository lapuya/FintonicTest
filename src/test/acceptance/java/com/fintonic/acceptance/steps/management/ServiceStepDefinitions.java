package com.fintonic.acceptance.steps.management;

import com.fintonic.acceptance.api.ServiceApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ServiceStepDefinitions {

  private final ServiceApi serviceApi;

  public ServiceStepDefinitions(ServiceApi serviceApi) {
    this.serviceApi = serviceApi;
  }

  @When("I request to the actuator")
  public void i_request_to_the_actuator() {
    serviceApi.getStatus();
  }

  @Then("the status is up")
  public void the_status_is_up() {
  }
}
