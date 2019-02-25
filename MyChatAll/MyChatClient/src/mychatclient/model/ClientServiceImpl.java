/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.model;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import commonservice.ClientService;
import commonservice.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import mychatclient.controller.MyChatClient;
import org.controlsfx.control.Notifications;

/**
 *
 * @author HP
 */
public class ClientServiceImpl extends UnicastRemoteObject implements ClientService {

    MyChatClient controller;
//    volatile Optional<ButtonType> result;
    InputStream istream = null;
    File file;
    Runnable recievingFileTask;

    public ClientServiceImpl(MyChatClient controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void notifyOnline(User friend) throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.getHome().updateOnlineList(friend);

                Notifications onlineNotification = Notifications.create().title(friend.getName() + " is Online")
                        .text(friend.getName() + " is Online").graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                onlineNotification.show();

            }
        });

    }

    @Override
    public void notifyOffline(User friend) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.getHome().updateOfflineList(friend);
                Notifications offlineNotification = Notifications.create().title(friend.getName() + " is Offline")
                        .text(friend.getName() + " is Offline").graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                offlineNotification.show();

            }
        });

        // this.controller.getHome().updateOfflineList(friend);
    }

    @Override
    public void notifyRequest(String phoneNumber) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.getHome().updateRequestList(phoneNumber);
                Notifications requestNotification = Notifications.create().title("New Friend Request")
                        .text(phoneNumber).graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                requestNotification.show();

            }
        });

        //this.controller.getHome().updateRequestList(phoneNumber);
    }

    @Override
    public void notifyMode(User friend) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                controller.getHome().updateModeList(friend);
                Notifications modeNotification = Notifications.create().title(friend.getName() + " is " + friend.getMode())
                        .text(friend.getName() + " is " + friend.getMode()).graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                modeNotification.show();

            }
        });

    }

    @Override
    public void notifyAdd(User friend) throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.getHome().addFriend(friend);
                Notifications addNotification = Notifications.create().title("New Friend is added to your Contacts")
                        .text(friend.getPhoneNum() + " : " + friend.getName()).graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                addNotification.show();

            }
        });

        // this.controller.getHome().updateRequestList(phoneNumber);
    }

    @Override
    public void receiveMsg(String message, String chatWindowID) throws RemoteException {
        controller.display(message, chatWindowID);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications msgNotification = Notifications.create().title("New Message")
                        .text("New Message").graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                msgNotification.show();

            }
        });

    }

    @Override
    public void receiveGroupMsg(String message, ArrayList users) throws RemoteException {
        controller.displayGroupMessage(message,users);
    }

   

    public void receiveFile(RemoteInputStream ristream, String phoneNumber, String fileExtenstion) throws RemoteException {
/*
        System.out.println("inside where i want it to be ::D ");
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("FILE TRANSFER");
            alert.setContentText("there's a file sent to you do you want to accept it ?");
            result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                recievingFileTask = () -> {
                    try {
                        istream = RemoteInputStreamClient.wrap(ristream);
                        System.out.println("3adet el wrap :D ");
                        FileOutputStream ostream = null;
                        try {
                            file = new File("D:\\Program Files\\serverToClient");
                            File tempFile = File.createTempFile("recievedFile_", fileExtenstion, file);

                            ostream = new FileOutputStream(tempFile);
                            System.out.println("Writing file " + tempFile);

                            byte[] buf = new byte[1024];
                            //if(istream==null){System.out.println("istream is null before the infinite loop");}
                            int bytesRead = 0;
                            while ((bytesRead = istream.read(buf)) >= 0) {
                                System.out.println("writing file to client");
                                ostream.write(buf, 0, bytesRead);
                            }
                            System.out.println("3adena el while loop fel recieve file :D ");
                            ostream.flush();
                            Platform.runLater(() -> {
                                Notifications msgNotification = Notifications.create().title("DOWNLOADS!!")
                                        .text("The file has been downloaded").graphic(null)
                                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                                msgNotification.show();
                            });

                            //System.out.println("Finished writing file " + tempFile);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                if (istream != null) {
                                    try {
                                        istream.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } finally {
                                if (ostream != null) {
                                    try {
                                        ostream.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            istream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };

            }
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(recievingFileTask);
        }
        );*/
    }
}
