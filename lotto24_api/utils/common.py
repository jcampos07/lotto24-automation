def save_info_in_file(file, info):
    """
    Save the info in a file to be used previously
    :param file:
    :param info
    :return:
    """
    file_to_write = open(file, "a")
    file_to_write.write(info)
    file_to_write.write("\n")
    file_to_write.close()
