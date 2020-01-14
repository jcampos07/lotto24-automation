import json

from utils import constants, common


def verify_post_information_format(json_response):
    """
        Compare the posts_format.json {key}:value with all the json response, it validates that each attribute has the
        right type, for example  "userId": "num", each userId should be a number, if it is not a number, it will
        concatenate it in the wrong_data string
        :param json_response:
        :return:
        """
    wrong_data = ""
    with open(constants.POST_INFORMATION_FORMAT, 'r') as post_format:
        post_format_json = json.load(post_format)
    for field_key, value in json_response.items():
        if field_key in post_format_json[0]:
            if post_format_json[0][field_key] == "str" and not isinstance(value, str):
                wrong_data += "[Key -> " + field_key + " value -> " + str(value) + "] expected type string," \
                                                                                   " for the array " + str(
                    json_response)
            if post_format_json[0][field_key] == "num" and not isinstance(value, int):
                wrong_data += "[Key -> " + field_key + " value -> " + str(value) + "] expected type string," \
                                                                                   " for the array " + str(
                    json_response)
    return wrong_data


def save_post_deleted(post_id):
    """
    Save the post deleted to a file where we can read previously
    :param post_id:
    :return:
    """
    common.save_info_in_file(constants.POST_DELETED, str(post_id))


def is_post_id_in_deleted_file(post_id):
    """
    Verify if the post id it gets as parameter is in the list of post id that were deleted previously
    :param post_id:
    :return:
    """
    posts_id_deleted = open(constants.POST_DELETED, "r")
    post_id_deleted_split = posts_id_deleted.read().split("\n")
    for post_id_deleted in post_id_deleted_split:
        if post_id_deleted == post_id:
            return True
    return False


def get_new_post_id():
    with open(constants.POST_INFORMATION_TO_UPDATE, 'r') as post_information:
        post_information_json = json.load(post_information)
    return post_information_json[0]["id"]

