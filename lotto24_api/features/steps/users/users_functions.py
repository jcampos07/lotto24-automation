def count_companies(company_name, json_response):
    """
    Count how many companies names end with the parameter it gets
    :param json_response:
    :param company_name:
    :return:
    """
    cont = 0
    for key in list(json_response):
        company_name_response = key['company']['name']
        company_name_response = company_name_response.split(" ")
        if company_name_response[len(company_name_response) - 1] == company_name:
            cont += 1
    return cont
