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
public class save_moh extends HttpServlet {
String fname,mname,lname,phone,county,found;
HttpSession session;
String before_id,after_id,userid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        before_id=after_id="";
        if(session.getAttribute("userid")!=null){
   userid=session.getAttribute("userid").toString();
   AddTrails trails = new AddTrails();
       if(session.getAttribute("moh_staff")!=null){
           before_id=session.getAttribute("moh_staff").toString();
       } 
county=request.getParameter("county_id3").toUpperCase();
fname=request.getParameter("fname").toUpperCase();
mname=request.getParameter("mname").toUpperCase();
lname=request.getParameter("lname").toUpperCase();
phone="";
found="";
System.out.println("the name is   :   "+fname);
System.out.println("found members  :   "+found);
//CHECK IF THE USER HAS ALREADY BEEN REGISTERED.....................................
String checker="SELECT * FROM moh_staff WHERE county_id='"+county+"' && fname='"+fname+"' && lname='"+lname+"'";
conn.rs=conn.st.executeQuery(checker);
if(conn.rs.next()==true){
 found=conn.rs.getString(1);  
}
//IF NOT FOUND THEN INSEERT AS NEW ENTRIES..................................
System.out.println("found members  :   "+found);
if(found.equals("")){
 IdGenerator IG = new IdGenerator();
       found=IG.sec+""+IG.micro;
       after_id=before_id+","+found;
      String timestamp=IG.date_key();
String inserter="INSERT INTO moh_staff(id,fname,mname,lname,phone,county_id,timestamp) VALUES('"+found+"','"+fname+"','"+mname+"','"+lname+"','"+phone+"','"+county+"','"+timestamp+"')";
conn.st.executeUpdate(inserter);
 trails.addTrails(userid, "added moh staff with name : "+fname+" "+mname+" "+lname+" in county with id : "+county+", moh staff id : "+found);   
    
}
else{
    after_id=before_id;
}
 session.setAttribute("moh_staff", after_id);
 
 System.out.println("The SESSION HAS  :   "+session.getAttribute("moh_staff").toString());
    }
        else{
//            session timeout
            
            
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
            Logger.getLogger(save_moh.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_moh.class.getName()).log(Level.SEVERE, null, ex);
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
