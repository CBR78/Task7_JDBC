package ua.com.foxminded.malzam.university.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableReCreatorDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";
    private static final String NAME_SCRIPT_FILE = "reCreateTable.sql";

    public void reCreateTable() {
        ClassLoader loader = getClass().getClassLoader();
        URL urlScriptFile = loader.getResource(NAME_SCRIPT_FILE);
        File scriptFile = new File(urlScriptFile.getFile());

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                BufferedReader br = new BufferedReader(new FileReader(scriptFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                statement.execute(line);
            }
        } catch (Exception ex) {
            System.out.println("TableReCreatorDao.reCreateTable failed...");
            System.out.println(ex);
        }
    }
}
