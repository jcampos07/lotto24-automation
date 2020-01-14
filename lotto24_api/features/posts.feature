Feature: (REST-API) Verify the posts endpoint retrieves the right responses when fetching, deleting, updating,
  creating posts in the jsonplaceholder
  As a tester
  I want to make sure the posts endpoint works as expected when fetching, deleting, updating, creating posts, for
  each request we should get the right status code and a proper message indicating the request has been executed successfully

  @rest
  Scenario: Fetch all the posts the jsonplaceholder api has available
    Given The headers the jsonplaceholder needs
    When The posts endpoint is consumed
    Then The status code is equals to "200"
    And The json response has 100 posts

  @rest
  Scenario: Create a new post in the jsonplaceholder api
    Given The headers the jsonplaceholder needs
    When A new post is created
    Then The status code is equals to "201"
    And The json response should retrieved the post id created

  @rest
  Scenario Outline: Delete a post
    Given The headers the jsonplaceholder needs
    Examples: posts
      | post_id |
      | 23      |
      | 28      |
      | 75      |
    When The post id = <post_id> is sent
    Then The status code is equals to "200"
    And The json response has a proper message indicating the post has been deleted <post_id>

  @rest
  Scenario Outline: Fetch a specific post
    Given The headers the jsonplaceholder needs
    When The post id = <post_id> is fetched
    Examples: posts
      | post_id |
      | 23      |
      | 7       |
      | 88      |
      | 101     |
    Then The status code is equals to "200"
    And The post information is retrieved successfully <post_id>


  @rest
  Scenario: Update the whole information for a post
    Given The headers the jsonplaceholder needs
    When The whole information for the post id = "1" is updated
    Then The status code is equals to "200"
    And The new post id should be retrieved