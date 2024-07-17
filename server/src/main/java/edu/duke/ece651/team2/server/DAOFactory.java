package edu.duke.ece651.team2.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties properties = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new RuntimeException("Properties file '" + PROPERTIES_FILE + "' not found in the classpath");
        }

        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load properties file '" + PROPERTIES_FILE + "'", e);
        }
    }

    private final String url;
    private final String username;
    private final String password;

    public DAOFactory() {
        url = properties.getProperty("javabase.jdbc.url");
        username = properties.getProperty("javabase.jdbc.username");
        password = properties.getProperty("javabase.jdbc.password");
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
