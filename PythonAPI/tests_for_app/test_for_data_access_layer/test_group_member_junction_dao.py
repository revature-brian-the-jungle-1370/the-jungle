import pytest
from _pytest.fixtures import fixture

from custom_exceptions.group_member_junction_exceptions import *
from data_access_layer.implementation_classes.group_member_junction_dao import GroupMemberJunctionDao
from util.database_connection import connection

group_member_junction_dao = GroupMemberJunctionDao()


@fixture
def create_fake_user():
    """Creates two new users for testing and deletes them once the tests are done"""

    sql = "Delete from user_table where user_id >= 100000000;" \
          "Insert into user_table values(100000000, 'first10000', 'last10000', 'email@email.com', 'username1000000', " \
          "'passcode100000', 'about', '1991-08-06', 'gif');" \
          "Insert into user_table values(100000001, 'first10000', 'last10000', 'email2@email.com', 'username100000001'," \
          "'passcode100000', 'about', '1991-08-06', 'gif');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    yield
    sql = "delete from user_table where user_id >= 100000000;"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


@fixture
def fake_join_group(create_fake_user):
    sql = "insert into group_member_junction_table values(10,100000000); " \
          "insert into group_member_junction_table values(10,100000001);"
    connection.cursor().execute(sql)
    connection.commit()


def test_get_all_users_in_a_group():
    result = group_member_junction_dao.get_all_users_in_a_group(9000)
    assert len(result) >= 1


# def test_list_contains_correct_info():
#     result = group_member_junction_dao.get_all_users_in_a_group(9000)
#     print(result)
#     mem = result[1]
#     assert mem.first_name == "Test"


def test_to_many():
    with pytest.raises(TypeError) as e:
        group_member_junction_dao.get_all_users_in_a_group(15, 2)
        assert "you have put the incorrect amount of arguments" in str(e.value)


def test_leave_group(fake_join_group):
    result = group_member_junction_dao.leave_group(100000000, 10)
    assert result


def test_leave_group2(fake_join_group):
    result = group_member_junction_dao.leave_group(100000001, 10)
    assert result


def test_failed_leave_group():
    with pytest.raises(TypeError) as e:
        group_member_junction_dao.leave_group(16, 10, 5)
        assert "too many arguments" in str(e.value)


def test_wrong_user():
    try:
        group_member_junction_dao.leave_group(13, 16)
    except WrongId as e:
        assert str(e) == "Incorrect ID"
