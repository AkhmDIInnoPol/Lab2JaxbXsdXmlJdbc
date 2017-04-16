package com.company.test.model.jdbc;


import com.company.model.jdbc.GregXMLAndDateSQLConverter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Di on 15.04.2017.
 */
class GregXMLAndDateSQLConverterTest
{

    static {
        PropertyConfigurator.configure("./src/com/company/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(GregXMLAndDateSQLConverterTest.class);




    @Test
    void convGregXmlToDateSqlTest() {

        int year = 2017;
        int month = 4;
        int dayOfMonth = 21;
        int hourOfDay = 8;
        int minute = 7;
        int second = 6;



        GregorianCalendar calendar = new GregorianCalendar(year, month,
                                            dayOfMonth,hourOfDay,minute, second);
        try {
            XMLGregorianCalendar xmlGregorianCalendar=
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            Date date = GregXMLAndDateSQLConverter.convGregXmlToDateSql(xmlGregorianCalendar);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            assertTrue(year == cal.get(Calendar.YEAR));
            assertTrue(month == cal.get(Calendar.MONTH));
            assertTrue(dayOfMonth == cal.get(Calendar.DAY_OF_MONTH));
            assertTrue(hourOfDay == cal.get(Calendar.HOUR));
            assertTrue(minute == cal.get(Calendar.MINUTE));
            assertTrue(second == cal.get(Calendar.SECOND));
        }
        catch (DatatypeConfigurationException ex)
        {
            logger.error(ex.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void convDateSqlToGregXmlTest() {





    }

}