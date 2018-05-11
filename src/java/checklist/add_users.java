/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;
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
public class add_users extends HttpServlet {
HttpSession session;

String f_name,m_name,s_name,phoneno,username,password, userid,level;
boolean statuz=false;
MessageDigest m;
String computername="";
int found;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

found=0;
              session=request.getSession();
             userid=request.getParameter("userid");
            f_name=request.getParameter("f_name");
            m_name=request.getParameter("m_name");
            s_name=request.getParameter("s_name");
            level=request.getParameter("level");
            m_name="";
          if(request.getParameter("phoneno")!=null){  phoneno=request.getParameter("phoneno");}
          else{
          phoneno="none";
          } 
            username=request.getParameter("username");
            password=request.getParameter("pass");
           //____________________COMPUTER NAME____________________________________
 computername=InetAddress.getLocalHost().getHostName();         

            //encrypt password

                            m = MessageDigest.getInstance("MD5");
                            m.update(password.getBytes(), 0, password.length());
                            String pw = new BigInteger(1, m.digest()).toString(16);
          
                            dbConn conn= new dbConn();                
                         //save details to clerk table
                           IdGenerator ig = new IdGenerator(); 
                            
                            String save="insert into users(userid,fname,mname,lname,phone,username,password,level,timestamp,computer_name) "
                                    + "values (?,?,?,?,?,?,?,?,?,?)";
                        conn.prest=conn.conn.prepareStatement(save);
                        conn.prest.setString(1, userid);
                        conn.prest.setString(2, f_name);  
                        conn.prest.setString(3, m_name);  
                        conn.prest.setString(4, s_name);  
                        conn.prest.setString(5, phoneno);  
                        conn.prest.setString(6, username);  
                        conn.prest.setString(7, pw);  
                        conn.prest.setString(8, level);  
                        conn.prest.setString(9, ig.date_key());  
                        conn.prest.setString(10, computername);                        
                            
                          
                       String checker="select count(userid) from users WHERE userid=? || username=?";
                       conn.pst=conn.conn.prepareStatement(checker);
                       conn.pst.setString(1, userid);
                       conn.pst.setString(2, username);
                       System.out.println("the user query is  :  "+checker);
                       conn.rs=conn.pst.executeQuery();
          
           //check if username is already used 
            if(conn.rs.next()==true){found=conn.rs.getInt(1); }
             if(found==0)  {   
           conn.prest.executeUpdate();   
                               
            if(level.equals("0")) {               
            session.setAttribute("clerk_added", "<font color=\"green\" >Administrator added succesfully</font>");
            }
            if(level.equals("3")) {               
            session.setAttribute("clerk_added", "<font color=\"green\">M&E Officer added succesfully</font>");
            }
            if(level.equals("2")){
             session.setAttribute("clerk_added", "<font color=\"green\">User added succesfully</font>");    
            }
            if(level.equals("5")) {               
            session.setAttribute("clerk_added", "<font color=\"green\">Guest added succesfully</font>");
            }
             }
            
            else{
            session.setAttribute("clerk_added", "<b><font color=\"red\">Sorry, That username is already used.Use a different one</font></b>");
            
            
            }
            if(conn.pst!=null){conn.pst.close();}
            if(conn.prest!=null){conn.prest.close();}
            if(conn.rs!=null){conn.rs.close();}
          response.sendRedirect("add_users.jsp");              
                            
        } catch (SQLException ex) {
            Logger.getLogger(add_users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(add_users.class.getName()).log(Level.SEVERE, null, ex);
        }


}

public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
