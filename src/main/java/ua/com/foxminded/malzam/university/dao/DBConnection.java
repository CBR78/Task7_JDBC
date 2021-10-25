package ua.com.foxminded.malzam.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static final String PATH_TO_PROPERTIES = "src\\main\\resources\\";

    private DBConnection() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getInstance() {
        Connection connection = null;
        Properties props = null;

        try (FileInputStream in = new FileInputStream(PATH_TO_PROPERTIES)) {
            props = new Properties();
            props.load(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        try {
            assert props != null;
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return connection;
    }
}
