package com.ec9590.sims.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream is = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            Properties props = new Properties();
            props.load(is);

            Class.forName(props.getProperty("db.driver"));
            URL      = props.getProperty("db.url");
            USER     = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                "Failed to load DB configuration: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Prevent instantiation
    private DBConnection() {}
}
