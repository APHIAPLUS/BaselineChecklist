/*
 * To change this template, choose Tools | Templates
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
public class save_hf extends HttpServlet {
String district_id,hf_name,hf_id="",userid;
String mfl,level;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
       session=request.getSession();
        mfl=level="";
        
          AddTrails trails = new AddTrails();
   if(session.getAttribute("userid")!=null){
   userid=session.getAttribute("userid").toString();
        
       district_id=request.getParameter("district_id2");
       hf_name=request.getParameter("hf_name");
       level=request.getParameter("level");
       mfl=request.getParameter("mfl");
       
       hf_name=hf_name.toUpperCase();
      String checker="SELECT * FROM health_facility WHERE district_id=? && hf_name=?";
      conn.pst=conn.conn.prepareStatement(checker);
      conn.pst.setString(1, district_id);
      conn.pst.setString(2, hf_name);
      
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()==true){
       hf_id=conn.rs.getString(1);   
      }
     if(hf_id.equals("")) {
//     ADD THE DISTRICT AS A NEW ENTRY
          IdGenerator IG = new IdGenerator();
       String id=IG.current_id();
       String timestamp = IG.date_key();
         String adder="INSERT INTO health_facility (hf_id,district_id,hf_name,timestamp,mflcode,facility_level) VALUES (?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(adder);
        conn.pst.setString(1, id);
        conn.pst.setString(2, district_id);
        conn.pst.setString(3, hf_name);
        conn.pst.setString(4, timestamp);
        conn.pst.setString(5, mfl);
        conn.pst.setString(6, level);
        
        conn.pst.executeUpdate();
       trails.addTrails(userid, "added health facility with name : "+hf_name+", id : "+id+" and mfl code : "+mfl);   
     } 
   }else{
//       session time out
   }
       System.out.println("DISTRICT ID IS  :  "+district_id+"  Health Facility NAME IS  :  "+hf_name);
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
            Logger.getLogger(save_hf.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_hf.class.getName()).log(Level.SEVERE, null, ex);
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
