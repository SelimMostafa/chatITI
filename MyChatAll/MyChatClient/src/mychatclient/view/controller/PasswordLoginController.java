/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PasswordLoginController implements Initializable {
    @FXML
    private Button signInButton;
    @FXML
    private Button backButton;
    @FXML
    private PasswordField passwordTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSigninButton(ActionEvent event) {
    }

    @FXML
    private void handleSignupButton(ActionEvent event) {
    }
    
}
