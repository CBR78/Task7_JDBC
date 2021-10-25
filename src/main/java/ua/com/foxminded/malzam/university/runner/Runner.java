package ua.com.foxminded.malzam.university.runner;

import ua.com.foxminded.malzam.university.service.DataTableLoader;
import ua.com.foxminded.malzam.university.service.TableReCreator;

public class Runner {

    public static void main() {
        new TableReCreator().reCreateTable();
        new DataTableLoader().loadGeneratedData();
        new MainConsole().printMainMenu();
    }
}
