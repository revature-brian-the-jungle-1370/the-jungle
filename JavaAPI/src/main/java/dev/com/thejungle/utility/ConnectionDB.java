package dev.com.thejungle.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection createConnection() {
        try {
            String dbURL = String.format(
                    "jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
                    "revatureproject3.c3f2ribjt3t3.us-east-1.rds.amazonaws.com",// System.getenv("HOST"),
                    5432,// System.getenv("PORT"),
                    "postgres",// System.getenv("DB"),
                    "thebatch",// System.getenv("USER"),
                    "revaturePythonJava"// System.getenv("PASSWORD")
            );
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        System.out.println(createConnection());
    }
}
