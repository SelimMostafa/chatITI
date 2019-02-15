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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychatserver.model.DAOImpl.UserDAO;

/**
 *
 * @author AmrHesham
 */
public class MyChatServiceImpl extends UnicastRemoteObject implements Remote, commonservice.ServerService {

    
    UserDAO userDAO;
    public MyChatServiceImpl() throws RemoteException {
        userDAO=new UserDAO();
    }

    @Override
    public User login(String phone, String password) throws RemoteException {
        
        return userDAO.retrieveUser(phone, password);
        
    }

    @Override
    public boolean register(User user) throws RemoteException {
       return userDAO.addUser(user);

    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProfile(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyOnlineOffline(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User addNewContact(String phoneNumber) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showFriendsStatus(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signout(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
