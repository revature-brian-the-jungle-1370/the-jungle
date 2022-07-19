import pytest

from data_access_layer.implementation_classes.group_member_junction_dao import GroupMemberJunctionDao
from entities.group_member_junction import GroupMemberJunction
from custom_exceptions.group_member_junction_exceptions import *
from service_layer.implementation_classes.group_member_junction_service import GroupMemberJunctionService

group_member_junction_dao_test = GroupMemberJunctionDao()
group_member_junction_service_test = GroupMemberJunctionService(group_member_junction_dao_test)

@fixture
def create_fake_user():
    """Creates new user for testing and deletes them once the tests are done"""

    sql = "Delete from user_table where user_id >= 100000000;" \
          "Insert into user_table values(100000000, 'first10000', 'last10000', 'email@email.com', 'username1000000', " \
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
    sql = "insert into group_member_junction_table values(10,100000000); "
    connection.cursor().execute(sql)
    connection.commit()

def create_fake_member():
    sql = "insert into group_member_junction values(10000, 100000000);"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()

def test_get_all_users_in_a_group_success():
    members_list = group_member_junction_service_test.get_all_users_in_a_group(10000)
    assert len(members_list) > 0

def test_get_all_users_in_a_group_invalid():
    members_list = group_member_junction_service_test.get_all_users_in_a_group(-1)
    assert len(members_list) = 0

def test_leave_group_success(fake_join_group):
    left = group_member_junction_service_test.leave_group(100000000, 10)
    assert left

def test_leave_group_invalid_user_id():
    with pytest.raises(WrongId)
        left = group_member_junction_service_test.leave_group(-1, 10000)

def test_leave_group_invalid_group_id():
    with pytest.raises(WrongId)
        left = group_member_junction_service_test.leave_group(13, -1)