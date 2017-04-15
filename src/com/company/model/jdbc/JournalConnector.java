package com.company.model.jdbc;

import com.company.model.xjc.Journal;
import com.company.model.xjc.Journals;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * Connector to data base for {@link Journal} object.
 */
public class JournalConnector
{


    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(JournalConnector.class);


    /**
     * Get all tuple from journal table
     * @return list of all journal objects that contain in table.
     */
    public static Journals selectAll()
    {
        Connection connection = ConnectionDB.getConnection();

        Journals journals = new Journals();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.journal");


            while (result.next())
            {
                Journal journal = new Journal();

                journal.setId(BigInteger.valueOf(result.getInt("id")));
                journal.setLessonId(BigInteger.valueOf(result.getInt("lesson_id")));
                journal.setStudentId(BigInteger.valueOf(result.getInt("student_id")));
                journal.setTimeCheck(GregXMLAndDateSQLConverter
                        .convDateSqlToGregXml(result.getDate("time_check")));
                journal.setIsDeleted(result.getBoolean("is_deleted"));

                journals.getJournals().add(journal);
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return journals;
    }


    /**
     * Function insert new tuple of {@link Journal} object in table.
     * @param journal - object of {@link Journal} type.
     */
    public static void insert(Journal journal)
    {
        Connection connection = ConnectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO journal(id, lesson_id, student_id, time_check, is_deleted) \n"
                    + "VALUES (?,?,?,?,?)"
            );

            preparedStatement.setInt(1, journal.getId().intValue());
            preparedStatement.setInt(2, journal.getLessonId().intValue());
            preparedStatement.setInt(3, journal.getStudentId().intValue());
            preparedStatement.setDate(4, GregXMLAndDateSQLConverter
                                                        .convGregXmlToDateSql(journal.getTimeCheck()));
            preparedStatement.setBoolean(5, journal.isIsDeleted());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }


    /**
     * Delete table journal in data base.
     */
    public void delete()
    {
        Connection connection = ConnectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();

            String query = "DROP TABLE journal ";
            statement.executeUpdate(query);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }

    }



}
