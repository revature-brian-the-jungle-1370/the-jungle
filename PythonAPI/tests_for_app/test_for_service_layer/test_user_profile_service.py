from unittest.mock import MagicMock, patch, Mock

from custom_exceptions.birth_date_is_null import BirthDateIsNull
from custom_exceptions.image_format_must_be_a_string import ImageFormatMustBeAString
from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.too_many_characters import TooManyCharacters
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.abstract_classes.user_profile_dao_abs import UserProfileDAO
from data_access_layer.implementation_classes.user_profile_dao import UserProfileDAOImp
from entities.user import User
from service_layer.abstract_classes.user_profile_service_abs import UserProfileService
from service_layer.implementation_classes.user_profile_service import UserProfileServiceImp

user_profile_dao: UserProfileDAO = UserProfileDAOImp()
user_profile_service: UserProfileService = UserProfileServiceImp(user_profile_dao)


def test_get_user_profile_service():
    user_profile_dao.get_user_profile = MagicMock(user_id='test purpose')
    assert user_profile_service.service_get_user_profile_service(1)


def test_get_user_profile_failure_not_int():
    try:
        user_profile_service.service_get_user_profile_service(1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_update_user_profile_service_success():
    updated_user_service: User = User(1, "test_first_name", "test_last_name", "test@test.com", "test_username",
                                    "test_passcode", "About me test", "2022-01-22", "Test image")
    user_profile_dao.update_user_profile = MagicMock(return_value=updated_user_service)
    assert user_profile_service.update_user_profile_service(updated_user_service)


def test_update_user_profile_service_failure_too_many_chars():
    updated_user_fail_about_me: User = User(1, "test_first_name", "test_last_name", "test@test.com", "test_username",
                                            "test_passcode",
                                            "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901",
                                            "2022-01-22", "Test image")
    try:
        user_profile_service.update_user_profile_service(updated_user_fail_about_me)
        assert False
    except TooManyCharacters as e:
        assert str(e) == "Too many characters."


def test_update_user_profile_service_failure_birth_date_is_null():
    updated_user_fail_birth_date: User = User(1, "test_first_name", "test_last_name", "test@test.com", "test_username",
                                            "test_passcode", "About me test", None, "Test image")
    try:
        user_profile_service.update_user_profile_service(updated_user_fail_birth_date)
        assert False
    except BirthDateIsNull as e:
        assert str(e) == "Birthdate cannot be null."


def test_get_user_image_service_success():
    user_profile_dao.get_user_image = MagicMock(return_value="thisisareturnvalue")
    assert user_profile_service.get_user_image_service(1)


def test_get_user_image_service_failure_not_int():
    try:
        user_profile_service.get_user_image_service(1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_update_user_image_service_success():
    user_profile_dao.update_user_image = MagicMock(return_value="thisisareturnvalue")
    assert user_profile_service.update_user_image_service(1, "thisisasketch")


def test_update_user_image_service_failure_not_int():
    try:
        user_profile_service.update_user_image_service(1.0, "thisisasketch")
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_update_user_image_service_failure_not_string():
    try:
        user_profile_service.update_user_image_service(1, b"thisisasketch")
        assert False
    except ImageMustBeAString as e:
        assert str(e) == "The image must be a string format."


def test_update_user_image_format_service_success():
    user_profile_dao.update_user_image_format = MagicMock(return_value="gif")
    assert user_profile_service.update_user_image_format_service(1, "gif")


def test_update_user_image_format_service_failure_():
    try:
        user_profile_service.update_user_image_format_service(1, b"thisisasketch")
        assert False
    except ImageFormatMustBeAString as e:
        assert str(e) == "The image format must be a string."


def test_update_password_service_failure():
    """Stretch goal"""
    try:
        user_profile_service.update_password_service(-1,"new_password")
        assert False
    except UserNotFound as e:
        assert str(e) == "The user could not be found."
    
def test_get_user_followers_success():
    user_profile_dao.get_user_followers = MagicMock(return_value="{'username': 1}")
    assert user_profile_service.get_user_followers_service(2)


def test_get_user_followers_failure_not_int():
    try:
        user_profile_service.get_user_followers_service(1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_get_user_following_success():
    user_profile_dao.get_users_following_user = MagicMock(return_value="{'username' : 1}")
    assert user_profile_service.get_users_following_user_service(2)


def test_get_user_following_failure_not_int():
    try:
        user_profile_service.get_users_following_user_service(1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_user_follow_success():
    user_profile_dao.follow_user = MagicMock(return_value=True)
    assert user_profile_service.follow_user_service(1, 2)


def test_user_follow_failure_not_int_chars():
    try:
        user_profile_service.follow_user_service("abc", "abc")
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_user_follow_failure_not_int_float():
    try:
        user_profile_service.follow_user_service(1.0, 1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_user_unfollow_success():
    user_profile_dao.unfollow_user = MagicMock(return_value=True)
    assert user_profile_service.unfollow_user_service(1, 2)


def test_user_unfollow_failure_not_int_chars():
    try:
        user_profile_service.unfollow_user_service("abc", "abc")
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_user_unfollow_failure_not_int_float():
    try:
        user_profile_service.unfollow_user_service(1.0, 1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."
