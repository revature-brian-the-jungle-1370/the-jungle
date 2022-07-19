from service_layer.implementation_classes.like_post_service import *
from unittest.mock import MagicMock
from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp

like_post_service: LikePostServiceImp=LikePostServiceImp(LikePostDaoImp);

def test_service_like_post():
    like_post_service.LikePostDaoImp.like_post = MagicMock(return_value=3)
    assert like_post_service.service_like_post(1)

def test_service_like_comment():
    like_post_service.LikePostDaoImp.like_comment = MagicMock(return_value=3)

def test_service_unlike_post():
    like_post_service.LikePostDaoImp.unlike_post = MagicMock(return_value=3)

def test_service_unlike_comment():
    like_post_service.LikePostDaoImp.unlike_comment = MagicMock(return_value=3)
