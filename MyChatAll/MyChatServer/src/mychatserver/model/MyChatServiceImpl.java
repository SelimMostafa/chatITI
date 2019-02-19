/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import commonservice.ClientService;
import commonservice.Message;
import commonservice.User;
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
    ClientService clientService;

    MysqlDataSource mysqlDataSource = DataSourceFactory.getMySQLDataSource();

    public MyChatServiceImpl() throws RemoteException {
        userDAO = new UserDAO();
    }

    @Override
    public User login(String phone, String password) throws RemoteException {
        User userTest = userDAO.retrieveUser(phone);
        if (userTest == null) {
            System.out.println("This number doesn't exist");

        } else if (!userTest.getPassword().equals(password)) {
            System.out.println("This password doesn't match ");
            userTest = null;

        } else {
            int times = userTest.getEntryTimes() + 1;
            userTest.setEntryTimes(times);
            this.user = userTest;
            try {

                this.friendDAO = new FriendsDAO(mysqlDataSource.getConnection(), user);
                this.requestDAO = new RequestsDAO(mysqlDataSource.getConnection(), user);
                this.userDAO.updateEntryTimes(user);
    //            Controller.addOnlineUser(user, clientService);
                System.out.println("Times of login = " + user.getEntryTimes());
                System.out.println("user is logined successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return userTest;
            }

        }
        return userTest;
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User addNewContact(String phoneNumber) throws RemoteException {

        User friend = null;
        if (this.requestDAO.addFriendRequest(phoneNumber)) {
            boolean checkRequest = this.requestDAO.checkFriendRequest(phoneNumber, user.getPhoneNum());
            //if the number doesn't request the user
            if (!checkRequest) {
                System.out.println("cannot add the contact because the contact hasn't request the user but a request by the user is made");
//                return null ;
            } else {
                if (this.friendDAO.addFriend(phoneNumber)) {
                    System.out.println("is added successfully");
                    friend = this.friendDAO.retrieveFriend(phoneNumber);
                    this.requestDAO.deleteRequest(user.getPhoneNum(), phoneNumber);
                    this.requestDAO.deleteRequest(phoneNumber, user.getPhoneNum());
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
    public void notifyOnlineOffline() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return this.requestDAO.retrieveIncomingRequests();
    }

    @Override
    public ArrayList<User> getFriends(User user) throws RemoteException {
        return this.friendDAO.retrieveAllFriends();
    }

    @Override
    public ArrayList<User> getOnlineFriends(User user) throws RemoteException {
        return this.friendDAO.retrieveOnlineFriends();
    }

    @Override
    public ArrayList<User> getOfflineFriends(User user) throws RemoteException {
        return this.friendDAO.retrieveOfflineFriends();
    }

    @Override
    public void connectToServer(ClientService clientService) throws RemoteException {
        this.clientService = clientService;
    }

    @Override
    public void updateMode(User user) throws RemoteException {
        this.userDAO.updateMode(user);
    }

}
