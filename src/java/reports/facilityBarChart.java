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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author Geofrey Nyabuto
 */
public class facilityBarChart extends HttpServlet {
HttpSession session;
String facilityID,districtid,countyid;
int recounted_Data,reported_711A,reported_731,reported_DHIS;
String data,indicator;
String allRecounted,all711,all731,allDHIS,allIndicators,alldata;
int year,month,prevYear,found;
String reportDuration,duration,period,url,semi_annual,quarter;
String tableName,programArea,programID,reportName,reportTitle,responseData;
String facilityIds;
int faciliChecker=0;
int checkEntered;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
            dbConn conn = new dbConn();
            facilityIds="WHERE (";
            
            JSONArray jsonArray = new JSONArray();
            
            tableName=programArea=programID="";
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration").replace(" ", "");
          programID=request.getParameter("programID");
          facilityID=request.getParameter("facilityID").replace(" ", "");
          districtid=request.getParameter("districtid").replace(" ", "");
          countyid=request.getParameter("countyid").replace(" ", "");
//           facilityID="52597";
//           year=2014;
//           reportDuration="4";
//           programID="2";
        responseData="";
           prevYear=year-1; 
        found=0;
        faciliChecker=checkEntered=0;
//           GET SITE AREA AND TABLE
           String getSite="SELECT table_name,program_area FROM site_programs WHERE id='"+programID+"'";
           conn.rs=conn.st.executeQuery(getSite);
           if(conn.rs.next()==true){
          tableName=conn.rs.getString(1);
          programArea=conn.rs.getString(2);    
           }
          
         
           if(!facilityID.equals("")){
            System.out.println("entered facility");    
         String getFacilityName ="SELECT hf_name FROM health_facility WHERE hf_id='"+facilityID+"'";
           conn.rs=conn.st.executeQuery(getFacilityName);
           if(conn.rs.next()==true){
               reportName=conn.rs.getString(1);
           facilityIds+=" "+tableName+".hf_id='"+facilityID+"') ";
           faciliChecker++;
           checkEntered++;
           }
        }
//           getAllFacilities in district
         if(checkEntered==0){
          if(!districtid.equals("")){
               System.out.println("entered dist"); 
            String getFacilities="SELECT hf_id,district_name FROM health_facility "
            + "JOIN district ON health_facility.district_id=district.district_id "
                    + "WHERE district.district_id='"+districtid+"'";
            conn.rs=conn.st.executeQuery(getFacilities);
            while(conn.rs.next()){
              faciliChecker++;  
            facilityIds+=" "+tableName+".hf_id='"+conn.rs.getString(1)+"' || ";
            reportName="<b>"+conn.rs.getString(2).toUpperCase()+"</b> DISTRICT ";
            checkEntered++;
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") "; 
              
          } 
           }
           
          if(checkEntered==0){
          if(!countyid.equals("")){
            System.out.println("entered county");    
            String getFacilities="SELECT hf_id,county_name FROM health_facility "
            + "JOIN district ON health_facility.district_id=district.district_id "
            + "JOIN county ON district.county_id=county.county_id "
                    + "WHERE county.county_id='"+countyid+"'";
            conn.rs=conn.st.executeQuery(getFacilities);
            while(conn.rs.next()){
                faciliChecker++;
            facilityIds+=" "+tableName+".hf_id='"+conn.rs.getString(1)+"' || ";
            reportName="<b>"+conn.rs.getString(2).toUpperCase()+"</b> COUNTY ";
            checkEntered++;
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") "; 
              
          }
           }
          if (checkEntered==0){
             System.out.println("entered all ");  
          if(faciliChecker==0){
             facilityIds="";
             reportName="<b>ALL PROJECT FACILITIES</b>. ";
          }
           }
          else{}
           System.out.println("facilichecker : "+faciliChecker+" check entered : "+checkEntered);
//        GET REPORT DURATION============================================
        
        if(reportDuration.equals("1")){
         duration=" HAVING YEARMONTH  BETWEEN "+prevYear+"10 AND "+year+"09";   
      period="M&E "+year+" Annual Checklist Report for "+reportName;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
       duration=" HAVING YEARMONTH BETWEEN "+prevYear+"10 AND "+year+"03";      
      period="M&E Oct "+prevYear+" -  March "+year+" Semi-Annual Checklist Report for "+reportName;
       }
           else{
       duration=" HAVING YEARMONTH  BETWEEN "+year+"04 AND "+year+"09";       
     period="M&E April "+year+" -  Sept "+year+" Semi-Annual Checklist Report for "+reportName;
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="4";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" HAVING YEARMONTH BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
     period="M&E "+conn.rs.getString(2).replace("-", " "+prevYear+" - ")+" "+prevYear+" Quarterly Checklist Report for "+reportName;
      }
      else{
     duration=" HAVING YEARMONTH BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;
    period="M&E "+conn.rs.getString(2).replace("-", " "+year+" - ")+" "+year+" Quarterly Checklist Report for "+reportName;
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
//     month=7;
     if(month>=10){
     duration="HAVING YEARMONTH BETWEEN "+prevYear+""+month+" AND "+prevYear+""+month;   
     }
     else{
      duration="HAVING YEARMONTH BETWEEN "+year+"0"+month+" AND "+year+"0"+month;
     }
    String getMonthName="SELECT month_name FROM month WHERE month_id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
       if(month>=10){
   period="Month : <b>"+conn.rs.getString(1)+"("+prevYear+")</b>"; 
period="M&E "+conn.rs.getString(1)+" "+prevYear+" Monthly Checklist Report for "+reportName;
       }
       else{
      period="M&E "+conn.rs.getString(1)+" "+year+" Monthly Checklist Report for "+reportName;
    }
     
    }
      }
      else{
     duration="";     
      }
        
         JSONObject obj = new JSONObject();
       
       System.out.println("DURATION IS : "+duration);
            allRecounted=all711=all731=allDHIS=allIndicators=alldata=""; 
String getData="SELECT SUM(temp.RECOUNTED_DATA)AS Recounted,SUM(temp.REPORTED711A)AS Reported711,"
+ "SUM(temp.REPORTED731)AS Reported731,SUM(temp.REPORTED_DHIS)AS ReportedDHIS,temp.INDICATORNAME AS IndicatorName,indicid "
+ "FROM ( SELECT SUM("+tableName+".recounted_data) AS RECOUNTED_DATA ,SUM("+tableName+".reported_711A) AS REPORTED711A ,"
+ "SUM("+tableName+".reported_731) AS REPORTED731,SUM("+tableName+".reported_DHIS) AS REPORTED_DHIS ,"
+ "indicators.indicator AS INDICATORNAME, indicators.id as indicid, CONCAT("+tableName+".year,"+tableName+".month) AS YEARMONTH  "
+ "FROM "+tableName+" JOIN indicators ON indicators.id="+tableName+".indicator_id "
+ " "+facilityIds+" "
+ " GROUP BY INDICATORNAME,YEARMONTH "
+ " "+duration+" ORDER BY indicid ) AS temp GROUP BY IndicatorName ORDER BY indicid";

System.out.println("query : "+getData);

         conn.rs=conn.st.executeQuery(getData);
         while(conn.rs.next()){
recounted_Data=reported_711A=reported_731=reported_DHIS=0;
          recounted_Data=conn.rs.getInt(1);
          reported_711A=conn.rs.getInt(2);
          reported_731=conn.rs.getInt(3);
          reported_DHIS=conn.rs.getInt(4);
          indicator=conn.rs.getString(5);
      
      allRecounted+=recounted_Data+",";
      all711+=reported_711A+",";
      all731+=reported_731+",";
      allDHIS+=reported_DHIS+",";
      allIndicators+=indicator+"^^";
      
     found++;
         }
         
         System.out.println("found : "+found);
         if(found>0){
        allRecounted = allRecounted.substring(0, allRecounted.length()-1); 
        all711 = all711.substring(0, all711.length()-1); 
        all731 = all731.substring(0, all731.length()-1); 
        allDHIS = allDHIS.substring(0, allDHIS.length()-1); 
        allIndicators = allIndicators.substring(0, allIndicators.length()-2); 
         
//         {name: 'Year 1800',data: [107, 31, 635, 203, 290]},
//         
         allRecounted="Recounted DataXX["+allRecounted+"]@@";
         all711="Reported 711AXX["+all711+"]@@";
         all731="Reported 731XX["+all731+"]@@";
         allDHIS="Reported DHISXX["+allDHIS+"]";
         allIndicators="["+allIndicators+"]";
         
         alldata=allRecounted+""+all711+""+all731+""+allDHIS;
//          System.out.println("NO LOOP : "+allIndicators);
          
      obj.put("titles: ",allIndicators);
      obj.put("values: ",alldata);
      
//      System.out.println("object : "+obj);
     
//            out.println();
        responseData=alldata+"<br>"+allIndicators+"<br>"+programArea+"<br>"+period;
         }
         else{
             responseData="failed to load report";
             }
           System.out.println(responseData);
          out.println(responseData); 
//            out.println(obj);
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
        Logger.getLogger(facilityBarChart.class.getName()).log(Level.SEVERE, null, ex);
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
