/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.mysql.cj.jdbc.MysqlDataSource;
import commonservice.ClientService;
import commonservice.Message;
import commonservice.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychatserver.controller.Controller;
//import javafx.scene.control.Alert;
import mychatserver.model.DAOImpl.FriendsDAO;
import mychatserver.model.DAOImpl.RequestsDAO;
import mychatserver.model.DAOImpl.UserDAO;

/**
 *
 * @author AmrHesham
 */
public class MyChatServiceImpl extends UnicastRemoteObject implements Remote, commonservice.ServerService {

    UserDAO userDAO;
    FriendsDAO friendDAO;
    RequestsDAO requestDAO;
    User user;
    ClientService clientInterface;
    Controller control = Controller.getInstance();
    ClientConnection clientConnection;
    MysqlDataSource mysqlDataSource = DataSourceFactory.getMySQLDataSource();

    public MyChatServiceImpl() throws RemoteException {
        userDAO = new UserDAO();
    }

    @Override
    public User login(String phone, String password, ClientService clientInterface) throws RemoteException {
        User userTest = userDAO.retrieveUser(phone);
        if (userTest == null) {
            System.out.println("This number doesn't exist");

        } else if (!userTest.getPassword().equals(password)) {
            System.out.println("This password doesn't match ");
            userTest = null;

        } else {
            userTest.setStatus("Online");
            int times = userTest.getEntryTimes() + 1;
            userTest.setEntryTimes(times);
            user = userTest;

            // this.clientInterface = clientInterface;
            friendDAO = new FriendsDAO(user);
            requestDAO = new RequestsDAO(user);
            userDAO.updateEntryTimes(user);
            userDAO.updateStatus(user);
            control.addOnlineUser(user, clientInterface);
            System.out.println("Times of login = " + user.getEntryTimes());
            System.out.println("user is logined successfully");
            clientConnection = new ClientConnection();

            clientConnection.onlineNotificationFriends(user);

            return userTest;

        }
        return userTest;
    }

    @Override
    public void sendMessage(String message, ArrayList<User> phoneNumbersList, String senderPhoneNumber) throws RemoteException {
        phoneNumbersList.forEach((c) -> {
            try {
                if (!c.getPhoneNum().equals(senderPhoneNumber)) {
                    control.getClientInterfaceObject(c).receiveMsg(message, senderPhoneNumber);
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public User addNewContact(User user, String phoneNumber) throws RemoteException {
        User friend = null;
        System.out.println("inside add new Contact = friend ->" + phoneNumber + " ,my number =" + user.getPhoneNum());
        RequestsDAO requestDAO = new RequestsDAO(user);
        FriendsDAO friendsDAO = new FriendsDAO(user);

        if (requestDAO.addFriendRequest(phoneNumber)) {
            boolean checkRequest = requestDAO.checkFriendRequestFromNumber(phoneNumber);
            //if the number doesn't request the user
            if (!checkRequest) {
                System.out.println("cannot add the contact because the contact hasn't request the user but a request by the user is made");
//                return null ;
            } else {
                if (friendsDAO.addFriend(phoneNumber)) {
                    System.out.println("is added successfully");
                    friend = friendsDAO.retrieveFriend(phoneNumber);
                    requestDAO.deleteRequestToNumber(phoneNumber);
                    requestDAO.deleteRequestFromNumber(phoneNumber);
                } else {
                    System.out.println("failed to add");
                }

            }
        }
        return friend;

    }

    @Override
    public void showFriendsStatus() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signout() throws RemoteException {
        //  Controller.removeOnlineUser(user, clientService);

    }

    @Override
    public boolean updateProfile(User user) throws RemoteException {
        return this.userDAO.updateUser(user);
    }

    @Override
    public boolean register(User user) throws RemoteException {
        return userDAO.addUser(user);

    }

    @Override
    public boolean checkUserAvailability(String phoneNumber) throws RemoteException {
        User userTest = userDAO.retrieveUser(phoneNumber);
        if (userTest == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ArrayList<String> getIncomingRequests(User user) throws RemoteException {
        return new RequestsDAO(user).retrieveIncomingRequests();
    }

    @Override
    public ArrayList<User> getFriends(User user) throws RemoteException {
        return new FriendsDAO(user).retrieveAllFriends();
    }

    @Override
    public ArrayList<User> getOnlineFriends(User user) throws RemoteException {
        return new FriendsDAO(user).retrieveOnlineFriends();
    }

    @Override
    public ArrayList<User> getOfflineFriends(User user) throws RemoteException {
        return new FriendsDAO(user).retrieveOfflineFriends();
    }

    @Override
    public void updateMode(User user) throws RemoteException {
        new UserDAO().updateMode(user);
    }

    @Override
    public void sendFile(RemoteInputStream ristream) throws RemoteException {
        InputStream istream = null;
        try {

            istream = RemoteInputStreamClient.wrap(ristream);
            FileOutputStream ostream = null;
            try {
                
                File tempFile = File.createTempFile("sentFile_", ".dat");
                ostream = new FileOutputStream(tempFile);
                System.out.println("Writing file " + tempFile);
                
                byte[] buf = new byte[1024];
                
                int bytesRead = 0;
                while ((bytesRead = istream.read(buf)) >= 0) {
                    ostream.write(buf, 0, bytesRead);
                }
                ostream.flush();
                
                System.out.println("Finished writing file " + tempFile);
                
            } finally {
                try {
                    if (istream != null) {
                        istream.close();
                    }
                } finally {
                    if (ostream != null) {
                        ostream.close();
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(MyChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                istream.close();
            } catch (IOException ex) {
                Logger.getLogger(MyChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
