/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychatserver.model.DAOImpl.FriendsDAO;
import mychatserver.model.DAOImpl.UserDAO;
import mychatserver.model.DataSourceFactory;
import mychatserver.model.MyChatServer;

/**
 *
 * @author AmrHesham
 */
public class MyChatServerController {

    
    public static void main(String[] args) {
        
        MyChatServer start = new MyChatServer();
        Controller control = new Controller();
        control.countOffline();
        control.countFemale();
        control.countMale();
        control.countOnline();
        control.groupByCountry();
        control.getEntryTimes();
        
    }
    
}
