/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.controller;

import mychatclient.view.view.RegisterForm;
import commonservice.ServerService;
import commonservice.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mychatclient.model.ClientModel;

/**
 *
 * @author AmrHesham
 */
public class MyChatClient extends Application {

   
    ClientModel model;
    
    public MyChatClient() {

        model = new ClientModel();
    }

    public void start(Stage primaryStage) {
        try {
            //RegisterForm root = new RegisterForm();
            
            //it didnt work the normal and we checked stackoverflow and they suggested that we put the path into the url anad pass it
            // to the FXMLLoader.load()
            //URL url = new File("/mychatclient/view/view/LoginForm.fxml").toURL();
            Parent root=FXMLLoader.load(getClass().getResource("/mychatclient/view/view/LoginForm.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("My Chat");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MyChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        MyChatClient myChatClient=new MyChatClient();

    }

    public boolean checkUser(String phoneNum) {
        return model.checkUser(phoneNum);
    }

    public boolean registerNewUser(User user) {
        return model.registerNewUser(user);
    }
    
    public ArrayList<String> getRequests(User user) {
        return model.getRequests(user);
    }
    
    

}
