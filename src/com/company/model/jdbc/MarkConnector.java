package com.company.model.jdbc;


import com.company.model.xjc.Mark;
import com.company.model.xjc.Marks;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.math.BigInteger;
import java.sql.*;


/**
 * Connector to data base for {@link com.company.model.xjc.Mark} object.
 */
public class MarkConnector
{


    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(JournalConnector.class);


    /**
     * Get all tuple from mark table
     * @return list of all mark objects that contain in table.
     */
    public static Marks selectAll()
    {
        Connection connection = ConnectionDB.getConnection();

        Marks marks = new Marks();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.mark");


            while (result.next())
            {
                Mark mark = new Mark();

                mark.setId(BigInteger.valueOf(result.getInt("id")));
                mark.setIsDeleted(result.getBoolean("is_deleted"));
                mark.setStudentId(BigInteger.valueOf(result.getInt("student_id")));
                mark.setTaskId(BigInteger.valueOf(result.getInt("task_id")));
                mark.setCriterionId(BigInteger.valueOf(result.getInt("criterion_id")));
                mark.setPoints(BigInteger.valueOf(result.getInt("points")));

                marks.getMarks().add(mark);
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return marks;
    }


    /**
     * Function insert new tuple of {@link Mark} object in table.
     * @param mark - object of {@link Mark} type.
     */
    public static void insert(Mark mark)
    {
        Connection connection = ConnectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO mark(id, is_deleted, student_id, task_id, criterion_id, points) \n"
                            + "VALUES (?,?,?,?,?,?)"
            );

            preparedStatement.setInt(1, mark.getId().intValue());
            preparedStatement.setBoolean(2, mark.isIsDeleted());
            preparedStatement.setInt(3, mark.getStudentId().intValue());
            preparedStatement.setInt(4, mark.getTaskId().intValue());
            preparedStatement.setInt(5, mark.getCriterionId().intValue());
            preparedStatement.setInt(6, mark.getPoints().intValue());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }


    /**
     * Delete table mark in data base.
     */
    public void delete()
    {
        Connection connection = ConnectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();

            String query = "DROP TABLE mark ";
            statement.executeUpdate(query);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }

    }





}
