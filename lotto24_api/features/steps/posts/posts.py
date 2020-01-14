from behave import given, when, then
from nose.tools import assert_false

from config import request
from features.steps.posts import posts_functions
from utils import constants


@given('The headers the jsonplaceholder needs')
def step_impl(context):
    """
    Prepare the url we are going to use for the test
    :param context:
    :return:
    """
    context.url = "https://jsonplaceholder.typicode.com/posts"


@when('The posts endpoint is consumed')
def step_impl(context):
    """
    Consume the endpoint to get all the posts the endpoint has
    :param context: Context
    :return:
    """
    context.response = request.get(context.url, "")


@then('The status code is equals to "{expected_status_code}"')
def step_impl(context, expected_status_code):
    """
    Verify the status code is 200, 201, etc according to the {expected_status_code} it gets as parameter
    :param context: Context(self)
    :param expected_status_code
    :return:
    """
    assert context.response.status_code == int(expected_status_code), "Response code is different: %s" % \
                                                                      context.response.status_code


@then('The json response has 100 posts')
def step_impl(context):
    """
    Count the amount of results is equals to 100 which is the expected one
    :param context: Context(self)
    :return:
    """
    assert len(context.response.json()) == 100, "Posts endpoint did not show 100 results as expected"


@when('A new post is created')
def step_impl(context):
    """
    Send the new post to create
    :param context: Context
    :return:
    """
    context.payload = constants.NEW_POST_INFORMATION
    context.response = request.post(context.url, context.payload, "")


@then('The json response should retrieved the post id created')
def step_impl(context):
    """
    Verify if the json response retrieved has the post id as response body
    :param context: Context(self)
    :return:
    """
    assert context.response.json()[
               "id"] == 101, "Post was not created, post id response body was not retrieved: Body %s" % \
                             context.response.json()


@when('The post id = {post_id} is fetched')
def step_impl(context, post_id):
    """
    Consume the endpoint to get a specific post
    :param context: Context
    :param post_id
    :return:
    """
    context.response = request.get(context.url + "/%s" % post_id, "")


@then('The post information is retrieved successfully {post_id}')
def step_impl(context, post_id):
    """
    Verify if the json response has the right format and if the id belongs to the one we requested
    :param context: Context(self)
    :param post_id
    :return:
    """
    assert_false(posts_functions.is_post_id_in_deleted_file(post_id),
                 "Post was retrieved when it was deleted in previous test")
    wrong_data = posts_functions.verify_post_information_format(context.response.json())
    assert len(wrong_data) == 0, "Some information in the json response is not the expected : %s" % wrong_data
    assert str(context.response.json()[
                   "id"]) == post_id, "Post id is not the expected one, expected: %s" % post_id + " found:" \
                                                                                                  "% s" % \
                                      context.response.json()["id"]


@when('The post id = {post_id} is sent')
def step_impl(context, post_id):
    """
    Send the delete request with the post id to delete
    :param context: Context
    :param post_id
    :return:
    """
    context.response = request.delete(context.url + "/%s" % post_id, "")


@then('The json response has a proper message indicating the post has been deleted {post_id}')
def step_impl(context, post_id):
    """
    Verify a proper message is retrieved to the user after deleting a post
    :param context: Context(self)
    :param post_id
    :return:
    """
    posts_functions.save_post_deleted(post_id)
    assert len(context.response.json()) > 0, "Json response body did not show a message indicating the post was deleted"


@when('The whole information for the post id = "{post_id}" is updated')
def step_impl(context, post_id):
    """
    Send the put request to update the whole post
    :param context: Context
    :param post_id
    :return:
    """
    context.payload = constants.POST_INFORMATION_TO_UPDATE
    context.response = request.put(context.url + "/%s" % post_id, context.payload)


@then('The new post id should be retrieved')
def step_impl(context):
    """
    Verify the new post id is retrieved as response
    :param context: Context(self)
    :return:
    """
    new_post_id = posts_functions.get_new_post_id()
    assert context.response.json()[
               "id"] == new_post_id, "The post was not updated, the id retrieved is different to the expected one. " \
                                     "Expected: %s " % new_post_id + " found: %s " % context.response.json()["id"]
