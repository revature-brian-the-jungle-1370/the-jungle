import pytest
from service_layer.implementation_classes.comment_service import CommentServiceImp
from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from custom_exceptions.comment_not_found import CommentNotFound
from custom_exceptions.post_not_found import PostNotFound

comment_dao = CommentDAOImp()
comment_service = CommentServiceImp(comment_dao)

def test_service_create_comment_success():
    comment_id = comment_service.service_create_comment(10000, 10000, "some text", 10000, None)
    assert isinstance(comment_id, int)

def test_service_create_comment_failure():
    with pytest.raises(PostNotFound)
        comment_service.service_create_comment(-1, 10000, "some text", 10000, None)

def test_get_comment_by_post_id_success():
    comments_list = comment_service.service_get_comment_by_post_id(10000)
    assert isinstance(comments_list, list)

def test_get_comment_by_post_id_failure():
    with pytest.raises(PostNotFound)
        comment_service.service_create_comment(-1)

def test_delete_comment_success():
    comments_list = comment_service.service_get_comment_by_post_id(10000)
    last_comment = comments_list.pop()
    status = comment_service.delete_comment(last_comment.comment_id)
    assert status

def test_delete_comment_failure():
    with pytest.raises(CommentNotFound)
        comment_service.delete_comment(-1)