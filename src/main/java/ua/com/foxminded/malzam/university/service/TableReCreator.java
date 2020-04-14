package ua.com.foxminded.malzam.university.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import ua.com.foxminded.malzam.university.dao.DBConnection;

public class TableReCreator {
    private static final String NAME_SCRIPT_FILE = "createtable.sql";

    public void reCreateTable() {
        ClassLoader loader = getClass().getClassLoader();
        URL urlScriptFile = loader.getResource(NAME_SCRIPT_FILE);
        File scriptFile = new File(urlScriptFile.getFile());

        try (Connection connection = DBConnection.getInstance();
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
