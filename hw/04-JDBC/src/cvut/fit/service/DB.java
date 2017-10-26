package cvut.fit.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

    private static Connection connection;


    public static Connection getConnection() {
        try {
            if (connection == null) {
                initConnection();
            }
            return connection;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static void initConnection() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://147.32.233.18:3306/mimdw?user=mimdw&password=mimdw");
    }
}
