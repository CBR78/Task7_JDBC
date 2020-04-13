package ua.com.foxminded.malzam.university.runner;

import ua.com.foxminded.malzam.university.dao.TableReCreatorDao;
import ua.com.foxminded.malzam.university.service.DataTableLoader;

public class Runner {

    public static void main(String[] args) {
        new TableReCreatorDao().reCreateTable();
        new DataTableLoader().loadGeneratedData();
        new MainConsole().showMainMenu();
    }
}
