Feature: Service

As an external agent
In order to user the service
I want to see if it is online

  Scenario: service is up

    When I request to the actuator

    Then the status is up