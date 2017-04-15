package com.company;

import com.company.model.xjc.Journal;
import com.company.model.xjc.Journals;
import com.company.model.xml.JavaObjectXmlConverter;


import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        testJournalConnector();

    }




    public static void testJournalConnector()
    {

//        ============ SELECT
//        ConnectionDB.initConnection();
//        List<Journal> journals = JournalConnector.selectAll();
//        for (Journal journal:journals
//                ) {
//            System.out.println(journal);
//        }







//         ========= INSERT
//        ConnectionDB.initConnection();
//
//        GregorianCalendar calendar = new GregorianCalendar(2020,7,11);
//        Journal journal = new Journal();
//        journal.setId(BigInteger.valueOf(6));
//        journal.setLessonId(BigInteger.valueOf(1));
//        journal.setStudentId(BigInteger.valueOf(2));
//        try {
//            journal.setTimeCheck(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
//        }
//        catch (DatatypeConfigurationException ex)
//        {
//            ex.printStackTrace();
//        }
//        journal.setIsDeleted(false);
//
//        JournalConnector.insert(journal);
//
//        ConnectionDB.closeConnection();




        // ================ Marshalling
//        GregorianCalendar calendar = new GregorianCalendar(2020,7,11);
//        Journal journal = new Journal();
//        journal.setId(BigInteger.valueOf(6));
//        journal.setLessonId(BigInteger.valueOf(1));
//        journal.setStudentId(BigInteger.valueOf(2));
//        try {
//            journal.setTimeCheck(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
//        }
//        catch (DatatypeConfigurationException ex)
//        {
//            ex.printStackTrace();
//        }
//        journal.setIsDeleted(false);
//
//
//
//        GregorianCalendar calendar2 = new GregorianCalendar(2018,6,14);
//        Journal journal2 = new Journal();
//        journal2.setId(BigInteger.valueOf(5));
//        journal2.setLessonId(BigInteger.valueOf(1));
//        journal2.setStudentId(BigInteger.valueOf(2));
//        try {
//            journal2.setTimeCheck(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
//        }
//        catch (DatatypeConfigurationException ex)
//        {
//            ex.printStackTrace();
//        }
//        journal2.setIsDeleted(false);
//
//
//
//        List<Journal> journals = new ArrayList<>();
//        journals.add(journal);
//        journals.add(journal2);
//
//        Journals journals1 = new Journals();
//        journals1.getJournals().addAll(journals);
//
//        JavaObjectXmlConverter.writeToXML(journals1, Journals.class, "./xml/journal");









        // Unmarshalling
        List<Journal> journals;



        journals = ((Journals)JavaObjectXmlConverter.readFromXML(Journals.class, "./xml/journal")).getJournals();
        for (Journal journal:journals
                ) {
            System.out.println(journal);
        }
    }




}
