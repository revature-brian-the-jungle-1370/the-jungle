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