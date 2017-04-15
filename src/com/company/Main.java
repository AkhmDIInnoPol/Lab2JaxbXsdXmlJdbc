package com.company;

import com.company.model.jdbc.ConnectionDB;
import com.company.model.jdbc.JournalConnector;
import com.company.model.xjc.Journal;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        testJournalConnector();

    }




    public static void testJournalConnector()
    {
//        ConnectionDB.initConnection();
//        List<Journal> journals = JournalConnector.selectAll();
//        for (Journal journal:journals
//                ) {
//            System.out.println(journal);
//        }


        ConnectionDB.initConnection();

        GregorianCalendar calendar = new GregorianCalendar(2020,7,11);
        Journal journal = new Journal();
        journal.setId(BigInteger.valueOf(6));
        journal.setLessonId(BigInteger.valueOf(1));
        journal.setStudentId(BigInteger.valueOf(2));
        try {
            journal.setTimeCheck(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        }
        catch (DatatypeConfigurationException ex)
        {
            ex.printStackTrace();
        }
        journal.setIsDeleted(false);

        JournalConnector.insert(journal);

        ConnectionDB.closeConnection();
    }



}
