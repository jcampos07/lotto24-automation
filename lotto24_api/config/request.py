import requests

session = requests.session()


def post(url, payload, header):
    """
    Send the information to the api getting the parameter it needs according to request type.
    :param url:
    :param payload: Json
    :param header: header the request need to be executed
    :return:
    """
    return requests.session().post(url, data=payload, headers=header, timeout=30)


def get(url, payload):
    """
    Get the response from the service
    :param url:
    :param payload:
    :return:
    """
    return requests.session().get(url, data=payload, headers=session.headers, timeout=2)


def delete(url, payload):
    """
    Delete a resource according to the parameters it gets
    :param url:
    :param payload:
    :return:
    """
    return requests.session().delete(url, data=payload, headers=session.headers, timeout=30)


def put(url, payload):
    """
    Update the the information according to the information we send
    :param url:
    :param payload:
    :return:
    """
    return requests.session().put(url, data=payload, headers=session.headers, timeout=30)

