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

/*    public void requestNotificationFriend(String phoneNumber) {
        ClientService clientService = control.getClientInterfaceObject(phoneNumber);
        if (clientService != null) {
            try {
                clientService.notifyRequest(chatService.user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

    }
    */
}
