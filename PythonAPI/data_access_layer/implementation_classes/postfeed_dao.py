#from asyncio.windows_events import NULL
from typing import List

import psycopg

from custom_exceptions.connection_error import ConnectionErrorr
from data_access_layer.abstract_classes.postfeed_dao_abs import PostFeedDao
from entities.post import Post
from util.database_connection import connection


class PostFeedDaoImp(PostFeedDao):

    def get_all_posts(self) -> List[Post]:
        try:
            sql = "select * from post_table"
            cursor = connection.cursor()
            cursor.execute(sql)
            post_record = cursor.fetchall()
            post_list = []
            for post in post_record:
                post_list.append(Post(*post))
            return post_list
        except ConnectionErrorr:
            return "something went wrong"

    def delete_a_post(self, post_id: int) -> bool:
        try:
            sql = " delete from post_table where post_id = %s"
            cursor = connection.cursor()
            cursor.execute(sql, [post_id])
            connection.commit()
            return True
        except ConnectionErrorr:
            connection.rollback()
            return False

    def get_all_posts_with_user_id(self, user_id: int) -> List[Post]:
        sql = "select * from post_table where user_id = %s  order by date_time_of_creation desc"
        cursor = connection.cursor()
        cursor.execute(sql, [user_id])
        post_records = cursor.fetchall()
        post_list = []
        for post in post_records:
            post_list.append(Post(*post))
        return post_list

    def get_all_bookmarkded_posts(self, user_id: int) -> List[Post]:
        sql = "select post_id from bookmark_table where user_id = %s"
        cursor =connection.cursor()
        cursor.execute(sql,[user_id])
        post_ids= cursor.fetchall()
        post_list=[]
        if(post_ids!=None): #originally NULL from the line 1 import
            for post_id in post_ids:
                (post_id_int)=post_id
                cursor.execute("select * from post_table where post_id = %s;",post_id_int)
                post=cursor.fetchone()
                post_list.append(Post(*post))
            return post_list 
        else:
            return  "No Data Found"

    def bookmark_post(self,user_id: int,post_id:int):
        if(user_id!=None and post_id!=None): #None was originally NULL from the line 1 import
            sql = "select * from bookmark_table where user_id = %s and post_id = %s;"
            cursor = connection.cursor()
            cursor.execute(sql, (user_id,post_id))
            if not cursor.fetchone():
                try:
                    sql = "insert into bookmark_table values( %s, %s)"
                    cursor = connection.cursor()
                    cursor.execute(sql, (user_id,post_id))
                    connection.commit()
                    return "Bookmark added"
                except psycopg.errors.ForeignKeyViolation as e:
                    connection.rollback()
                    return"Invalid Post Id or User Id"
            else:
                sql = "delete from bookmark_table where user_id= %s and post_id = %s"
                cursor = connection.cursor()
                cursor.execute(sql, (user_id,post_id))
                connection.commit()
                return "Bookmark Deleted"
        else:
            return "Invalid userId or postId"

    
