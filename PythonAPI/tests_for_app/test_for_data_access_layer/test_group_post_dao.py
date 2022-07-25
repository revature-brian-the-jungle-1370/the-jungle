import pytest

from custom_exceptions.post_exceptions import PostNotFound
from data_access_layer.implementation_classes.group_post_dao import GroupPostDAO
from entities.group_post import GroupPost

post_dao = GroupPostDAO()


# ------------------------------ TEST CREATE POST ------------------------------
# Test create post
def test_create_post_success():
    post_to_be_created: GroupPost = GroupPost(9000, 13, 10000, "test create post", "", 0, "")
    created_post = post_dao.create_post(post_to_be_created)
    assert created_post.post_id != 0


# Test create post with string input
def test_create_another_post_success():
    post_to_be_created: GroupPost = GroupPost("9001", 13, 10000, "test create post", "", 0, "")
    created_post = post_dao.create_post(post_to_be_created)
    assert created_post.post_id != 0


# Test create post failed too many arguments entered
def test_create_post_fail_too_many_arguments():
    with pytest.raises(TypeError) as e:
        failed_create_post: GroupPost = GroupPost(9000, 13, 10000, "test create post", "", 0, "", "hi")
        post_dao.create_post(failed_create_post)
        assert "too many arguments" in str(e.value)


# Test create post failed long string
def test_create_post_fail_long_string():
    pass


# ------------------------------ TEST GET POST BY ID ------------------------------
# Test get post by ID
def test_get_post_by_id():
    get_post = post_dao.get_post_by_id(10000)
    assert get_post.post_id == 10000


# Test get another post by ID
def test_get_another_post_by_id():
    get_post = post_dao.get_post_by_id(517)
    assert get_post.post_id == 517


# Test get post by ID failed
def test_get_post_by_id_fail():
    with pytest.raises(TypeError) as e:
        post_dao.get_post_by_id(10000, "hi")
        assert "too many arguments" in str(e.value)


# ------------------------------ TEST GET ALL POSTS ------------------------------
# Test get all posts
def test_get_all_posts():
    get_posts = post_dao.get_all_posts()
    assert len(get_posts) >= 1


# ------------------------------ TEST GET ALL POST BY GROUP ID ------------------------------
# Test get all post by group ID
def test_get_all_posts_by_group_id():
    get_posts = post_dao.get_all_posts_by_group_id(10000)
    assert len(get_posts) >= 1


# Test get all posts by group ID failed
def test_get_all_posts_by_group_id_fails():
    try:
        post_dao.get_all_posts_by_group_id(9000)
    except PostNotFound as e:
        assert str(e) == "Post Not Found!"


# ------------------------------ TEST DELETE POST BY ID ------------------------------
# Test delete post
def test_delete_post_by_id():
    delete_post = post_dao.delete_post_by_post_id(505)
    assert delete_post


# Test delete post failed
def test_delete_post_by_id_fail_post_not_found():
    try:
        post_dao.delete_post_by_post_id(9000)
    except PostNotFound as e:
        assert str(e) == 'The post could not be found.'
