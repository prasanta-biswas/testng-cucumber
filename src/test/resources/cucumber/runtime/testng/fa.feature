Feature: FA
  # Some characters that need HTML escaping
  @FA-test
  Scenario: SA
    Given G&A
    When G<A
    Then T>A
  @FA-test2
  Scenario: PA
    Given N&A
    When O<A
    Then Q>A