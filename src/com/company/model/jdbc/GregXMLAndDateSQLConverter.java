package com.company.model.jdbc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Created by Di on 15.04.2017.
 */
public class GregXMLAndDateSQLConverter
{

    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(GregXMLAndDateSQLConverter.class);





    /**
     * Function convert {@link XMLGregorianCalendar} to {@link  java.sql.Date}
     * @param gregXML - date in {@link XMLGregorianCalendar} format.
     * @return - date in {@link  java.sql.Date} format.
     */
    public static Date convGregXmlToDateSql(XMLGregorianCalendar gregXML)
    {
        long time = gregXML.toGregorianCalendar().getTime().getTime();
        return new Date(time);
    }


    /**
     * Function convert {@link  java.sql.Date} to {@link XMLGregorianCalendar}.
     * @param dateSql - date in {@link  java.sql.Date}.
     * @return date in {@link XMLGregorianCalendar}.
     */
    public static XMLGregorianCalendar convDateSqlToGregXml(Date dateSql)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateSql);

        XMLGregorianCalendar gregXml = null;

        try {
           gregXml = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        }
        catch (DatatypeConfigurationException ex)
        {
            logger.error(ex.getMessage());
        }

        return gregXml;
    }






}
