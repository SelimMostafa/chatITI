/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import commonservice.ServerService;
import commonservice.User;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import mychatclient.model.ClientModel;
import mychatclient.view.view.HomePageBase;

/**
 *
 * @author HP
 */
public class AddContactHandler implements EventHandler {

    HomePageBase addContactObject;
    ServerService serverservice;
    User user;

    //  String phone ;
    public AddContactHandler(HomePageBase addContactObject) {

        this.addContactObject = addContactObject;
        //  this.serverservice = this.addContactObject.serverservice;
        this.serverservice = ClientModel.serverservice;
        this.user = addContactObject.getUser();
//        this.phone = this.addContactObject.getPhoneTF().getText();
    }

    @Override
    public void handle(Event event) {

        for (int counter = 0; counter < this.addContactObject.getContactsTF().size(); counter++) {
            User friend = null;
            String phone = this.addContactObject.getContactsTF().get(counter).getText();

            if (!phone.equals("") && !phone.equals(user.getPhoneNum())) {
                try {

                    friend = this.serverservice.addNewContact(this.addContactObject.getContactsTF().get(counter).getText());
                    if (friend == null) {
                        System.out.println("Doesn't Exist in the DB or is already a friend or doesn't request user");

                    } else {

                        System.out.println("Exist And his name is " + friend.getName());
                        friend = null;

                    }

                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            } else {
                if (phone.equals("")) {
                    System.out.println("Empty TF");
                } else if (phone.equals(user.getPhoneNum())) {
                    System.out.println("Cannot add yourself!!");
                }
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

//                addContactObject.getAnchorPane4().getChildren().removeAll(addContactObject.getContactsTF());
                addContactObject.getContactsTF().clear();
                addContactObject.getPhoneAddedTF().setText("");
            }
        });
    }
}
