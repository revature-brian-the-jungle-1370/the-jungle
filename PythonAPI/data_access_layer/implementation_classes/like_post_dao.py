import psycopg

from custom_exceptions.connection_error import ConnectionErrorr
from data_access_layer.abstract_classes.like_post_dao_abs import LikePostDAO
from entities.post import Post
from util.database_connection import connection


class LikePostDaoImp(LikePostDAO):
    def like_post(self,post_id:int):
            sql = "update post_table set likes = likes + 1 where post_id=%s returning likes"
            cursor = connection.cursor()
            cursor.execute(sql,[post_id])
            connection.commit()
            generated_likes_number = cursor.fetchone()[0]

            if  generated_likes_number > 0:
                return generated_likes_number
            else:
                connection.rollback()
                raise ConnectionErrorr('post not found')

    def like_comment(self, comment_id: int):
            sql = "update comment_table set likes = likes + 1 where comment_id=%s returning likes"
            cursor = connection.cursor()
            cursor.execute(sql, [comment_id])
            connection.commit()
            generated_likes_number = cursor.fetchone()[0]

            if generated_likes_number > 0:
                return generated_likes_number
            else:
                connection.rollback()
                raise ConnectionErrorr('comment not found')

    def unlike_post(self,post_id:int):
            sql = "update post_table set likes = likes - 1 where post_id=%s returning likes"
            cursor = connection.cursor()
            cursor.execute(sql,[post_id])
            connection.commit()
            generated_likes_number = cursor.fetchone()[0]

            if  generated_likes_number <= 0:
                return generated_likes_number
            else:
                connection.rollback()
                raise ConnectionErrorr('post not found')

    def unlike_comment(self, comment_id: int):
            sql = "update comment_table set likes = likes - 1 where comment_id=%s returning likes"
            cursor = connection.cursor()
            cursor.execute(sql, [comment_id])
            connection.commit()
            generated_likes_number = cursor.fetchone()[0]
            
            if generated_likes_number <= 0:
                return generated_likes_number
            else:
                connection.rollback()
                raise ConnectionErrorr('comment not found')

    def liketable_post(self,user_id: int,post_id:int):
        if(user_id!=None and post_id!=None): #None was originally NULL from the line 1 import
            sql = "select * from like_table where user_id = %s and post_id = %s;"
            cursor = connection.cursor()
            cursor.execute(sql, (user_id,post_id))
            if not cursor.fetchone():
                try:
                    sql = "insert into like_table values( %s, %s)"
                    cursor = connection.cursor()
                    cursor.execute(sql, (user_id,post_id))
                    connection.commit()
                    return "Like added"
                except psycopg.errors.ForeignKeyViolation as e:
                    connection.rollback()
                    return"Invalid Post Id or User Id"
            else:
                sql = "delete from like_table where user_id= %s and post_id = %s"
                cursor = connection.cursor()
                cursor.execute(sql, (user_id,post_id))
                connection.commit()
                return "Like Deleted"
        else:
            return "Invalid userId or postId"

    def get_liketable_post_with_user_id_post_id(self, user_id: int, post_id: int) -> Post:
        if(user_id!=None and post_id!=None): #None was originally NULL from the line 1 import
            sql = "select * from like_table where user_id = %s and post_id = %s;"
            cursor = connection.cursor()
            cursor.execute(sql, (user_id,post_id))
            post_record = cursor.fetchone()
            if not post_record:
                return "Like not found"
            else:
                post_record = Post(*post_record)
                return post_record
        else:
            return "Invalid userId or postId"
