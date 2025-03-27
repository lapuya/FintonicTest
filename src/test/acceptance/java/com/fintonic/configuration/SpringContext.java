package com.fintonic.configuration;


import io.cucumber.java.After;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringContext {

  @Autowired
  private ScenarioContext context;


  @After
  public void tearDown() {
    context.clear();
  }

}