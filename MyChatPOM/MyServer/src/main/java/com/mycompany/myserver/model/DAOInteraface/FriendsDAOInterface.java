/*
 * This an interface representing the CRUD operations done on the friends table in the DB.
 */
package mychatserver.model.DAOInteraface;

import commonservice.User;

/**
 *
 * @author Mohamed Jamal
 */
public interface FriendsDAOInterface {
    
    public boolean addFriend(String phoneNumber);
    //Will retrieve iserId from the FriedList Table and then retrieve User profile from the User Table
    public User retrieveFriend(String phoneNumber);
    public boolean deleteFriend(String phoneNumber);
}
