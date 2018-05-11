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

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_facilities extends HttpServlet {
String facilities, district_id;
String distQuery;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
             dbConn conn = new dbConn();
           
           district_id=request.getParameter("district_id");
           if(district_id.equals("")){
            distQuery=""   ;
           }
           else{
            distQuery="WHERE district_id='"+district_id+"'"   ;    
           }
              facilities="<option value=\"\">Choose Health Facility</option>";
         String get_facilities="SELECT * FROM health_facility "+distQuery+" ORDER BY hf_name";
         System.out.println("query facility : "+get_facilities);
         conn.rs=conn.st.executeQuery(get_facilities);
         while(conn.rs.next()){
//            facilities+="<option value=\""+conn.rs.getString("hf_id") +"\">"+conn.rs.getString("hf_name") +" ("+conn.rs.getString("facility_level") +")</option>";
         
               facilities+="<option value=\""+conn.rs.getString("hf_id") +"\">"+conn.rs.getString("hf_name") +"</option>";
         
         
         }
            
          if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet load_districts</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet load_districts at " +facilities+ "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(load_facilities.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_facilities.class.getName()).log(Level.SEVERE, null, ex);
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
