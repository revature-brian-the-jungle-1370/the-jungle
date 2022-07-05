from pytest import fixture

from custom_exceptions.group_name_already_taken import GroupNameAlreadyTaken
from custom_exceptions.group_not_found import GroupNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.group_dao import GroupDAOImp
from entities.group import Group
from util.database_connection import connection

group_dao = GroupDAOImp()


@fixture
def create_fake_user():
    """For putting a fake user into the database for testing then removing the fake user."""
    # this is the setup
    sql = "Delete from user_table where user_id >= 100000000;" \
          "Insert into user_table values(100000000, 'first10000', 'last10000', 'email@email.com', 'username1000000', " \
          "'passcode100000', 'about', '1991-08-06', 'gif');" \
          "Insert into user_table values(100000001, 'first10000', 'last10000', 'email100000001@email.com', " \
          "'username1000001', 'passcode100000', 'about', '1991-08-06', 'gif');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    yield  # everything after the yield is the teardown and called after each test
    sql = "Delete from user_table where user_id >= 100000000;"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


@fixture
def create_fake_post(create_fake_user):  # notice that the other fixture has been injected into this one.
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into post_table values(100000000, 100000000); " \
          "Insert into post_table values(100000001, 100000000);"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the post from the user in the database


@fixture
def create_fake_group(create_fake_user):  # notice that the other fixture has been injected into this one.
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into group_table values(100000000, 100000000, 'Keyser Söze'); " \
          "Insert into group_table values(100000001, 100000000, 'Dean Keaton'); "
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the group from the user in the database


@fixture
def create_fake_comment(create_fake_post):
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into comment_table values(100000000, 100000000, 100000000, Null, 100000001, 'Keaton');" \
          "Insert into comment_table values(100000001, 100000000, 100000000, Null, 100000001, 'Keaton');" \
          "Insert into comment_table values(100000002, 100000001, 100000000, Null, 100000001, 'Keaton');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the post from the user in the database


# --------------------------------------Create Group HAPPY Path------------------------------------
# Passing standard 0 argument for group ID
def test_create_group_success(create_fake_user):
    test_group = Group(0, 100000000, "Smashing Pumpkins", "This group is for all Smashing Pumpkins fans!",
                       "image format")
    created_group = group_dao.create_group(test_group)
    print(created_group)
    assert created_group


# Passing a non-integer argument, a string, as argument
def test_create_group_success_2(create_fake_user):
    test_group = Group(0, 100000001, "ZZe Top", "This group is for all ZZ Top fans!", "image format")
    created_group = group_dao.create_group(test_group)
    print(created_group)
    assert created_group


# --------------------------------------Create Group SAD Path---------------------------------------

# Group No user found.
def test_create_group_failure_no_user_found():
    try:
        test_group = Group(0, 100000000, "ZZe Top", "This group is for all ZZ Top fans!", "image format")
        group_dao.create_group(test_group)
        assert False
    except UserNotFound as e:
        print(str(e))
        assert str(e) == 'The user could not be found.'


# Group Name already taken.
def test_create_group_failure_group_name_already_taken(create_fake_group):
    try:
        test_group = Group(0, 100000000, 'Keyser Söze', "This group is for all ZZ Top fans!", "image format")
        group_dao.create_group(test_group)
        assert False
    except GroupNameAlreadyTaken as e:
        print(str(e))
        assert str(e) == 'That group name has already been taken.'


# --------------------------------------Join Group HAPPY Path-----------------------------------------
# Passing viable group ID and user ID
def test_join_group_success(create_fake_group):
    group_joined = group_dao.join_group(100000000, 100000001)
    assert group_joined


# Passing another set of viable group and user IDs
def test_join_group_success_2(create_fake_group):
    group_joined = group_dao.join_group(100000001, 100000001)
    assert group_joined


# ---------------------------------------Join Group SAD Path-------------------------------------------
# Passing a non-existent group ID
def test_join_group_fail_non_existent_group_id(create_fake_user):
    try:
        group_dao.join_group(100000000, 100000001)
        assert False
    except GroupNotFound as e:
        print(str(e))
        assert str(e) == 'The group could not be found.'


def test_join_group_fail_non_existent_user_id():
    try:
        group_dao.join_group(100000000, 100000000)
        assert False
    except UserNotFound as e:
        print(str(e))
        assert str(e) == 'The user could not be found.'

# def test_bad_Id():
#     try:
#         result = group_dao.get_creator(1)
#         assert False;
#     except as e:
#         assert "This Id does not exist" in str(e.value)
#
#
# def test_string_as_id():
#     with pytest.raises(WrongType) as e:
#         result = group_dao.get_creator("nan")
#         assert "please enter a number" in str(e.value)
