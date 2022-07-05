from unittest.mock import MagicMock, patch, Mock

from custom_exceptions.image_format_must_be_a_string import ImageFormatMustBeAString
from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.post_text_must_be_a_string import PostTextMustBeAString
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from data_access_layer.abstract_classes.create_post_dao_abs import CreatePostDAO
from data_access_layer.implementation_classes.create_post_dao import CreatePostDAOImp
from entities.post import Post
from service_layer.abstract_classes.create_post_service_abs import CreatePostService
from service_layer.implementation_classes.create_post_service import CreatePostServiceImp

create_post_dao: CreatePostDAO = CreatePostDAOImp()
create_post_service: CreatePostService = CreatePostServiceImp(create_post_dao)


def test_create_post_service_success():
    create_post_dao.create_post = MagicMock(return_value="thisisareturnvalue")
    temp_post = Post(1, 1, None, "text", "image_format", 0, '1991-8-6')
    assert create_post_service.create_post_service(temp_post)


def test_create_post_service_failure_user_no_int():
    create_post_dao.create_post = MagicMock(return_value="thisisareturnvalue")
    temp_post = Post(1, "hello", None, "text", "image_format", 0, '1991-8-6')
    try:
        create_post_service.create_post_service(temp_post)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_create_post_service_failure_text_not_string():
    create_post_dao.create_post = MagicMock(return_value="thisisareturnvalue")
    temp_post = Post(1, 1, None, b"text", "image_format", 0, '1991-8-6')
    try:
        create_post_service.create_post_service(temp_post)
        assert False
    except PostTextMustBeAString as e:
        assert str(e) == "The post text must be a string."


def test_create_post_service_failure_image_format_not_string():
    create_post_dao.create_post = MagicMock(return_value="thisisareturnvalue")
    temp_post = Post(1, 1, None, "text", b"image_format", 0, '1991-8-6')
    try:
        create_post_service.create_post_service(temp_post)
        assert False
    except ImageFormatMustBeAString as e:
        assert str(e) == "The image format must be a string."


def test_create_post_image_service_success():
    create_post_dao.create_post_image = MagicMock(return_value="thisisareturnvalue")
    assert create_post_service.create_post_image_service(1, "thisisaforwardvalue")


def test_create_post_image_service_failure_not_int():
    create_post_dao.create_post_image = MagicMock(return_value="thisisareturnvalue")
    try:
        create_post_service.create_post_image_service(1.0, "thisisaforwardvalue")
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == "The post id must be an integer."


def test_create_post_image_service_failure_not_str():
    create_post_dao.create_post_image = MagicMock(return_value="thisisareturnvalue")
    try:
        create_post_service.create_post_image_service(1, b"thisisaforwardvalue")
        assert False
    except ImageMustBeAString as e:
        assert str(e) == "The image must be a string format."


def test_get_post_image_service_success():
    create_post_dao.get_post_image = MagicMock(return_value="thisisareturnvalue")
    assert create_post_service.get_post_image_service(12345)


def test_get_post_image_service_failure_post_id_no_int():
    create_post_dao.get_post_image = MagicMock(return_value="thisisareturnvalue")
    try:
        create_post_service.get_post_image_service(b"12345")
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == "The post id must be an integer."

