from custom_exceptions.post_image_not_found import PostImageNotFound
from custom_exceptions.post_not_found import PostNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.create_post_dao import CreatePostDAOImp, CreatePostDAO
from entities.post import Post
from util.database_connection import connection
from pytest import fixture

create_post_dao: CreatePostDAO = CreatePostDAOImp()


@fixture
def create_fake_user():
    """For putting a fake user into the database for testing then removing the fake user."""
    # this is the setup
    sql = "Delete from user_table where user_id >= 100000000;" \
          "Insert into user_table values(100000000, 'first10000', 'last10000', 'email@email.com', 'username1000000', " \
          "'passcode100000', 'about', '1991-08-06', 'gif');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    yield  # everything after the yield is the teardown and called after each test
    sql = "delete from user_table where user_id = 100000000;"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


@fixture
def create_fake_post(create_fake_user):  # notice that the other fixture has been injected into this one.
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into post_table values(100000000, 100000000); " \
          "Insert into post_table values(100000001, 100000000);"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the post from the user in the database


@fixture
def create_fake_post_image(create_fake_post):
    """For putting a fake post image into the database for testing and then removing."""
    sql = "Insert into post_picture_table values(100000000, 100000000, 'fixture image');" \
          "Insert into post_picture_table values(100000001, 100000001, 'fixture image');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


def test_create_post_success(create_fake_user):
    post_to_be_created: Post = Post(user_id=100_000_000, post_text="success")
    new_post = create_post_dao.create_post(post_to_be_created)
    print(new_post.make_dictionary())
    assert new_post


def test_create_post_success_2(create_fake_user):
    post_to_be_created: Post = Post(user_id=100_000_000, post_text="success")
    assert create_post_dao.create_post(post_to_be_created)


def test_create_post_failure():
    post_to_be_created: Post = Post(user_id=100_000_000, post_text="failure")
    try:
        create_post_dao.create_post(post_to_be_created)
        assert False
    except UserNotFound as e:
        assert str(e) == 'The user could not be found.'


def test_create_post_image_success_1(create_fake_post):
    """tests by using an existing"""
    assert create_post_dao.create_post_image(100_000_000, "thisisahappytest")


def test_create_post_image_success_2(create_fake_post):
    """tests by using an existing"""
    assert create_post_dao.create_post_image(100_000_000, "thisIsAlsoAHappyTest")


def test_create_post_image_failure_no_post():
    """tests by using a post id that can't possibly exist"""
    try:
        create_post_dao.create_post_image(100_000_000, "this is a sad test")
        assert False
    except PostNotFound as e:
        assert str(e) == 'The post could not be found.'


def test_get_post_image_success_1(create_fake_post_image):
    assert create_post_dao.get_post_image(100000000)


def test_get_post_image_success_2(create_fake_post_image):
    assert create_post_dao.get_post_image(100000001)


def test_get_post_image_failure():
    try:
        create_post_dao.get_post_image(100000000)
        assert False
    except PostImageNotFound as e:
        assert str(e) == 'The post image could not be found.'
