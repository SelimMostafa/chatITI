/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import com.jfoenix.controls.JFXButton;
import commonservice.Message;
import commonservice.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;
import mychatclient.model.ClientModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    @FXML
    private JFXButton saveSessionBtn;

    User user;
    User userFromOnlineList;
    ArrayList<User> chatUsers;
    ArrayList<String> activeChats;
    MyChatClient controller;

    ArrayList<Message> chatHistory;
    ClientModel model = ClientModel.getInstance();
    File file;

    public ChatwindowController(ArrayList<User> chatUsers, User user) {
        this.user = user;
//        this.userFromOnlineList = userFromOnlineList;
        controller = new MyChatClient();
        this.chatUsers = chatUsers;
        //chatUsers.add(user);
        //chatUsers.add(userFromOnlineList);

        chatHistory = new ArrayList<Message>();
        file = null;

//        System.out.println(user.getPhoneNum());
//        System.out.println(userFromOnlineList.getPhoneNum());
//        Stage stage = (Stage) sendFileLabel.getScene().getWindow();
//        stage.setOnCloseRequest((event) -> {
//            
//        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        htmlEditor.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String msg = htmlEditor.getHtmlText();
                String senderPhone = user.getPhoneNum();
                controller.sendMessage(msg, chatUsers, senderPhone);
                htmlEditor.setHtmlText("");
                display(msg, true);

             //   ArrayList<User> receivers = new ArrayList<User>();
              //  receivers.add(userFromOnlineList);
//                System.out.println("inside intialize sender= " + user.getPhoneNum() + " , receiver= " + userFromOnlineList.getPhoneNum());
                Message messageObject = convertToMsgObject(msg, user, chatUsers);
                chatHistory.add(messageObject);

            }
        });

        saveSessionBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Chat Session");
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

                file = fileChooser.showSaveDialog((Stage) saveSessionBtn.getScene().getWindow());
                if (file != null) {
                    saveChatSession(chatHistory, file);

                } else {
                    System.out.println("no directory choosen");
                }

            }

        });

    }

    public void display(String message, boolean isSender) {

        Platform.runLater(() -> {

            WebView webView = new WebView();
            webView.getEngine().loadContent(message);
            webView.setStyle("-fx-background-color: #1B887D");
            HBox hbox = new HBox(webView);

            if (isSender) {
                hbox.setAlignment(Pos.BASELINE_RIGHT);
        
            } else {
                hbox.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<User> receivers = new ArrayList<User>();
                receivers.add(user);
                
                Message msgObject = convertToMsgObject(message, userFromOnlineList, receivers);
                chatHistory.add(msgObject);

            }
            chatArea.getChildren().add(hbox);
        });

    }

    public void saveChatSession(ArrayList<Message> msgs, File file) {
        try {
            model.serverservice.saveChatSession(msgs, file);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    public Message convertToMsgObject(String msg, User sender, ArrayList<User> users) {
        System.out.println("inside convert to msg object");
        ArrayList<User> receivers = new ArrayList<>(users);
        receivers.remove(sender);
        Document document = Jsoup.parse(msg);
 
        Message messageObject = new Message(sender, receivers);
        messageObject.setFormattedMsg(msg);
        messageObject.setMessageText(document.select("body").text());

        messageObject.setFontSize(document.select("font").attr("size"));
        messageObject.setFontStyle(document.select("font").attr("face"));
        //messageObject.setFontColor(document.select("font").attr("color"));
        // messageObject.setTextBackGround();

        messageObject.setBold(!document.select("b").isEmpty());
        messageObject.setUnderlined(!document.select("u").isEmpty());
        messageObject.setItalic(!document.select("i").isEmpty());
        return messageObject;
    }
}
