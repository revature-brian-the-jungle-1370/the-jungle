package dev.com.thejungle.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection createConnection() {
        try {
//            String dbURL = String.format(
//                    "jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
//                    System.getenv("HOST"),
//                    System.getenv("PORT"),
//                    System.getenv("DB"),
//                    System.getenv("USER"),
//                    System.getenv("PASSWORD")
//            );
            String dbURL="jdbc:postgresql://revatureproject3.c3f2ribjt3t3.us-east-1.rds.amazonaws.com:5432/postgres?user=thebatch&password=revaturePythonJava";
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
