/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class LoadFacilities extends HttpServlet {
HttpSession session;
String district_name,county_name,hf_name,level,mfl_code,hf_id;
ArrayList Facil = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      session=request.getSession();
      dbConn conn = new dbConn();
     Facil.clear();
      
     String getAll="SELECT county.county_name,district.district_name,health_facility.hf_id,"
             + "health_facility.hf_name,health_facility.mflcode,health_facility.facility_level"
             + " FROM health_facility JOIN district ON health_facility.district_id=district.district_id JOIN county "
             + "ON district.county_id=county.county_id" ;
     conn.rs=conn.st.executeQuery(getAll);
     while(conn.rs.next()){
     getFacilities getFa = new getFacilities();
         
     county_name=conn.rs.getString(1);
     district_name=conn.rs.getString(2);    
     hf_id=conn.rs.getString(3);    
     hf_name=conn.rs.getString(4);    
     mfl_code=conn.rs.getString(5);
     level=conn.rs.getString(6);
     
     
     getFa.setCounty_name(county_name);
     getFa.setDistrict_name(district_name);
     getFa.setHf_id(hf_id);
     getFa.setLevel(level);
     getFa.setMfl_code(mfl_code);
     getFa.setHf_name(hf_name);
      
     
     Facil.add(getFa);
     }
      session.setAttribute("Facil", Facil);
      response.sendRedirect("editFacilities.jsp");
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
        Logger.getLogger(LoadFacilities.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadFacilities.class.getName()).log(Level.SEVERE, null, ex);
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
