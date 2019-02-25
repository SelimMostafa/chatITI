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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;
import mychatclient.view.view.HomePageBase;

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
    Stage stage;
    /**
     * Initializes the controller class.
     */
    MyChatClient controller;
    String phone;

    PasswordLoginController(String phone, MyChatClient controller) {
        this.phone = phone;
        this.controller = controller;
    }

    public PasswordLoginController() {
//        controller=new MyChatClient();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleSigninButton(ActionEvent event) {
        String password = passwordTF.getText();
        User user = controller.checkPassword(phone, password);

        if (user == null) {

            /*   Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("not valid!");
             alert.show();
             */
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("not valid!");
            System.out.println("NotVALID!");

        } else {
//            controller.getModel().connectToServer();
            stage = (Stage) signInButton.getScene().getWindow();
            HomePageBase home = new HomePageBase(stage, user);
            controller.setHome(home);
            
            Parent root = home;
            Platform.setImplicitExit(false);
            Scene scene = new Scene(root);


            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/mychatclient/view/view/LoginForm.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PasswordLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
