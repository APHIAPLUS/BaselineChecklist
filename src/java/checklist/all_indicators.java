/*
 * To change this template, choose Tools | Templates
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
public class all_indicators extends HttpServlet {

HttpSession session;
String  indicator_name,indicator_id,program_area,source_doc,recounted_data,reported_711A,reported_731,reported_DHIS,section;
    ArrayList indicators= new ArrayList();
    int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        indicators.clear();
        session=request.getSession();
        dbConn conn = new dbConn();
        position=0;
        
//       SELECT ALL INDICATORS AND ADD THEM AS OBJECTS TO THE ARRAYLIST.
        String all_selector="SELECT * FROM indicators";
        conn.rs=conn.st.executeQuery(all_selector);
        while(conn.rs.next()){
            position++;
//        CREATE AN OBJECT OF THE BEAN CLASS    
          indicator_bean ib = new indicator_bean();
          
          indicator_id=conn.rs.getString("id");
          program_area=conn.rs.getString("program_area");
          source_doc=conn.rs.getString("source_doc");
          indicator_name=conn.rs.getString("indicator");
          recounted_data=conn.rs.getString("recounted_data");
          reported_711A=conn.rs.getString("reported_711A");
          reported_731=conn.rs.getString("reported_731");
          reported_DHIS=conn.rs.getString("reported_DHIS");
          section=conn.rs.getString("section");
          
          
//          ADD THESE VALUES TO THE BEAN OBJECT.
          ib.setIndicator_id(indicator_id);
          ib.setIndicator_name(indicator_name);
          ib.setPosition(position);
          ib.setProgram_area(program_area);
          ib.setRecounted_data(recounted_data);
          ib.setReported_711A(reported_711A);
          ib.setReported_731(reported_731);
          ib.setReported_DHIS(reported_DHIS);
          ib.setSource_doc(source_doc);
          ib.setSection(section);
          
//          ADD TO THE ARRAYLIST OBJECT.
          indicators.add(ib);
       }
        
         if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
    session.setAttribute("indicators", indicators);
    response.sendRedirect("site_checklist.jsp");
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
            Logger.getLogger(all_indicators.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(all_indicators.class.getName()).log(Level.SEVERE, null, ex);
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
