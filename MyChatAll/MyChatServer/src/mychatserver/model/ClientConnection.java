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

/**
 *
 * @author Sahar Hany
 */
public class ClientConnection {

    MyChatServiceImpl chatService;
    Controller control = Controller.getInstance();

    public ClientConnection(MyChatServiceImpl chatService) {
        this.chatService = chatService;

    }

    public void onlineNotificationFriends() {

        ArrayList<User> friends = this.chatService.friendDAO.retrieveOnlineFriends();
        System.out.println("friendslist length = " + friends.size());

        for (int counter = 0; counter < friends.size(); counter++) {
            ClientService clientService = control.getClientInterfaceObject(friends.get(counter));
            try {
                clientService.notifyOnline(chatService.user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void offlineNotificationFriends() {
        ArrayList<User> friends = this.chatService.friendDAO.retrieveOnlineFriends();
        System.out.println("friendslist length = " + friends.size());

        for (int counter = 0; counter < friends.size(); counter++) {
            ClientService clientService = control.getClientInterfaceObject(friends.get(counter));
            try {
                clientService.notifyOffline(chatService.user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void modeNotification() {

        ArrayList<User> friends = this.chatService.friendDAO.retrieveOnlineFriends();
        System.out.println("friendslist length = " + friends.size());

        for (int counter = 0; counter < friends.size(); counter++) {
            if (friends.get(counter).getStatus().equals("Online")) {

                ClientService clientService = control.getClientInterfaceObject(friends.get(counter));
                try {
                    clientService.notifyMode(chatService.user);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void notifyAdded(User friend) {
        if (friend.equals("Online")) {

            ClientService clientService = control.getClientInterfaceObject(friend);
            try {
                clientService.notifyAdd(chatService.user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateRequestList(String phoneNumber) {
        ClientService userClientService = control.getClientInterfaceObject(chatService.user);
        try {
            userClientService.notifyRequest(phoneNumber);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
