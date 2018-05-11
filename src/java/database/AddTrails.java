/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import checklist.IdGenerator;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 *
 * @author Geofrey Nyabuto
 */
public class AddTrails {
 int status=0;
 String id,compname;
 public int addTrails(String userid,String task) throws SQLException, UnknownHostException{
   compname=InetAddress.getLocalHost().getHostName();
    dbConn conn = new dbConn();
     IdGenerator IG = new IdGenerator();
     id=IG.current_id();
    String adder="INSERT INTO audit(id,userid,task,hostcomp) VALUES(?,?,?,?)";
    conn.pst=conn.conn.prepareStatement(adder);
    conn.pst.setString(1, id);
    conn.pst.setString(2, userid);
    conn.pst.setString(3, task);
    conn.pst.setString(4, compname);
    
   status=conn.pst.executeUpdate();
     
     
     return status;
 }
}
