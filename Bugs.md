# Bugs

## ID: 1

**Description:**
Delete a post shows a 200 response, however, no response body with a message is displayed in order to let the user know the deletion was successful.
Additionally, when we fetch the post id deleted, the endpoint retrieves it, when it should not exist.

**Preconditions:**
 
**Severity:**
Critical


**Steps:**
| 1- Go to postman, enter the following url -> http://jsonplaceholder.typicode.com/posts/1 |
| 2- In "Headers" section enter -> "Accept -> application/json" |
| 3- Select "Delete" method, click on "Send" button. |
| 4- Modify the method to use from "Delete" to "Get". |
| 5- Hit on "Send" button. |

**Actual Result**
|Step 3 -> 200 is retrieved as response. No json body is displayed. |
|Step 5 -> Post is retrieved |

**Expected behavior**
| Step 3 -> Json body message should be displayed indicating the post has been deleted. |
| Step 5 -> Post should not be retrieved, 404 status code with a proper message should be displayed |



## ID: 2

**Description:**
When a new post is created, the response retrieves the new post id created, when we fetch the new post, it does not exist.
It means the create post endpoint is not working.

**Preconditions:**
 
**Severity:**
Critical


**Steps:**
| 1- Go to postman, enter the following url -> http://jsonplaceholder.typicode.com/posts |
| 2- In "Headers" section enter -> "Accept -> application/json" |
| 3- In the "Body" enter the following json: |
```shell
{
  "userId": 4545,
  "id": 101,
  "title": "jason campos",
  "body": "some information"
}
```
 |4- Select "Post" method, click on "Send" button. |
| 5- Modify the method to use from "Post" to "Get". Add to the url the post id retrieved -> http://jsonplaceholder.typicode.com/posts/101 |
| 6- Hit on "Send" button. |

**Actual Result**
| Step 6 -> 404 is retrieved. No error message is displayed in the json response body |

**Expected behavior**
| Step 6 -> Post created information should be retrieved. |


## ID: 3

**Description:**
When a post is updated using the put option, it retrieves an id that belongs to the old one when it should be the new one.

**Preconditions:**
 
**Severity:**
Critical


**Steps:**
| 1- Go to postman, enter the following url -> http://jsonplaceholder.typicode.com/posts/1 |
| 2- In "Headers" section enter -> "Accept -> application/json" |
| 3- In the "Body" enter the following json: |
```shell
{
   "userId": 1252,
  "id": 1125,
  "title": "test",
  "body": "test"
}
```
| 4- Select "Put" method, click on "Send" button. |
| 5- Modify the method to use from "Put" to "Get". Add to the url the post id used in the json -> http://jsonplaceholder.typicode.com/posts/1125 |
| 6- Hit on "Send" button. |

**Actual Result**
| Step 4 -> The id retrieved belongs to the old post id |
| Step 6 -> 404 is retrieved |

**Expected behavior**
| Step 4 -> Post id retrieved should be the new one. |
| Step 6 -> Post should be retrieved with the new information |