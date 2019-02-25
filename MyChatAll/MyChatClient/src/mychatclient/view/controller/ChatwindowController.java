/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import commonservice.User;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;
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
    User user;
    User userFromOnlineList;
    ArrayList<User> chatUsers;
    ArrayList<String> activeChats;
    MyChatClient controller;
    ExecutorService executorService;

    public ChatwindowController(User userFromOnlineList, User user) {
        this.user = user;
        this.userFromOnlineList = userFromOnlineList;
        controller = new MyChatClient();
        chatUsers = new ArrayList<>();
        chatUsers.add(user);
        chatUsers.add(userFromOnlineList);
        Platform.runLater(() -> {
            sendFileLabel.setGraphic(new ImageView("/mychatclient/view/view/attachment3.png"));
        });
        executorService = Executors.newFixedThreadPool(1);
//        System.out.println(user.getPhoneNum());
//        System.out.println(userFromOnlineList.getPhoneNum());
//        Stage stage = (Stage) sendFileLabel.getScene().getWindow();
//        stage.setOnCloseRequest((event) -> {
//            
//        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendFileLabel.setOnMouseClicked((event) -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            String fileName = file.getName();
            int i = fileName.lastIndexOf(".");
            String fileExtentsion = fileName.substring(i);
            Runnable sendFileTask=() -> {
                controller.sendFile(file, userFromOnlineList.getPhoneNum(), fileExtentsion,user);
            };
            executorService.submit(sendFileTask);
        });
        htmlEditor.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String msg = htmlEditor.getHtmlText();
                String senderPhone = user.getPhoneNum();
                controller.sendMessage(msg, chatUsers, senderPhone);
                htmlEditor.setHtmlText("");
                display(msg, true);

            }
        });

    }

    public void display(String message, boolean isSender) {
        System.out.println("inside display method in chatWindowController");
        System.out.println("Delivered Successfully " + message);

        /*
        
        Document document = Jsoup.parse(message);
        Elements element = document.getElementsByTag("font");
        String msg = element.first().text().trim();
         */
        Platform.runLater(() -> {

            WebView webView = new WebView();
            webView.getEngine().loadContent(message);
            webView.setStyle("-fx-background-color: #1B887D");
            HBox hbox = new HBox(webView);

            if (isSender) {
                hbox.setAlignment(Pos.BASELINE_RIGHT);
            } else {
                hbox.setAlignment(Pos.BASELINE_LEFT);
            }
            chatArea.getChildren().add(hbox);
        });

    }

}
