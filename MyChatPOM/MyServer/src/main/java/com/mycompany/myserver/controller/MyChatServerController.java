/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ha a l klam ya jemy ?
package mychatserver.controller;

import commonservice.User;
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
        try {
            MyChatServer start = new MyChatServer();
            // new UserDAO();
            //User user = new User("Kamal","012345","kamal@gmail.com","1234","M","italy","1999-09-19","Hellllllllo");
            User user = new User();
            user.setName("kamal");
            user.setPhoneNum("8989");
            user.setChatBotStatus(1);
            user.setBIO("Hello");
            user.setPassword("123");
            user.setCountry("germany");
            user.setStatus("online");
            user.setMode("busy");
            user.setGender("M");
            user.setEmail("admm@gmail.com");
            user.setDateOfBirth("1999-09-19");
            user.setPicture(null);
//
//       new UserDAO().updateUser(user);
//       
//       new UserDAO().deleteUser(user);
//       
//       new UserDAO().retrieveUser("0111");
//       new UserDAO().addUser(user);
         new FriendsDAO(DataSourceFactory.getMySQLDataSource().getConnection(), new UserDAO().retrieveUser("8989")).deleteFriend("12345");
         
         
        } catch (SQLException ex) {
            Logger.getLogger(MyChatServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
