from behave import given, when, then

# START FETCH USERS
from config import request
from features.steps.users import users_functions


@given('The endpoint url')
def step_impl(context):
    """
    Prepare the url we are going to use for the test
    :param context:
    :return:
    """
    context.url = "https://jsonplaceholder.typicode.com/users"


@when('We fetch the list of users')
def step_impl(context):
    """
    Consume the endpoint to get a list of users
    :param context: Context
    :return:
    """
    context.response = request.get(context.url, "")


@then('The status code should be 200')
def step_impl(context):
    """
    Verify the status code is 200
    :param context: Context(self)
    :return:
    """
    assert context.response.status_code == 200, "Response code is different: %s" % context.response.status_code


@then('The response time should be less than 2000')
def step_impl(context):
    """
    Verify the response time is less than 2000 ms
    :param context: Context(self)
    :return:
    """
    assert context.response.elapsed.total_seconds() < 2, "Response time is higher than 2000: %s" % \
                                                         context.response.elapsed.total_seconds()


@then('The amount of companies objects that end with Group name is 2')
def step_impl(context):
    """
    Count how many times the Group word appear in the company name, it validates if the amount is equals to 2
    :param context: Context(self)
    :return:
    """
    amount_word_appears = users_functions.count_companies("Group", context.response.json())
    assert amount_word_appears == 2, "Group company name does not appear 2 times, it appeared: %s" % amount_word_appears
# END FETCH USERS
