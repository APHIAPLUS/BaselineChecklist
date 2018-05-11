/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.AddTrails;
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
public class save_aphia extends HttpServlet {
String fname,mname,lname,phone,county,found,userid;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         session=request.getSession();
        dbConn conn = new dbConn();
         if(session.getAttribute("userid")!=null){
   userid=session.getAttribute("userid").toString();
   AddTrails trails = new AddTrails();
   
county=request.getParameter("county_id4");
fname=request.getParameter("fname2").toUpperCase();
mname=request.getParameter("mname2").toUpperCase();
lname=request.getParameter("lname2").toUpperCase();
phone=request.getParameter("phone2").toUpperCase();
System.out.println("here");
found="";
//CHECK IF THE USER HAS ALREADY BEEN REGISTERED.....................................
String checker="SELECT * FROM aphia_staff WHERE (phone='"+phone+"' && phone!='') || (county_id='"+county+"' && mname='"+mname+"' && lname='"+lname+"')";
conn.rs=conn.st.executeQuery(checker);
if(conn.rs.next()==true){
 found=conn.rs.getString(1);  
}
//IF NOT FOUND THEN INSEERT AS NEW ENTRIES..................................
if(found.equals("")){
 IdGenerator IG = new IdGenerator();
       String id=IG.sec+""+IG.micro;
         String timestamp=IG.date_key();
String inserter="INSERT INTO aphia_staff(id,fname,mname,lname,phone,county_id,timestamp) VALUES('"+id+"','"+fname+"','"+mname+"','"+lname+"','"+phone+"','"+county+"','"+timestamp+"')";
conn.st.executeUpdate(inserter);
     trails.addTrails(userid, "added aphia staff with name : "+fname+" "+mname+" "+lname+" in county with id : "+county+", aphia staff id : "+id);   
   }
    }
         else{
//             session timeout here 
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
            Logger.getLogger(save_aphia.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_aphia.class.getName()).log(Level.SEVERE, null, ex);
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
