from abc import ABC, abstractmethod

from entities.post import Post


class LikePostDAO(ABC):

    @abstractmethod
    def like_post(self, post_id: int) -> Post:
        pass

    @abstractmethod
    def like_comment(self, comment_id : int) :
        pass

    @abstractmethod
    def unlike_post(self, post_id: int) -> Post:
        pass

    @abstractmethod
    def unlike_comment(self, comment_id : int) :
        pass

    @abstractmethod
    def liketable_post(self, user_id: int, post_id: int):
        pass

    @abstractmethod
    def get_liketable_post_with_user_id_post_id(self, user_id: int, post_id: int) -> Post:
        pass
