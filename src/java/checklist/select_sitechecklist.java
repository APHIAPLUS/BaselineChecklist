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
public class select_sitechecklist extends HttpServlet {
HttpSession session;
String date,hf_id,moh_staff,aphia_staff,name_aphia,name_moh,district_id,hf_name,district_name="";
String year="",month="";
String moh_cat="";
String moh [],moh2 [];
String all_moh="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
      all_moh="";
       date=hf_id=moh_staff=aphia_staff=hf_name=name_aphia=name_moh="";
       date=request.getParameter("date");
       hf_id=request.getParameter("hf_id");
       aphia_staff=request.getParameter("aphia_staff");
       year=date.split("/")[2];
       session.setAttribute("year_dater",year);
       month=date.split("/")[1];
       session.setAttribute("month_dater",month);
       moh_cat=request.getParameter("moh_cat");
       moh=request.getParameterValues("moh_staff");
       
       session.setAttribute("date",date);
       
      if(moh==null && moh_cat.equals("2")){
          if(session.getAttribute("moh_staff")!=null){
           moh=session.getAttribute("moh_staff").toString().split(",");  
          }
      }
      
       System.out.println();
   String  get_hf="SELECT hf_name FROM health_facility WHERE hf_id='"+hf_id+"'";
   conn.rs=conn.st.executeQuery(get_hf);
   while(conn.rs.next()){
      hf_name=conn.rs.getString(1); 
       break;
   }
      String  get_aphia="SELECT * FROM aphia_staff WHERE id='"+aphia_staff+"'";
   conn.rs=conn.st.executeQuery(get_aphia);
   while(conn.rs.next()){
      name_aphia=conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("lname"); 
       break;
   }
    if(moh_cat.equals("3") ){
           moh_staff="";
           name_moh="";
       }
   else{
        for(String moh_staff1:moh){
            moh_staff=moh_staff1;
            all_moh+=moh_staff+",";
    String  get_moh="SELECT * FROM moh_staff WHERE id='"+moh_staff+"'";
   conn.rs=conn.st.executeQuery(get_moh);
   while(conn.rs.next()){
      name_moh+=conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("lname")+","; 
  }
        }
        
        
    }
     district_id=request.getParameter("district_id");
       
       String get_distname="SELECT district_name FROM district WHERE district_id='"+district_id+"'";
       conn.rs=conn.st.executeQuery(get_distname);
       while(conn.rs.next()){
        district_name=conn.rs.getString(1);   
       } 
       session.setAttribute("district_name", district_name);
       session.setAttribute("moh_staff_name", name_moh);
       session.setAttribute("aphia_staff_name", name_aphia);
       session.setAttribute("hf_name", hf_name);  
       
       session.setAttribute("date", date);
       
       session.setAttribute("aphia_staff", aphia_staff);
       session.setAttribute("hf_id", hf_id);
       session.setAttribute("source", "all_indicators");
       System.out.println("moh name is  :"+moh_staff);
       if(!moh_staff.equals("")){
         session.setAttribute("moh_staff", all_moh);  
       }
       
       System.out.println("moh staff id  :  "+all_moh);
       
       response.sendRedirect("marking_level_checker");
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
            Logger.getLogger(select_sitechecklist.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(select_sitechecklist.class.getName()).log(Level.SEVERE, null, ex);
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
