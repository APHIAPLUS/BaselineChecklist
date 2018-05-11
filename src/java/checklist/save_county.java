/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class save_county extends HttpServlet {
HttpSession session;
String countyName,id,status,found,userid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
          
          if(session.getAttribute("userid")!=null){
              userid=session.getAttribute("userid").toString();
          
            AddTrails trails = new AddTrails();
          countyName=request.getParameter("cname").toUpperCase();
          found="";
          String checker="SELECT county_id FROM county WHERE county_name=?";
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, countyName);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){
              found=conn.rs.getString(1);
          }
          if(found.length()==0){
              IdGenerator IG = new IdGenerator();
              id=IG.current_id();
           String inserter="INSERT INTO county (county_id,county_name) VALUES(?,?)";
           conn.pst=conn.conn.prepareStatement(inserter);
           conn.pst.setString(1, id);
           conn.pst.setString(2, countyName);   
           
           conn.pst.executeUpdate();
           
         System.out.println("added");
         
         trails.addTrails(userid, "added county with name : "+countyName+" and id : "+id);
          }
          else{
              System.out.println("county already exist");
          }
          }
          
            out.println(status);
        } finally {
            out.close();
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
        Logger.getLogger(save_county.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(save_county.class.getName()).log(Level.SEVERE, null, ex);
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
