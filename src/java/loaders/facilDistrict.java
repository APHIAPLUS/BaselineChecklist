/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loaders;

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
 * @author Nyabuto Geofrey
 */
public class facilDistrict extends HttpServlet {
HttpSession session;
String facilName,facilID,districtName,data,sessID,district_id,whereQuery;
int counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         session=request.getSession();
         dbConn conn = new dbConn();
        
         sessID=district_id=whereQuery="";
         district_id=request.getParameter("district");
//         System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<entere data for dist facili");
         if(district_id.equals("")){
             whereQuery="";
         }
         else{
           whereQuery="WHERE health_facility.district_id='"+district_id+"'";
           session.setAttribute("selectedDistrict", district_id);
         }
         
         if(session.getAttribute("hf_id")!=null){
         sessID=session.getAttribute("hf_id").toString();
         }
         System.out.println("current facility : "+sessID);
         data="<option value=\"\">Choose facility</option>";
         String getAll="SELECT health_facility.hf_id,health_facility.hf_name,district.district_name "
                 + "FROM health_facility JOIN district ON health_facility.district_id=district.district_id "
                 + " "+whereQuery+" "
                 + "ORDER BY health_facility.hf_name";
         conn.rs=conn.st.executeQuery(getAll);
         while(conn.rs.next()){
          facilID=conn.rs.getString(1);
          facilName=conn.rs.getString(2);
          districtName=conn.rs.getString(3);   
           
          if(sessID.equals(facilID)){
              data+="<option value=\""+facilID+"\" selected>"+facilName+"</option>";
          }
          else{
      data+="<option value=\""+facilID+"\">"+facilName+"</option>";         
          }
         }
//            System.out.println("data is : "+data);
//        System.out.println("data for dist facili>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
         
            out.println(data);
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
        Logger.getLogger(facilDistrict.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(facilDistrict.class.getName()).log(Level.SEVERE, null, ex);
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
