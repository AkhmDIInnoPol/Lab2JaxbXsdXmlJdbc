//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.16 at 10:54:38 AM MSK 
//


package model.xjc;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SomeModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SomeModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intVal" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="doubleVal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="boolVal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stringVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SomeModel", propOrder = {
    "intVal",
    "doubleVal",
    "boolVal",
    "stringVal"
})
public class SomeModel {

    @XmlElement(required = true)
    protected BigInteger intVal;
    @XmlElement(required = true)
    protected BigDecimal doubleVal;
    protected boolean boolVal;
    @XmlElement(required = true)
    protected String stringVal;

    /**
     * Gets the value of the intVal property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIntVal() {
        return intVal;
    }

    /**
     * Sets the value of the intVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIntVal(BigInteger value) {
        this.intVal = value;
    }

    /**
     * Gets the value of the doubleVal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDoubleVal() {
        return doubleVal;
    }

    /**
     * Sets the value of the doubleVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDoubleVal(BigDecimal value) {
        this.doubleVal = value;
    }

    /**
     * Gets the value of the boolVal property.
     * 
     */
    public boolean isBoolVal() {
        return boolVal;
    }

    /**
     * Sets the value of the boolVal property.
     * 
     */
    public void setBoolVal(boolean value) {
        this.boolVal = value;
    }

    /**
     * Gets the value of the stringVal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringVal() {
        return stringVal;
    }

    /**
     * Sets the value of the stringVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringVal(String value) {
        this.stringVal = value;
    }

}
