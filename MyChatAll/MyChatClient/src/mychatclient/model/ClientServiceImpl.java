/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.model;

import commonservice.ClientService;
import commonservice.User;
import java.rmi.RemoteException;

/**
 *
 * @author HP
 */
public class ClientServiceImpl implements ClientService{

    @Override
    public void notifyOnline(User friend) throws RemoteException {
        
    }

    @Override
    public void notifyOffline(User friend) throws RemoteException {
        
    }

    @Override
    public void notifyRequest(String phoneNumber) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyMode(User friend) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
