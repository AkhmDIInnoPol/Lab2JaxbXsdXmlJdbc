//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.15 at 04:02:10 PM MSK 
//


package com.company.model.xjc;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Journal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Journal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="lesson_id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="student_id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="time_check" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="is_deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Journal", propOrder = {
    "id",
    "lessonId",
    "studentId",
    "timeCheck",
    "isDeleted"
})
public class Journal {

    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(name = "lesson_id", required = true)
    protected BigInteger lessonId;
    @XmlElement(name = "student_id", required = true)
    protected BigInteger studentId;
    @XmlElement(name = "time_check", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeCheck;
    @XmlElement(name = "is_deleted")
    protected boolean isDeleted;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the lessonId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLessonId() {
        return lessonId;
    }

    /**
     * Sets the value of the lessonId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLessonId(BigInteger value) {
        this.lessonId = value;
    }

    /**
     * Gets the value of the studentId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudentId() {
        return studentId;
    }

    /**
     * Sets the value of the studentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudentId(BigInteger value) {
        this.studentId = value;
    }

    /**
     * Gets the value of the timeCheck property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeCheck() {
        return timeCheck;
    }

    /**
     * Sets the value of the timeCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeCheck(XMLGregorianCalendar value) {
        this.timeCheck = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     */
    public void setIsDeleted(boolean value) {
        this.isDeleted = value;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", studentId=" + studentId +
                ", timeCheck=" + timeCheck +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
