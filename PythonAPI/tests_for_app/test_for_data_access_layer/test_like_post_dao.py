from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp
from data_access_layer.implementation_classes.group_post_dao import GroupPostDAO
from custom_exceptions.connection_error import ConnectionErrorr

like_post_dao = LikePostDaoImp()
postObj=GroupPostDAO()
def test_like_post_success():
    post=postObj.get_post_by_id(230)
    likes=post.likes
    liked_post=like_post_dao.like_post(post.post_id)
    assert liked_post==likes+1


def test_like_post_success2():
    post=postObj.get_post_by_id(228)
    likes=post.likes
    liked_post=like_post_dao.like_post(post.post_id)
    assert liked_post==likes+1


def test_like_post_invalid_postId():
    try:
        like_post_dao.like_post(5)
        assert False
    except ConnectionErrorr as e:
        str(e) == "post not found"

