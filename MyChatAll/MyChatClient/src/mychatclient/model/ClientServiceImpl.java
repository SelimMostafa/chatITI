/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.model;

import commonservice.ClientService;
import commonservice.User;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;
import mychatclient.controller.MyChatClient;
import org.controlsfx.control.Notifications;

/**
 *
 * @author HP
 */
public class ClientServiceImpl extends UnicastRemoteObject implements ClientService {

    MyChatClient controller;

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

   
}
