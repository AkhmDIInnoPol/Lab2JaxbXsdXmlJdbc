package com.company.interactor;

import com.company.model.jdbc.ConnectionDB;
import com.company.model.jdbc.LessonConnector;
import com.company.model.xjc.Lesson;
import com.company.model.xjc.Lessons;
import com.company.model.xml.JavaObjectXmlConverter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;

/**
 * Class backup {@link com.company.model.xjc.Lesson} table in xml file.
 */
public class BackUpCheckLesson extends Thread
{
    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(BackUpCheckLesson.class);













    private static final String lessonsXmlFileName = "./xml/lesson";

    private LessonConnector lessonConnector;

    private ConnectionDB connectionDB;







    @Override
    public void run() {


        buildLessonConnection();






        saveData();

        deleteTables();

        backupTables();





        connectionDB.closeConnection();
    }







    /**
     * Function build connection with data base
     * for {@link LessonConnector}.
     */
    private void buildLessonConnection()
    {
        connectionDB = new ConnectionDB();
        connectionDB.initConnection();
        Connection connection =  connectionDB.getConnection();

        lessonConnector = new LessonConnector(connection);
    }



    /**
     * Function save data of {@link com.company.model.xjc.Lesson} table in xml files.
     */
    private  void saveData()
    {
        Lessons lessons = lessonConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(lessons, Lessons.class, lessonsXmlFileName);
    }


    /**
     * Delete {@link Lesson} table from data base.
     */
    private void deleteTables()
    {
        lessonConnector.delete();
    }


    /**
     * Function backup {@link Lesson} table from xml to data base data.
     */
    private  void backupTables()
    {
        Lessons journals = JavaObjectXmlConverter.readFromXML(Lessons.class, lessonsXmlFileName);
        lessonConnector.insert(journals);
    }


    /**
     * Log current state of some tables in data base.
     */
    public void logDataInDB()
    {
        Lessons journals = lessonConnector.selectAll();
        for (Lesson lesson:journals.getLessons()
                ) {
            logger.info(lesson.toString());
        }
    }






}
