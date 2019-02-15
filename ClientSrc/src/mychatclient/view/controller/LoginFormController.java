/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import commonservice.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;
import static mychatclient.model.ClientModel.serverservice;
import mychatclient.view.view.RegisterForm;

/**
 * FXML Controller class
 *
 * @author AmrHesham
 */
public class LoginFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField phoneNumberTF;
    
    @FXML
    Button  signInButton;
    
    @FXML
    Button  signUpButton;
    
    @FXML
    PasswordField passswordPF;
    User user=null;
    MyChatClient controller;
    Stage stage;
    public LoginFormController() {
        controller = new MyChatClient();
    }
            
            
                    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void handleSigninButton(ActionEvent event){
        String phoneNum = phoneNumberTF.getText();
        String password = passswordPF.getText();
        user=controller.checkUser(phoneNum,password);
        if(user==null){System.out.println("not valid!");}
        else{System.out.println("valid!");}
    }
    
   public void handleSignupButton(ActionEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/mychatclient/view/view/RegisterForm.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            stage=(Stage)signInButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
   }
    
}
