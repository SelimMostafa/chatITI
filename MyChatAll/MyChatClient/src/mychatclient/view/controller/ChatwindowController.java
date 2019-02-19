/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.view.controller;

import commonservice.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
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
    private ScrollPane chatArea;
    @FXML
    private ImageView userImage;
    @FXML
    private Label sendFileLabel;
    User user;
    public ChatwindowController(User user) {
        this.user=user;
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
