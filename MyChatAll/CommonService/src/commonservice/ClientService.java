/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author AmrHesham
 */
public interface ClientService extends Remote{
    public void receiveMsg(String message)throws RemoteException;
    //public void receiveFile(Message message)throws RemoteException;
    public void notifyOnline(User friend)throws RemoteException;
    public void notifyOffline(User friend)throws RemoteException;
    public void notifyRequest(String phoneNumber)throws RemoteException;
    public void notifyMode(User friend)throws RemoteException;
    public void notifyAdd(User friend)throws RemoteException;
    
}
