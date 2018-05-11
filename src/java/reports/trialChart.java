/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Geofrey Nyabuto
 */
public class trialChart extends HttpServlet {
HttpSession session;
String facilityID;
int recounted_Data,reported_711A,reported_731,reported_DHIS;
String data,indicator;
int year,month,prevYear;
String reportDuration,duration,period,url,semi_annual,quarter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
            dbConn conn = new dbConn();
//           String allRecounted,all711,all731,allDHIS,allIndicators,alldata; 
            JSONArray jsonArray = new JSONArray();
            
//        year=Integer.parseInt(request.getParameter("year"));
//        reportDuration=request.getParameter("reportDuration");
 
//           facilityID=request.getParameter("facilityID");
           facilityID="58371";
        year=2015;
        reportDuration="3";
        
        prevYear=year-1; 
     
        
//        GET REPORT DURATION============================================
        
        if(reportDuration.equals("1")){
         duration=" HAVING YEARMONTH  BETWEEN "+prevYear+"10 AND "+year+"09";   
      period="Annual Report ";
      url="ANNUAL_REPORT_YEAR("+year+")";
        }
        else if(reportDuration.equals("2")){
//        semi_annual=request.getParameter("semi_annual");
        semi_annual="1";
       if(semi_annual.equals("1")){
       duration=" HAVING YEARMONTH BETWEEN "+prevYear+"10 AND "+year+"03";      
      period="Semi-Annual : <b> OCT ("+prevYear+") -  MARCH ("+year+")</b>"; 
      url="SEMI_ANNUAL_REPORT_FOR_OCT("+prevYear+")_MARCH("+year+")";
       }
           else{
       duration=" HAVING YEARMONTH  BETWEEN "+year+"04 AND "+year+"09";      
       period="Semi-Annual : <b> APRIL ("+year+") -  SEPT ("+year+")</b>";  
      url="SEMI_ANNUAL_REPORT_FOR_APRIL("+year+")_SEPT("+year+")";
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
//       quarter=request.getParameter("quarter");
       quarter="1";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" HAVING YEARMONTH BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", prevYear+" - ")+" "+prevYear+"</b>";
     url="QUARTERLY_REPORT_FOR_"+conn.rs.getString(2).replace("-", prevYear+"-")+"_"+prevYear+"";
      }
      else{
     duration=" HAVING YEARMONTH BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", year+" - ")+" "+year+"</b>";
    url="QUARTERLY_REPORT_FOR_"+conn.rs.getString(2).replace("-", year+"-")+"_"+year+"";
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
//     month=Integer.parseInt(request.getParameter("month"));
     month=12;
     if(month>=10){
     duration="HAVING YEARMONTH BETWEEN "+prevYear+""+month+" AND "+prevYear+""+month;   
     }
     else{
      duration="HAVING YEARMONTH BETWEEN "+year+"0"+month+" AND "+year+"0"+month;
     }

      }
      else{
     duration="";     
      }
        
         JSONObject obj = new JSONObject();
        JSONArray allRecounted = new JSONArray();
        JSONArray all711 = new JSONArray();
        JSONArray all731 = new JSONArray();
        JSONArray allDHIS = new JSONArray();
        JSONArray allIndicators = new JSONArray();
        
      allRecounted = new JSONArray(new ArrayList<String>());
      all711 = new JSONArray(new ArrayList<String>());
      all731 = new JSONArray(new ArrayList<String>());
      allDHIS = new JSONArray(new ArrayList<String>());
      allIndicators = new JSONArray(new ArrayList<String>());
      
       System.out.println("DURATION IS : "+duration);
//            allRecounted=all711=all731=allDHIS=allIndicators=alldata=""; 
String getHTC="SELECT SUM(temp.RECOUNTED_DATA)AS Recounted,SUM(temp.REPORTED711A)AS Reported711,"
+ "SUM(temp.REPORTED731)AS Reported731,SUM(temp.REPORTED_DHIS)AS ReeportedDHIS,temp.INDICATORNAME AS IndicatorName "
+ "FROM ( SELECT SUM(htc_site.recounted_data) AS RECOUNTED_DATA ,SUM(htc_site.reported_711A) AS REPORTED711A ,"
+ "SUM(htc_site.reported_731) AS REPORTED731,SUM(htc_site.reported_DHIS) AS REPORTED_DHIS ,"
+ "indicators.indicator AS INDICATORNAME,CONCAT(htc_site.year,htc_site.month) AS YEARMONTH  "
+ "FROM htc_site JOIN indicators ON indicators.id=htc_site.indicator_id "
+ "WHERE htc_site.hf_id='"+facilityID+"' "
+ "GROUP BY INDICATORNAME,YEARMONTH "
+ " "+duration+" ) AS temp GROUP BY IndicatorName ";

System.out.println("query : "+getHTC);

         conn.rs=conn.st.executeQuery(getHTC);
         while(conn.rs.next()){
recounted_Data=reported_711A=reported_731=reported_DHIS=0;
          recounted_Data=conn.rs.getInt(1);
          reported_711A=conn.rs.getInt(2);
          reported_731=conn.rs.getInt(3);
          reported_DHIS=conn.rs.getInt(4);
          indicator=conn.rs.getString(5);

      
      allRecounted.put(recounted_Data);
      all711.put(reported_711A);
      all731.put(reported_731);
      allDHIS.put(reported_DHIS);
      allIndicators.put(indicator);
          
     
         }
         
       obj.put("name", "RECOUNTED DATA");
       obj.put("data", allRecounted);
       
       obj.put("name", "REPORTED 711A");
       obj.put("data", all711);
       
       obj.put("name", "REPORTED 731");
       obj.put("data", all731);
       
       obj.put("name", "REPORTED DHIS");
       obj.put("data", allDHIS);
 
       obj.put("indicator_name", allIndicators);
         
         out.println(obj);
         out.println(",");
      
      System.out.println("object : "+obj);
            out.println(obj);
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
        Logger.getLogger(trialChart.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JSONException ex) {
        Logger.getLogger(trialChart.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(facilityBarChart.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JSONException ex) {
        Logger.getLogger(facilityBarChart.class.getName()).log(Level.SEVERE, null, ex);
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
