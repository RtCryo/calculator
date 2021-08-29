package org.example.service;

import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class SqlService {
    private static Connection connection;

    public SqlService() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = getConnection();
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/calculator3", "calculator", "root");
        }
        return connection;
    }
}
