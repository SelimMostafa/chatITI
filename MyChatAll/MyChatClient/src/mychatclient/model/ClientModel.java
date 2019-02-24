/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.model;

import commonservice.ClientService;
import mychatclient.controller.*;
import mychatclient.view.view.RegisterForm;
import commonservice.ServerService;
import commonservice.User;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AmrHesham
 */
public class ClientModel {

    public ServerService serverservice;
    private static ClientModel clientModelInstance = new ClientModel();

    private ClientModel() {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5000);
            serverservice = (ServerService) registry.lookup("chatService");
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ClientModel getInstance() {
        return clientModelInstance;
    }

    public boolean checkUser(String phoneNum) {
        boolean isUser = false;
        try {
            isUser = serverservice.checkUserAvailability(phoneNum);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return isUser;
        }

    }

    public boolean registerNewUser(User user) {
        boolean registred = false;
        try {
            registred = serverservice.register(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registred;
    }

    public ArrayList<String> getRequests(User user) {
        ArrayList<String> requests = null;
        try {
            requests = serverservice.getIncomingRequests(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } finally {
            return requests;
        }
    }

    public ArrayList<User> getFriends(User user) {
        ArrayList<User> friends = null;
        try {
            friends = serverservice.getFriends(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } finally {
            return friends;
        }
    }

    public User checkPassword(String PhoneNumber, String password, ClientServiceImpl clientServiceImpl) {
        User user = null;
        try {
            user = serverservice.login(PhoneNumber, password, clientServiceImpl);

        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }

    }

    public ArrayList<User> getOnlineFriends(User user) {
        ArrayList<User> friends = null;
        try {
            friends = serverservice.getOnlineFriends(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } finally {
            return friends;
        }
    }

    public ArrayList<User> getOfflineFriends(User user) {
        ArrayList<User> friends = null;
        try {
            friends = serverservice.getOfflineFriends(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } finally {
            return friends;
        }
    }

    public void updateMode(User user) {
        try {
            serverservice.updateMode(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(String message, ArrayList<User> phoneNumbersList, String senderPhoneNumber) {
        try {
            serverservice.sendMessage(message, phoneNumbersList, senderPhoneNumber);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerService getServerservice() {
        return serverservice;
    }

    public void signOut() {
        try {
            serverservice.signout();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProfile(User user) {
        try {
            serverservice.updateProfile(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

}
