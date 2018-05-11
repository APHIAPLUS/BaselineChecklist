/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class save_ur_details extends HttpServlet {
String fname,mname,lname,username,phone,password,userid,fullname;
int counter;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
        dbConn conn = new dbConn();
         counter=0;
         session=request.getSession();
         
        userid=request.getParameter("userid");
        fname=request.getParameter("fname");
        mname=request.getParameter("mname");
        lname=request.getParameter("lname");
        username=request.getParameter("username");
        phone=request.getParameter("phone");
        password=request.getParameter("pass");
        
        IdGenerator ig = new IdGenerator();
        
        if(userid!=null && !userid.equals("")){
            
             //encrypt password
            
      MessageDigest    m = MessageDigest.getInstance("MD5");
                m.update(password.getBytes(), 0, password.length());
                String pw = new BigInteger(1, m.digest()).toString(16);
            
           String check_userid_existence="SELECT COUNT(userid) FROM users WHERE userid='"+userid+"'";
           conn.rs=conn.st.executeQuery(check_userid_existence);
           if(conn.rs.next()==true){
               counter=conn.rs.getInt(1);
              }
            
            if(counter>0){
               String updator="UPDATE users SET fname='"+fname+"',lname='"+lname+"',phone='"+phone+"',username='"+username+"',password='"+pw+"',timestamp='"+ig.toDay()+"' WHERE userid='"+userid+"'";
         int update= conn.st.executeUpdate(updator);
         
      
         
          String get_fullnames="SELECT * FROM users WHERE userid='"+userid+"'";
         conn.rs1=conn.st1.executeQuery(get_fullnames);
         if(conn.rs1.next()==true){
           fullname=conn.rs1.getString("fname")+" "+conn.rs1.getString("lname");                         
         }
          conn.rs1.close();
 conn.st1.close();
         if(update>0){
         session.setAttribute("det_update", "<font color=\"green\">Your details have been updated successfully</font>"); 
          session.setAttribute("fullname", fullname); 
         }
            }
        }
        if(conn.rs!=null){ conn.rs.close();}
        if(conn.st!=null){ conn.st.close();}
 System.out.println(session.getAttribute("det_update"));
        response.sendRedirect("edit_ur_details.jsp");
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
