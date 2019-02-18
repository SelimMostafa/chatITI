/*
 * This an interface representing the CRUD operations done on the friends table in the DB.
 */
package mychatserver.model.DAOInteraface;

import commonservice.User;
import java.util.ArrayList;

/**
 *
 * @author Mohamed Jamal
 */
public interface FriendsDAOInterface {
    
    public boolean addFriend(String phoneNumber);
    //Will retrieve userId from the FriedList Table and then retrieve User profile from the User Table
    public User retrieveFriend(String phoneNumber);
    public boolean deleteFriend(String phoneNumber);
    public boolean isFriend(String phoneNumber);
    public ArrayList<User> retrieveAllFriends() ;
    public ArrayList<User> retrieveOnlineFriends() ;
    public ArrayList<User> retrieveOfflineFriends() ;
    
}
