package commonservice;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable{

    private String fontStyle;
    private String fontColor;
    private String textBackGround;
    private String messageText;
    private String fontSize;
    private boolean italic;
    private boolean bold;
    private boolean underlined;
    private Timestamp timestamp;
    private User sender;

    public Message(User sender) {
        fontColor = "BLUE";
        fontStyle = "comic-sans";
        fontSize = "10";
        textBackGround = "WHITE";
        italic=false;
        bold=false;
        underlined=false;
        timestamp=getTimestamp();
        this.sender=sender;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getTextBackGround() {
        return textBackGround;
    }

    public void setTextBackGround(String textBackGround) {
        this.textBackGround = textBackGround;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

}
