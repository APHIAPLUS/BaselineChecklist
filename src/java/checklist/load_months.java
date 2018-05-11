/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
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
public class load_months extends HttpServlet {
String correct_months;
int found=0,month=0;
HttpSession session;
int month_max,year_max,selecteMonth,selected_year,visitMonth,yearVisit;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
            visitMonth=yearVisit=0;
             if(session.getAttribute("month_visit")!=null){visitMonth=Integer.parseInt(session.getAttribute("month_visit").toString());}
             if(session.getAttribute("year_visit")!=null){yearVisit=Integer.parseInt(session.getAttribute("year_visit").toString());}
 
            if(request.getParameter("year")!=null && !request.getParameter("year").equals("")){
     selected_year=Integer.parseInt(request.getParameter("year"));}
            else{
                if(session.getAttribute("assessYear")!=null){
                    if(!session.getAttribute("assessYear").toString().equals("")){
      selected_year=Integer.parseInt(session.getAttribute("assessYear").toString());
            }
            }
            }
     session.setAttribute("assessYear", selected_year);
               //    CURRENT DATE
 Calendar cal = Calendar.getInstance();
if(session.getAttribute("year_dater")!=null){
    if(!session.getAttribute("year_dater").equals("")){
 year_max=Integer.parseInt(session.getAttribute("year_dater").toString());    
 }
    else{
        year_max=cal.get(Calendar.YEAR);
    }
}
 else{
year_max=cal.get(Calendar.YEAR);
 }
selecteMonth=0;
if(session.getAttribute("assessMonth")!=null){if(!session.getAttribute("assessMonth").toString().equals("")){selecteMonth=Integer.parseInt(session.getAttribute("assessMonth").toString());}}
System.out.println("selected month : "+selecteMonth);
if(session.getAttribute("month_dater")!=null){
 month_max=Integer.parseInt(session.getAttribute("month_dater").toString());    
 }
 else{
month_max=cal.get(Calendar.MONTH)+1;
 }
System.out.println("max year : "+year_max+"   month max  :  "+month_max);

           dbConn conn = new dbConn();
             correct_months="<option value=\"\">Assessment Month</option>";
//          if(year_max==selected_year){
          if(yearVisit==selected_year){
          String get_months="SELECT * FROM month";
           conn.rs= conn.st.executeQuery(get_months);
           while(conn.rs.next()){
               month=conn.rs.getInt(1);
               System.out.println("selected month : "+selecteMonth+" got month : "+month);
             
               
//                if(month<=month_max) {
             if(month<=visitMonth) {
                 if(selecteMonth==month){
              correct_months+="<option value=\""+conn.rs.getInt(1)+"\" selected>"+conn.rs.getString(2)+"</option>";       
                 }
                 else{
                 correct_months+="<option value=\""+conn.rs.getInt(1)+"\">"+conn.rs.getString(2)+"</option>";
                 }
                 System.out.println("lesser or  equal");
             }
//             else{
//              correct_months+="<option value=\""+conn.rs.getInt(1)+"\" disabled>"+conn.rs.getString(2)+"</option>";   
//            System.out.println("more than selected");
//             }
           }
          }
          else{
           String get_months="SELECT * FROM month";
           conn.rs= conn.st.executeQuery(get_months);
           while(conn.rs.next()){
                month=conn.rs.getInt(1); 
              System.out.println("selected month : "+selecteMonth+" got month : "+month);  
          if(selecteMonth==month){
              correct_months+="<option value=\""+conn.rs.getInt(1)+"\" selected>"+conn.rs.getString(2)+"</option>";       
                 }
                 else{
                 correct_months+="<option value=\""+conn.rs.getInt(1)+"\">"+conn.rs.getString(2)+"</option>";
                 }
           }
          }    
              
          
            
            /* TODO output your page here. You may use following sample code. */
            out.println(correct_months);
          
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
            Logger.getLogger(load_months.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_months.class.getName()).log(Level.SEVERE, null, ex);
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
