from typing import List

from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from entities.comment import Comment
from entities.returned_comment import ReturnedComment
from service_layer.abstract_classes.comment_service_abs import CommentService
from custom_exceptions.comment_id_must_be_an_integer import CommentIdMustBeAnInteger
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.comment_must_be_a_string import CommentMustBeAString
from custom_exceptions.comment_not_found import CommentNotFound


class CommentServiceImp(CommentService):
    def __init__(self, comment_dao: CommentDAOImp):
        self.comment_dao = comment_dao

    def service_create_comment(self, post_id: int, user_id: int, comment_text: str, group_id: int,
                               reply_user: int) -> Comment:
                               
        # Check to make sure the user_id is an integer
        if not str(post_id).isnumeric():
            raise PostIdMustBeAnInteger('The Post ID must be an integer.')
        post_id = int(post_id)

        # Check to make sure the text is a string.
        if not type(comment_text) == str:
            raise CommentMustBeAString("The comment text must be a string.")

        return self.comment_dao.create_comment(post_id, user_id, comment_text, group_id, reply_user)

    def service_get_comment_by_post_id(self, post_id: int) -> List[ReturnedComment]:
        return self.comment_dao.get_comment_by_post_id(post_id)

    def service_delete_comment(self, comment_id: int) -> bool:
        # Check to make sure the user_id is an integer
        if not str(comment_id).isnumeric():
            raise CommentIdMustBeAnInteger('The Comment ID must be an integer.')
        comment_id = int(comment_id)
        return self.comment_dao.delete_comment(comment_id)
