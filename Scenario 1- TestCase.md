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
When users search information in wikipedia page and they have a typo in the system, the page should show a "did you mean" option with some matches, after they click on the link, the list of articles should be displayed, when they opened a article the table of contents should be displayed.

| Steps: | Expected result |
| --- | --- |
| 1- Go to "https://wikipedia.org" in "Search field" enter "furry rabbits", click on search button | "Did you mean fury rabbit" link should be displayed. List of results should be retrieved  |
| 2- Click on "Fury rabbit" link. | Verify 20 results are displayed in the search |
| 3- Click on the first article. | Verify the article has a title. Additionally, make sure a table of contents is displayed. Each title in the table of contents should have a section in the article. |
