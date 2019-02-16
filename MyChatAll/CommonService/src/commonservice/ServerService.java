/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonservice;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author AmrHesham
 */
public interface ServerService extends Remote {
    public User login(String phone , String password) throws RemoteException;
    public boolean register(User user) throws RemoteException;
    //chat session msh 3arfen da a asln we yalla chatbot kaman aha
    public void sendMessage(Message message)throws RemoteException;
    //public void sendFile(File file);
    public void updateProfile()throws RemoteException;
    public void notifyOnlineOffline()throws RemoteException;
    public User addNewContact(String phoneNumber)throws RemoteException;
    public void showFriendsStatus()throws RemoteException;
    public void signout()throws RemoteException;
    public boolean checkUserAvailability(String phoneNumber)throws RemoteException;
    
}
