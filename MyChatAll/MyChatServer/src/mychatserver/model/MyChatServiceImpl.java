/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import commonservice.ClientService;
import commonservice.Message;
import commonservice.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import mychatserver.controller.Controller;
//import javafx.scene.control.Alert;
import mychatserver.model.DAOImpl.FriendsDAO;
import mychatserver.model.DAOImpl.RequestsDAO;
import mychatserver.model.DAOImpl.UserDAO;
import mychatserver.model.chatsession.ChatSessionType;
import mychatserver.model.chatsession.MsgType;
import mychatserver.model.chatsession.ObjectFactory;

/**
 *
 * @author AmrHesham
 */
public class MyChatServiceImpl extends UnicastRemoteObject implements Remote, commonservice.ServerService {

    UserDAO userDAO;
    FriendsDAO friendDAO;
    RequestsDAO requestDAO;
    User user;
    ClientService clientInterface;
    Controller control = Controller.getInstance();
    ClientConnection clientConnection;
    MysqlDataSource mysqlDataSource = DataSourceFactory.getMySQLDataSource();

    public MyChatServiceImpl() throws RemoteException {
        userDAO = new UserDAO();
    }

    @Override
    public User login(String phone, String password, ClientService clientInterface) throws RemoteException {
        User userTest = userDAO.retrieveUser(phone);
        if (userTest == null) {
            System.out.println("This number doesn't exist");

        } else if (!userTest.getPassword().equals(password)) {
            System.out.println("This password doesn't match ");
            userTest = null;

        } else {
            userTest.setStatus("Online");
            int times = userTest.getEntryTimes() + 1;
            userTest.setEntryTimes(times);
            user = userTest;

            // this.clientInterface = clientInterface;
            friendDAO = new FriendsDAO(user);
            requestDAO = new RequestsDAO(user);
            userDAO.updateEntryTimes(user);
            userDAO.updateStatus(user);
            control.addOnlineUser(user, clientInterface);
            System.out.println("Times of login = " + user.getEntryTimes());
            System.out.println("user is logined successfully");
            clientConnection = new ClientConnection();

            clientConnection.onlineNotificationFriends(user);

            return userTest;

        }
        return userTest;
    }

    @Override
    public void sendMessage(String message, ArrayList<User> chatUsers, String senderPhoneNumber) throws RemoteException {
        System.out.println("inside server sending to the client");
        System.out.println(chatUsers.size() + "Chatusers size");
        chatUsers.forEach((c) -> {
            try {
                if (!c.getPhoneNum().equals(senderPhoneNumber)) {
                    if (chatUsers.size() == 2) {
                        System.out.println("Sending to user inside server");
                        control.getClientInterfaceObject(c).receiveMsg(message, senderPhoneNumber);
                    }else{
                         control.getClientInterfaceObject(c).receiveGroupMsg(message, chatUsers);
                    }

                }else{
                    System.out.println("This is sender msg");
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public User addNewContact(User user, String phoneNumber) throws RemoteException {
        User friend = null;
        System.out.println("inside add new Contact = friend ->" + phoneNumber + " ,my number =" + user.getPhoneNum());
        RequestsDAO requestDAO = new RequestsDAO(user);
        FriendsDAO friendsDAO = new FriendsDAO(user);

        if (requestDAO.addFriendRequest(phoneNumber)) {
            boolean checkRequest = requestDAO.checkFriendRequestFromNumber(phoneNumber);
            //if the number doesn't request the user
            if (!checkRequest) {
                System.out.println("cannot add the contact because the contact hasn't request the user but a request by the user is made");
                clientConnection.notifyRequest(phoneNumber);
            } else {
                if (friendsDAO.addFriend(phoneNumber)) {
                    System.out.println("is added successfully");
                    friend = friendsDAO.retrieveFriend(phoneNumber);
                    requestDAO.deleteRequestToNumber(phoneNumber);
                    requestDAO.deleteRequestFromNumber(phoneNumber);
                    clientConnection.notifyAdded(friend, user);
                } else {
                    System.out.println("failed to add");
                }

            }
        }
        return friend;

    }

    @Override
    public void showFriendsStatus() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signout() throws RemoteException {
        //  Controller.removeOnlineUser(user, clientService);

    }

    @Override
    public boolean updateProfile(User user) throws RemoteException {
        return this.userDAO.updateUser(user);
    }

    @Override
    public boolean register(User user) throws RemoteException {
        return userDAO.addUser(user);

    }

    @Override
    public boolean checkUserAvailability(String phoneNumber) throws RemoteException {
        User userTest = userDAO.retrieveUser(phoneNumber);
        if (userTest == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ArrayList<String> getIncomingRequests(User user) throws RemoteException {
        return new RequestsDAO(user).retrieveIncomingRequests();
    }

    @Override
    public ArrayList<User> getFriends(User user) throws RemoteException {
        return new FriendsDAO(user).retrieveAllFriends();
    }

    @Override
    public ArrayList<User> getOnlineFriends(User user) throws RemoteException {
        return new FriendsDAO(user).retrieveOnlineFriends();
    }

    @Override
    public ArrayList<User> getOfflineFriends(User user) throws RemoteException {
        return new FriendsDAO(user).retrieveOfflineFriends();
    }

    @Override
    public void updateMode(User user) throws RemoteException {
        new UserDAO().updateMode(user);
    }

    @Override
    public void saveChatSession(ArrayList<Message> msgs, File file) throws RemoteException {
        // FileOutputStream result = null;
        try {

            JAXBContext context = JAXBContext.newInstance("mychatserver.model.chatsession");
            ObjectFactory factory = new ObjectFactory();
            ChatSessionType chatSession = factory.createChatSessionType();

            for (int counter = 0; counter < msgs.size(); counter++) {
                MsgType newMsg = factory.createMsgType();

                User sender = msgs.get(counter).getSender();
                String senderPhone = sender.getPhoneNum();
                String senderName = sender.getName();

                newMsg.setFrom(senderPhone + " : " + senderName);

                for (int recieverIndx = 0; recieverIndx < msgs.get(counter).getReceiver().size(); recieverIndx++) {

                    Message currentMsg = msgs.get(counter);
                    User receiver = currentMsg.getReceiver().get(recieverIndx);
                    String receiverPhone = receiver.getPhoneNum();
                    String receiverName = receiver.getName();

                    System.out.println(receiverName + " --> " + receiverPhone);
                    newMsg.getTo().add(receiverPhone + " : " + receiverName);
                }

                newMsg.setBody(msgs.get(counter).getMessageText());
                newMsg.setFontStyle(msgs.get(counter).getFontStyle());
                newMsg.setFontSize(msgs.get(counter).getFontSize());
                newMsg.setFontColor(msgs.get(counter).getFontColor());
                newMsg.setBold(msgs.get(counter).isBold());
                newMsg.setItalic(msgs.get(counter).isItalic());
                newMsg.setUnderlined(msgs.get(counter).isUnderlined());

//   newMsg.setTime(msgs.get(counter).getTime());
                chatSession.getMsg().add(newMsg);

            }

            JAXBElement mymsg = factory.createChatSession(chatSession);
            Marshaller marshaller = context.createMarshaller();

            marshaller.marshal(mymsg, file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

}
