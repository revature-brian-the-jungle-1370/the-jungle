from unittest.mock import MagicMock
from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp
from data_access_layer.abstract_classes.like_post_dao_abs import LikePostDAO
from data_access_layer.implementation_classes.group_post_dao import GroupPostDAO
from custom_exceptions.connection_error import ConnectionErrorr
from service_layer.abstract_classes.like_post_service_abs import LikePostService
from service_layer.implementation_classes.like_post_service import LikePostServiceImp

like_post_dao : LikePostDAO = LikePostDaoImp()
postObj=GroupPostDAO()
like_post_service: LikePostService= LikePostServiceImp(like_post_dao)
post_id=20
def test_like_post_success():
    like_post_service.like_post_dao.like_post = MagicMock(return_value=post_id)
    assert like_post_service.service_like_post(post_id)

def test_get_user_profile_failure_not_int():
    try:
        like_post_service.service_like_post(-5)
    except ConnectionErrorr as e:
        assert str(e) == "post not found"

