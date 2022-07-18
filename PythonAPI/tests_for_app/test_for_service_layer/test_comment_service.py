from unittest.mock import MagicMock, patch, Mock
from PythonAPI.custom_exceptions import comment_id_must_be_an_integer

from custom_exceptions.comment_id_must_be_an_integer import CommentIdMustBeAnInteger
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.comment_must_be_a_string import CommentMustBeAString
from custom_exceptions.comment_not_found import CommentNotFound
from data_access_layer.abstract_classes.comment_dao_abs import CommentDAO
from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from entities.comment import Comment
from service_layer.abstract_classes.comment_service_abs import CommentService
from service_layer.implementation_classes.comment_service import CommentServiceImp

comment_dao: CommentDAO = CommentDAOImp()
comment_service: CommentService = CommentServiceImp(comment_dao)

def test_service_create_comment_success():
    comment_dao.create_comment = MagicMock(return_value="thisisareturnvalue")
    assert comment_service.service_create_comment(post_id= 1000, user_id= 1000, comment_text="success", group_id=1000, reply_user=1000)

def test_service_create_comment_failure_comment_no_int():
    comment_dao.create_comment = MagicMock(return_value="thisisareturnvalue")
    try:
        comment_service.service_create_comment(post_id= 1000, user_id= 1000, comment_text=100.0, group_id=1000, reply_user=1000)
        assert False
    except CommentMustBeAString as e:
        assert str(e) == "The comment text must be a string."

def test_service_get_comment_by_post_id_success():
    comment_dao.get_comment_by_post_id = MagicMock(return_value="thisisareturnvalue")
    assert comment_service.service_get_comment_by_post_id(1000)

def test_service_get_comment_by_post_id_failure_post_id_not_int():
    comment_dao.create_comment = MagicMock(return_value="thisisareturnvalue")
    try:
        comment_service.service_create_comment(post_id= 100.4, user_id= 1000, comment_text="failure", group_id=1000, reply_user=1000)
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == "The Post ID must be an integer."   

def test_service_delete_comment_success():
    comment_dao.delete_comment = MagicMock(return_value="thisisareturnvalue")
    assert comment_service.service_delete_comment(1000)

def test_service_delete_comment_failure_comment_id_not_int():
    comment_dao.delete_comment = MagicMock(return_value="thisisareturnvalue")
    try:
        comment_service.service_delete_comment(100.1)
        assert False
    except CommentIdMustBeAnInteger as e:
        assert str(e) == "The Comment ID must be an integer."  