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

/**
 *
 * @author Nyabuto Geofrey
 */
public class variationReport extends HttpServlet {
HttpSession session;
String data,tableName,facilityIds,duration,indicator_id;
double recounted,reported711A,reported731,reportedDHIS;
double varianceREC_V_REPO,varianceREPO_V_DHIS,varianceREC_V_DHIS,reported;
int year,prevYear,position,tableid;
String TrendvarianceREC_V_REPO,TrendvarianceREPO_V_DHIS,TrendvarianceREC_V_DHIS,TrendPeriods;
String yearMonth,gotPeriod,responseData,indicatorName;
String facilityID,districtid,countyid,reportName,reportType;
int faciliChecker,checkEntered;
String reportDuration,indicatorQuery,tableJoins;
ArrayList addedTables= new ArrayList();
//String dataValues,selectedIndicators,periods;

ArrayList dataValues= new ArrayList();
ArrayList selectedIndicators= new ArrayList();
ArrayList periods= new ArrayList();
String variationLabel="";
int variationType=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
           session=request.getSession();
           addedTables.clear();
              year=2014;
           prevYear=year-1; 
           
          dataValues.clear();
          selectedIndicators.clear();
          periods.clear(); 
           
           
           
           
           indicator_id="2";
          indicatorName=tableJoins=variationLabel="";
          faciliChecker=checkEntered=0;
          tableName="";
          facilityIds=indicatorQuery=" (";
          
          
          
          
          variationType=Integer.parseInt(request.getParameter("variationType"));
                  
                  
          tableJoins=" indicators JOIN ";
          if(request.getParameter("dataElement")!=null && !request.getParameter("dataElement").equals("")){
              String allIndicators []=request.getParameter("dataElement").split(",");
              
              for(String indicatordetails : allIndicators){
                  if(indicatordetails.contains("-")){
              String dataElement [] = indicatordetails.split("-");
          tableid=Integer.parseInt(dataElement [1])-1;
          indicator_id=dataElement [0];
          
           String tables[]="htc_site,pmtct_site,caretreatment_site,tb_site".split(",");
           tableName=tables[tableid];
            indicatorQuery+=" indicator_id='"+indicator_id+"' || "; 
          if(!addedTables.contains(tableName)){
          tableJoins+=" JOIN "+tableName+" ON indicators.id="+tableName+".indicator_id ";     
           addedTables.add(tableName);
           }
            else{
                
                
            }
          
                  }
              }
              
              indicatorQuery = indicatorQuery.substring(0, indicatorQuery.length()-3);
              indicatorQuery=indicatorQuery+") ";
     System.out.println("indicators : "+indicatorQuery);
     System.out.println("tables : "+tableJoins);   
          }
          
          
      
       
       else{
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
           facilityIds+=" hf_id='"+facilityID+"') && ";
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
            facilityIds+=" hf_id='"+conn.rs.getString(1)+"' || ";
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
            facilityIds+=" hf_id='"+conn.rs.getString(1)+"' || ";
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
           tableName="htc_site";
           position=1;
           
           String tables[]="htc_site,pmtct_site,caretreatment_site,tb_site".split(",");
           
//           START OF LOOPS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        for(String currentTable:tables){
           if(addedTables.contains(currentTable)){
           
           String getData="SELECT SUM(temp.RECOUNTED_DATA)AS Recounted,SUM(temp.REPORTED711A)AS Reported711,"
+ "SUM(temp.REPORTED731)AS Reported731,SUM(temp.REPORTED_DHIS)AS ReportedDHIS,PERIOD,INDICATORNAME "
+ "FROM ( SELECT SUM("+currentTable+".recounted_data) AS RECOUNTED_DATA ,SUM("+currentTable+".reported_711A) AS REPORTED711A ,"
+ "SUM("+currentTable+".reported_731) AS REPORTED731,SUM("+currentTable+".reported_DHIS) AS REPORTED_DHIS ,"
+ "CONCAT("+currentTable+".year,"+currentTable+".month) AS YEARMONTH  ,"+currentTable+".year AS YEAR,"+currentTable+".month AS MONTH,"
                    + "CASE "+
"WHEN "+currentTable+".month=01 THEN CONCAT('JAN',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=02 THEN CONCAT('FEB',\" '\",YEAR) " +
"WHEN "+currentTable+".month=03 THEN CONCAT('MAR',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=04 THEN CONCAT('APR',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=05 THEN CONCAT('MAY',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=06 THEN CONCAT('JUN',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=07 THEN CONCAT('JUL',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=08 THEN CONCAT('AUG',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=09 THEN CONCAT('SEPT',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=10 THEN CONCAT('OCT',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=11 THEN CONCAT('NOV',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=12 THEN CONCAT('DEC',\" '\",YEAR)  " +
"ELSE 'UNKNOWN PERIOD' " +
"END AS PERIOD,indicators.indicator AS INDICATORNAME " 

+ "FROM "+currentTable+" JOIN indicators ON indicators.id="+currentTable+".indicator_id "
+ " WHERE "+facilityIds+" "+indicatorQuery+" "
+ "GROUP BY YEARMONTH,indicators.indicator "
//+ " "+duration+""
                    + " ) AS temp GROUP BY  YEARMONTH,INDICATORNAME ORDER BY INDICATORNAME";
 System.out.println("Query : "+getData);
 
 
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
          + "END AS YEARQUARTER,INDICATORNAME "
          + " "
+ "FROM ( SELECT SUM("+currentTable+".recounted_data) AS RECOUNTED_DATA ,SUM("+currentTable+".reported_711A) AS REPORTED711A ,"
+ "SUM("+currentTable+".reported_731) AS REPORTED731,SUM("+currentTable+".reported_DHIS) AS REPORTED_DHIS ,"
+ "CONCAT("+currentTable+".year,"+currentTable+".month) AS YEARMONTH  ,"+currentTable+".year AS YEAR,"+currentTable+".month AS MONTH,"
                    + "CASE "+
"WHEN "+currentTable+".month=01 THEN CONCAT('JAN',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=02 THEN CONCAT('FEB',\" '\",YEAR) " +
"WHEN "+currentTable+".month=03 THEN CONCAT('MAR',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=04 THEN CONCAT('APR',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=05 THEN CONCAT('MAY',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=06 THEN CONCAT('JUN',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=07 THEN CONCAT('JUL',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=08 THEN CONCAT('AUG',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=09 THEN CONCAT('SEPT',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=10 THEN CONCAT('OCT',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=11 THEN CONCAT('NOV',\" '\",YEAR)  " +
"WHEN "+currentTable+".month=12 THEN CONCAT('DEC',\" '\",YEAR)  " +
"ELSE 'UNKNOWN PERIOD' " +
"END AS PERIOD,indicators.indicator AS INDICATORNAME " 

+ "FROM "+currentTable+" JOIN indicators ON indicators.id="+currentTable+".indicator_id "
+ " WHERE "+facilityIds+" "+indicatorQuery+" "
+ "GROUP BY PERIOD,indicators.indicator "
//+ " "+duration+""
                    + " ) AS temp GROUP BY  YEARQUARTER,INDICATORNAME ORDER BY INDICATORNAME,YEARQUARTER";
  
//  System.out.println("quarter qury : "+getDataQuarterly);
  if(reportDuration.equals("monthly")){
   conn.rs=conn.st.executeQuery(getData);
   reportType="Monthly report for ";

  }
  else if (reportDuration.equals("quarterly")){
  conn.rs=conn.st.executeQuery(getDataQuarterly); 
  reportType="Quarterly report for ";
  
  }
  else{

  } 
    while(conn.rs.next()){
 recounted=conn.rs.getDouble(1);
 reported711A=conn.rs.getDouble(2);
 reported731=conn.rs.getDouble(3);
 reportedDHIS=conn.rs.getDouble(4);
 gotPeriod=conn.rs.getString(5);
indicatorName=conn.rs.getString(6);

if (reportDuration.equals("quarterly")){
indicatorName=conn.rs.getString(7);
 }
if(reported711A>0){reported=reported711A;} else if(reported731>0){reported=reported731;} else{reported=0;}
 
if(!(recounted==0 && reported==0 && reportedDHIS==0)){
    
  if(recounted!=0){varianceREC_V_REPO=(double) Math.round(((reported-recounted)*100)/recounted);}else{varianceREC_V_REPO=0;}
  if(reported!=0){varianceREPO_V_DHIS=(double) Math.round(((reportedDHIS-reported)*100)/reported);}else{varianceREPO_V_DHIS=0;}
  if(recounted!=0){varianceREC_V_DHIS= (double) Math.round(((reportedDHIS-recounted)*100)/recounted);}else{varianceREC_V_DHIS=0;} 
}
else{
  varianceREC_V_REPO=0;
  varianceREPO_V_DHIS=0;
  varianceREC_V_DHIS=0;   
}

//DETERMINE WHICH VATIATION IS ENTERED TO THE ARRAYLIST=====================================================

if(variationType==1){
dataValues.add(varianceREC_V_REPO);
variationLabel=" Recounted vs Reported ";
}
else if(variationType==2){
dataValues.add(varianceREC_V_DHIS);
variationLabel=" Recounted vs DHIS ";
}
else if(variationType==3){
 dataValues.add(varianceREPO_V_DHIS);
 variationLabel=" Reported vs DHIS ";
}
else{}

if(!selectedIndicators.contains(indicatorName)){
selectedIndicators.add(indicatorName);
}
if(!periods.contains(gotPeriod)){
periods.add(gotPeriod); 
}        

  position++;
            }
            
            System.out.println("periods : "+periods.size()+" indicators : "+selectedIndicators.size()+" values : "+dataValues.size());
 
           }
           
        }
        
//        END OF LOOPING HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 
      if(position>1) {    
    String varianceTrend="";
       TrendvarianceREC_V_REPO=TrendPeriods="";
                for (Object period : periods) {
              TrendPeriods+=period+"^^";      
                }
                int j=1,valuePos=0;
          for(int i=1;i<=selectedIndicators.size();i++){
             j=1;varianceTrend="";
          while(j<=periods.size()) {
            varianceTrend+=dataValues.get(valuePos).toString()+",";
              j++;
              valuePos++;
          }   
          indicatorName= selectedIndicators.get(i-1).toString();   
          varianceTrend = varianceTrend.substring(0, varianceTrend.length()-1); 
         TrendvarianceREC_V_REPO+=indicatorName+"XX["+varianceTrend+"]@@";
          }
          
          
        TrendvarianceREC_V_REPO = TrendvarianceREC_V_REPO.substring(0, TrendvarianceREC_V_REPO.length()-2); 
        TrendPeriods = TrendPeriods.substring(0, TrendPeriods.length()-2); 

        data=TrendvarianceREC_V_REPO;
        responseData=data+"<br>"+TrendPeriods+"<br>"+variationLabel+" <br>"+reportName+"<br>"+reportType+"<br>";          
        }       
      else{
             responseData="failed to load report";
             }       
    
//            System.out.println("response data : "+responseData);    
            
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
        Logger.getLogger(variationReport.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(variationReport.class.getName()).log(Level.SEVERE, null, ex);
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
