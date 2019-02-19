/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.view.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import mychatserver.model.DAOImpl.UserStatisticsDAO;

/**
 * FXML Controller class
 *
 * @author Ahmed-pc
 */
public class AdminProfilePageController implements Initializable {

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
    private BarChart<String, Number> onoffBarChart;
    @FXML
    private Button loadOnlineOffline;
    @FXML
    private Tab femalestatTab;
    @FXML
    private BarChart<String, Number> femaleBarChart;
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

    UserStatisticsDAO statisticsdao = new UserStatisticsDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleOnlineOfflineBarChart(ActionEvent event) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Online vs. Offline Users");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of users");

        XYChart.Series<String, Number> online = new XYChart.Series<>();
        online.setName("Online");
        XYChart.Series<String, Number> offline = new XYChart.Series<>();
        offline.setName("Offline");

        online.getData().add(new XYChart.Data<>("Online", statisticsdao.countOnline()));
        offline.getData().add(new XYChart.Data<>("Offline", statisticsdao.countOffline()));

        ObservableList<XYChart.Series<String, Number>> dataList = FXCollections.observableArrayList();
        dataList.addAll(online, offline);

        onoffBarChart.setData(dataList);

    }

    @FXML
    private void handleMaleFemaleBarChart(ActionEvent event) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Male vs. Female Users");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of users");

        XYChart.Series<String, Number> male = new XYChart.Series<>();
        male.setName("Male");
        XYChart.Series<String, Number> female = new XYChart.Series<>();
        female.setName("Female");

        male.getData().add(new XYChart.Data<>("Male", statisticsdao.countMale()));
        female.getData().add(new XYChart.Data<>("Female", statisticsdao.countFemale()));

        ObservableList<XYChart.Series<String, Number>> dataList = FXCollections.observableArrayList();
        dataList.addAll(male, female);

        femaleBarChart.setData(dataList);

    }

    @FXML
    private void handleUserCountriesPieChart(ActionEvent event) {

        //Preparing ObservbleList object         
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        countriesPieChart.setClockwise(true);
        //Setting the length of the label line 
        //countriesPieChart.setLabelLineLength(50);
        //Setting the labels of the pie chart visible  
        countriesPieChart.setLabelsVisible(true);

        Map<String, Integer> countryStatistics = new HashMap<>();
        countryStatistics = statisticsdao.groupByCountry();
        
        for(Map.Entry<String,Integer> entry : countryStatistics.entrySet() )
        {
            if(entry.getValue() > 0)
            {
                String countryName = entry.getKey();
                pieChartData.add(new PieChart.Data(countryName,entry.getValue()));
                       
            }
        }
        countriesPieChart.setData(pieChartData);
        
        for(PieChart.Data data : countriesPieChart.getData())
        {
            Node slice = data.getNode();
            double percent = (data.getPieValue()/60)*100;
            String tip = data.getName()+ " = " +String.format("%.2f", percent)+"%";
            Tooltip mytooltip = new Tooltip(tip);
            Tooltip.install(slice, mytooltip);
            
        }

    }
    
    @FXML
    private void handleUserEntriesPieChart(ActionEvent event) {

        //Preparing ObservbleList object         
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        entriesPieChart.setClockwise(true);  
        entriesPieChart.setLabelsVisible(true);

        Map<String, Integer> entryStatistics = new HashMap<>();
        entryStatistics = statisticsdao.getEntryTimes();
        
        for(Map.Entry<String,Integer> entry : entryStatistics.entrySet() )
        {
            if(entry.getValue() > 0)
            {
                String username = entry.getKey();
                pieChartData.add(new PieChart.Data(username,entry.getValue()));
                       
            }
        }
        entriesPieChart.setData(pieChartData);
        
        for(PieChart.Data data : entriesPieChart.getData())
        {
            Node slice = data.getNode();
            double percent = (data.getPieValue()/60)*100;
            String tip = data.getName()+ " = " +String.format("%.2f", percent)+"%";
            Tooltip mytooltip = new Tooltip(tip);
            Tooltip.install(slice, mytooltip);
            
        }

    }
}
