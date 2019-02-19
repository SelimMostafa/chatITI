/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;

import mychatserver.model.MyChatServiceImpl;
import mychatserver.model.DataSourceFactory;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AmrHesham
 */
public class MyChatServer  {
    
    public MysqlDataSource mysqlDataSource=DataSourceFactory.getMySQLDataSource();
    
    public MyChatServer() {
        try {
            Registry registry=LocateRegistry.createRegistry(5000);
            MyChatServiceImpl chatService= new MyChatServiceImpl();
            registry.rebind("chatService", chatService);
            System.out.println("Server is running");
        } catch (RemoteException ex) {
            Logger.getLogger(MyChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    
    
}
