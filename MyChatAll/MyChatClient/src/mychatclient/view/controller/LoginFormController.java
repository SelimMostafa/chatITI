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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginFormController implements Initializable {

    @FXML
    private TextField phoneNumberTF;
    @FXML
    private Button nextButton;
    @FXML
    private Button signUpButton;

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
    User user = null;
    MyChatClient controller;
    Stage stage;

    @FXML
    public void handleSignupButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mychatclient/view/view/RegisterForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage = (Stage) signUpButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
