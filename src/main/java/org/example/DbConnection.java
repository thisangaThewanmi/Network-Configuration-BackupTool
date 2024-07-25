package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    public static java.lang.System System;
    private static  DbConnection dbConnection;

    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CNS","root", "Ijse@1234");
        System.out.println("Connected to database");
    }

    static DbConnection getInstance() throws SQLException {
        if(dbConnection == null) {
            return new DbConnection();
        }
        return dbConnection;
    }

    public  Connection getConnection() {
        return connection;
    }
}
