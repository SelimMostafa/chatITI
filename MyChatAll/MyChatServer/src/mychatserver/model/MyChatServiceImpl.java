/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;

import commonservice.Message;
import commonservice.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import mychatserver.model.DAOImpl.FriendsDAO;
import mychatserver.model.DAOImpl.RequestsDAO;
import mychatserver.model.DAOImpl.UserDAO;
import static mychatserver.model.MyChatServer.mysqlDataSource;

/**
 *
 * @author AmrHesham
 */
public class MyChatServiceImpl extends UnicastRemoteObject implements Remote, commonservice.ServerService {

    UserDAO userDAO;
    FriendsDAO friendDAO;
    RequestsDAO requestDAO;
    User user;

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
            userTest=null;

        } else {
            this.user = userTest;
            try {
                this.friendDAO = new FriendsDAO(mysqlDataSource.getConnection(), user);
                this.requestDAO = new RequestsDAO(mysqlDataSource.getConnection(), user);
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
            try {
                String query = " select Sender from requests where Sender = ? AND Receiver =? ";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
                preparedStmt.setString(1, phoneNumber);
                preparedStmt.setString(2, user.getPhoneNum());
                // execute the preparedstatement
                preparedStmt.execute();
                ResultSet resultSet = preparedStmt.getResultSet();

                //if the number doesn't request the user
                if (!resultSet.next()) {
                    System.out.println("cannot add the contact because the contact hasn't request the user but a request by the user is made");
//                return null ;
                } else {
                    if (this.friendDAO.addFriend(phoneNumber)) {
                        System.out.println("is added successfully");
                        friend = this.friendDAO.retrieveFriend(phoneNumber);
                        this.requestDAO.deleteRequest(phoneNumber);
                        query = " delete from requests where Sender = ? AND Receiver =?  ";

                        // create the mysql insert preparedstatement
                        preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
                        preparedStmt.setString(1, phoneNumber);
                        preparedStmt.setString(2, user.getPhoneNum());
                        // execute the preparedstatement
                        preparedStmt.execute();

                    } else {
                        System.out.println("failed to add");
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProfile() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    

}
