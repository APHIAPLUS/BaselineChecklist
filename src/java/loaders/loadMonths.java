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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geofrey Nyabuto
 */
public class loadMonths extends HttpServlet {
String data,currentMonth,monthnm;
 Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int selectedyear;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           data="";
            data="<option value=\"\">Choose Month</option>"; 
           dbConn conn = new dbConn();
           if(month<10){currentMonth="0"+month;}
           if(month>=10){currentMonth=""+month;}
           selectedyear=Integer.parseInt(request.getParameter("year"));
           if(selectedyear<year){
            String getMonths="SELECT * FROM month";
           conn.rs=conn.st.executeQuery(getMonths);
           while(conn.rs.next()){
               monthnm="";
           if(conn.rs.getInt(1)<10){monthnm="0"+conn.rs.getInt(1);}
           if(conn.rs.getInt(1)>=10){monthnm=""+conn.rs.getInt(1);}   
             data+="<option value=\""+monthnm+"\">"+conn.rs.getString(2)+"</option>";  
           }
          
           }
           else if(selectedyear==year){
           String getMonths="SELECT * FROM month where month_id<='"+month+"'";
           conn.rs=conn.st.executeQuery(getMonths);
           while(conn.rs.next()){
               monthnm="";
           if(conn.rs.getInt(1)<10){monthnm="0"+conn.rs.getInt(1);}
           if(conn.rs.getInt(1)>=10){monthnm=""+conn.rs.getInt(1);}   
             data+="<option value=\""+monthnm+"\">"+conn.rs.getString(2)+"</option>";  
          }
           }
           else{}
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
        Logger.getLogger(loadMonths.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadMonths.class.getName()).log(Level.SEVERE, null, ex);
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
