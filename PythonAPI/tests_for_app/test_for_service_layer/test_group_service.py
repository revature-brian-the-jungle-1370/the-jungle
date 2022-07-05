from typing import List
from unittest.mock import MagicMock
from custom_exceptions.group_exceptions import NullValues, InputTooLong, InputTooShort, GroupNameTaken
from data_access_layer.implementation_classes.group_dao import GroupDAOImp
from data_access_layer.implementation_classes.group_view_postgres_dao import GroupViewPostgresDao
from entities.group import Group
from service_layer.implementation_classes.group_service import GroupPostgreService

group_dao = GroupDAOImp()
group_view_dao = GroupViewPostgresDao()

group_service = GroupPostgreService(group_dao, group_view_dao)

group_null = Group(0, 0, "", "", "")

group_white_spaces = Group(0, 0, "  ", "  ", "  ")

x = 0
while x <= 41:
    group_name_1 = "s" * x
    x += 1
group_name = Group(0, 9000, group_name_1, "test", "image format")

i = 0
while i <= 501:
    group_about_1 = "a" * i
    i += 1
group_about = Group(0, 9000, "Z Top", group_about_1, "image format")

y = 0
while y <= 41:
    image_format_1 = "s" * y
    y += 1
image_format = Group(0, 9000, "test", "test", image_format_1)

group_name_too_short = Group(0, 9000, "Z", "test", "image format")

duplicate_group_name = Group(0, 9000, "Metallica Fans", "test", "image format")

group_list: List = [Group(0, 9000, "Metallica Fans", "This group is for all Metallica fans!", "image format"),
                    Group(0, 9000, "GNR Fans", "This group is for all GNR fans!", "image format")]


# ------------------------------------------------Create Group-----------------------------------------------
# Null Values
def test_create_group_service_fail_null_values():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=group_null)
        group_service.service_create_group(group_null)
        assert False
    except NullValues as e:
        assert str(e) == "You must fill in all inputs!"


# -----------White Spaces-----------
def test_create_group_service_fail_white_spaces():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=group_white_spaces)
        group_service.service_create_group(group_white_spaces)
        assert False
    except NullValues as e:
        assert str(e) == "You must fill in all inputs!"


# -----------Inputs Too Long-----------
# Group Name
def test_create_group_service_fail_too_long_input_group_name():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=group_name)
        group_service.service_create_group(group_name)
        assert False
    except InputTooLong as e:
        assert str(e) == "You have exceeded the 40-character limit!"


# Group About
def test_create_group_service_fail_too_long_input_group_about():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=group_about)
        group_service.service_create_group(group_about)
        assert False
    except InputTooLong as e:
        assert str(e) == "You have exceeded the 500-character limit!"


# Image Format
def test_create_group_service_fail_too_long_input_image_format():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=image_format)
        group_service.service_create_group(image_format)
        assert False
    except InputTooLong as e:
        assert str(e) == "You have exceeded the 40-character limit!"


# -----------Group Name Input Too Short-----------
def test_create_group_service_fail_too_short_input_group_name():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=group_name_too_short)
        group_service.service_create_group(group_name_too_short)
        assert False
    except InputTooShort as e:
        assert str(e) == "Group name should be at least three characters long!"


# -----------Group Name Duplicate-----------
def test_create_group_service_fail_duplicate_group_name():
    try:
        group_service.group_dao.create_group = MagicMock(return_value=duplicate_group_name)
        group_service.group_view_dao.get_all_groups = MagicMock(return_value=group_list)
        group_service.service_create_group(duplicate_group_name)
        assert False
    except GroupNameTaken as e:
        assert str(e) == "The group name you entered is already taken! Please try another group name."
