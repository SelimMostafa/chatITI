/*
 * This an implementation for the CRUD operations done on the requests table in the DB.
 */
package mychatserver.model.DAOImpl;

import com.mysql.cj.jdbc.MysqlDataSource;
import commonservice.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychatserver.model.DAOInteraface.RequestsDAOInterface;
import mychatserver.model.DataSourceFactory;

/**
 *
 * @author Sahar Hany
 */
public class RequestsDAO implements RequestsDAOInterface {

    User user;
    FriendsDAO friendDAO;
    MysqlDataSource mysqlDataSource = DataSourceFactory.getMySQLDataSource();
    Connection connection;

    public RequestsDAO(User user) {
        this.user = user;
        try {
            connection = mysqlDataSource.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        friendDAO = new FriendsDAO(user);
    }

    @Override
    public boolean addFriendRequest(String phoneNumber) {
        boolean check = false;
        System.out.println("inside add friendRequest = friend ->" + phoneNumber + " ,my number =" + user.getPhoneNum());

        if (!friendDAO.isFriend(phoneNumber)) {
            try {

                if (!checkFriendRequestToNumber(phoneNumber)) {
                    System.out.println("this user doesn't request this number before");
                    String query = " insert into requests values (?,?) ";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setString(1, user.getPhoneNum());
                    preparedStmt.setString(2, phoneNumber);
                    // execute the preparedstatement
                    preparedStmt.execute();
                    check = true;

                } else {
                    System.out.println("this number is requested before ");
                    check = true;

                }

            } catch (SQLException ex) {
                check = false;
                ex.printStackTrace();
            } finally {
                return check;
            }
        } else {
            System.out.println("this phoneNumber is already a friend to this user");
            return false;
        }
    }

    @Override
    public boolean checkFriendRequestToNumber(String phoneNumber) {
        boolean check = false;

        try {
            String query = " select Sender from requests where Sender = ? AND Receiver =? ";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getPhoneNum());
            preparedStmt.setString(2, phoneNumber);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (resultSet.next()) {
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException ex) {
            check = false;
            ex.printStackTrace();
        } finally {
            return check;
        }
    }

    //my requests to others
    @Override
    public ArrayList<String> retrieveOutgoingRequests() {
        ArrayList<String> friendsNums = new ArrayList<String>();

        try {
            String query = " select Receiver from requests where Sender = ? ";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            while (resultSet.next()) {
                String phone = resultSet.getString(1);
                friendsNums.add(phone);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friendsNums;
        }

    }

    //requests of others to me
    @Override
    public ArrayList<String> retrieveIncomingRequests() {
        ArrayList<String> friendsNums = new ArrayList<String>();

        try {
            String query = " select Sender from requests where Receiver = ? ";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            while (resultSet.next()) {
                String phone = resultSet.getString(1);
                friendsNums.add(phone);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friendsNums;
        }

    }

    // @Override
//    public boolean deleteRequestToNumber(String sender, String receiver) {
        /*
     boolean check = false;

     try {
     if (checkFriendRequestfromNumber(sender,receiver)) {
     //                System.out.println("this user doesn't request this number before");
     String query = " delete from requests where Sender = ? AND Receiver =?  ";

     PreparedStatement preparedStmt = connection.prepareStatement(query);
     preparedStmt.setString(1, sender);
     preparedStmt.setString(2, receiver);
     // execute the preparedstatement
     preparedStmt.execute();
     check = true;
     //                    return true;
     } else {
     check = false;

     }
     } catch (SQLException ex) {
     check = false;
     ex.printStackTrace();
     } finally {
     return check;
     }
     */ //return true;
    // }
    @Override
    public boolean checkFriendRequestFromNumber(String phoneNumber) {
        boolean check = false;

        try {
            String query = " select Sender from requests where Sender = ? AND Receiver =? ";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, phoneNumber);
            preparedStmt.setString(2, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (resultSet.next()) {
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException ex) {
            check = false;
            ex.printStackTrace();
        } finally {
            return check;
        }
    }

    @Override
    public boolean deleteRequestFromNumber(String phoneNumber) {
        boolean check = false;

        try {
            if (checkFriendRequestFromNumber(phoneNumber)) {
//                System.out.println("this user doesn't request this number before");
                String query = " delete from requests where Sender = ? AND Receiver =?  ";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, phoneNumber);
                preparedStmt.setString(2, user.getPhoneNum());
                // execute the preparedstatement
                preparedStmt.execute();
                check = true;
//                    return true;
            } else {
                check = false;

            }
        } catch (SQLException ex) {
            check = false;
            ex.printStackTrace();
        } finally {
            return check;
        }

    }

    @Override
    public boolean deleteRequestToNumber(String phoneNumber) {
        boolean check = false;

        try {
            if (checkFriendRequestToNumber(phoneNumber)) {
//                System.out.println("this user doesn't request this number before");
                String query = " delete from requests where Sender = ? AND Receiver =?  ";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, user.getPhoneNum());
                preparedStmt.setString(2, phoneNumber);
                // execute the preparedstatement
                preparedStmt.execute();
                check = true;
//                    return true;
            } else {
                check = false;

            }
        } catch (SQLException ex) {
            check = false;
            ex.printStackTrace();
        } finally {
            return check;
        }

    }

}
