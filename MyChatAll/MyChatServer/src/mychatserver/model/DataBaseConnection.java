/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.OutputStream; 
import java.util.Properties; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author AmrHesham
 */

//*************************class malosh lazma we hanms7o kaman shwia ********************************
public class DataBaseConnection {

    public DataBaseConnection() {
        Properties properties=null;
        OutputStream outputStream=null;
        try {
            properties=new Properties();
            outputStream=new FileOutputStream("db.properties");
            properties.setProperty("MYSQL_DB_URL","jdbc:mysql://localhost:3306/chatdb");
            properties.setProperty("MYSQL_DB_USERNAME","conuser");
            properties.setProperty("MYSQL_DB_PASSWORD","admin123");
            properties.store(outputStream,null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection=new DataBaseConnection();
    }
    
}
