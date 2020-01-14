Feature: (REST-API) Fetch users list from user endpoint to verify the response code, response time and users that work
  on a company that ends with Group is equals to 2.
  As a tester
  I want to make sure the respond code is the correct one as well as the execution time is less than 2000ms. Additionally
  I would like to validate the json response for the users contains exactly 2 companies that end with Group word.


  @rest
  Scenario: Fetch users to know if the response time, status code and information retrieved is correct
    Given The endpoint url
    When We fetch the list of users
    Then The status code should be 200
    And The response time should be less than 2000
    And The amount of companies objects that end with Group name is 2