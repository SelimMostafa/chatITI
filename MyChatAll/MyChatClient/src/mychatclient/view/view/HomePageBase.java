package mychatclient.view.view;

import commonservice.ServerService;
import commonservice.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mychatclient.model.ClientModel;
import mychatclient.view.controller.AddContactHandler;
import mychatclient.view.controller.ChatwindowController;
//import mychatclient.view.controller.AddContactHandler;

public class HomePageBase extends AnchorPane {

    ArrayList<String> activeChats;
    protected final Pane pane;
    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem menuItem;
    protected final Menu menu0;
    protected final Menu menu1;
    protected final MenuItem menuItem0;
    protected final ImageView profileImageViewer;
    protected final Label userNameLabel;
    protected final AnchorPane anchorPane;
    protected final TabPane tabPane;
    protected final Tab onlineTab;
    protected final AnchorPane anchorPane0;
    protected final ListView onlineListView;
    protected final Tab offlineTab;
    protected final AnchorPane anchorPane1;
    protected final ListView<String> friendsListView;
    protected final Tab RequestTab;
    protected final AnchorPane anchorPane2;
    protected final ListView<String> requestListView;
    protected final Tab updateTab;
    protected final AnchorPane anchorPane3;
    protected final Label label;
    protected final Label nameLabel;
    protected final Label passwordLabel;
    protected final Label emailLabel;
    protected final Label genderLabel;
    protected final Label dobLabel;
    protected final Label countryLabel;
    protected final Label bioLabel;
    protected final TextField nameTF;
    protected final TextField passwordTF;
    protected final TextField emailTF;
    protected final TextField genderTF;
    protected final TextField dobTF;
    protected final TextField countryTF;
    protected final TextField bioTF;
    protected final Button updateBtn;
    protected final Tab addContactTab;
    protected final AnchorPane anchorPane4;
    protected final Button addMoreBtn;
    protected final Label label0;
    protected final TextField phoneAddedTF;
    protected final Button addBtn;
    protected final Button signoutBtn;
    protected final ImageView imageView;
    // protected final SplitMenuButton statusMenu;
    protected final ComboBox statusMenu;

    /*protected final RadioMenuItem availableItem;
     protected final ToggleGroup status;
     protected final RadioMenuItem busyItem;
     protected final RadioMenuItem awayItem;
     */
    protected final CheckBox chatbotCheckBox;

    ArrayList<String> friendRequests;
    ArrayList<User> friends;
    ArrayList<User> onlineFriends;
    ArrayList<User> offlineFriends;
    ObservableList<String> requestsObsrvList;
    ObservableList<String> friendsObsrvList;
    ObservableList<User> onlineFriendsObsrvList;
    ObservableList<String> offlineFriendsObsrvList;

//public ServerService serverService ;
    User user;
    double y = 60.0;
    ArrayList<TextField> contactsTF;
    ClientModel model;

    public HomePageBase(Stage Stage, User user) {
        activeChats = new ArrayList<>();
        this.user = user;
        this.contactsTF = new ArrayList<TextField>();
        this.model = new ClientModel();
        pane = new Pane();
        menuBar = new MenuBar();
        menu = new Menu();
        menuItem = new MenuItem();
        menu0 = new Menu();
        menu1 = new Menu();
        menuItem0 = new MenuItem();
        profileImageViewer = new ImageView();
        userNameLabel = new Label();
        anchorPane = new AnchorPane();
        tabPane = new TabPane();
        onlineTab = new Tab();
        anchorPane0 = new AnchorPane();
        onlineListView = new ListView();
        offlineTab = new Tab();
        anchorPane1 = new AnchorPane();
        friendsListView = new ListView<String>();
        RequestTab = new Tab();
        anchorPane2 = new AnchorPane();
        requestListView = new ListView<String>();
        updateTab = new Tab();
        anchorPane3 = new AnchorPane();
        label = new Label();
        nameLabel = new Label();
        passwordLabel = new Label();
        emailLabel = new Label();
        genderLabel = new Label();
        dobLabel = new Label();
        countryLabel = new Label();
        bioLabel = new Label();
        nameTF = new TextField();
        passwordTF = new TextField();
        emailTF = new TextField();
        genderTF = new TextField();
        dobTF = new TextField();
        countryTF = new TextField();
        bioTF = new TextField();
        updateBtn = new Button();
        addContactTab = new Tab();
        anchorPane4 = new AnchorPane();
        addMoreBtn = new Button();
        label0 = new Label();
        phoneAddedTF = new TextField();
        addBtn = new Button();
        signoutBtn = new Button();
        imageView = new ImageView();
        //statusMenu = new SplitMenuButton();
        statusMenu = new ComboBox();

        /*   availableItem = new RadioMenuItem();
         status = new ToggleGroup();
         busyItem = new RadioMenuItem();
         awayItem = new RadioMenuItem();
         */
        chatbotCheckBox = new CheckBox();

        pane.setMaxHeight(USE_PREF_SIZE);
        pane.setMaxWidth(USE_PREF_SIZE);
        pane.setMinHeight(USE_PREF_SIZE);
        pane.setMinWidth(USE_PREF_SIZE);
        pane.setPrefHeight(631.0);
        pane.setPrefWidth(350.0);

        menuBar.setLayoutY(1.0);
        menuBar.setPrefHeight(25.0);
        menuBar.setPrefWidth(350.0);

        menu.setMnemonicParsing(false);
        menu.setText("Messenger");

        menuItem.setMnemonicParsing(false);
        menuItem.setText("Close");

        menu0.setMnemonicParsing(false);
        menu0.setText("Contacts");

        menu1.setMnemonicParsing(false);
        menu1.setText("Help");

        menuItem0.setMnemonicParsing(false);
        menuItem0.setText("About");

        profileImageViewer.setFitHeight(53.0);
        profileImageViewer.setFitWidth(55.0);
        profileImageViewer.setLayoutX(21.0);
        profileImageViewer.setLayoutY(38.0);
        profileImageViewer.setPickOnBounds(true);
        profileImageViewer.setPreserveRatio(true);

        userNameLabel.setLayoutX(105.0);
        userNameLabel.setLayoutY(38.0);
        userNameLabel.setPrefHeight(17.0);
        userNameLabel.setPrefWidth(82.0);
        userNameLabel.setText("UserName");
        userNameLabel.setTextFill(javafx.scene.paint.Color.valueOf("#0e09b2"));
        userNameLabel.setFont(new Font("System Bold Italic", 13.0));

        anchorPane.setLayoutY(142.0);

        tabPane.setPrefHeight(496.0);
        tabPane.setPrefWidth(350.0);
        tabPane.setTabClosingPolicy(javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE);

        onlineTab.setText("Online");

        anchorPane0.setMinHeight(0.0);
        anchorPane0.setMinWidth(0.0);
        anchorPane0.setPrefHeight(180.0);
        anchorPane0.setPrefWidth(200.0);

        onlineListView.setLayoutX(14.0);
        onlineListView.setLayoutY(25.0);
        onlineListView.setPrefHeight(424.0);
        onlineListView.setPrefWidth(325.0);
        onlineTab.setContent(anchorPane0);

        offlineTab.setText("Offline");

        anchorPane1.setMinHeight(0.0);
        anchorPane1.setMinWidth(0.0);
        anchorPane1.setPrefHeight(180.0);
        anchorPane1.setPrefWidth(200.0);

        friendsListView.setLayoutX(14.0);
        friendsListView.setLayoutY(25.0);
        friendsListView.setPrefHeight(424.0);
        friendsListView.setPrefWidth(325.0);
        offlineTab.setContent(anchorPane1);

        RequestTab.setText("Requests");

        anchorPane2.setMinHeight(0.0);
        anchorPane2.setMinWidth(0.0);
        anchorPane2.setPrefHeight(180.0);
        anchorPane2.setPrefWidth(200.0);

        requestListView.setLayoutX(14.0);
        requestListView.setLayoutY(25.0);
        requestListView.setPrefHeight(424.0);
        requestListView.setPrefWidth(325.0);
        RequestTab.setContent(anchorPane2);

        updateTab.setText("Update Profile");

        anchorPane3.setMinHeight(0.0);
        anchorPane3.setMinWidth(0.0);
        anchorPane3.setPrefHeight(467.0);
        anchorPane3.setPrefWidth(309.0);

        label.setLayoutX(23.0);
        label.setLayoutY(7.0);
        label.setPrefHeight(32.0);
        label.setPrefWidth(96.0);
        label.setText("Profile :");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#173b9e"));
        label.setFont(new Font("System Bold Italic", 19.0));

        nameLabel.setLayoutX(27.0);
        nameLabel.setLayoutY(58.0);
        nameLabel.setPrefHeight(17.0);
        nameLabel.setPrefWidth(87.0);
        nameLabel.setText("Name :");

        passwordLabel.setLayoutX(27.0);
        passwordLabel.setLayoutY(104.0);
        passwordLabel.setPrefHeight(17.0);
        passwordLabel.setPrefWidth(66.0);
        passwordLabel.setText("Password :");

        emailLabel.setLayoutX(27.0);
        emailLabel.setLayoutY(149.0);
        emailLabel.setPrefHeight(17.0);
        emailLabel.setPrefWidth(66.0);
        emailLabel.setText("Email :");

        genderLabel.setLayoutX(27.0);
        genderLabel.setLayoutY(191.0);
        genderLabel.setPrefHeight(17.0);
        genderLabel.setPrefWidth(59.0);
        genderLabel.setText("Gender :");

        dobLabel.setLayoutX(27.0);
        dobLabel.setLayoutY(234.0);
        dobLabel.setPrefHeight(17.0);
        dobLabel.setPrefWidth(87.0);
        dobLabel.setText("Date Of Birth :");

        countryLabel.setLayoutX(27.0);
        countryLabel.setLayoutY(278.0);
        countryLabel.setPrefHeight(17.0);
        countryLabel.setPrefWidth(59.0);
        countryLabel.setText("Country : ");

        bioLabel.setLayoutX(27.0);
        bioLabel.setLayoutY(323.0);
        bioLabel.setPrefHeight(17.0);
        bioLabel.setPrefWidth(59.0);
        bioLabel.setText("Bio :");

        nameTF.setLayoutX(108.0);
        nameTF.setLayoutY(54.0);

        passwordTF.setLayoutX(108.0);
        passwordTF.setLayoutY(100.0);

        emailTF.setLayoutX(108.0);
        emailTF.setLayoutY(145.0);

        genderTF.setLayoutX(108.0);
        genderTF.setLayoutY(187.0);

        dobTF.setLayoutX(109.0);
        dobTF.setLayoutY(230.0);
        dobTF.setPrefHeight(25.0);
        dobTF.setPrefWidth(149.0);

        countryTF.setLayoutX(108.0);
        countryTF.setLayoutY(274.0);

        bioTF.setLayoutX(109.0);
        bioTF.setLayoutY(319.0);

        updateBtn.setLayoutX(134.0);
        updateBtn.setLayoutY(397.0);
        updateBtn.setMnemonicParsing(false);
        updateBtn.setPrefHeight(32.0);
        updateBtn.setPrefWidth(82.0);
        updateBtn.setText("Update");
        updateTab.setContent(anchorPane3);

        addContactTab.setText("Add Contacts");

        anchorPane4.setMinHeight(0.0);
        anchorPane4.setMinWidth(0.0);
        anchorPane4.setPrefHeight(180.0);
        anchorPane4.setPrefWidth(200.0);

        addMoreBtn.setLayoutX(252.0);
        addMoreBtn.setLayoutY(57.0);
        addMoreBtn.setMnemonicParsing(false);
        addMoreBtn.setText("+");
        addMoreBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        addMoreBtn.setTextFill(javafx.scene.paint.Color.valueOf("#0b1abc"));
        addMoreBtn.setFont(new Font("System Bold Italic", 12.0));

        label0.setLayoutX(25.0);
        label0.setLayoutY(13.0);
        label0.setPrefHeight(25.0);
        label0.setPrefWidth(113.0);
        label0.setText("Add New Contacts : ");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#191cab"));

        phoneAddedTF.setLayoutX(25.0);
        phoneAddedTF.setLayoutY(57.0);
        phoneAddedTF.setPromptText("Phone Number");

        addBtn.setLayoutX(123.0);
        addBtn.setLayoutY(399.0);
        addBtn.setMnemonicParsing(false);
        addBtn.setPrefHeight(33.0);
        addBtn.setPrefWidth(102.0);
        addBtn.setText("Add");
        addBtn.setTextFill(javafx.scene.paint.Color.valueOf("#1f0d7c"));
        addBtn.setFont(new Font(13.0));
        addContactTab.setContent(anchorPane4);

        signoutBtn.setLayoutX(268.0);
        signoutBtn.setLayoutY(103.0);
        signoutBtn.setMnemonicParsing(false);
        signoutBtn.setPrefHeight(25.0);
        signoutBtn.setPrefWidth(68.0);
        signoutBtn.setText("SignOut");

        imageView.setFitHeight(19.0);
        imageView.setFitWidth(18.0);
        imageView.setLayoutX(80.0);
        imageView.setLayoutY(38.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        statusMenu.setLayoutX(88.0);
        statusMenu.setLayoutY(65.0);
        /*   // statusMenu.setMnemonicParsing(false);
         // statusMenu.setText("What are you doing ?");

         availableItem.setMnemonicParsing(false);
         availableItem.setText("Available");

         availableItem.setToggleGroup(status);

         busyItem.setMnemonicParsing(false);
         busyItem.setText("Busy");
         busyItem.setToggleGroup(status);

         awayItem.setMnemonicParsing(false);
         awayItem.setText("Away");
         awayItem.setToggleGroup(status);
         */
        chatbotCheckBox.setLayoutX(28.0);
        chatbotCheckBox.setLayoutY(107.0);
        chatbotCheckBox.setMnemonicParsing(false);
        chatbotCheckBox.setText("Enable ChatBot");

        menu.getItems().add(menuItem);
        menuBar.getMenus().add(menu);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(menuItem0);
        menuBar.getMenus().add(menu1);
        pane.getChildren().add(menuBar);
        pane.getChildren().add(profileImageViewer);
        pane.getChildren().add(userNameLabel);
        anchorPane0.getChildren().add(onlineListView);
        tabPane.getTabs().add(onlineTab);
        anchorPane1.getChildren().add(friendsListView);
        tabPane.getTabs().add(offlineTab);
        anchorPane2.getChildren().add(requestListView);
        tabPane.getTabs().add(RequestTab);
        anchorPane3.getChildren().add(label);
        anchorPane3.getChildren().add(nameLabel);
        anchorPane3.getChildren().add(passwordLabel);
        anchorPane3.getChildren().add(emailLabel);
        anchorPane3.getChildren().add(genderLabel);
        anchorPane3.getChildren().add(dobLabel);
        anchorPane3.getChildren().add(countryLabel);
        anchorPane3.getChildren().add(bioLabel);
        anchorPane3.getChildren().add(nameTF);
        anchorPane3.getChildren().add(passwordTF);
        anchorPane3.getChildren().add(emailTF);
        anchorPane3.getChildren().add(genderTF);
        anchorPane3.getChildren().add(dobTF);
        anchorPane3.getChildren().add(countryTF);
        anchorPane3.getChildren().add(bioTF);
        anchorPane3.getChildren().add(updateBtn);
        tabPane.getTabs().add(updateTab);
        anchorPane4.getChildren().add(addMoreBtn);
        anchorPane4.getChildren().add(label0);
        anchorPane4.getChildren().add(phoneAddedTF);
        anchorPane4.getChildren().add(addBtn);
        tabPane.getTabs().add(addContactTab);
        anchorPane.getChildren().add(tabPane);
        pane.getChildren().add(anchorPane);
        pane.getChildren().add(signoutBtn);
        pane.getChildren().add(imageView);

        /*statusMenu.getItems().add(availableItem);
         statusMenu.getItems().add(busyItem);
         statusMenu.getItems().add(awayItem);
         */
        this.statusMenu.getItems().add("Available");
        this.statusMenu.getItems().add("Busy");
        this.statusMenu.getItems().add("Away");

        pane.getChildren().add(statusMenu);
        pane.getChildren().add(chatbotCheckBox);
        getChildren().add(pane);

        contactsTF.add(this.phoneAddedTF);

        onlineListView.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                try {
                    System.out.println("open the chat window with" + ((User) (onlineListView.getSelectionModel().getSelectedItem())).getPhoneNum());
                    FXMLLoader loader = new FXMLLoader();
                    User userFromOnlineFriendsList = (User) (onlineListView.getSelectionModel().getSelectedItem());
                    if (!activeChats.contains((String) userFromOnlineFriendsList.getPhoneNum())) {
                        activeChats.add(userFromOnlineFriendsList.getPhoneNum());
                        ChatwindowController chatwindowController = new ChatwindowController(userFromOnlineFriendsList,user);
                        loader.setController(chatwindowController);
                        Parent root = loader.load(getClass().getResource("/mychatclient/view/view/chatwindow.fxml").openStream());
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle(userFromOnlineFriendsList.getPhoneNum());
                        stage.show();
                    
                    }
                    else{
                        System.out.println("session is already opened");
                    }

                } catch (IOException ex) {
                    Logger.getLogger(HomePageBase.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        this.statusMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                user.setStatus((String) statusMenu.getValue());
                model.updateMode(user);
                System.out.println(user.getStatus());

            }
        });

        this.addBtn.setOnAction(new AddContactHandler(this));

        this.addMoreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (contactsTF.size() < 5) {
                    TextField newPhoneTF = new TextField();
                    y += 50;
                    newPhoneTF.setLayoutX(25.0);
                    newPhoneTF.setLayoutY(y);
                    newPhoneTF.setPromptText("Phone Number");
                    anchorPane4.getChildren().add(newPhoneTF);
                    contactsTF.add(newPhoneTF);
                    // contactsTFIndx.add();

                }
            }
        });

        this.updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User updatedUser = new User();

            }
        });

        Runnable viewRequestsTask = () -> {

            friendRequests = model.getRequests(user);

            requestsObsrvList = FXCollections.<String>observableArrayList();
            for (String friend : friendRequests) {
                requestsObsrvList.add(friend);
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    requestListView.setItems(requestsObsrvList);

                }
            });
        };

        Runnable viewFriendsTask = () -> {
            friends = model.getFriends(user);
            friendsObsrvList = FXCollections.<String>observableArrayList();

            for (User friend : friends) {
                friendsObsrvList.add(friend.getName() + " : " + friend.getPhoneNum());
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    friendsListView.setItems(friendsObsrvList);
                }
            });
        };

        Runnable viewOnlineFriendsTask = () -> {
            onlineFriends = model.getOnlineFriends(user);
            onlineFriendsObsrvList = FXCollections.<User>observableArrayList();

            for (User friend : onlineFriends) {
                onlineFriendsObsrvList.add(friend);
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    onlineListView.setItems(onlineFriendsObsrvList);
                }
            });
        };

        Runnable viewOfflineFriendsTask = () -> {
            offlineFriends = model.getOfflineFriends(user);
            offlineFriendsObsrvList = FXCollections.<String>observableArrayList();

            for (User friend : offlineFriends) {
                offlineFriendsObsrvList.add(friend.getName() + " : " + friend.getPhoneNum());
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    friendsListView.setItems(offlineFriendsObsrvList);
                }
            });
        };

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.scheduleAtFixedRate(viewRequestsTask, 0, 2, TimeUnit.SECONDS);
        //executorService.scheduleAtFixedRate(viewFriendsTask, 0, 2, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(viewOnlineFriendsTask, 0, 2, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(viewOfflineFriendsTask, 0, 2, TimeUnit.SECONDS);
        this.userNameLabel.setText(user.getName() + " : " + user.getPhoneNum());

    }

    public TextField getPhoneAddedTF() {
        return phoneAddedTF;
    }

    public ArrayList<TextField> getContactsTF() {
        return contactsTF;
    }

    public User getUser() {
        return user;
    }

    public AnchorPane getAnchorPane4() {
        return anchorPane4;
    }

}
