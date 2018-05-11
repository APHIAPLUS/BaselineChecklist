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
public class marking_level_checker extends HttpServlet {
HttpSession session;
String source,nextpage,year,month,date,hf_id;
int found=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      session=request.getSession();
      dbConn conn = new dbConn();
      found=0;
    if(session.getAttribute("source")!=null)  {
          if(session.getAttribute("date")!=null){
       String dates [] = session.getAttribute("date").toString().split("/");
       year=dates[2];
       month=dates[1];
       date=session.getAttribute("date").toString();
         hf_id=session.getAttribute("hf_id").toString();
          }
     source=session.getAttribute("source").toString();  
     if(source.equals("all_tools")){
//      String checker="SELECT COUNT(id) FROM me_toolsvalue WHERE year='"+year+"' && month='"+month+"' && hf_id='"+hf_id+"'"; 
//      conn.rs=conn.st.executeQuery(checker);
//      if(conn.rs.next()==true){
//         found=conn.rs.getInt(1); 
//      }
//     if(found>0){
////         DATA ALREADY ENTERED EDIT VALUES..................................
//         nextpage="edit_toolsvalue";
//     }
//     else{
//         DATA MISSING,, VALUES..................................
         nextpage="all_tools";
//     }
     }
     
          if(source.equals("all_indicators")){
//      String checker="SELECT COUNT(id) FROM pmtct_site WHERE year='"+year+"' && month='"+month+"' && hf_id='"+hf_id+"'"; 
//      conn.rs=conn.st.executeQuery(checker);
//      if(conn.rs.next()==true){
//         found=conn.rs.getInt(1); 
//      }
//     if(found>0){
////         DATA ALREADY ENTERED EDIT VALUES..................................
//         nextpage="edit_sitevalue";
//     }
//     else{
//         DATA MISSING,, VALUES..................................
         nextpage="all_indicators";
//     }
          }
     
     
     }
    else{
        nextpage="entry_type.jsp";
    }
    
    response.sendRedirect(nextpage);
    
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
            Logger.getLogger(marking_level_checker.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(marking_level_checker.class.getName()).log(Level.SEVERE, null, ex);
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
