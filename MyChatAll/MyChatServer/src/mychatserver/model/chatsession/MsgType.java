//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.25 at 03:58:35 AM EET 
//


package mychatserver.model.chatsession;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}from"/>
 *         &lt;element ref="{}to" maxOccurs="unbounded"/>
 *         &lt;element ref="{}time"/>
 *         &lt;element ref="{}fontStyle"/>
 *         &lt;element ref="{}fontColor"/>
 *         &lt;element ref="{}textBackGround"/>
 *         &lt;element ref="{}fontSize"/>
 *         &lt;element ref="{}italic"/>
 *         &lt;element ref="{}bold"/>
 *         &lt;element ref="{}underlined"/>
 *         &lt;element ref="{}body"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MsgType", propOrder = {
    "from",
    "to",
    "time",
    "fontStyle",
    "fontColor",
    "textBackGround",
    "fontSize",
    "italic",
    "bold",
    "underlined",
    "body"
})
public class MsgType {

    @XmlElement(required = true)
    protected String from;
    @XmlElement(required = true)
    protected List<String> to;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    @XmlElement(required = true)
    protected String fontStyle;
    @XmlElement(required = true)
    protected String fontColor;
    @XmlElement(required = true)
    protected String textBackGround;
    @XmlElement(required = true)
    protected String fontSize;
    protected boolean italic;
    protected boolean bold;
    protected boolean underlined;
    @XmlElement(required = true)
    protected String body;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the to property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTo() {
        if (to == null) {
            to = new ArrayList<String>();
        }
        return this.to;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the fontStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontStyle() {
        return fontStyle;
    }

    /**
     * Sets the value of the fontStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontStyle(String value) {
        this.fontStyle = value;
    }

    /**
     * Gets the value of the fontColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontColor() {
        return fontColor;
    }

    /**
     * Sets the value of the fontColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontColor(String value) {
        this.fontColor = value;
    }

    /**
     * Gets the value of the textBackGround property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextBackGround() {
        return textBackGround;
    }

    /**
     * Sets the value of the textBackGround property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextBackGround(String value) {
        this.textBackGround = value;
    }

    /**
     * Gets the value of the fontSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontSize() {
        return fontSize;
    }

    /**
     * Sets the value of the fontSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontSize(String value) {
        this.fontSize = value;
    }

    /**
     * Gets the value of the italic property.
     * 
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Sets the value of the italic property.
     * 
     */
    public void setItalic(boolean value) {
        this.italic = value;
    }

    /**
     * Gets the value of the bold property.
     * 
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Sets the value of the bold property.
     * 
     */
    public void setBold(boolean value) {
        this.bold = value;
    }

    /**
     * Gets the value of the underlined property.
     * 
     */
    public boolean isUnderlined() {
        return underlined;
    }

    /**
     * Sets the value of the underlined property.
     * 
     */
    public void setUnderlined(boolean value) {
        this.underlined = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

}