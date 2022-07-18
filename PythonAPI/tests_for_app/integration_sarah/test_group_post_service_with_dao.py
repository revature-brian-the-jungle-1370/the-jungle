import pytest

from custom_exceptions.post_exceptions import PostNotFound
from custom_exceptions.post_exceptions import InvalidInput, InputTooLong, NoInputGiven, WrongTypeInput
from data_access_layer.implementation_classes.group_post_dao import GroupPostDAO
from entities.group_post import GroupPost
from service_layer.implementation_classes.group_post_service import GroupPostService

group_post_dao_test = GroupPostDAO()
group_post_service_test = GroupPostService(group_post_dao_test)




def test_service_create_post_success():
    post = GroupPost(100000, 10000, 10000, "This is some text", "image_format", 0, '2001-3-23')
    new_post = group_post_service_test.service_create_post(post)
    assert isinstance(new_post, GroupPost)

def test_service_create_post_invalid_user_id():
    with pytest.raises(InvalidInput)
        post = GroupPost(100000, -1, 10000, "This is some text", "image_format", 0, '2001-3-23')
        new_post = group_post_service_test.service_create_post(post)

def test_service_create_post_invalid_post_id():
    with pytest.raises(WrongTypeInput)
        post = GroupPost("dfs", 1000013, 10000, "This is some text", "image_format", 0, '2001-3-23')
        new_post = group_post_service_test.service_create_post(post)

def test_service_create_post_invalid_post_text_empty():
    with pytest.raises(NoInputGiven)
        post = GroupPost(100000, 10000, 10000, "", "image_format", 0, '2001-3-23')
        new_post = group_post_service_test.service_create_post(post)

def test_service_create_post_invalid_post_text_long():
    with pytest.raises(InputTooLong)
        post = GroupPost(100000, 10000, 10000, "This is some text This is some text This is some text This is some text This is some text This is some textThis is some text This is some text This is some text This is some textThis is some text This is some text This is some text This is some text This is some text", "image_format", 0, '2001-3-23')
        new_post = group_post_service_test.service_create_post(post)

def test_service_get_post_by_id_success():
    post = group_post_service_test.service_get_post_by_id(10000)
    assert isinstance(post, GroupPost)

#No way to check failure for post by id

def tets_service_get_all_posts():
    post_list = group_post_service_test.service_get_all_posts()
    assert isinstance(post_list, list)

def service_get_all_posts_by_group_id():
    post_list = group_post_service_test.get_all_group_posts_by_group_id(10000)
    assert isinstance(post_list, list)

def test_service_delete_post_by_post_id_success():
    post_list = group_post_service_test.get_all_group_posts_by_group_id(10000)
    post = post_list.pop()
    status = group_post_service_test.service_delete_post_by_post_id(post.post_id)
    assert status

def test_service_delete_post_by_post_id_wrong_id():
    with pytest.raises(PostNotFound)
        status = group_post_service_test.service_delete_post_by_post_id(-1)