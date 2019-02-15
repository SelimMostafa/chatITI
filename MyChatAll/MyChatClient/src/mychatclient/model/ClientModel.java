/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatclient.model;

import mychatclient.controller.*;
import mychatclient.view.view.RegisterForm;
import commonservice.ServerService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AmrHesham
 */
public class ClientModel  {
    public static ServerService serverservice;
    

    public ClientModel() {
        try {
            Registry registry=LocateRegistry.getRegistry("127.0.0.1",5000);
            serverservice = (ServerService) registry.lookup("chatService");
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ClientModel();
        
    }

}
