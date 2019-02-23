/*
 *  This an interface representing the CRUD operations done on the user table in the DB.
 */
package mychatserver.model.DAOInteraface;

import commonservice.User;

/**
 *
 * @author Mohamed Jamal
 */
public interface UserDAOInterface {
    
    public boolean addUser(User user);
    public User    retrieveUser(String phoneNumber);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public boolean updateEntryTimes(User user);
    public boolean updateStatus(User user);
    public boolean updateMode(User user);

}
