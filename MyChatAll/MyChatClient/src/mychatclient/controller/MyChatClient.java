/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.controller;

import mychatclient.view.view.RegisterForm;
import commonservice.ServerService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        RegisterForm root = new RegisterForm();
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        new MyChatClient();

    }

}
