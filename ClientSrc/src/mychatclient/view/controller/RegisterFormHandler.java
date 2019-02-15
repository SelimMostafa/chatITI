/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import mychatclient.view.view.RegisterForm;
import commonservice.ServerService;
import commonservice.User;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author AmrHesham
 */
public class RegisterFormHandler implements EventHandler {

    RegisterForm gui;
    User user = new User();
    ServerService serverservice;

    RegisterFormHandler(ServerService serverservice) {
        this.serverservice=serverservice;
    }

    @Override
    public void handle(Event event) {
        try {
            user.setName(gui.getNameTF().getText());
            user.setPhoneNum(gui.getPhoneNumberTF().getText());
            user.setGender(gui.getGenderTF().getText());
            user.setCountry(gui.getCountryTF().getText());
            user.setDateOfBirth(gui.getDateOfBirthTF().getText());
            user.setPassword(gui.getPasswordTF().getText());
            user.setEmail(gui.getEmailTF().getText());
            user.setBIO(gui.getBioTF().getText());
            //serverservice.register(user);
            System.out.println(serverservice.register(user));
        } catch (RemoteException ex) {
            Logger.getLogger(RegisterFormHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RegisterFormHandler(RegisterForm gui, ServerService serverservice) {

        this.gui = gui;
        this.serverservice = serverservice;

    }

}
