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
public class lineGraph extends HttpServlet {
HttpSession session;
String data,tableName,facilityIds,duration,indicator_id;
double recounted,reported711A,reported731,reportedDHIS,reported;
double varianceREC_V_REPO,varianceREPO_V_DHIS,varianceREC_V_DHIS;
int year,prevYear,position,tableid;
String TrendvarianceREC_V_REPO,TrendvarianceREPO_V_DHIS,TrendvarianceREC_V_DHIS,TrendPeriods;
String yearMonth,gotPeriod,responseData,indicatorName;
String facilityID,districtid,countyid,reportName,reportType;
int faciliChecker,checkEntered;
String reportDuration;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session=request.getSession();
              year=2014;
//           reportDuration="4";
//           programID="2";
           prevYear=year-1; 
           
           indicator_id="2";
          indicatorName="";
          faciliChecker=checkEntered=0;
          tableName="";
          facilityIds=" (";
          String dataElement [] = request.getParameter("dataElement").split("-");
          tableid=Integer.parseInt(dataElement [1])-1;
          indicator_id=dataElement [0];
          
           String tables[]="htc_site,pmtct_site,caretreatment_site,tb_site".split(",");
           
           tableName=tables[tableid];
           
           System.out.println("table id : "+tableid+" table name : "+tableName);
           
           String elementName="SELECT indicator FROM indicators WHERE id='"+indicator_id+"'";
           conn.rs=conn.st.executeQuery(elementName);
           if(conn.rs.next()==true){
               indicatorName=conn.rs.getString(1);
           }
           
           facilityID=request.getParameter("facilityID").replace(" ", "");
          districtid=request.getParameter("districtid").replace(" ", "");
          countyid=request.getParameter("countyid").replace(" ", "");
          reportDuration=request.getParameter("reportDuration");
          
           if(!facilityID.equals("")){
            System.out.println("entered facility");    
         String getFacilityName ="SELECT hf_name FROM health_facility WHERE hf_id='"+facilityID+"'";
           conn.rs=conn.st.executeQuery(getFacilityName);
           if(conn.rs.next()==true){
               reportName=conn.rs.getString(1);
           facilityIds+=" "+tableName+".hf_id='"+facilityID+"') && ";
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
            reportName="All health facilities in <b>"+conn.rs.getString(2).toUpperCase()+"</b> District ";
            checkEntered++;
    }
            if(checkEntered>0){
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && "; 
            }
            else{
                
            }
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
            reportName=" All health facilities in <b>"+conn.rs.getString(2).toUpperCase()+"</b> County. ";
            checkEntered++;
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && "; 
              
          }
           }
          if (checkEntered==0){
             System.out.println("entered all ");  
          if(faciliChecker==0){
             facilityIds="";
             reportName="<b>All health facilities.</b>. ";
          }
           }
          else{}
          
          
           
           
          gotPeriod="";
          TrendvarianceREC_V_REPO=TrendvarianceREPO_V_DHIS=TrendvarianceREC_V_DHIS=TrendPeriods="";
//           facilityIds="WHERE  ("+tableName+".hf_id='51544') ";
//           duration=" HAVING YEARMONTH  BETWEEN "+prevYear+"10 AND "+year+"09"; 
           
           position=1;
            String getData="SELECT SUM(temp.RECOUNTED_DATA)AS Recounted,SUM(temp.REPORTED711A)AS Reported711,"
+ "SUM(temp.REPORTED731)AS Reported731,SUM(temp.REPORTED_DHIS)AS ReportedDHIS,PERIOD "
+ "FROM ( SELECT SUM("+tableName+".recounted_data) AS RECOUNTED_DATA ,SUM("+tableName+".reported_711A) AS REPORTED711A ,"
+ "SUM("+tableName+".reported_731) AS REPORTED731,SUM("+tableName+".reported_DHIS) AS REPORTED_DHIS ,"
+ "CONCAT("+tableName+".year,"+tableName+".month) AS YEARMONTH  ,"+tableName+".year AS YEAR,"+tableName+".month AS MONTH,"
                    + "CASE "+
"WHEN "+tableName+".month=01 THEN CONCAT('JAN',\" '\",YEAR)  " +
"WHEN "+tableName+".month=02 THEN CONCAT('FEB',\" '\",YEAR) " +
"WHEN "+tableName+".month=03 THEN CONCAT('MAR',\" '\",YEAR)  " +
"WHEN "+tableName+".month=04 THEN CONCAT('APR',\" '\",YEAR)  " +
"WHEN "+tableName+".month=05 THEN CONCAT('MAY',\" '\",YEAR)  " +
"WHEN "+tableName+".month=06 THEN CONCAT('JUN',\" '\",YEAR)  " +
"WHEN "+tableName+".month=07 THEN CONCAT('JUL',\" '\",YEAR)  " +
"WHEN "+tableName+".month=08 THEN CONCAT('AUG',\" '\",YEAR)  " +
"WHEN "+tableName+".month=09 THEN CONCAT('SEPT',\" '\",YEAR)  " +
"WHEN "+tableName+".month=10 THEN CONCAT('OCT',\" '\",YEAR)  " +
"WHEN "+tableName+".month=11 THEN CONCAT('NOV',\" '\",YEAR)  " +
"WHEN "+tableName+".month=12 THEN CONCAT('DEC',\" '\",YEAR)  " +
"ELSE 'UNKNOWN PERIOD' " +
"END AS PERIOD " 

+ "FROM "+tableName+" "
+ " WHERE "+facilityIds+" "+tableName+".indicator_id='"+indicator_id+"' "
+ "GROUP BY YEARMONTH "
//+ " "+duration+""
                    + " ) AS temp GROUP BY  YEARMONTH";
// System.out.println("Query : "+getData);
 
 
  String getDataQuarterly="SELECT SUM(temp.RECOUNTED_DATA)AS Recounted,SUM(temp.REPORTED711A)AS Reported711,"
+ "SUM(temp.REPORTED731)AS Reported731,SUM(temp.REPORTED_DHIS)AS ReportedDHIS,"
          + " CASE "
          + "WHEN MONTH >=1 && MONTH<=3 THEN CONCAT('JAN-MARCH',\" '\",YEAR) "
          + "WHEN MONTH >=4 && MONTH<=6 THEN CONCAT('APR-JUN',\" '\",YEAR) "
          + "WHEN MONTH >=7 && MONTH<=9 THEN CONCAT('JUL-SEP',\" '\",YEAR) "
          + "WHEN MONTH >=10 && MONTH<=12 THEN CONCAT('OCT-DEC',\" '\",YEAR) "
          + "ELSE 'UKNOWN PERIOD' "
          + "END AS PERIODS,"
          + " CASE "
          + "WHEN MONTH >=1 && MONTH<=3 THEN CONCAT(YEAR,1) "
          + "WHEN MONTH >=4 && MONTH<=6 THEN CONCAT(YEAR,2) "
          + "WHEN MONTH >=7 && MONTH<=9 THEN CONCAT(YEAR,3) "
          + "WHEN MONTH >=10 && MONTH<=12 THEN CONCAT(YEAR,4) "
          + "ELSE 'UKNOWN YEARQUARTER ' "
          + "END AS YEARQUARTER"
          + " "
+ "FROM ( SELECT SUM("+tableName+".recounted_data) AS RECOUNTED_DATA ,SUM("+tableName+".reported_711A) AS REPORTED711A ,"
+ "SUM("+tableName+".reported_731) AS REPORTED731,SUM("+tableName+".reported_DHIS) AS REPORTED_DHIS ,"
+ "CONCAT("+tableName+".year,"+tableName+".month) AS YEARMONTH  ,"+tableName+".year AS YEAR,"+tableName+".month AS MONTH,"
                    + "CASE "+
"WHEN "+tableName+".month=01 THEN CONCAT('JAN',\" '\",YEAR)  " +
"WHEN "+tableName+".month=02 THEN CONCAT('FEB',\" '\",YEAR) " +
"WHEN "+tableName+".month=03 THEN CONCAT('MAR',\" '\",YEAR)  " +
"WHEN "+tableName+".month=04 THEN CONCAT('APR',\" '\",YEAR)  " +
"WHEN "+tableName+".month=05 THEN CONCAT('MAY',\" '\",YEAR)  " +
"WHEN "+tableName+".month=06 THEN CONCAT('JUN',\" '\",YEAR)  " +
"WHEN "+tableName+".month=07 THEN CONCAT('JUL',\" '\",YEAR)  " +
"WHEN "+tableName+".month=08 THEN CONCAT('AUG',\" '\",YEAR)  " +
"WHEN "+tableName+".month=09 THEN CONCAT('SEPT',\" '\",YEAR)  " +
"WHEN "+tableName+".month=10 THEN CONCAT('OCT',\" '\",YEAR)  " +
"WHEN "+tableName+".month=11 THEN CONCAT('NOV',\" '\",YEAR)  " +
"WHEN "+tableName+".month=12 THEN CONCAT('DEC',\" '\",YEAR)  " +
"ELSE 'UNKNOWN PERIOD' " +
"END AS PERIOD " 

+ "FROM "+tableName+" "
+ " WHERE "+facilityIds+" "+tableName+".indicator_id='"+indicator_id+"' "
+ "GROUP BY PERIOD "
//+ " "+duration+""
                    + " ) AS temp GROUP BY  YEARQUARTER ORDER BY YEARQUARTER";
  
  
  if(reportDuration.equals("monthly")){
   conn.rs=conn.st.executeQuery(getData);
   reportType="Monthly report for ";
     System.out.println("monthly report");
  }
  else if (reportDuration.equals("quarterly")){
  conn.rs=conn.st.executeQuery(getDataQuarterly); 
  reportType="Quarterly report for ";
  System.out.println("quarterly report");
  }
  else{
        System.out.println("no report : "+reportDuration);
  }         
            
            while(conn.rs.next()){
//            add data here to the string    
 recounted=conn.rs.getDouble(1);
 reported711A=conn.rs.getDouble(2);
 reported731=conn.rs.getDouble(3);
 reportedDHIS=conn.rs.getDouble(4);
 gotPeriod=conn.rs.getString(5);

 System.out.println("recounted : "+recounted+" 711 : "+reported711A+" reported 731 : "+reported731+" dhis : "+reportedDHIS);
 
if(reported711A>0){reported=reported711A;} else if(reported731>0){reported=reported731;} else{reported=0;}
 
if(recounted!=0){
if(recounted!=0){varianceREC_V_REPO=(double) Math.round(((reported-recounted)*100)/recounted);}else{varianceREC_V_REPO=0;}
  if(reported!=0){varianceREPO_V_DHIS=(double) Math.round(((reportedDHIS-reported)*100)/reported);}else{varianceREPO_V_DHIS=0;}
  if(recounted!=0){varianceREC_V_DHIS= (double) Math.round(((reportedDHIS-recounted)*100)/recounted);}else{varianceREC_V_DHIS=0;} 

//  System.out.println("var 1 : "+varianceREC_V_REPO+" var 2 : "+varianceREPO_V_DHIS+"   var 3 : "+varianceREC_V_DHIS);
            }
else{
  varianceREC_V_REPO=0;
  varianceREPO_V_DHIS=0;
  varianceREC_V_DHIS=0;   
}
  TrendvarianceREC_V_REPO+=varianceREC_V_REPO+",";
  TrendvarianceREPO_V_DHIS+=varianceREPO_V_DHIS+",";
  TrendvarianceREC_V_DHIS+=varianceREC_V_DHIS+",";
  TrendPeriods+=gotPeriod+"^^";
      
//  System.out.println("at position : "+position);
  position++;
            }
      if(position>1) {     
        TrendvarianceREC_V_REPO = TrendvarianceREC_V_REPO.substring(0, TrendvarianceREC_V_REPO.length()-1); 
        TrendvarianceREPO_V_DHIS = TrendvarianceREPO_V_DHIS.substring(0, TrendvarianceREPO_V_DHIS.length()-1); 
        TrendvarianceREC_V_DHIS = TrendvarianceREC_V_DHIS.substring(0, TrendvarianceREC_V_DHIS.length()-1); 
        TrendPeriods = TrendPeriods.substring(0, TrendPeriods.length()-2); 

         
//         {name: 'Year 1800',data: [107, 31, 635, 203, 290]},
//         
         TrendvarianceREC_V_REPO="Verified vs Reported XX["+TrendvarianceREC_V_REPO+"]@@";
         TrendvarianceREPO_V_DHIS="Reported vs DHIS XX["+TrendvarianceREPO_V_DHIS+"]@@";
         TrendvarianceREC_V_DHIS="Verified vs DHIS XX["+TrendvarianceREC_V_DHIS+"]";
         TrendPeriods=""+TrendPeriods+"";
         
         data=TrendvarianceREC_V_REPO+""+TrendvarianceREPO_V_DHIS+""+TrendvarianceREC_V_DHIS;
        responseData=data+"<br>"+TrendPeriods+"<br>"+indicatorName+"<br>"+reportName+"<br>"+reportType+"<br>";          
        }       
      else{
             responseData="failed to load report";
             }       
            
            
//    System.out.println("variance rec_v_repo : "+TrendvarianceREC_V_REPO);
//    System.out.println("variance repo_v_dhis : "+TrendvarianceREPO_V_DHIS);
//    System.out.println("variance rec_v_dhis : "+TrendvarianceREC_V_DHIS);
//    System.out.println("variance periods : "+TrendPeriods);
//    
            
            
            out.println(responseData);
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
        Logger.getLogger(lineGraph.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(lineGraph.class.getName()).log(Level.SEVERE, null, ex);
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
