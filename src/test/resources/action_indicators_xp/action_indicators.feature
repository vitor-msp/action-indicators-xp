Feature: action indicators

  Scenario: get action indicators for VALE3
    Given I am in action indicators search page
    When insert "VALE3" in action field
      And insert "01-02-2023" in initial date field
      And insert "01-06-2023" in final date field
      And click in submit button
    Then the fields date, open, high, low, close and rsi should be in main table
      And first table line date should be greater than or equal initial date
      And last table line date should be less than or equal final date
      And the fields medium return, volatility and var should be in top table

  Scenario: receive error for invalid action
    Given I am in action indicators search page 2
    When insert "invalid" in action field 2
    Then an error message should be displayed
      And the main table should not be displayed