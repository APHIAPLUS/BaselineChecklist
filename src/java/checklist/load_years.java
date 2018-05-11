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
public class load_years extends HttpServlet {
String correct_years;
int found=0,year=0;
HttpSession session;
int year_max,yearVisit;
int sessyear,selectedYear;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
               //    CURRENT DATE
            yearVisit=0;
 Calendar cal = Calendar.getInstance();
 if(session.getAttribute("year_visit")!=null){yearVisit=Integer.parseInt(session.getAttribute("year_visit").toString());}
 if(session.getAttribute("year_dater")!=null){
 year_max=Integer.parseInt(session.getAttribute("year_dater").toString());    
 }
 else{
year_max=cal.get(Calendar.YEAR);
 }
 if(yearVisit<=year_max && yearVisit!=0){
  year_max=yearVisit;
 }
 
 selectedYear=0;
 if(session.getAttribute("assessYear")!=null){ if(!session.getAttribute("assessYear").toString().equals("")){selectedYear=Integer.parseInt(session.getAttribute("assessYear").toString());}}
// sessyear=Integer.parseInt(correct_years);
           dbConn conn = new dbConn();
           correct_years="<option value=\"\">Assessment Year</option>";
           String get_years="SELECT year FROM years";
           conn.rs= conn.st.executeQuery(get_years);
           while(conn.rs.next()){
               year=conn.rs.getInt(1);
             if(year<=year_max) {
              if(year==selectedYear){
          correct_years+="<option value=\""+year+"\" selected>"+year+"</option>";        
              } 
              else{
                 correct_years+="<option value=\""+year+"\">"+year+"</option>";
             }
             }
//             else{
//              correct_years+="<option value=\""+year+"\" disabled>"+year+"</option>";   
//             }
           }
        
            out.println(correct_years);

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
            Logger.getLogger(load_years.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_years.class.getName()).log(Level.SEVERE, null, ex);
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
