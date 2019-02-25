/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import animatefx.animation.BounceOutRight;
import animatefx.animation.FadeOutUpBig;
import animatefx.animation.RollIn;
import animatefx.animation.RollOut;
import animatefx.animation.RotateOutUpLeft;
import animatefx.animation.RotateOutUpRight;
import animatefx.animation.RubberBand;

import animatefx.animation.SlideInUp;

import commonservice.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView flyBirdRight1;
    @FXML
    private ImageView flyBirdRight2;
    @FXML
    private ImageView flyBirdRight3;
    @FXML
    private ImageView flyBirdLeft1;
    @FXML
    private ImageView flyBirdLeft2;
    @FXML
    private ImageView flyBirdLeft3;

    @FXML
    private Label logoLabel;

    private Image logoImage;

    private Image birdImage;

    public LoginFormController() {
        try {
            controller = new MyChatClient();
            File logoFile = new File("C:\\Users\\HP\\Documents\\GitHub\\chatITI\\MyChatAll\\MyChatClient\\src\\mychatclient\\view\\controller\\Bird_Logo.png");
            BufferedImage logoBufferedImage = ImageIO.read(logoFile);
            logoImage = SwingFXUtils.toFXImage(logoBufferedImage, null);

            File birdFile = new File("C:\\Users\\HP\\Documents\\GitHub\\chatITI\\MyChatAll\\MyChatClient\\src\\mychatclient\\view\\controller\\flyBird.png");

            BufferedImage birdBufferedImage = ImageIO.read(birdFile);
            birdImage = SwingFXUtils.toFXImage(birdBufferedImage, null);

        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        signUpButton.getStylesheets().add("loginButton.css");
        signUpButton.getStyleClass().add("button");
        flyBirdRight1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        flyBirdRight1.setImage(birdImage);
        flyBirdRight2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        flyBirdRight2.setImage(birdImage);
        flyBirdRight3.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        flyBirdRight3.setImage(birdImage);
        flyBirdLeft1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        flyBirdLeft1.setImage(birdImage);
        flyBirdLeft2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        flyBirdLeft2.setImage(birdImage);
        flyBirdLeft3.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        flyBirdLeft3.setImage(birdImage);
        logoImageView.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKCYAN));
        logoImageView.setImage(logoImage);
        nextButton.getStylesheets().add("loginButton.css");
        nextButton.getStyleClass().add("button");
        Platform.runLater(() -> {
            //nextButton.requestFocus();
            new SlideInUp(logoImageView).play();
            new RollIn(logoLabel).play();

            new RotateOutUpLeft(flyBirdRight1).playOnFinished(new RotateOutUpRight(flyBirdLeft1)).play();
            new RotateOutUpLeft(flyBirdRight2).playOnFinished(new RotateOutUpRight(flyBirdLeft2)).play();
            new RotateOutUpLeft(flyBirdRight3).playOnFinished(new RotateOutUpRight(flyBirdLeft3)).play();
        });

       

    }
    User user = null;
    MyChatClient controller;
    Stage stage;

    @FXML
    private void handleNextButton() {

        String phone;
        phone = phoneNumberTF.getText();
        boolean isUser = controller.checkUser(phone);
        if (isUser) {
            try {
                FXMLLoader loader2 = new FXMLLoader();
                PasswordLoginController passwordController = new PasswordLoginController(phone, controller);
                loader2.setController(passwordController);
                Parent root = loader2.load(getClass().getResource("/mychatclient/view/view/passwordLogin.fxml").openStream());
                Scene scene = new Scene(root);
                stage = (Stage) nextButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            /*            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setContentText("user does not exist");
             alert.showAndWait();*/
            System.out.println("user does not exist");
        }
        // a b2a ?
    }

    @FXML
    public void handleSignupButton() {

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

    public TextField getPhoneNumberTF() {
        return phoneNumberTF;
    }
}
