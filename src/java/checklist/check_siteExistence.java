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
public class check_siteExistence extends HttpServlet {
HttpSession session;
String month,year,message,source,hf_id;
String found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
        dbConn conn = new dbConn();    
      hf_id=session.getAttribute("hf_id").toString();
        month=request.getParameter("month");
        
        int mnth=Integer.parseInt(month);
        if(mnth<10){
            month="0"+mnth;
        }
        year=request.getParameter("year");
        source=request.getParameter("source");
        found="";
      System.out.println(" hf : id  "+hf_id+" year  :  "+year+"  month  :  "+month+"  source  :  "+source);  
        
       if(source.equals("site")){
  String checker="SELECT * FROM marked_checklist WHERE hf_id='"+hf_id+"' && year='"+year+"' && month='"+month+"' limit 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
   found=conn.rs.getString(1);   
  }
  if(!found.equals("")) {
      message="<font color=\"red\">Data Exist.</font>";
  }        
  else{
     message="<font color=\"green\">Ok.</font>";  
  }        
    
    
       }  
    found="";   
       
          if(source.equals("tools")){
  String checker="SELECT * FROM me_toolsvalue WHERE hf_id='"+hf_id+"' && year='"+year+"' && month='"+month+"' limit 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
    found=conn.rs.getString(1);  
  }
  if(!found.equals("")) {
      message="<font color=\"red\">Data Exist.</font>";
  }        
  else{
     message="<font color=\"green\">Ok.</font>";  
  }        
           
       }
          
          if(month.equals("06") || month.equals("03") || month.equals("09") || month.equals("12")){
     session.setAttribute("selected_month", "data");   
    }
          else{
              session.removeAttribute("selected_month");
             }
          
      System.out.println("message is  :  "+session.getAttribute("selected_month"));      
            
            /* TODO output your page here. You may use following sample code. */
            
            out.println(message);
           
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
            Logger.getLogger(check_siteExistence.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(check_siteExistence.class.getName()).log(Level.SEVERE, null, ex);
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
