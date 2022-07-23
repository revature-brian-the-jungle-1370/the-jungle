from abc import ABC, abstractmethod

from entities.post import Post


class LikePostService(ABC):

    @abstractmethod
    def service_like_post(self, post_id: int):
        pass

    @abstractmethod
    def service_like_comment(self, comment_id: int):
        pass

    @abstractmethod
    def service_unlike_post(self, post_id: int):
        pass

    @abstractmethod
    def service_unlike_comment(self, comment_id: int):
        pass

    @abstractmethod
    def liketable_post_service(self, userid:int, postid:int):
        pass

    @abstractmethod
    def get_liketable_post_service(self, userid:int, postid:int):
        pass
