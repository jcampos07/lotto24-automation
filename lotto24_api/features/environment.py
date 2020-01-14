from utils import constants


def before_all(context):
    """
    Before all the test run, we clear the post id we have deleted
    again.
    :return:
    """
    file = open(constants.POST_DELETED, 'r+')
    file.truncate(0)
    file.close()

