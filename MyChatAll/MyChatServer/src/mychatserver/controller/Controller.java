/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.controller;

import commonservice.ClientService;
import commonservice.Message;
import commonservice.User;
import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mychatserver.model.MyChatServer.mysqlDataSource;

/**
 *
 * @author Sahar Hany
 */
public class Controller {

    static ArrayList<User> onlineUsers = new ArrayList<User>();
  //  static ArrayList<ClientService> onlineClientInterface = new ArrayList<ClientService>();
    
    private static int online = 0;
    private static int offline = 0;
    private static int female = 0;
    private static int male = 0;
    static Map<String, Integer> countryStatistics = new HashMap<String, Integer>();
    static Map<String, Integer> entryStatistics = new HashMap<String, Integer>();
    //static ClientService clientService;

    public static void addOnlineUser(User user) {
        onlineUsers.add(user);
//        online++;
    }

    public static void removeOnlineUser(User user) {
        onlineUsers.remove(user);
        //      online--;
    }

    public static int countOffline() {
        try {
            String query = " select count(PhoneNum) from user where Status IN('offline','Offline')";

            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (resultSet.next()) {
                offline = resultSet.getInt(1);
                System.out.println(offline);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return offline;
        }
    }

    public static int countOnline() {
        try {
            String query = " select count(PhoneNum) from user where Status IN('online','Online')";

            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (resultSet.next()) {
                online = resultSet.getInt(1);
                System.out.println(online);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return online;
        }

    }

    public static int countFemale() {
        try {
            String query = " select count(PhoneNum) from user where Gender='F'";

            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (resultSet.next()) {
                female = resultSet.getInt(1);
                System.out.println(female);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return female;
        }

    }

    public static int countMale() {
        try {
            String query = " select count(PhoneNum) from user where Gender='M'";

            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (resultSet.next()) {
                male = resultSet.getInt(1);
                System.out.println(male);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return male;
        }

    }

    public static Map<String, Integer> groupByCountry() {
        try {
            String query = " SELECT Country,count(PhoneNum) FROM user group by Country";

            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            while (resultSet.next()) {
                countryStatistics.put(resultSet.getString(1), resultSet.getInt(2));
                System.out.println(resultSet.getString(1) + " = " + resultSet.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return countryStatistics;
        }

    }

    public static Map<String, Integer> getEntryTimes() {
        try {
            String query = " SELECT PhoneNum , EntryTimes FROM user";

            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            while (resultSet.next()) {
                entryStatistics.put(resultSet.getString(1), resultSet.getInt(2));
                System.out.println(resultSet.getString(1) + " = " + resultSet.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return entryStatistics;
        }

    }

    
    //lesa m7taga tzbet
/*    public static void broadCast(Message message) {
        for (User user : onlineUsers) {
            try {
                clientService.receiveMsg(message);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }
*/
}
