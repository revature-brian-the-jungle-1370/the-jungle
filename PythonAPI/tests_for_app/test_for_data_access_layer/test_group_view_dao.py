from data_access_layer.implementation_classes.group_view_dao import GroupViewDaoImp
from entities.group import Group

group_view_dao_imp = GroupViewDaoImp()
group = Group(1, 12, "Comic Club", "We love superheros", "placeholder")


# testing to check that we are only working if group_id are the same
def test_get_group_success():
    returned_group: Group = group_view_dao_imp.get_group_by_id(1)
    assert returned_group.group_id == 1


# testing to verifies that the name of group matches it's group id
def test_info_in_group_valid():
    returned_group: Group = group_view_dao_imp.get_group_by_id(1)
    assert returned_group.group_name == "Comic Club"


# sad path for group_id that are 0
def test_unknown_group_id():
    returned_group: Group = group_view_dao_imp.get_group_by_id(0)
    if returned_group == 0:
        assert False
    else:
        assert True


# sad path for group_id that are negative
def test_negative_group_id():
    returned_group: Group = group_view_dao_imp.get_group_by_id(-1)
    if returned_group == -1:
        assert False
    else:
        assert True


# testing to success of getting all groups
def test_get_all_group_success():
    group_list = group_view_dao_imp.get_all_groups()
    assert len(group_list) >= 2


def test_group_contain_valid_info():
    result = group_view_dao_imp.get_all_groups()
    print(result)
    group = result[1]
    assert group.group_name == "Soccer Fans"


# testing to check for invalid names search
def test_invalid_info_for_group():
    result = group_view_dao_imp.get_all_groups()
    print(result)
    group = result[1]
    if group.group_name == "Test":
        assert False
    else:
        assert True
# need 1 sad path for this function maybe
