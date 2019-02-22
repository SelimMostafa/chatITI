/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.controller;

import commonservice.ClientService;
import commonservice.User;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Notifier {

    ClientService clientService;

    public Notifier(ClientService clientService) {
        this.clientService = clientService;
    }

    public void notifyOnline(User friend) {
        try {
            clientService.notifyOnline(friend);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void notifyOffline(User friend) {
        try {
            clientService.notifyOffline(friend);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void notifyRequest(String phoneNumber) {
        try {
            clientService.notifyRequest(phoneNumber);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void notifyMode(User friend) {
        try {
            clientService.notifyMode(friend);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

}
