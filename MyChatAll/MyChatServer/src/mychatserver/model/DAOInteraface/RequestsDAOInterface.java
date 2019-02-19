/*
  This an interface representing the CRUD operations done on the requests table in the DB.
 */
package mychatserver.model.DAOInteraface;

import commonservice.User;
import java.util.ArrayList;

/**
 *
 * @author Mohamed Jamal
 */
public interface RequestsDAOInterface {
    
    public boolean addFriendRequest(String phoneNumber);
    public boolean checkFriendRequest(String sender , String receiver);
    public ArrayList<String> retrieveOutgoingRequests();
    public ArrayList<String> retrieveIncomingRequests();
    public boolean deleteRequest(String sender , String receiver);
    
}
