/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonservice;

import java.io.Serializable;

/**
 *
 * @author AmrHesham
 */
public class User implements Serializable {

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBIO(String BIO) {
        this.BIO = BIO;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBIO() {
        return BIO;
    }
    private String name;
    private String phoneNum;
    private String Email;
    private String password;
    private String gender;
    private String country;
    private String dateOfBirth;
    private String BIO;
    private int chatBotStatus;
    private String status;
    private String mode;
    private byte[] picture;
    private int entryTimes ;
    
    public User(String name, String phoneNum, String Email, String password, String gender, String country, String dateOfBirth, String BIO) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.Email = Email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.BIO = BIO;
    }

    public int getChatBotStatus() {
        return chatBotStatus;
    }

    public void setChatBotStatus(int chatBotStatus) {
        this.chatBotStatus = chatBotStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getEntryTimes() {
        return entryTimes;
    }

    public void setEntryTimes(int entryTimes) {
        this.entryTimes = entryTimes;
    }

    
    @Override
    public String toString() {
        return "UserEntity{" + "name=" + name + ", phoneNumber=" + phoneNum + ", country=" + country + ", dateOfBirth=" + dateOfBirth + ", password=" + password + ", email=" + Email + ", bio=" + BIO + '}';
    }

}
