/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model.DAOImpl;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import mychatserver.model.DAOInteraface.UserStatisticsDAOInterface;
import mychatserver.model.DataSourceFactory;

/**
 *
 * @author HP
 */
public class UserStatisticsDAO implements UserStatisticsDAOInterface {

    MysqlDataSource mysqlDataSource = DataSourceFactory.getMySQLDataSource();

    public int countOffline() {
        int offline = 0;
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

    public int countOnline() {
        int online = 0;
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

    public int countFemale() {
        int female = 0;
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

    public int countMale() {
        int male = 0;
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

    public Map<String, Integer> groupByCountry() {
        Map<String, Integer> countryStatistics = new HashMap<String, Integer>();

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

    public Map<String, Integer> getEntryTimes() {
        
        Map<String, Integer> entryStatistics = new HashMap<String, Integer>();

        try {
            String query = " SELECT Name , EntryTimes FROM user";

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

}
