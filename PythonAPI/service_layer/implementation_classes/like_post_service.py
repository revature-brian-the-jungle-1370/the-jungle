from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp
from service_layer.abstract_classes.like_post_service_abs import LikePostService


class LikePostServiceImp(LikePostService):

    def __init__(self, like_post_dao):
        self.like_post_dao: LikePostDaoImp = like_post_dao

    def service_like_post(self, post_id):
        return self.like_post_dao.like_post(post_id)

    def service_like_comment(self, comment_id: int):
        return self.like_post_dao.like_comment(comment_id)

    def service_unlike_post(self, post_id):
        return self.like_post_dao.unlike_post(post_id)

    def service_unlike_comment(self, comment_id: int):
        return self.like_post_dao.unlike_comment(comment_id)

    def liketable_post_service(self, userid: int, postid: int):
        return self.like_post_dao.liketable_post(userid, postid)

    def get_liketable_post_service(self, userid:int, postid:int):
        return self.like_post_dao.get_liketable_post_with_user_id_post_id(userid, postid)
