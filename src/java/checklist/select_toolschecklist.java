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
public class select_toolschecklist extends HttpServlet {
HttpSession session;
String date,hf_id,moh_staff,aphia_staff,source,district_id,hf_name,district_name="";
String year="",month="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       date=request.getParameter("date");
       hf_id=request.getParameter("hf_id");
       district_id=request.getParameter("district_id");
       year=date.split("/")[2];
       session.setAttribute("year_dater",year);
       month=date.split("/")[1];
        session.setAttribute("month_dater",month);
       String get_distname="SELECT district_name FROM district WHERE district_id='"+district_id+"'";
       conn.rs=conn.st.executeQuery(get_distname);
       while(conn.rs.next()){
        district_name=conn.rs.getString(1);   
       }
       String  get_hf="SELECT hf_name FROM health_facility WHERE hf_id='"+hf_id+"'";
   conn.rs=conn.st.executeQuery(get_hf);
   while(conn.rs.next()){
      hf_name=conn.rs.getString(1); 
       break;
   } 
       session.setAttribute("district_name", district_name);
       session.setAttribute("hf_name", hf_name);
       session.setAttribute("date", date);
       session.setAttribute("hf_id", hf_id);
       session.setAttribute("source", "all_tools");

       
       response.sendRedirect("marking_level_checker");
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
            Logger.getLogger(select_toolschecklist.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(select_toolschecklist.class.getName()).log(Level.SEVERE, null, ex);
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
