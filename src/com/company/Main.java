package com.company;

import com.company.interactor.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {


    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(Main.class);








    public static void main(String[] args) {


        BackUpCheckJournal backUpCheckJournalThread = new BackUpCheckJournal();
        backUpCheckJournalThread.start();

        BackUpCheckMark backUpCheckMarkThread = new BackUpCheckMark();
        backUpCheckMarkThread.start();

        BackUpCheckTaskToGroup backUpCheckTaskToGroupThread = new BackUpCheckTaskToGroup();
        backUpCheckTaskToGroupThread.start();

    }



}
