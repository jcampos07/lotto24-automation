# Scenario 1

**Test Case** id: 
01

**Title:**
Verify wikipedia search retrieves information
 
**Module:** 
Search

**Type:**
Functional

**Priority:**
High

**Preconditions:**

**Description:**
Verify the search on wikipedia works as expected, it should suggest to customer when they type a wrong search, when they open a article, a table of content should be displayed, each title in this table should have a section in the article

| Steps: | Expected result |
| --- | --- |
| 1- Go to "https://wikipedia.org" in "Search field" enter "furry rabbits", click on search button | "Did you mean fury rabbit" link should be displayed. List of results should be retrieved  |
| 2- Click on "Fury rabbit" link. | Verify 20 results are displayed in the search |
| 3- Click on the first article. | Verify the article has a title. Additionally, make sure a table of contents is displayed. Each title in the table of contents should have a section in the article. |
