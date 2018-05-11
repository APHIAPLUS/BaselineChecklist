/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geofrey Nyabuto
 */

public class dbConn2{
public ResultSet rst3,rst4,rst5,rst1,rst,rst6,rts7;
    public Statement stt, stt1, stt2, stt3, stt4, stt5, stt6, stt7;
   PreparedStatement pst;
   public String passw="";
   public dbConn2 (){
    
    try{
        dbConn conn = new dbConn(); 
            if (conn.dbsetup[3] != null) {
                passw = conn.dbsetup[3];
}
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn2 = DriverManager.getConnection("jdbc:mysql://"+conn.dbsetup[0]+"/checklist_temp",conn.dbsetup[2], ""+passw+"");
                 stt = conn2.createStatement();
                stt1 = conn2.createStatement();
                stt2 = conn2.createStatement();
                stt3 = conn2.createStatement();
                stt4 = conn2.createStatement();
                stt5 = conn2.createStatement();
       
    }
    catch(Exception e){
      Logger.getLogger(dbConn2.class.getName()).log(Level.SEVERE, null, e);  
    }
    
}
}