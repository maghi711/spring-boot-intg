Feature: Greeting feature
  For a given name, we need to provide a greeting

  Scenario Outline: Greet a person
    Given given the name of the person <name>
    When if its morning
    Then greet him with morning salutation
    Examples:
      | name     |
      | Ramesh   |
      | Simon   |
      | Dominic   |