from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp
from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from custom_exceptions.connection_error import ConnectionErrorr


like_post_dao = LikePostDaoImp()
commentObj= CommentDAOImp()


def test_like_comment_success():
    comments=commentObj.get_comment_by_post_id(230)
    # for comment in comments:
    likes=comments[0].likes
    liked_comment=like_post_dao.like_comment(comments[0].comment_id)
    assert liked_comment>0


def test_like_comment_invalid_postId():
    try:
        like_post_dao.like_comment(5)
        assert False
    except ConnectionErrorr as e:
        str(e) == "post not found"

def test_like_comment_invalid_postId2():
    try:
        like_post_dao.like_comment("5")
        assert False
    except ConnectionErrorr as e:
        str(e) == "post not found"
