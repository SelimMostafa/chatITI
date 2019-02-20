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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import mychatclient.controller.MyChatClient;

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
    private ScrollPane chatArea;
    @FXML
    private ImageView userImage;
    @FXML
    private Label sendFileLabel;
    User user;
    User userFromOnlineList;
    ArrayList<String> chatUsers;

    MyChatClient controller;
    public ChatwindowController(User userFromOnlineList, User user) {
        this.user = user;
        this.userFromOnlineList = userFromOnlineList;
        controller = new MyChatClient();
        chatUsers = new ArrayList<>();
        chatUsers.add(user.getPhoneNum());
        chatUsers.add(userFromOnlineList.getPhoneNum());
//        System.out.println(user.getPhoneNum());
//        System.out.println(userFromOnlineList.getPhoneNum());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        htmlEditor.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String msg = htmlEditor.getHtmlText();
                controller.sendMessage(msg,chatUsers,user.getPhoneNum());
                htmlEditor.setHtmlText("");
                
            }
        });

    }

}
