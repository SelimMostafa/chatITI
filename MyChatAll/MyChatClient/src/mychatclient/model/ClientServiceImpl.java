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

                Notifications onlineNotification = Notifications.create().title(friend.getName() + " go Online")
                        .text(friend.getName() + " go Online").graphic(null)
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
                Notifications offlineNotification = Notifications.create().title(friend.getName() + " go Offline")
                        .text(friend.getName() + " go Offline").graphic(null)
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
                Notifications modeNotification = Notifications.create().title(friend.getName() + " go " + friend.getMode())
                        .text(friend.getName() + " go " + friend.getMode()).graphic(null)
                        .hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT);

                modeNotification.show();

            }
        });

        //  this.controller.getHome().updateModeList(friend);
    }

    @Override
    public void notifyAdd(User friend) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
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
    }

}
