/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
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
public class all_tools extends HttpServlet {
HttpSession session;
ArrayList me_tools = new ArrayList();
String tool_name,tool_id,tool_sp,section;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        me_tools.clear();
        dbConn conn = new dbConn();
        System.out.println("worked");
      String get_tools="SELECT * FROM tools";
      conn.rs=conn.st.executeQuery(get_tools);
      while(conn.rs.next()){
         tools_bean tb = new tools_bean();
         tool_id=conn.rs.getString("id");
         tool_name=conn.rs.getString("tool_name");
         tool_sp=conn.rs.getString("specification");
         section=conn.rs.getString("section");
        
         
//         insert to the tools bean.
         
         tb.setSection(section);
         tb.setTool_name(tool_name);
         tb.setTool_id(tool_id);
         tb.setTool_sp(tool_sp);
         
         me_tools.add(tb);
      }
      
       if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
      session.setAttribute("tools", me_tools);
      response.sendRedirect("tools_checklist.jsp");
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
            Logger.getLogger(all_tools.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(all_tools.class.getName()).log(Level.SEVERE, null, ex);
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
