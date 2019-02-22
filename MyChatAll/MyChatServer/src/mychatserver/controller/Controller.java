/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.controller;

import com.mysql.cj.jdbc.MysqlDataSource;
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
import mychatserver.model.DAOImpl.UserStatisticsDAO;

/**
 *
 * @author Sahar Hany
 */
public class Controller {

    ArrayList<User> onlineUsers = new ArrayList<User>();
    Map<String, ClientService> client = new HashMap<String, ClientService>();

    private int online = 0;
    private int offline = 0;
    private int female = 0;
    private int male = 0;
    private Map<String, Integer> countryStatistics = new HashMap<String, Integer>();
    private Map<String, Integer> entryStatistics = new HashMap<String, Integer>();
    private UserStatisticsDAO userStatObject = new UserStatisticsDAO();

    private static Controller controllerInstance = new Controller();

    private Controller() {

    }

    public static Controller getInstance() {
        return controllerInstance;
    }

    public void addOnlineUser(User user, ClientService clientService) {
        onlineUsers.add(user);
        client.put(user.getPhoneNum(), clientService);
    }

    public void removeOnlineUser(User user, ClientService clientService) {
        onlineUsers.remove(user);
        client.remove(user.getPhoneNum(), clientService);
        //      online--;
    }

    public int countOffline() {
        offline = userStatObject.countOffline();
        return offline;

    }

    public int countOnline() {
        online = userStatObject.countOnline();
        return online;

    }

    public int countFemale() {
        female = userStatObject.countFemale();
        return female;
    }

    public int countMale() {
        male = userStatObject.countMale();
        return male;
    }

    public Map<String, Integer> groupByCountry() {
        countryStatistics = userStatObject.groupByCountry();
        return countryStatistics;

    }

    public Map<String, Integer> getEntryTimes() {
        entryStatistics = userStatObject.getEntryTimes();
        return entryStatistics;

    }

    public ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public ClientService getClientInterfaceObject(User user) {
        return client.get(user.getPhoneNum());
    }

}
