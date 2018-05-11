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
public class get_aphia extends HttpServlet {
HttpSession session;
ArrayList aphia = new ArrayList();
String county_id;
public String fname,mname,lname,phone,id,pos,county;
int position;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
                dbConn conn = new dbConn();
                aphia.clear();
//               county_id=session.getAttribute("county_id").toString();
                position=0;
                county="";
                
//                String get_allAPHIA="SELECT * FROM aphia_Staff WHERE county_id='"+county_id+"' ORDER BY fname";
                String get_allAPHIA="SELECT * FROM aphia_Staff JOIN county ON county.county_id=aphia_staff.county_id ORDER BY aphia_staff.fname,aphia_staff.mname,aphia_staff.lname";
                conn.rs=conn.st.executeQuery(get_allAPHIA);
                while(conn.rs.next()){
                   pos="";
                    position++;
                    fname=mname=lname=phone=id="";
                    get_aphiaBean abean = new get_aphiaBean();
                    id=conn.rs.getString("id");
                    fname=conn.rs.getString("fname");
                    mname=conn.rs.getString("mname");
                    lname=conn.rs.getString("lname");
                    phone=conn.rs.getString("phone");
                    county=conn.rs.getString("county_name");
                    pos=""+position;
                   abean.setId(id);
                   abean.setCounty(county);
                   abean.setPos(pos);
                   abean.setFname(fname); 
                   abean.setMname(mname);
                   abean.setLname(lname);
                   abean.setPhone(phone);
                aphia.add(abean);    
                }
               
               session.setAttribute("aphia", aphia); 
               response.sendRedirect("edit_APHIAStaff.jsp");
        
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
            Logger.getLogger(get_aphia.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(get_aphia.class.getName()).log(Level.SEVERE, null, ex);
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
