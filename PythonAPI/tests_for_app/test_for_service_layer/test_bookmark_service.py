from unittest.mock import MagicMock
from PythonAPI.data_access_layer.abstract_classes.postfeed_dao_abs import PostFeedDao
from PythonAPI.data_access_layer.implementation_classes.postfeed_dao import PostFeedDaoImp
from PythonAPI.entities.user import User
from entities.post import Post
from PythonAPI.data_access_layer.abstract_classes.create_post_dao_abs import CreatePostDAO
from PythonAPI.data_access_layer.abstract_classes.user_profile_dao_abs import UserProfileDAO
from PythonAPI.data_access_layer.implementation_classes.create_post_dao import CreatePostDAOImp
from PythonAPI.data_access_layer.implementation_classes.user_profile_dao import UserProfileDAOImp
from PythonAPI.service_layer.abstract_classes.create_post_service_abs import CreatePostService
from PythonAPI.service_layer.abstract_classes.user_profile_service_abs import UserProfileService
from PythonAPI.service_layer.implementation_classes.create_post_service import CreatePostServiceImp
from PythonAPI.service_layer.implementation_classes.user_profile_service import UserProfileServiceImp
from service_layer.abstract_classes.postfeed_service_abs import PostfeedService
from service_layer.implementation_classes.postfeed_service import PostFeedServiceImp


create_post_dao: CreatePostDAO = CreatePostDAOImp()
create_post_service: CreatePostService = CreatePostServiceImp(create_post_dao)

user_profile_dao: UserProfileDAO = UserProfileDAOImp()
user_profile_service: UserProfileService = UserProfileServiceImp(user_profile_dao)

post_feed_dao: PostFeedDao = PostFeedDaoImp()
post_feed_service: PostfeedService = PostFeedServiceImp(post_feed_dao)

create_post_dao.create_post = MagicMock(return_value="thisisareturnvalue")
temp_post = Post(230, 1, None, "text", "image_format", 0, '1991-8-6')
user_profile_dao.get_user_profile =MagicMock(return_value="Uservlaue")
temp_user=User(13,"first_name","last_name","email@email.com","username","password","about","1144558","none")


def test_save_valid_bookmark():
    assert PostFeedServiceImp.bookmark_post_service(post_feed_service,temp_user.user_id, temp_post.post_id) =="Bookmark added"

def test_valid_get_bookmarked_posts():
    assert PostFeedServiceImp.get_all_bookmarkded_posts_service(post_feed_service,temp_user.user_id)

def test_unsave_valid_bookmark():
        assert PostFeedServiceImp.bookmark_post_service(post_feed_service,temp_user.user_id, temp_post.post_id) =="Bookmark Deleted"

def test_invalid_post_bookmark():
    assert PostFeedServiceImp.bookmark_post_service(post_feed_service,temp_user.user_id,8)== "Invalid Post Id or User Id"
 

