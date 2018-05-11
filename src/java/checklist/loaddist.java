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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel E
 */
public class loaddist extends HttpServlet {

  HttpSession session;  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            String facilid="";
            
            String data="<option value=''>Choose District.</option>";
            String currentdist="";
            
            facilid=request.getParameter("facilid");
            
            String getdistfromfacil="select district_id from health_facility where hf_id='"+facilid+"'";
               dbConn conn= new dbConn();
            conn.rs1=conn.st1.executeQuery(getdistfromfacil);
            while(conn.rs1.next()){
            
             currentdist=conn.rs1.getString("district_id");
            session.setAttribute("dist_id", currentdist);
            }
            
            String getdist="select * from district";
            
         
            conn.rs=conn.st.executeQuery(getdist);
            
            while(conn.rs.next()){
            
                String isselected="";
                
                if(conn.rs.getString("district_id").equals(currentdist)){ isselected="selected='true'";
               // System.out.println("  SAME DIST "+currentdist);
                }
                
                data+="<option "+isselected+"  value='"+conn.rs.getString("district_id")+"'>"+conn.rs.getString("district_name")+"</option>";
            
            
            }
            
            
            
            PrintWriter out = response.getWriter();
            try {
                
                out.println(data);
                //System.out.println(""+data);
                
            } finally {
                out.close();
            }
        }   catch (SQLException ex) {
            Logger.getLogger(loaddist.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
