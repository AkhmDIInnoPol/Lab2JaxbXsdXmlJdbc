package com.company.model.jdbc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class initialize connection with data base.
 */
public class ConnectionDB
{

    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(ConnectionDB.class);


    private static Connection connection;


    /**
     * Function initialize connection with data base and return
     * connection object for communication with data base.
     * @return - connection object for communication with data base.
     */
    public static Connection initConnection()
    {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SRMStudyCenter", "ProjectUser", "1234");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return connection;
    }


    public static Connection getConnection() {
        return connection;
    }


    /**
     * Close connection with data base.
     */
    public static void closeConnection()
    {
        try {
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
}
