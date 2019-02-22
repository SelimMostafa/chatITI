/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author solo
 */
public class ValidationController {
        Matcher matcher ;
        Pattern pattern ;
        String regex;

    public boolean checkName(String name) {
        regex = "^[\\p{L} .'-]+$";
        
        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(name);
        return matcher.find() && name.length() <= 30 && !name.trim().isEmpty()  ;
    }
    public boolean checkPhone(String phone) {
        regex = "\\d{11}";
        
        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(phone);
        return matcher.find() && phone.length() == 11;
    }
    public boolean checkGender(String gender) {
            return gender.equals("M") || gender.equals("F") ;
    }
    public boolean checkCountry(String country) {
            return country.length() <= 20 && !country.trim().isEmpty() ;
    }
    public boolean checkDOB(String DOB) {
        regex = "\\d{4}-\\d{2}-\\d{2}";
        
        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(DOB);
        return matcher.find();
    }
    public boolean checkPW(String password) {
        regex = "^(?=\\S+$)";
        
        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(password);
        return matcher.find() && password.length() >= 8 ;
    }
    
    public boolean checkEmail(String email) {
        regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,3}$";
        
        // Create a Pattern object
        this.pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        // Now create matcher object.
        matcher = pattern.matcher(email);
        return matcher.find() ;
    }
    public boolean checkBio(String Bio) {
            return Bio.length() <= 200   ;
    }
    

}
