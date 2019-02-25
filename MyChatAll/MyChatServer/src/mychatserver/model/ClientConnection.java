/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;

import commonservice.ClientService;
import commonservice.User;
import java.rmi.RemoteException;
import java.util.ArrayList;
import mychatserver.controller.Controller;
import mychatserver.model.DAOImpl.FriendsDAO;

/**
 *
 * @author Sahar Hany
 */
public class ClientConnection {

    Controller control = Controller.getInstance();

    public ClientConnection() {

    }

    public void onlineNotificationFriends(User user) {

        ArrayList<User> friends = new FriendsDAO(user).retrieveOnlineFriends();
        System.out.println("friendslist length = " + friends.size());

        for (int counter = 0; counter < friends.size(); counter++) {
            ClientService clientService = control.getClientInterfaceObject(friends.get(counter));
            try {
                clientService.notifyOnline(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void offlineNotificationFriends(User user) {
        ArrayList<User> friends = new FriendsDAO(user).retrieveOnlineFriends();
        System.out.println("friendslist length = " + friends.size());

        for (int counter = 0; counter < friends.size(); counter++) {
            ClientService clientService = control.getClientInterfaceObject(friends.get(counter));
            try {
                clientService.notifyOffline(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void modeNotification(User user) {

        ArrayList<User> friends = new FriendsDAO(user).retrieveOnlineFriends();
        System.out.println("friendslist length = " + friends.size());

        for (int counter = 0; counter < friends.size(); counter++) {
            if (friends.get(counter).getStatus().equals("Online")) {

                ClientService clientService = control.getClientInterfaceObject(friends.get(counter));
                try {
                    clientService.notifyMode(user);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void notifyAdded(User friend, User user) {
        if (friend.equals("Online")) {

            ClientService clientService = control.getClientInterfaceObject(friend);
            try {
                clientService.notifyAdd(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
    public void updateRequestList(String phoneNumber, User user) {
        ClientService userClientService = control.getClientInterfaceObject(user);
        try {
            userClientService.notifyRequest(phoneNumber);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }*/

    public void notifyRequest(String phoneNumber) {
        ClientService userClientService = control.getClientInterfaceObjectByPhone(phoneNumber);
        try {
            userClientService.notifyRequest(phoneNumber);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }
}
