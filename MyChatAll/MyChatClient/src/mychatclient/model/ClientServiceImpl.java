/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.model;

import commonservice.ClientService;
import commonservice.User;
import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;
import mychatclient.controller.MyChatClient;
import org.controlsfx.control.Notifications;

/**
 *
 * @author HP
 */
public class ClientServiceImpl implements ClientService {

    MyChatClient controller;

    public ClientServiceImpl(MyChatClient controller) {
        this.controller = controller;
    }

    @Override
    public void notifyOnline(User friend) throws RemoteException {

    }

    @Override
    public void notifyOffline(User friend) throws RemoteException {

    }

    @Override
    public void notifyRequest(String phoneNumber) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyMode(User friend) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
