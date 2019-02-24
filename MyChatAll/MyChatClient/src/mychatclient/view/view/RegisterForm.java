package mychatclient.view.view;

//import com.gluonhq.charm.glisten.control.DropdownButton;
import commonservice.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import mychatclient.model.ClientModel;
import mychatclient.view.controller.RegisterFormHandler;


public class RegisterForm extends Pane {

        protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Label label5;
    protected final Label label6;
    protected final Label label7;
    protected final TextField phoneNumberTF;
    protected final TextField nameTF;
    protected final TextField passwordTF;
    protected final TextField confirmpasswordTF;
    protected final TextField emailTF;
    protected final TextField dateOfBirthTF;
    protected final TextField countryTF;
    //protected final DropdownButton dropdownButton;
    protected final MenuItem menuItem;
    protected final MenuItem menuItem0;
    protected final MenuItem menuItem1;
    protected final TextField genderTF;
    protected final TextField bioTF;
    protected final Label label8;
    protected final Button button;

    ClientModel model = ClientModel.getInstance();
    public TextField getPhoneNumberTF() {
        return phoneNumberTF;
    }

    public TextField getNameTF() {
        return nameTF;
    }

    public TextField getPasswordTF() {
        return passwordTF;
    }

    public TextField getConfirmpasswordTF() {
        return confirmpasswordTF;
    }

    public TextField getEmailTF() {
        return emailTF;
    }

    public TextField getDateOfBirthTF() {
        return dateOfBirthTF;
    }

    public TextField getCountryTF() {
        return countryTF;
    }

    public TextField getGenderTF() {
        return genderTF;
    }

    public TextField getBioTF() {
        return bioTF;
    }

    public Button getButton() {
        return button;
    }


    public RegisterForm() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        label5 = new Label();
        label6 = new Label();
        label7 = new Label();
        phoneNumberTF = new TextField("");
        nameTF = new TextField("");
        passwordTF = new TextField("");
        confirmpasswordTF = new TextField("");
        emailTF = new TextField("");
        dateOfBirthTF = new TextField("");
        countryTF = new TextField("");
        
        menuItem = new MenuItem();
        menuItem0 = new MenuItem();
        menuItem1 = new MenuItem();
        genderTF = new TextField("");
        bioTF = new TextField("");
        label8 = new Label();
        button = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(558.0);
        setPrefWidth(600.0);

        label.setLayoutX(47.0);
        label.setLayoutY(39.0);
        label.setPrefHeight(32.0);
        label.setPrefWidth(96.0);
        label.setText("Register :");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#173b9e"));
        label.setFont(new Font("System Bold Italic", 19.0));

        label0.setLayoutX(47.0);
        label0.setLayoutY(123.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(107.0);
        label0.setText("Phone Number :");

        label1.setLayoutX(47.0);
        label1.setLayoutY(159.0);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(87.0);
        label1.setText("Name :");

        label2.setLayoutX(47.0);
        label2.setLayoutY(194.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(66.0);
        label2.setText("Password :");

        label3.setLayoutX(47.0);
        label3.setLayoutY(227.0);
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(114.0);
        label3.setText("Confirm Password :");

        label4.setLayoutX(47.0);
        label4.setLayoutY(267.0);
        label4.setPrefHeight(17.0);
        label4.setPrefWidth(66.0);
        label4.setText("Email :");

        label5.setLayoutX(47.0);
        label5.setLayoutY(308.0);
        label5.setPrefHeight(17.0);
        label5.setPrefWidth(59.0);
        label5.setText("Gender :");

        label6.setLayoutX(47.0);
        label6.setLayoutY(338.0);
        label6.setPrefHeight(17.0);
        label6.setPrefWidth(87.0);
        label6.setText("Date Of Birth :");

        label7.setLayoutX(47.0);
        label7.setLayoutY(373.0);
        label7.setPrefHeight(17.0);
        label7.setPrefWidth(59.0);
        label7.setText("Country : ");

        phoneNumberTF.setLayoutX(198.0);
        phoneNumberTF.setLayoutY(119.0);

        nameTF.setLayoutX(198.0);
        nameTF.setLayoutY(155.0);

        passwordTF.setLayoutX(198.0);
        passwordTF.setLayoutY(190.0);

        confirmpasswordTF.setLayoutX(198.0);
        confirmpasswordTF.setLayoutY(223.0);

        emailTF.setLayoutX(198.0);
        emailTF.setLayoutY(263.0);

        dateOfBirthTF.setLayoutX(198.0);
        dateOfBirthTF.setLayoutY(334.0);

        countryTF.setLayoutX(198.0);
        countryTF.setLayoutY(369.0);

//        dropdownButton.setLayoutX(-113.0);
//        dropdownButton.setLayoutY(14.0);
        menuItem.setText("Choice 1");

        menuItem0.setText("Choice 2");

        menuItem1.setText("Choice 3");

        genderTF.setLayoutX(198.0);
        genderTF.setLayoutY(304.0);

        bioTF.setLayoutX(198.0);
        bioTF.setLayoutY(410.0);

        label8.setLayoutX(47.0);
        label8.setLayoutY(414.0);
        label8.setPrefHeight(17.0);
        label8.setPrefWidth(59.0);
        label8.setText("Bio :");

        button.setLayoutX(246.0);
        button.setLayoutY(474.0);
        button.setMnemonicParsing(false);
        button.setText("Register");
        
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(label5);
        getChildren().add(label6);
        getChildren().add(label7);
        getChildren().add(phoneNumberTF);
        getChildren().add(nameTF);
        getChildren().add(passwordTF);
        getChildren().add(confirmpasswordTF);
        getChildren().add(emailTF);
        getChildren().add(dateOfBirthTF);
        getChildren().add(countryTF);

        getChildren().add(genderTF);
        getChildren().add(bioTF);
        getChildren().add(label8);
        getChildren().add(button);
        button.setOnAction(new RegisterFormHandler(this,model.serverservice));
    }
}
