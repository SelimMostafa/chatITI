/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mychatserver.controller.Controller;

/**
 * FXML Controller class
 *
 * @author Ahmed-pc
 */
public class AdminProfilePageController implements Initializable {

    public AdminProfilePageController() {
    }
    
    Controller serverController;

    @FXML
    private Tab servicesTab;
    @FXML
    private Button startServer;
    @FXML
    private Button stopServer;
    @FXML
    private Tab addUserTab;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField nameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private Button addUserButton;
    @FXML
    private TextField dobTF;
    @FXML
    private TextField countryTF;
    @FXML
    private Tab announceTab;
    @FXML
    private Button sendAnnouncmentMsg;
    @FXML
    private TextArea broadcasttextArea;
    @FXML
    private Tab onoffstatTab;
    @FXML
    private BarChart<String, Integer> onoffBarChart;
    @FXML
    private Button loadOnlineOffline;
    @FXML
    private Tab femalestatTab;
    @FXML
    private BarChart<?, ?> femaleBarChart;
    @FXML
    private Button femaleLoadButton;
    @FXML
    private Tab countryStat;
    @FXML
    private PieChart countriesPieChart;
    @FXML
    private Button countriesLoadButton;
    @FXML
    private Tab entriesTab;
    @FXML
    private PieChart entriesPieChart;
    @FXML
    private Button entriesLoadButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRegisterButton(ActionEvent event) {
    }

    @FXML
    private void handleOnlineOfflineBarChart(ActionEvent event) {
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Online vs. Offline Users"); 
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Number of users");
        
        
        XYChart.Series<String, Integer> online = new XYChart.Series<>();
        online.setName("Online");
        XYChart.Series<String, Integer> offline = new XYChart.Series<>();
        offline.setName("Offline");
        
        online.getData().add(new XYChart.Data<>("Online", serverController.countOnline()));
        
        ObservableList<XYChart.Series<String, Integer>> dataList = FXCollections.observableArrayList();
        dataList.addAll(online,offline);
        
        onoffBarChart.setData(dataList);
        
    }  
          
    
    }
