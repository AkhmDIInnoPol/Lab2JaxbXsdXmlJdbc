package com.company.model.jdbc;


import com.company.model.xjc.TaskToGroup;
import com.company.model.xjc.TaskToGroups;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.math.BigInteger;
import java.sql.*;

/**
 * Connector to data base for {@link com.company.model.xjc.TaskToGroup} object.
 */
public class TaskToGroupConnector
{

    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(JournalConnector.class);





    /**
     * Get all tuple from taskToGroup table.
     * @return list of all taskToGroup objects that contain in table.
     */
    public static TaskToGroups selectAll()
    {
        Connection connection = ConnectionDB.getConnection();

        TaskToGroups taskToGroups = new TaskToGroups();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.task_to_group");


            while (result.next())
            {
                TaskToGroup taskToGroup = new TaskToGroup();

                taskToGroup.setId(BigInteger.valueOf(result.getInt("id")));
                taskToGroup.setTaskId(BigInteger.valueOf(result.getInt("task_id")));
                taskToGroup.setStudyGroupId(BigInteger.valueOf(result.getInt("study_group_id")));
                taskToGroup.setIsActive(result.getBoolean("is_active"));
                taskToGroup.setEndDate(GregXMLAndDateSQLConverter
                        .convDateSqlToGregXml(result.getDate("criterion_id")));

                taskToGroups.getTaskToGroups().add(taskToGroup);
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return taskToGroups;
    }


    /**
     * Function insert new tuple of {@link TaskToGroup} object in table.
     * @param taskToGroup - object of {@link TaskToGroup} type.
     */
    public static void insert(TaskToGroup taskToGroup)
    {
        Connection connection = ConnectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO task_to_group(id, task_id, study_group_id, is_active, end_date) \n"
                            + "VALUES (?,?,?,?,?,?)"
            );

            preparedStatement.setInt(1, taskToGroup.getId().intValue());
            preparedStatement.setInt(2, taskToGroup.getTaskId().intValue());
            preparedStatement.setInt(3, taskToGroup.getStudyGroupId().intValue());
            preparedStatement.setBoolean(4, taskToGroup.isIsActive());
            preparedStatement.setDate(5,
                    GregXMLAndDateSQLConverter.convGregXmlToDateSql(taskToGroup.getEndDate()));

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }



    /**
     * Delete table task_to_group in data base.
     */
    public void delete()
    {
        Connection connection = ConnectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();

            String query = "DROP TABLE task_to_group ";
            statement.executeUpdate(query);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }

    }


}
