/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cleaners;

import checklist.getFacilities;
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
public class UpdateFacilityLevel extends HttpServlet {
HttpSession session;
String name,keyword,level,facilityname,facilityid;
int pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
      
       pos=0;
       String getAllFacilities="SELECT hf_id,hf_name FROM health_facility";
       conn.rs=conn.st.executeQuery(getAllFacilities);
       while(conn.rs.next()){
           facilityname=facilityid=level="";
           facilityname=conn.rs.getString(2);
           facilityid=conn.rs.getString(1);
           
           if(facilityname.contains("Dispensary")){
               level="Level 2";
           }
           else if(facilityname.contains("Centre")){
               level="Level 3";
           }
           else if(facilityname.contains("Memorial")){
               level="Level 3";
           }
           else if(facilityname.contains("Provincial")){
               level="Level 5";
           }
           else if(facilityname.contains("District")){
               level="Level 4";
           }
           if(!level.equals("")){
           String updator="UPDATE health_facility SET facility_level='"+level+"' WHERE hf_id='"+facilityid+"'";
           conn.st1.executeUpdate(updator);}
        pos++;
        System.out.println("POSITION   :     "+pos);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        Logger.getLogger(UpdateFacilityLevel.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        Logger.getLogger(UpdateFacilityLevel.class.getName()).log(Level.SEVERE, null, ex);
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
