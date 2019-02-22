/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import commonservice.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;

import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author AmrHesham
 */
public class ChatwindowController implements Initializable {

    public ChatwindowController() {
    }

    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private VBox chatArea;
    @FXML
    private ImageView userImage;
    @FXML
    private Label sendFileLabel;
    User user;
    User userFromOnlineList;
    ArrayList<User> chatUsers;
    ArrayList<String> activeChats;
    MyChatClient controller;

    public ChatwindowController(User userFromOnlineList, User user) {
        this.user = user;
        this.userFromOnlineList = userFromOnlineList;
        controller = new MyChatClient();
        chatUsers = new ArrayList<>();
        chatUsers.add(user);
        chatUsers.add(userFromOnlineList);

//        System.out.println(user.getPhoneNum());
//        System.out.println(userFromOnlineList.getPhoneNum());
//        Stage stage = (Stage) sendFileLabel.getScene().getWindow();
//        stage.setOnCloseRequest((event) -> {
//            
//        });
    }

    public ChatwindowController(User user) {
        this.user = user;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        htmlEditor.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String msg = htmlEditor.getHtmlText();
                String senderPhone = user.getPhoneNum();
                controller.sendMessage(msg, chatUsers, senderPhone);
                htmlEditor.setHtmlText("");
                display(msg);

            }
        });

    }

    public void display(String message) {
        System.out.println("inside display method in chatWindowController");
        System.out.println("Delivered Successfully " + message);

        //Label label = new Label(message);
        //label.setPrefSize(300, 100);
        Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.getEngine().loadContent(message, "text/html");
            HBox hbox = new HBox(webView);
            chatArea.getChildren().add(hbox);
        });

    }

    // TODO
}
