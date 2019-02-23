/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

/**
 *
 * @author solo
 */
import commonservice.User;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.time.LocalDate;
import java.time.Month;
import mychatclient.controller.MyChatClient;

public class RegisterFormController implements Initializable {

    MyChatClient controller;
    User user;
    Stage stage;
    ValidationController validationController;
    boolean validInformation;

    public RegisterFormController() {
        controller = new MyChatClient();
        validationController = new ValidationController();
        validInformation = false;
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
    private ComboBox<String> genderComboBox ;
    
    @FXML
    private DatePicker dobDatePicker;



    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField bioTF;

    @FXML
    private Button registerButton;
    @FXML
    private Button goBackButton;
    
    @FXML
    private ImageView validEmailImageView; 
    
    @FXML
    private ImageView validPhoneImageView;
    
    @FXML
    private ImageView validNameImageView;
    
    @FXML
    private ImageView validPasswordImageView;
    
    @FXML
    private ImageView validconfirmPasswordImageView;
    
    @FXML
    private ImageView validGenderImageView;
    
    @FXML
    private  ImageView validDOBImageView;
    
    @FXML
    private ImageView validCountryImageView;
    
    Image invalidImage;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO


            File invalidFile = new File("D:\\ITI\\chatITI\\MyChatAll\\invalid.png");
            BufferedImage invalidBufferedImage = ImageIO.read(invalidFile);
            Image invalidImage = SwingFXUtils.toFXImage(invalidBufferedImage, null);
            
            File validFile = new File("D:\\ITI\\chatITI\\MyChatAll\\valid.png");
            BufferedImage validBufferedImage = ImageIO.read(validFile);
            Image validImage = SwingFXUtils.toFXImage(validBufferedImage, null);
            
            nameTF.textProperty().addListener((observable, oldValue, newValue) -> {
                validNameImageView.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
                validNameImageView.setImage(invalidImage);
                if (validationController.checkName(nameTF.getText())) 
                    validNameImageView.setImage(validImage);
                
            });
            
           

            emailTF.textProperty().addListener((observable, oldValue, newValue) -> {
                validEmailImageView.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
                validEmailImageView.setImage(invalidImage);
                if (validationController.checkEmail(emailTF.getText())) 
                    validEmailImageView.setImage(validImage);
                
            });
            
            phoneNumberTF.textProperty().addListener((observable, oldValue, newValue) -> {
                validPhoneImageView.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
                validPhoneImageView.setImage(invalidImage);
                if (validationController.checkPhone(phoneNumberTF.getText())) 
                    validPhoneImageView.setImage(validImage);
                
            });
            
            passwordTF.textProperty().addListener((observable, oldValue, newValue) -> {
                validPasswordImageView.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
                validPasswordImageView.setImage(invalidImage);
                if (validationController.checkPW(passwordTF.getText())) 
                    validPasswordImageView.setImage(validImage);
                
            });
            
            confirmPasswordTF.textProperty().addListener((observable, oldValue, newValue) -> {
                validconfirmPasswordImageView.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
                validconfirmPasswordImageView.setImage(invalidImage);
                if (confirmPasswordTF.getText().equals(passwordTF.getText())) 
                    validconfirmPasswordImageView.setImage(validImage);
                
            });
            
            
            
           
            
            

            ObservableList<String> cities = FXCollections.observableArrayList();

            String[] locales1 = Locale.getISOCountries();
            for (String countrylist : locales1) {
                Locale obj = new Locale("", countrylist);
                String[] city = {obj.getDisplayCountry()};
                for (String city1 : city) {
                    cities.add(obj.getDisplayCountry());
                }
            }
            comboBox.setItems(cities);
            
            ObservableList<String> genderOptions
                    = FXCollections.observableArrayList(
                            "Male",
                            "Female"
                            
                    );
            genderComboBox.setItems(genderOptions);
            genderComboBox.getSelectionModel().select(0);
            
            dobDatePicker.setValue(LocalDate.of(1992, Month.OCTOBER, 5));
            
            
            registerButton.getStylesheets().add("myButton.css");
            registerButton.getStyleClass().add("button");
            
            goBackButton.getStylesheets().add("myButton.css");
            goBackButton.getStyleClass().add("button");

            FxUtilTest.autoCompleteComboBoxPlus(comboBox, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()));
            comboBox.getSelectionModel().select("Egypt");
        } catch (IOException ex) {
            Logger.getLogger(RegisterFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleRegisterButton(ActionEvent event) {
        user = new User();
       
        if (validationController.checkName(nameTF.getText()) && validationController.checkPhone(phoneNumberTF.getText())
                && validationController.checkGender(genderComboBox.getValue().trim().substring(0, 1)) && validationController.checkCountry(comboBox.getValue())
                && validationController.checkDOB(dobDatePicker.getValue().toString().trim()) && validationController.checkPW(passwordTF.getText())
                && (passwordTF.getText().equals(confirmPasswordTF.getText())) && validationController.checkEmail(emailTF.getText())
                && validationController.checkBio(bioTF.getText())) {
            System.out.println(dobDatePicker.getValue().toString().trim());
            user.setName(nameTF.getText());
            user.setPhoneNum(phoneNumberTF.getText());
            user.setGender(genderComboBox.getValue().trim().substring(0, 1));
            user.setCountry(comboBox.getValue());
            user.setDateOfBirth(dobDatePicker.getValue().toString().trim());
            user.setPassword(passwordTF.getText());
            user.setEmail(emailTF.getText());
            user.setBIO(bioTF.getText());

            if (controller.registerNewUser(user)) {

                System.out.println("registerd sucessfully");
                goBackButton.fire();

            } else {
/*                Alert alert = new Alert(AlertType.INFORMATION);

            alert.setTitle("Welcome back !");
            alert.setHeaderText("Your number is already registred !");
            alert.setContentText("Try to remeber yor password");
            DialogPane dialogPane = alert.getDialogPane();

            dialogPane.getStylesheets().add("myDialogs.css");
            dialogPane.getStyleClass().add("dialog-pane");

            alert.showAndWait();
  */          }
        } else {
            
            
    /*        Alert alert = new Alert(AlertType.INFORMATION);

            alert.setTitle("Validation Error");
            alert.setHeaderText("Results:");
            alert.setContentText("Check that you have entered required information !");
            DialogPane dialogPane = alert.getDialogPane();

            dialogPane.getStylesheets().add("myDialogs.css");
            dialogPane.getStyleClass().add("dialog-pane");

            alert.showAndWait();
    */    }
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
