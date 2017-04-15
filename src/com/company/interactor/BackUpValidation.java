package com.company.interactor;

import com.company.model.jdbc.JournalConnector;
import com.company.model.jdbc.MarkConnector;
import com.company.model.jdbc.TaskToGroupConnector;
import com.company.model.xjc.*;
import com.company.model.xml.JavaObjectXmlConverter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Class backup some tables in xml files.
 */
public class BackUpValidation
{

    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(BackUpValidation.class);





    private static final String journalsXmlFileName = "./xml/journal";
    private static final String marksXmlFileName = "./xml/mark";
    private static final String taskToGroupXmlFileName = "./xml/taskToGroup";


    /**
     * Function save data of some tables in xml files.
     */
    public static void saveData()
    {
        Journals journals = JournalConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(journals, Journals.class, journalsXmlFileName);

        Marks marks = MarkConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(marks, Marks.class, marksXmlFileName);

        TaskToGroups taskToGroups = TaskToGroupConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(taskToGroups, TaskToGroups.class, taskToGroupXmlFileName);
    }


    /**
     * Delete some tables from data base.
     */
    public static void deleteTables()
    {
        JournalConnector.delete();
        MarkConnector.delete();
        TaskToGroupConnector.delete();
    }


    /**
     * Function backup some tables from xml to data base data.
     */
    public static void backupTables()
    {
        Journals journals = JavaObjectXmlConverter.readFromXML(Journals.class, journalsXmlFileName);
        JournalConnector.insert(journals);

        Marks marks = JavaObjectXmlConverter.readFromXML(Marks.class, marksXmlFileName);
        MarkConnector.insert(marks);

        TaskToGroups taskToGroups = JavaObjectXmlConverter
                .readFromXML(TaskToGroups.class, taskToGroupXmlFileName);
        TaskToGroupConnector.insert(taskToGroups);
    }


    /**
     * Log current state of some tables in data base.
     */
    public static void logDataInDB()
    {
        Journals journals = JournalConnector.selectAll();
        for (Journal journal:journals.getJournals()
             ) {
            logger.info(journal.toString());
        }

        Marks marks = MarkConnector.selectAll();
        for (Mark mark:marks.getMarks()
                ) {
            logger.info(mark.toString());
        }

        TaskToGroups taskToGroups = TaskToGroupConnector.selectAll();
        for (TaskToGroup taskToGroup:taskToGroups.getTaskToGroups()
                ) {
            logger.info(taskToGroup.toString());
        }
    }



}
