Feature: the deposit can be done
  Scenario: client makes a deposit
    When bank client wants to save money
    Then make a deposit in my account of 100
    And the client gets balance of 100
  Scenario: client makes a withdrawal
    When bank client wants to retrieve some or all of his savings
    Then make a withdrawal from my account of 30
    And the client gets balance of 70
  Scenario: client checks his operations (no operations)
    When bank client wants to check his operations
    Then wants to see the history (operation, date, amount, balance) of my operations
    And the client gets response DATE | OPERATION | AMOUNT | BALANCE