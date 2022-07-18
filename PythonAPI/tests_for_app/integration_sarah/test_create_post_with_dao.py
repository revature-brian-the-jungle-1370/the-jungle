import pytest
from data_access_layer.implementation_classes.create_post_dao import CreatePostDAOImp
from service_layer.implementation_classes.create_post_service import CreatePostServiceImp
from entities.post import Post
from custom_exceptions.image_format_must_be_a_string import ImageFormatMustBeAString
from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.post_text_must_be_a_string import PostTextMustBeAString
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from custom_exceptions.user_not_found import UserNotFound
from custom_exceptions.post_not_found import PostNotFound
from custom_exceptions.post_image_not_found import PostImageNotFound

create_post_dao  = CreatePostDAO()
service_create_post = CreatePostServiceImp(create_post_dao)

def test_create_post_success():
    post = Post(10001, 10000, 10000, "This is some text", "image_format", 0, '2001-3-23')
    new_post = service_create_post.create_post_service(post)
    assert isinstance(new_post, Post)

def test_create_post_invalid_user_id():
    with pytest.raises(UserIdMustBeAnInteger)
        post = Post(10001, "yo", 10000, "This is some text",  "image_format", 0, '2001-3-23')
        new_post = service_create_post.create_post_service(post)

def test_create_post_invalid_image_format():
    with pytest.raises(ImageFormatMustBeAString)
        post = Post(10001, 10000, 10000, "This is some text", 24343, 0, '2001-3-23')
        new_post = service_create_post.create_post_service(post)

def test_create_post_invalid_post_text():
    with pytest.raises(PostTextMustBeAString)
        post = Post(10001, 10000, 10000, 456, "image_format", 0, '2001-3-23')
        new_post = service_create_post.create_post_service(post)

def test_create_post_invalid_user_id():
    with pytest.raises(UserNotFound)
        post = Post(10001, -1, 10000, "This is some text", "image_format", 0, '2001-3-23')
        new_post = service_create_post.create_post_service(post)

def test_create_post_image_service_success():
    image_decoded = service_create_post.create_post_image_service(10001, "image_format")
    assert isinstance(image_decoded, str)

def test_create_post_image_service_not_int_post_id():
    with pytest.raises(PostIdMustBeAnInteger)
        image_decoded = service_create_post.create_post_image_service("hello", "image_format")
    
def test_create_post_image_invalid_image_string():
    with pytest.raises(ImageMustBeAString)
        image_decoded = service_create_post.create_post_image_service(10001, 3456)

def test_create_post_image_invalid_post_id():
    with pytest.raises(PostNotFound)
        image_decoded = service_create_post.create_post_image_service(-1, 3456)

def test_get_post_image():
    image_decoded = service_create_post.get_post_image_service(10001)
    assert isinstance(image_decoded, str)

def test_get_post_image_not_numeric():
    with pytest.raises(PostIdMustBeAnInteger)
        mage_decoded = service_create_post.get_post_image_service("skjf")  

def test_get_post_image_no_post():
    with pytest.raises(PostImageNotFound)
        mage_decoded = service_create_post.get_post_image_service(-1)  