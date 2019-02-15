/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

/**
 *
 * @author AmrHesham
 */
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

public class RegisterFormController implements Initializable {

    MyChatClient controller;
    User user;
    Stage stage;

    public RegisterFormController() {
        controller = new MyChatClient();
    }

    @FXML
    private TextField phoneNumberTF;

    @FXML
    private TextField nameTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private PasswordField confirmPasswordTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField genderTF;

    @FXML
    private TextField dobTF;

    @FXML
    private TextField countryTF;

    @FXML
    private TextField bioTF;

    @FXML
    private Button registerButton;
    @FXML
    private Button goBackButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleRegisterButton(ActionEvent event) {
        user = new User();
        user.setName(nameTF.getText());
        user.setPhoneNum(phoneNumberTF.getText());
        user.setGender(genderTF.getText());
        user.setCountry(countryTF.getText());
        user.setDateOfBirth(dobTF.getText());
        user.setPassword(passwordTF.getText());
        user.setEmail(emailTF.getText());
        user.setBIO(bioTF.getText());

        if (controller.registerNewUser(user)) {
            
                System.out.println("registerd sucessfully");
                goBackButton.fire();
            
        } else {
            System.out.println("not registred sucessfully");
        }
    }

    public void handleGoBackButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mychatclient/view/view/LoginForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
