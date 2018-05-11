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
public class edit_sitevalue extends HttpServlet {
HttpSession session;
String year,month,hf_id,date,nextpage;
String id,indicator_id,indicator_name,recounted_data,reported_711A,reported_731,reported_DHIS,section;
ArrayList editSite = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       editSite.clear();
       
       nextpage="";
       year=request.getParameter("year");
           if(!year.equals("")){
//       String dates [] = session.getAttribute("date").toString().split("/");
//       year=request.getParameter("year");
       month=request.getParameter("month");
//       date=session.getAttribute("date").toString();
         hf_id=request.getParameter("hf_id");
         session.setAttribute("hf_id", hf_id);
         session.setAttribute("month", month);
         session.setAttribute("year", year);
         
//      LOAD HTC DATA TO A BEAN................
         String get_htcSite="SELECT htc_site.id,htc_site.indicator_id,indicators.indicator,htc_site.recounted_data,htc_site.reported_711A,htc_site.reported_731,htc_site.reported_DHIS,indicators.section "
                 + " FROM htc_site JOIN indicators ON htc_site.indicator_id=indicators.id WHERE htc_site.year='"+year+"' && htc_site.month='"+month+"' && htc_site.hf_id='"+hf_id+"'";
         conn.rs=conn.st.executeQuery(get_htcSite);
         while(conn.rs.next()==true){
           
             edit_siteBean esbean= new edit_siteBean();
             
             id=conn.rs.getString(1);
             indicator_id=conn.rs.getString(2);
             indicator_name=conn.rs.getString(3);
             recounted_data=conn.rs.getString(4);
             reported_711A=conn.rs.getString(5);
             reported_731=conn.rs.getString(6);
             reported_DHIS=conn.rs.getString(7);
             section=conn.rs.getString(8);
//             ADD THE VALUES TO THE BEAN.
             esbean.setId(id);
             esbean.setIndicator_id(indicator_id);
             esbean.setIndicator_name(indicator_name);
             esbean.setRecounted_data(recounted_data);
             esbean.setRe_711A(reported_711A);
             esbean.setRe_731(reported_731);
             esbean.setRe_DHIS(reported_DHIS);
             esbean.setSection(section);
            editSite.add(esbean); 
                
         }
        //      LOAD PMTCT DATA TO A BEAN................
         String get_pmtctSite="SELECT pmtct_site.id,pmtct_site.indicator_id,indicators.indicator,pmtct_site.recounted_data,pmtct_site.reported_711A,pmtct_site.reported_731,pmtct_site.reported_DHIS,indicators.section "
                 + " FROM pmtct_site JOIN indicators ON pmtct_site.indicator_id=indicators.id WHERE pmtct_site.year='"+year+"' && pmtct_site.month='"+month+"' && pmtct_site.hf_id='"+hf_id+"'";
         conn.rs=conn.st.executeQuery(get_pmtctSite);
         while(conn.rs.next()==true){
           
             edit_siteBean esbean= new edit_siteBean();
             
             id=conn.rs.getString(1);
             indicator_id=conn.rs.getString(2);
             indicator_name=conn.rs.getString(3);
             recounted_data=conn.rs.getString(4);
             reported_711A=conn.rs.getString(5);
             reported_731=conn.rs.getString(6);
             reported_DHIS=conn.rs.getString(7);
             section=conn.rs.getString(8);
//             ADD THE VALUES TO THE BEAN.
             esbean.setId(id);
             esbean.setIndicator_id(indicator_id);
             esbean.setIndicator_name(indicator_name);
             esbean.setRecounted_data(recounted_data);
             esbean.setRe_711A(reported_711A);
             esbean.setRe_731(reported_731);
             esbean.setRe_DHIS(reported_DHIS);
             esbean.setSection(section);
            editSite.add(esbean); 
                
         } 
         
                 //      LOAD CARE AND TREATMENT DATA TO A BEAN................
         String get_caretreatmentSite="SELECT caretreatment_site.id,caretreatment_site.indicator_id,indicators.indicator,caretreatment_site.recounted_data,caretreatment_site.reported_711A,caretreatment_site.reported_731,caretreatment_site.reported_DHIS,indicators.section "
                 + " FROM caretreatment_site JOIN indicators ON caretreatment_site.indicator_id=indicators.id WHERE caretreatment_site.year='"+year+"' && caretreatment_site.month='"+month+"' && caretreatment_site.hf_id='"+hf_id+"'";
         conn.rs=conn.st.executeQuery(get_caretreatmentSite);
         while(conn.rs.next()==true){
           
             edit_siteBean esbean= new edit_siteBean();
             
             id=conn.rs.getString(1);
             indicator_id=conn.rs.getString(2);
             indicator_name=conn.rs.getString(3);
             recounted_data=conn.rs.getString(4);
             reported_711A=conn.rs.getString(5);
             reported_731=conn.rs.getString(6);
             reported_DHIS=conn.rs.getString(7);
             section=conn.rs.getString(8);
//             ADD THE VALUES TO THE BEAN.
             esbean.setId(id);
             esbean.setIndicator_id(indicator_id);
             esbean.setIndicator_name(indicator_name);
             esbean.setRecounted_data(recounted_data);
             esbean.setRe_711A(reported_711A);
             esbean.setRe_731(reported_731);
             esbean.setRe_DHIS(reported_DHIS);
             esbean.setSection(section);
            editSite.add(esbean); 
                
         }
         
                          //      LOAD TB AND HIV DATA TO A BEAN................
         String get_tbSite="SELECT tb_site.id,tb_site.indicator_id,indicators.indicator,tb_site.recounted_data,tb_site.reported_711A,tb_site.reported_731,tb_site.reported_DHIS,indicators.section "
                 + " FROM tb_site JOIN indicators ON tb_site.indicator_id=indicators.id WHERE tb_site.year='"+year+"' && tb_site.month='"+month+"' && tb_site.hf_id='"+hf_id+"'";
         conn.rs=conn.st.executeQuery(get_tbSite);
         while(conn.rs.next()==true){
           
             edit_siteBean esbean= new edit_siteBean();
             
             id=conn.rs.getString(1);
             indicator_id=conn.rs.getString(2);
             indicator_name=conn.rs.getString(3);
             recounted_data=conn.rs.getString(4);
             reported_711A=conn.rs.getString(5);
             reported_731=conn.rs.getString(6);
             reported_DHIS=conn.rs.getString(7);
             section=conn.rs.getString(8);
//             ADD THE VALUES TO THE BEAN.
             esbean.setId(id);
             esbean.setIndicator_id(indicator_id);
             esbean.setIndicator_name(indicator_name);
             esbean.setRecounted_data(recounted_data);
             esbean.setRe_711A(reported_711A);
             esbean.setRe_731(reported_731);
             esbean.setRe_DHIS(reported_DHIS);
             esbean.setSection(section);
            editSite.add(esbean); 
                
         }
         
       
         String moh_staff,aphia_staff,moh_name="",hf_name="",aphia_name="",moh_array[];
           String hf_namegetter="SELECT hf_name FROM health_facility WHERE hf_id='"+hf_id+"'";
         conn.rs=conn.st.executeQuery(hf_namegetter);
         while(conn.rs.next()){
             hf_name=conn.rs.getString(1);
         }
           session.setAttribute("hf_name", hf_name);
         String get_date="SELECT * FROM marked_checklist WHERE year='"+year+"' && month='"+month+"' && hf_id='"+hf_id+"' limit 1";
         conn.rs=conn.st.executeQuery(get_date);
         while(conn.rs.next()){
             session.setAttribute("date", conn.rs.getString("date"));
             moh_staff=conn.rs.getString("moh_staff");
             aphia_staff=conn.rs.getString("aphia_staff");
             moh_array=moh_staff.split(",");
             session.setAttribute("moh_staff", moh_staff);
           session.setAttribute("aphia_staff", aphia_staff);
         for (String staffer:moh_array){
             
             String get_moh="SELECT * FROM moh_staff WHERE id='"+staffer+"'";
             conn.rs1=conn.st1.executeQuery(get_moh);
             while(conn.rs1.next()){
              moh_name+=conn.rs1.getString("fname")+" "+conn.rs1.getString("mname")+" "+conn.rs1.getString("lname")+",";   
             }
         }
              String get_aphia="SELECT * FROM aphia_staff WHERE id='"+aphia_staff+"'";
             conn.rs1=conn.st1.executeQuery(get_aphia);
             while(conn.rs1.next()){
              aphia_name=conn.rs1.getString("fname")+" "+conn.rs1.getString("mname")+" "+conn.rs1.getString("lname");   
             }
           session.setAttribute("moh_staff_name", moh_name);
           session.setAttribute("aphia_staff_name", aphia_name);
         }
         session.setAttribute("editSite", editSite);
    nextpage="edit_siteChecklist.jsp";
           }
           else{
               nextpage="entry_type.jsp";
           }
           
       session.removeAttribute("justLoaded");
       
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
            Logger.getLogger(edit_sitevalue.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_sitevalue.class.getName()).log(Level.SEVERE, null, ex);
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
