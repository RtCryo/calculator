package org.example.config;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConfig {

    String url;
    String username;
    String password;
    Connection myConn;

    @Before
    public void initialize (){
        url = "jdbc:postgresql://127.0.0.1:5432/calculator";
        username = "calculator";
        password = "root";
    }

    @Test
    public void dbConnection() throws SQLException {
        myConn = DriverManager.getConnection(url, username, password);
        Assert.assertNotNull(myConn);
    }

    @After
    public void close() throws SQLException {
        myConn.close();
    }

}
