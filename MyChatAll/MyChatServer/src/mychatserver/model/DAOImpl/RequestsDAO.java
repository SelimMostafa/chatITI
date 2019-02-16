/*
 * This an implementation for the CRUD operations done on the requests table in the DB.
 */
package mychatserver.model.DAOImpl;

import commonservice.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychatserver.model.DAOInteraface.RequestsDAOInterface;

/**
 *
 * @author Sahar Hany
 */
public class RequestsDAO implements RequestsDAOInterface {

    Connection connection;
    User user;
    FriendsDAO friendDAO;

    public RequestsDAO(Connection connection, User user) {
        this.connection = connection;
        this.user = user;
        friendDAO = new FriendsDAO(connection, user);
    }

    @Override
    public boolean addFriendRequest(String phoneNumber) {
        boolean check = false;

        if (!friendDAO.isFriend(phoneNumber)) {
            try {

                if (!checkFriendRequest(phoneNumber)) {
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
    public boolean checkFriendRequest(String phoneNumber) {
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
            
            while(resultSet.next())
            {
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
            
            while(resultSet.next())
            {
                String phone = resultSet.getString(1);
                friendsNums.add(phone);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friendsNums;
        }

    }

    
    @Override
    public boolean deleteRequest(String phoneNumber) {

        boolean check = false;

        try {
            if (checkFriendRequest(phoneNumber)) {
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
