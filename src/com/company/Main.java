package com.company;

import com.company.interactor.BackUpValidation;
import com.company.model.jdbc.ConnectionDB;
import com.company.model.xjc.Journal;
import com.company.model.xjc.Journals;
import com.company.model.xjc.Mark;
import com.company.model.xml.JavaObjectXmlConverter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {


    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(Main.class);







    public static void main(String[] args) {

        ConnectionDB.initConnection();
        logger.info("Save data in XML.\n");
        BackUpValidation.saveData();
        logger.info("Print data in data base:");
        BackUpValidation.logDataInDB();
        logger.info("\n");
        logger.info("Delete tables content in data base.\n");
        BackUpValidation.deleteTables();
        logger.info("Print data in data base:");
        BackUpValidation.logDataInDB();
        logger.info("Backup data in data base.\n");
        BackUpValidation.backupTables();
        logger.info("Print data in data base:");
        BackUpValidation.logDataInDB();
    }



}
