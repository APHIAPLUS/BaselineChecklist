/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import checklist.IdGenerator;
import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Geofrey Nyabuto
 */
public class BackUpReminder extends HttpServlet {
HttpSession session;
String lastbackup,today,status,maxid,timestamp,year,month,day,lastdate;
int days,entries;
String start,end;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
          IdGenerator IG = new IdGenerator();
          today=IG.toDay();days=entries=0;
          maxid=timestamp=year=month=day=lastdate="";status="";
         String getId="SELECT MAX(id) FROM timestamper";
          conn.rs=conn.st.executeQuery(getId);
          if(conn.rs.next()==true){
              maxid=conn.rs.getString(1);
          }
        String tmstamper="SELECT timestamp FROM timestamper WHERE id='"+maxid+"'";
        conn.rs=conn.st.executeQuery(tmstamper);
        if(conn.rs.next()==true){
            timestamp=conn.rs.getString(1);
        }
          
        year=timestamp.substring(0, 4);  
        month=timestamp.substring(4, 6);
        day=timestamp.substring(6, 8);
        lastbackup=year+"-"+month+"-"+day;
      
 
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 
	Date d1 = format.parse(lastbackup);
	Date d2 = format.parse(today);
               
		DateTime StartDate = new DateTime(d1);
		DateTime EndDate = new DateTime(d2);
      System.out.print(Days.daysBetween(StartDate, EndDate).getDays() + " days, ");  
      days=Days.daysBetween(StartDate, EndDate).getDays(); 
     
      start=lastbackup.replace("-", "")+"0000";
       end=today.replace("-", "")+"0000";
      if(days>=5) {
//      CHEK IF DATA EXISTS===========================================================
     String checkData="SELECT COUNT(id) FROM visitdates WHERE timestamp>='"+start+"' && timestamp<='"+end+"'";
     System.out.println(checkData);
     conn.rs=conn.st.executeQuery(checkData);
     if(conn.rs.next()==true){
  entries=conn.rs.getInt(1);
     }
     if(entries>=10){
   status="NOTE: "+days+" days have gone since you last backed up your data. There is data that has not yet been backed up. Please "
                  + "log in to the system and back up this data." ;  
      }
     
      }
            
       
        System.out.println(status);
          
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
        Logger.getLogger(BackUpReminder.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(BackUpReminder.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(BackUpReminder.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(BackUpReminder.class.getName()).log(Level.SEVERE, null, ex);
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
