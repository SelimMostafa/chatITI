package mychatserver.model.DAOImpl;

import commonservice.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychatserver.model.DAOInteraface.FriendsDAOInterface;

/**
 *
 * @author Sahar Hany
 */
public class FriendsDAO implements FriendsDAOInterface {

    Connection connection;
    User user;

    public FriendsDAO(Connection connection, User user) {
        this.connection = connection;
        this.user = user;
    }

    @Override
    public boolean addFriend(String phoneNumber) {
        try {

            if (!isFriend(phoneNumber)) {
                String query = " insert into Friends values (?,?) ";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, user.getPhoneNum());
                preparedStmt.setString(2, phoneNumber);
                // execute the preparedstatement
                preparedStmt.execute();

                // create the mysql insert preparedstatement
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, phoneNumber);
                preparedStmt.setString(2, user.getPhoneNum());
                // execute the preparedstatement
                preparedStmt.execute();

                return true;
            } else {
                System.out.println("this user is already a friend");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public User retrieveFriend(String phoneNumber) {
        User friend = null;
        try {

            System.out.println("inside retrieve");
            String query = " select Friend from friends where friend = ? AND user =? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, phoneNumber);
            preparedStmt.setString(2, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();
            if (!resultSet.next()) {
                System.out.println("this number isn't a friend to the user ");
                return null;

            } else {

                System.out.println("this number is a friend to the user ");

                query = " select Name, PhoneNum, Gender, Country, DOB, Picture, Password, Status, ChatBotStatus, Email, BIO ,Mode from user where PhoneNum = ?";

                // create the mysql insert preparedstatement
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, phoneNumber);
                // execute the preparedstatement
                preparedStmt.execute();
                resultSet = preparedStmt.getResultSet();

                if (!resultSet.next()) {
                    System.out.println("Doesn't Exist");
                } else {

                    String Name = resultSet.getString(1);
                    System.out.println("Friend Exists -> " + Name);

                    friend = new User();
                    friend.setName(resultSet.getString(1));
                    friend.setPhoneNum(resultSet.getString(2));
                    friend.setGender(resultSet.getString(3));
                    friend.setCountry(resultSet.getString(4));
                    friend.setDateOfBirth(resultSet.getString(5));
                    //friend.setPicture(resultSet.getBytes(6));
                    friend.setPassword(resultSet.getString(7));
                    friend.setStatus(resultSet.getString(8));
                    friend.setChatBotStatus(resultSet.getInt(9));
                    friend.setEmail(resultSet.getString(10));
                    friend.setBIO(resultSet.getString(11));
                    friend.setMode(resultSet.getString(12));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friend;
        }
    }

    @Override
    public boolean deleteFriend(String phoneNumber) {
        try {

            if (isFriend(phoneNumber)) {
                String query = " delete from friends where friend = ? AND user =? ";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, phoneNumber);
                preparedStmt.setString(2, user.getPhoneNum());
                // execute the preparedstatement
                preparedStmt.execute();

                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, user.getPhoneNum());
                preparedStmt.setString(2, phoneNumber);
                // execute the preparedstatement
                preparedStmt.execute();

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public boolean isFriend(String phoneNumber) {
        try {

            String query = " select Friend from friends where friend = ? AND user =? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, phoneNumber);
            preparedStmt.setString(2, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();
            if (!resultSet.next()) {
                System.out.println("this number isn't a friend to the user ");
                return false;

            } else {

                System.out.println("this number is a friend to the user ");
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<User> retrieveAllFriends() {
        //User friend = null;
        ArrayList<User> friends = new ArrayList<User>();

        try {

            System.out.println("inside retrieve");
            String query = " select Friend from friends where  user =? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();
            
            while (resultSet.next()) {
//              System.out.println("this number is a friend to the user ");

                query = " select Name, PhoneNum, Gender, Country, DOB, Picture, Password, Status, ChatBotStatus, Email, BIO ,Mode from user where PhoneNum = ?";

                // create the mysql insert preparedstatement
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, resultSet.getString(1));
                // execute the preparedstatement
                preparedStmt.execute();
                ResultSet resultSet2 = preparedStmt.getResultSet();

                if (!resultSet2.next()) {
                    System.out.println("Doesn't Exist");
                } else {

                    String Name = resultSet2.getString(1);
                    System.out.println("Friend Exists -> " + Name);

                    User friend = new User();
                    friend.setName(resultSet2.getString(1));
                    friend.setPhoneNum(resultSet2.getString(2));
                    friend.setGender(resultSet2.getString(3));
                    friend.setCountry(resultSet2.getString(4));
                    friend.setDateOfBirth(resultSet2.getString(5));
                    //friend.setPicture(resultSet.getBytes(6));
                    friend.setPassword(resultSet2.getString(7));
                    friend.setStatus(resultSet2.getString(8));
                    friend.setChatBotStatus(resultSet2.getInt(9));
                    friend.setEmail(resultSet2.getString(10));
                    friend.setBIO(resultSet2.getString(11));
                    friend.setMode(resultSet2.getString(12));

                    friends.add(friend);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friends;
        }

    }

    @Override
    public ArrayList<User> retrieveOnlineFriends() {
        ArrayList<User> friends = new ArrayList<User>();

        try {

            System.out.println("inside online retrieve");
            String query = " select Friend from friends where  user =? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();
            while (resultSet.next()) {
//              System.out.println("this number is a friend to the user ");

                query = " select Name, PhoneNum, Gender, Country, DOB, Picture, Password, Status, ChatBotStatus, Email, BIO ,Mode from user where PhoneNum = ? AND Status IN('online','Online')";

                // create the mysql insert preparedstatement
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, resultSet.getString(1));
                // execute the preparedstatement
                preparedStmt.execute();
                ResultSet resultSet2 = preparedStmt.getResultSet();

                if (!resultSet2.next()) {
                    System.out.println("Doesn't Exist");
                } else {

                    String Name = resultSet2.getString(1);
                    System.out.println("Friend Exists -> " + Name);

                    User friend = new User();
                    friend.setName(resultSet2.getString(1));
                    friend.setPhoneNum(resultSet2.getString(2));
                    friend.setGender(resultSet2.getString(3));
                    friend.setCountry(resultSet2.getString(4));
                    friend.setDateOfBirth(resultSet2.getString(5));
                    //friend.setPicture(resultSet.getBytes(6));
                    friend.setPassword(resultSet2.getString(7));
                    friend.setStatus(resultSet2.getString(8));
                    friend.setChatBotStatus(resultSet2.getInt(9));
                    friend.setEmail(resultSet2.getString(10));
                    friend.setBIO(resultSet2.getString(11));
                    friend.setMode(resultSet2.getString(12));

                    friends.add(friend);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friends;
        }

    }

    @Override
    public ArrayList<User> retrieveOfflineFriends() {
        ArrayList<User> friends = new ArrayList<User>();

        try {

            System.out.println("inside offline retrieve");
            String query = " select Friend from friends where  user =? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getPhoneNum());
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();
            while (resultSet.next()) {
//              System.out.println("this number is a friend to the user ");

                query = " select Name, PhoneNum, Gender, Country, DOB, Picture, Password, Status, ChatBotStatus, Email, BIO ,Mode from user where PhoneNum = ? AND Status IN('offline','Offline')";

                // create the mysql insert preparedstatement
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, resultSet.getString(1));
                // execute the preparedstatement
                preparedStmt.execute();
                ResultSet resultSet2 = preparedStmt.getResultSet();

                if (!resultSet2.next()) {
                    System.out.println("Doesn't Exist");
                } else {

                    String Name = resultSet2.getString(1);
                    System.out.println("Friend Exists -> " + Name);

                    User friend = new User();
                    friend.setName(resultSet2.getString(1));
                    friend.setPhoneNum(resultSet2.getString(2));
                    friend.setGender(resultSet2.getString(3));
                    friend.setCountry(resultSet2.getString(4));
                    friend.setDateOfBirth(resultSet2.getString(5));
                    //friend.setPicture(resultSet.getBytes(6));
                    friend.setPassword(resultSet2.getString(7));
                    friend.setStatus(resultSet2.getString(8));
                    friend.setChatBotStatus(resultSet2.getInt(9));
                    friend.setEmail(resultSet2.getString(10));
                    friend.setBIO(resultSet2.getString(11));
                    friend.setMode(resultSet2.getString(12));

                    friends.add(friend);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return friends;
        }

    }

}
