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
public class save_district extends HttpServlet {
String county_id,district_name,district_id,userid;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
   dbConn conn = new dbConn();
   session=request.getSession();
   district_id="";
   AddTrails trails = new AddTrails();
   if(session.getAttribute("userid")!=null){
              userid=session.getAttribute("userid").toString();
   county_id=request.getParameter("county");
   district_name=request.getParameter("district");
   district_name=district_name.toUpperCase();
   System.out.println("THE COUNTY ID IS  :  "+county_id+"   and the district name  :  "+district_name );
        
     String check_existence="SELECT * FROM district WHERE county_id='"+county_id+"' && district_name='"+district_name+"'";
     conn.rs=conn.st.executeQuery(check_existence);
     if(conn.rs.next()==true){
      district_id=conn.rs.getString(1);   
     }
    if(district_id.equals("")){
//    INSERT AS NEW ENTRIES TO THE DATABASE
        IdGenerator IG = new IdGenerator();
        int id=IG.micro;
        String inserter="INSERT INTO district (district_id,county_id,district_name) VALUES('"+id+"','"+county_id+"','"+district_name+"')";
        conn.st.executeUpdate(inserter);
   trails.addTrails(userid, "added district with name : "+district_name+" and id : "+id);
    }
    else{
        System.out.println("district already exist");
    }
   }
   else{
       System.out.println("sessions have timed out");
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
            Logger.getLogger(save_district.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_district.class.getName()).log(Level.SEVERE, null, ex);
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
