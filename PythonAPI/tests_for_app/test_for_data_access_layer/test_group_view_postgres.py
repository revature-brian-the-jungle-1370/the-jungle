from data_access_layer.implementation_classes.group_view_postgres_dao import GroupViewPostgresDao

group_view_postgres_dao = GroupViewPostgresDao()


# test retrieving group by group id
def test_check_group_info_valid_by_id():
    groups = group_view_postgres_dao.get_group_by_id(10000)
    assert groups.group_name == "Testing Group"


# test grabbing groups by there id
def test_view_group_by_group_id():
    group = group_view_postgres_dao.get_group_by_id(10000)
    assert group


# test if we grab a group using an id that's is not in the database
# def test_retrieve_group_with_unknown_id():


# test retrieving groups link to user by user id
def test_get_group_by_user_id():
    groups = group_view_postgres_dao.get_all_groups_by_user_id(10000)
    assert groups


def test_get_all_group():
    result = group_view_postgres_dao.get_all_groups()
    assert len(result) >= 0
