/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
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
public class login extends HttpServlet {
 String uname,pass,error_login,nextPage,current_time;
    String computername;
    MessageDigest m;
    String full_name;
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
String yr,mnth,dater,hr,mn,sc,action="";
yr=Integer.toString(year);
mnth=Integer.toString(month);
dater=Integer.toString(date);
hr=Integer.toString(hour);
mn=Integer.toString(min);
sc=Integer.toString(sec);
session= request.getSession();

AddTrails trails = new AddTrails();


//____________________COMPUTER NAME____________________________________
computername=InetAddress.getLocalHost().getHostName();
System.out.println("Computer name "+computername);
session.setAttribute("computer_name", computername);

current_time=yr+"-"+mnth+"-"+dater+"-"+hr+":"+mn+":"+sc;
            //get username and password
            
           uname=request.getParameter("uname");
            
           pass=request.getParameter("pass");
            m = MessageDigest.getInstance("MD5");
                m.update(pass.getBytes(), 0, pass.length());
                String pw = new BigInteger(1, m.digest()).toString(16);
         
                dbConn conn = new dbConn();  
                String select1 = "select * from users";
          
                   conn.rs = conn.st.executeQuery(select1);
                   
              int user_found=0;     
                    while (conn.rs.next()) {
                        user_found=1;
                        full_name=conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("lname");
                        session.setAttribute("level", conn.rs.getString("level"));
                        session.setAttribute("fullname", full_name); 
                        session.setAttribute("userid", conn.rs.getString("userid"));
                        session.setAttribute("username", conn.rs.getString("username"));
                        
  if (conn.rs.getString("username").equals(uname) && conn.rs.getString("password").equals(pw)) {
          
                    error_login = "";
                    if (conn.rs.getString("level").equals("1") || conn.rs.getString("level").equals("3")) {
                   System.out.println("Admin or ME OFFICER");
                        String ip=InetAddress.getLocalHost().getHostAddress();   
//              String inserter="insert into audit set host_comp='"+computername+" "+ip+"' , action='Logged in ',time='"+current_time+"',actor_id='"+conn.rs.getString("userid")+"'";                         
//                conn.st3.executeUpdate(inserter);                
                        nextPage = "entry_type.jsp"; 
                       
//                        AUDIT TRAILS IS SAVED HERE
                       trails.addTrails(conn.rs.getString("userid"), "Logged in as administrator");
                       break;  
                    }
             
                       else if(conn.rs.getString("level").equals("2")){ 
                           //                        AUDIT TRAILS IS SAVED HERE
                       trails.addTrails(conn.rs.getString("userid"), "Logged in as a user");
                nextPage = "user_main.jsp";
                             break;} 
 
//         ^^^^^^^^^^^^^^^^ IF  USER EXIST  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^              
                       else if(conn.rs.getString("level").equals("5"))
                       {
                        nextPage = "user_main.jsp";
               break;}                                            
                       else { nextPage = "index.jsp";
                     
                       error_login="<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";
                       }
     }

  
  else {
                  nextPage = "index.jsp";
                     session.setAttribute("error_login", "<b><font color=\"red\">wrong username and or password</font></b>"); 
  
  }  }
                    if(user_found==0){
                          nextPage = "index.jsp";
                        error_login="<b><font color=\"red\">wrong username and or password</font></b>"; 
                    }

if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
if(conn.rs1!=null){
            conn.rs1.close();
            }
if(conn.st1!=null){
            conn.st1.close();
            }
if(conn.st3!=null){
            conn.st3.close();
            }
                    session.setAttribute("error_login", error_login);
                   response.sendRedirect(nextPage); 
                    
              
                           
                        
                         
                           
                 
                          
        } finally {            
            out.close();
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
