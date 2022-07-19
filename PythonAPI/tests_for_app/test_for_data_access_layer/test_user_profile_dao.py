from custom_exceptions.follower_not_found import FollowerNotFound
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from custom_exceptions.user_image_not_found import UserImageNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.user_profile_dao import UserProfileDAOImp, UserProfileDAO
from entities.user import User
from pytest import fixture

from util.database_connection import connection

user_profile_dao: UserProfileDAO = UserProfileDAOImp()

user_about_me_for_tests = "Updating Profile About me"
user_email_for_tests = "test@email.com"
user_passcode_for_tests = "test_passcode"
user_not_found_message = "The user could not be found."



@fixture
def create_fake_user():
    """Creates two new users for testing and deletes them once the tests are done"""
    sql = "Delete from user_table where user_id >= 100000000;" \
        "Insert into user_table values(100000000, 'first10000', 'last10000', 'email@email.com', 'username1000000', " \
        "'passcode100000', 'about', '1991-08-06', 'gif');" \
        "Insert into user_table values(100000001, 'first10000', 'last10000', 'email1@email.com', 'username100000001'," \
        "'passcode100000', 'about', '1991-08-06', 'gif');"\
        "Insert into user_table values(100000002, 'first10000', 'last10000', 'email2@email.com', 'username100000002'," \
        "'passcode100000', 'about', '1991-08-06', 'gif');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    yield
    sql = "delete from user_table where user_id >= 100000000;"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


@fixture
def create_fake_image(create_fake_user):
    """Creates a fake images for the test users"""

    sql = "Insert into user_picture_table values(100000000, 100000000, 'test_image');" \
        "Insert into user_picture_table values(100000001, 100000001, 'test_image');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


@fixture
def create_fake_followers(create_fake_user):
    """Create followers for the fake users"""

    sql = "Insert into user_follow_junction_table values(100000000, 100000001);" \
          "Insert into user_follow_junction_table values(100000000, 100000002);" \
          "Insert into user_follow_junction_table values(100000001, 100000000);" \
          "Insert into user_follow_junction_table values(100000002, 100000000);"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()



def test_get_user_profile_success(create_fake_user):
    show_user = user_profile_dao.get_user_profile(100000000)
    assert show_user.user_id == 100000000


def test_get_user_profile_success_2(create_fake_user):
    show_user = user_profile_dao.get_user_profile(100000001)
    assert show_user.user_id == 100000001


def test_update_user_profile_about_me_success(create_fake_user):
    """Happy test to see if user about me is updated correctly"""
    updated_user = User(100000000, "test_first_name", "test_last_name", user_email_for_tests, "test_username",
                        "test_passcode", user_about_me_for_tests, "2022-01-21", "test_image")
    updated_profile: User = user_profile_dao.update_user_profile(updated_user)
    assert updated_profile.user_about == "Updating Profile About me"


def test_update_user_profile_birth_date_success(create_fake_user):
    """Happy test to see if user birth date is updated correctly"""
    updated_user = User(100000000, "test_first_name", "test_last_name", "test@email.com", "test_username",
                        "test_passcode",
                        user_about_me_for_tests, "2022-01-05", "test_image")
    updated_profile: User = user_profile_dao.update_user_profile(updated_user)
    assert updated_profile.user_about == user_about_me_for_tests


def test_update_user_profile_failure_no_user():
    """Sad test to see if there is no user found by the ID"""
    updated_user_fail = User(100000000, "test_first_name", "test_last_name", user_email_for_tests, "test_username",
                            "test_passcode", user_about_me_for_tests, "2022-01-21", "test_image")
    try:
        user_profile_dao.update_user_profile(updated_user_fail)
        assert False
    except UserNotFound as e:
        assert str(e) == user_not_found_message


def test_update_user_profile_failure_sql_injection(create_fake_user):
    """Tests to see if an sql injection will occur when updating the about me"""

    updated_user_fail_sql_injection = User(100000000, "test_first_name", "test_last_name", user_email_for_tests,
                                                "test_username",
                                                "test_passcode",
                                                "'; update user_table set passcode = 'sqlinjection' where user_id = 10000; --",
                                                "2016-01-01", "Test image")
    if user_profile_dao.update_user_profile(updated_user_fail_sql_injection):
        assert updated_user_fail_sql_injection.user_about == "'; update user_table set passcode = 'sqlinjection' " \
                                                            "where user_id = 10000; --"
    else:
        assert updated_user_fail_sql_injection.passcode != "test_passcode"


def test_get_user_image_success(create_fake_image):
    image = user_profile_dao.get_user_image(100000000)
    assert image


def test_get_user_image_success_2(create_fake_image):
    image = user_profile_dao.get_user_image(100000001)
    assert image


def test_get_user_image_failure_no_image():
    try:
        user_profile_dao.get_user_image(-5)
        assert False
    except UserImageNotFound as e:
        assert str(e) == 'The user image could not be found.'


def test_update_user_image_success_1(create_fake_image):
    assert user_profile_dao.update_user_image(100000000, "thisisahappytest")


def test_update_user_image_success_2(create_fake_image):
    assert user_profile_dao.update_user_image(100000001, "thisisahappytest")


def test_update_user_image_failure_no_user():
    try:
        user_profile_dao.update_user_image(-5, "this is a sad test")
        assert False
    except UserNotFound as e:
        assert str(e) == user_not_found_message


def test_update_image_format_success_1(create_fake_image):
    assert user_profile_dao.update_user_image_format(100000000, "testing")


def test_update_image_format_success_2(create_fake_image):
    assert user_profile_dao.update_user_image_format(100000001, "testing")


def test_update_image_format_failure_no_user():
    try:
        user_profile_dao.update_user_image_format(-5, "gif")
        assert False
    except UserNotFound as e:
        assert str(e) == user_not_found_message


# def test_update_password(create_fake_user):
#     """Test to see if password is changed"""
#     try:
#         test_user = user_profile_dao.get_user_profile(100000000)
#         user_profile_dao.update_password(test_user.user_id,user_passcode_for_tests)
#     except UserNotFound as e:
#         assert str(e) == user_not_found_message
#     assert test_user.passcode == user_passcode_for_tests


def test_user_followers_success(create_fake_followers):
    """Tests to see if user 100000000 has more than two followers"""
    follower_dict: dict[str:int] = user_profile_dao.get_user_followers(100000000)
    print(str(follower_dict))
    assert len(follower_dict) >= 2


def test_user_following_success(create_fake_followers):
    """Tests to see if user 100000000 is following the two fake users"""
    following_dict: dict[str:int] = user_profile_dao.get_users_following_user(100000000)
    assert len(following_dict) >= 2


def test_user_followers_failure_no_user():
    try:
        user_profile_dao.get_user_followers(-1)
    except KeyError as ke:
        assert False
    except UserNotFound as e:
        assert str(e) == user_not_found_message


def test_user_following_failure_no_user():
    try:
        user_profile_dao.get_users_following_user(-1)
    except UserNotFound as e:
        assert str(e) == user_not_found_message


def test_follow_user_success_1(create_fake_user):
    user_profile_dao.follow_user(100000000, 100000001)


def test_follow_user_success_2(create_fake_user):
    user_profile_dao.follow_user(100000000, 100000002)


def test_follow_user_failure_user_follower_id_failure(create_fake_user):
    try:
        user_profile_dao.follow_user(-1, 100000000)
        assert False
    except UserNotFound as e:
        assert str(e) == user_not_found_message

def test_follow_user_failure_user_follower_string_failure(create_fake_user):
    try:
        user_profile_dao.follow_user(100000000, "apple")
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == user_not_found_message


def test_follow_user_failure_user_being_followed_id_failure(create_fake_user):
    try:
        user_profile_dao.follow_user(100000000, -1)
        assert False
    except UserNotFound as e:
        assert str(e) == user_not_found_message


def test_unfollow_user_success_1(create_fake_followers):
    user_profile_dao.unfollow_user(100000000, 100000001)


def test_unfollow_user_success_2(create_fake_followers):
    user_profile_dao.unfollow_user(100000000, 100000002)


def test_unfollow_user_failure_follower_not_found(create_fake_followers):
    try:
        user_profile_dao.unfollow_user(100000000, -1)
        assert False
    except FollowerNotFound as e:
        assert str(e) == "The follower was not found."
