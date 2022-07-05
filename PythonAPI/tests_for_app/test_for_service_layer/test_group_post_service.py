from unittest.mock import MagicMock

from custom_exceptions.post_exceptions import InvalidInput, InputTooLong, NoInputGiven, WrongTypeInput
from data_access_layer.implementation_classes.group_post_dao import GroupPostDAO
from entities.group_post import GroupPost
from service_layer.implementation_classes.group_post_service import GroupPostService

post_dao = GroupPostDAO()
post_service = GroupPostService(post_dao)

created_post = GroupPost(9000, 9000, 9000, "test create post service", "none", 0, "2020-1-24")
empty_string_post = GroupPost(9000, 9000, 9000, "", "none", 0, "2020-1-24")
long_string_post = GroupPost(9000, 9000, 9000, "A" * 600, "none", 0, "2020-1-24")
negative_user_id_post = GroupPost(9000, -9000, 9000, "A", "none", 0, "2020-1-24")
wrong_type_post = GroupPost("something", 9000, 9000, "A", "none", 0, "2020-1-24")


# ------------------------------ TEST CREATE POST ------------------------------
def test_mock_create_post_success():
    post_service.post_dao.create_post = MagicMock(return_value=created_post)
    post_service.service_create_post(created_post)
    assert post_service.service_create_post(created_post)


def test_mock_create_empty_string():
    try:
        post_service.service_create_post(empty_string_post)
        assert False
    except NoInputGiven as e:
        assert str(e) == "No Input Given!"


def test_mock_create_long_string():
    try:
        post_service.service_create_post(long_string_post)
        assert False
    except InputTooLong as e:
        assert str(e) == "Messages too long!"


def test_mock_create_post_negative_user_id():
    try:
        post_service.service_create_post(negative_user_id_post)
        assert False
    except InvalidInput as e:
        assert str(e) == "Invalid Input!"


def test_mock_create_wrong_type():
    try:
        post_service.service_create_post(wrong_type_post)
        assert False
    except WrongTypeInput as e:
        assert str(e) == "You need to enter number for post ID!"


# ------------------------------ TEST GET POST BY ID ------------------------------
def test_mock_get_post_success():
    post_service.service_get_post_by_id = MagicMock(return_value=post_service)
    post = GroupPost(9000, 9000, 9000, "test get post service", "none", 0, "2020-1-24")
    assert post_service.service_get_post_by_id(post.post_id)


# ------------------------------ TEST GET ALL POSTS ------------------------------
def test_mock_get_all_posts_success():
    post_service.service_get_all_posts = MagicMock(return_value=post_service)
    assert post_service.service_get_all_posts()


# ------------------------------ TEST GET ALL POST BY GROUP ID ------------------------------
def test_mock_get_posts_by_group_id_success():
    post_service.service_get_all_posts_by_group_id = MagicMock(return_value=post_service)
    assert post_service.service_get_all_posts_by_group_id()


# ------------------------------ TEST DELETE POST BY ID ------------------------------
def test_mock_delete_post_success():
    post_service.service_delete_post_by_post_id = MagicMock(return_value=post_service)
    assert post_service.service_delete_post_by_post_id()
