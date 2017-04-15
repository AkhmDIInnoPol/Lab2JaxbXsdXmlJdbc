package com.company.interactor;

import com.company.model.jdbc.JournalConnector;
import com.company.model.jdbc.MarkConnector;
import com.company.model.jdbc.TaskToGroupConnector;
import com.company.model.xjc.*;
import com.company.model.xml.JavaObjectXmlConverter;

/**
 * Created by Di on 15.04.2017.
 */
public class BackUpValidation
{

    private final String journalsXmlFileName = "./xml/journal";
    private final String marksXmlFileName = "./xml/mark";
    private final String taskToGroupXmlFileName = "./xml/taskToGroup";


    public void saveData()
    {
        Journals journals = JournalConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(journals, Journal.class, journalsXmlFileName);

        Marks marks = MarkConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(marks, Mark.class, marksXmlFileName);

        TaskToGroups taskToGroups = TaskToGroupConnector.selectAll();
        JavaObjectXmlConverter.writeToXML(taskToGroups, TaskToGroup.class, taskToGroupXmlFileName);
    }







}
