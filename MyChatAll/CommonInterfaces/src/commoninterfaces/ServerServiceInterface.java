/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commoninterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author AmrHesham
 */
public interface ServerServiceInterface extends Remote {
    public void broadcast(String message)throws RemoteException;
    public void register(ClientServiceInterface client)throws RemoteException;
    public void unregister(ClientServiceInterface client)throws RemoteException;
}
