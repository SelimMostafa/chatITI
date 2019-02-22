package mychatserver.view.controller;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class AdminProfileGUIBase extends AnchorPane {

    protected final Label label;
    protected final Button button;
    protected final TabPane tabPane;
    protected final Tab tab;
    protected final AnchorPane anchorPane;
    protected final Button button0;
    protected final Button button1;
    protected final Tab tab0;
    protected final AnchorPane anchorPane0;
    protected final Label label0;
    protected final TextField phoneNumberTF;
    protected final Label label1;
    protected final Label label2;
    protected final TextField nameTF;
    protected final PasswordField confirmPasswordTF;
    protected final Label label3;
    protected final TextField emailTF;
    protected final Label label4;
    protected final Label label5;
    protected final Button registerButton;
    protected final TextField dobTF;
    protected final TextField countryTF;
    protected final Tab tab1;
    protected final AnchorPane anchorPane1;
    protected final Button button2;
    protected final TextArea textArea;
    protected final Tab tab2;
    protected final AnchorPane anchorPane2;
    protected final CategoryAxis categoryAxis;
    protected final NumberAxis numberAxis;
    protected final BarChart barChart;
    protected final Tab tab3;
    protected final AnchorPane anchorPane3;
    protected final CategoryAxis categoryAxis0;
    protected final NumberAxis numberAxis0;
    protected final BarChart barChart0;
    protected final Tab tab4;
    protected final AnchorPane anchorPane4;
    protected final PieChart pieChart;
    protected final Tab tab5;
    protected final AnchorPane anchorPane5;
    protected final PieChart pieChart0;

    public AdminProfileGUIBase() {

        label = new Label();
        button = new Button();
        tabPane = new TabPane();
        tab = new Tab();
        anchorPane = new AnchorPane();
        button0 = new Button();
        button1 = new Button();
        tab0 = new Tab();
        anchorPane0 = new AnchorPane();
        label0 = new Label();
        phoneNumberTF = new TextField();
        label1 = new Label();
        label2 = new Label();
        nameTF = new TextField();
        confirmPasswordTF = new PasswordField();
        label3 = new Label();
        emailTF = new TextField();
        label4 = new Label();
        label5 = new Label();
        registerButton = new Button();
        dobTF = new TextField();
        countryTF = new TextField();
        tab1 = new Tab();
        anchorPane1 = new AnchorPane();
        button2 = new Button();
        textArea = new TextArea();
        tab2 = new Tab();
        anchorPane2 = new AnchorPane();
        categoryAxis = new CategoryAxis();
        numberAxis = new NumberAxis();
        barChart = new BarChart(categoryAxis, numberAxis);
        tab3 = new Tab();
        anchorPane3 = new AnchorPane();
        categoryAxis0 = new CategoryAxis();
        numberAxis0 = new NumberAxis();
        barChart0 = new BarChart(categoryAxis0, numberAxis0);
        tab4 = new Tab();
        anchorPane4 = new AnchorPane();
        pieChart = new PieChart();
        tab5 = new Tab();
        anchorPane5 = new AnchorPane();
        pieChart0 = new PieChart();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(543.0);
        setPrefWidth(627.0);

        label.setLayoutX(239.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(37.0);
        label.setPrefWidth(148.0);
        label.setText("Admin Profile");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#0c4cfa"));
        label.setFont(new Font(24.0));

        button.setLayoutX(-90.0);
        button.setLayoutY(83.0);
        button.setMnemonicParsing(false);
        button.setText("Start Server");

        tabPane.setLayoutY(59.0);
        tabPane.setPrefHeight(482.0);
        tabPane.setPrefWidth(627.0);
        tabPane.setTabClosingPolicy(javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE);

        tab.setText("Services");

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(353.0);
        anchorPane.setPrefWidth(424.0);

        button0.setLayoutX(74.0);
        button0.setLayoutY(64.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(59.0);
        button0.setPrefWidth(136.0);
        button0.setText("Start Server");

        button1.setLayoutX(75.0);
        button1.setLayoutY(227.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(63.0);
        button1.setPrefWidth(134.0);
        button1.setText("Stop Server");
        tab.setContent(anchorPane);

        tab0.setText("Add User");

        anchorPane0.setMinHeight(0.0);
        anchorPane0.setMinWidth(0.0);
        anchorPane0.setPrefHeight(353.0);
        anchorPane0.setPrefWidth(550.0);

        label0.setLayoutX(83.0);
        label0.setLayoutY(59.0);
        label0.setPrefHeight(21.0);
        label0.setPrefWidth(119.0);
        label0.setText("Phone Number :");
        label0.setFont(new Font(14.0));

        phoneNumberTF.setLayoutX(269.0);
        phoneNumberTF.setLayoutY(54.0);
        phoneNumberTF.setPrefHeight(31.0);
        phoneNumberTF.setPrefWidth(211.0);

        label1.setLayoutX(83.0);
        label1.setLayoutY(106.0);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(87.0);
        label1.setText("Name :");
        label1.setFont(new Font(14.0));

        label2.setLayoutX(83.0);
        label2.setLayoutY(155.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(66.0);
        label2.setText("Password :");
        label2.setFont(new Font(14.0));

        nameTF.setLayoutX(269.0);
        nameTF.setLayoutY(99.0);
        nameTF.setPrefHeight(31.0);
        nameTF.setPrefWidth(211.0);

        confirmPasswordTF.setLayoutX(269.0);
        confirmPasswordTF.setLayoutY(148.0);
        confirmPasswordTF.setPrefHeight(31.0);
        confirmPasswordTF.setPrefWidth(211.0);

        label3.setLayoutX(83.0);
        label3.setLayoutY(212.0);
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(66.0);
        label3.setText("Email :");
        label3.setFont(new Font(14.0));

        emailTF.setLayoutX(269.0);
        emailTF.setLayoutY(207.0);
        emailTF.setPrefHeight(31.0);
        emailTF.setPrefWidth(211.0);

        label4.setLayoutX(79.0);
        label4.setLayoutY(330.0);
        label4.setPrefHeight(21.0);
        label4.setPrefWidth(96.0);
        label4.setText("Date Of Birth :");

        label5.setLayoutX(79.0);
        label5.setLayoutY(267.0);
        label5.setPrefHeight(21.0);
        label5.setPrefWidth(74.0);
        label5.setText("Country : ");

        registerButton.setLayoutX(251.0);
        registerButton.setLayoutY(385.0);
        registerButton.setMnemonicParsing(false);
        registerButton.setPrefHeight(44.0);
        registerButton.setPrefWidth(108.0);
        registerButton.setText("Add New User");

        dobTF.setLayoutX(269.0);
        dobTF.setLayoutY(325.0);
        dobTF.setPrefHeight(31.0);
        dobTF.setPrefWidth(211.0);

        countryTF.setLayoutX(269.0);
        countryTF.setLayoutY(262.0);
        countryTF.setPrefHeight(31.0);
        countryTF.setPrefWidth(211.0);
        tab0.setContent(anchorPane0);

        tab1.setText("Announcement");

        anchorPane1.setMinHeight(0.0);
        anchorPane1.setMinWidth(0.0);
        anchorPane1.setPrefHeight(180.0);
        anchorPane1.setPrefWidth(200.0);

        button2.setLayoutX(266.0);
        button2.setLayoutY(346.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(46.0);
        button2.setPrefWidth(92.0);
        button2.setText("Send");

        textArea.setLayoutX(68.0);
        textArea.setLayoutY(56.0);
        textArea.setPrefHeight(252.0);
        textArea.setPrefWidth(488.0);
        tab1.setContent(anchorPane1);

        tab2.setText("Online/Offline Statistics");

        anchorPane2.setMinHeight(0.0);
        anchorPane2.setMinWidth(0.0);
        anchorPane2.setPrefHeight(180.0);
        anchorPane2.setPrefWidth(200.0);

        categoryAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis.setSide(javafx.geometry.Side.LEFT);
        barChart.setLayoutX(53.0);
        barChart.setLayoutY(21.0);
        barChart.setPrefHeight(379.0);
        barChart.setPrefWidth(500.0);
        tab2.setContent(anchorPane2);

        tab3.setText("F/M Statistics");

        anchorPane3.setMinHeight(0.0);
        anchorPane3.setMinWidth(0.0);
        anchorPane3.setPrefHeight(180.0);
        anchorPane3.setPrefWidth(200.0);

        categoryAxis0.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis0.setSide(javafx.geometry.Side.LEFT);
        barChart0.setLayoutX(53.0);
        barChart0.setLayoutY(23.0);
        barChart0.setPrefHeight(388.0);
        barChart0.setPrefWidth(500.0);
        tab3.setContent(anchorPane3);

        tab4.setText("Users' Countries");

        anchorPane4.setMinHeight(0.0);
        anchorPane4.setMinWidth(0.0);
        anchorPane4.setPrefHeight(180.0);
        anchorPane4.setPrefWidth(200.0);

        pieChart.setLayoutX(64.0);
        pieChart.setLayoutY(23.0);
        pieChart.setPrefHeight(366.0);
        pieChart.setPrefWidth(500.0);
        pieChart.setTitle("Users' Countries Distribution");
        tab4.setContent(anchorPane4);

        tab5.setText("Users' Entries");

        anchorPane5.setMinHeight(0.0);
        anchorPane5.setMinWidth(0.0);
        anchorPane5.setPrefHeight(180.0);
        anchorPane5.setPrefWidth(200.0);

        pieChart0.setLayoutX(64.0);
        pieChart0.setLayoutY(20.0);
        pieChart0.setPrefHeight(362.0);
        pieChart0.setPrefWidth(500.0);
        pieChart0.setTitle("Users' Entries Distribution");
        tab5.setContent(anchorPane5);

        getChildren().add(label);
        getChildren().add(button);
        anchorPane.getChildren().add(button0);
        anchorPane.getChildren().add(button1);
        tabPane.getTabs().add(tab);
        anchorPane0.getChildren().add(label0);
        anchorPane0.getChildren().add(phoneNumberTF);
        anchorPane0.getChildren().add(label1);
        anchorPane0.getChildren().add(label2);
        anchorPane0.getChildren().add(nameTF);
        anchorPane0.getChildren().add(confirmPasswordTF);
        anchorPane0.getChildren().add(label3);
        anchorPane0.getChildren().add(emailTF);
        anchorPane0.getChildren().add(label4);
        anchorPane0.getChildren().add(label5);
        anchorPane0.getChildren().add(registerButton);
        anchorPane0.getChildren().add(dobTF);
        anchorPane0.getChildren().add(countryTF);
        tabPane.getTabs().add(tab0);
        anchorPane1.getChildren().add(button2);
        anchorPane1.getChildren().add(textArea);
        tabPane.getTabs().add(tab1);
        anchorPane2.getChildren().add(barChart);
        tabPane.getTabs().add(tab2);
        anchorPane3.getChildren().add(barChart0);
        tabPane.getTabs().add(tab3);
        anchorPane4.getChildren().add(pieChart);
        tabPane.getTabs().add(tab4);
        anchorPane5.getChildren().add(pieChart0);
        tabPane.getTabs().add(tab5);
        getChildren().add(tabPane);

    }
}
