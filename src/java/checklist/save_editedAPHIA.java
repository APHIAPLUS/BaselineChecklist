/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class save_editedAPHIA extends HttpServlet {
HttpSession session;
String id,value,columnName,columnId,columnPosition,rowId;
int found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        IdGenerator IG = new IdGenerator();
          String timestamp=IG.date_key();
       id = request.getParameter("id");// values passed from the ajax
       value = request.getParameter("value").toUpperCase();// values passed from the ajax
       columnName = request.getParameter("columnName");// values passed from the ajax
       columnId = request.getParameter("columnId");// values passed from the ajax
       columnPosition = request.getParameter("columnPosition");// values passed from the ajax
       rowId = request.getParameter("rowId"); // values passed from the ajax 
//     CHECK THE COLUN TO UPDATE...................................   
       
   System.out.println("id  :  "+id+"  val  :  "+value+"  clname :  "+columnName+" colid  :  "+columnId+" columnpos  :  "+columnPosition+" rowd  : "+rowId);   
//    UPDATE TH DATA TO THE DATABASE....................
try{
       if(columnName.equalsIgnoreCase("First Name")){
          String update="UPDATE aphia_staff SET fname=? WHERE id=?"; 
         conn.prest=conn.conn.prepareStatement(update);
         conn.prest.setString(1, value);
         conn.prest.setString(2, id);  
         conn.prest.executeUpdate();  
       }
       
      else if(columnName.equalsIgnoreCase("Middle Name")){
          String update="UPDATE aphia_staff SET mname=? WHERE id=?"; 
         conn.prest=conn.conn.prepareStatement(update);
         conn.prest.setString(1, value);
         conn.prest.setString(2, id);  
         conn.prest.executeUpdate();  
       }
      else if(columnName.equalsIgnoreCase("Last Name")){
          String update="UPDATE aphia_staff SET lname=? WHERE id=?"; 
         conn.prest=conn.conn.prepareStatement(update);
         conn.prest.setString(1, value);
         conn.prest.setString(2, id);  
         conn.prest.executeUpdate();  
       }
       
      else if(columnName.equalsIgnoreCase("Phone Number")){
          String phone=value;
           if((phone.startsWith("070") ||phone.startsWith("071") ||phone.startsWith("072")||phone.startsWith("073")||phone.startsWith("075")||phone.startsWith("077")||phone.startsWith("078"))&&(phone.length()==10))
       {
          String update="UPDATE aphia_staff SET phone=? WHERE id=?"; 
         conn.prest=conn.conn.prepareStatement(update);
         conn.prest.setString(1, value);
         conn.prest.setString(2, id);  
         conn.prest.executeUpdate();
         System.out.println("Phone update Successfully.");
    }
           else{
             System.out.println("Wrong Phone Number.");    
           }
       }
      else{
          System.out.println("Wrong credentials Passed");
      }
       
}
catch (SQLException s){
  System.out.println("SQL statement is not executed!"+s);
  }

       
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(save_editedAPHIA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(save_editedAPHIA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
