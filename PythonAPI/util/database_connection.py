import os

from psycopg import connect, OperationalError


def create_connection():
    try:
        conn = connect(
            host=       "revatureproject3.c3f2ribjt3t3.us-east-1.rds.amazonaws.com", # os.environ.get("HOST"),
            dbname=     "postgres", # os.environ.get("DBNAME"),
            user=       "thebatch", # os.environ.get("USER"),
            password=   "revaturePythonJava", # os.environ.get("PASSWORD"),
            port=       5432 # os.environ.get("PORT")
        )
        return conn
    except OperationalError as e:
        print(str(e))


connection = create_connection()
print(connection)
