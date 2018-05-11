/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loaders;

import database.dbConn;
import java.sql.SQLException;
import java.util.ArrayList; 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import loaders.BarChart;
/**
 *
 * @author Geofrey Nyabuto
 */
public class GetBarData {
String facilityID;
int recounted_Data,reported_711A,reported_731,reported_DHIS;
String data,indicator;
String allRecounted,all711,all731,allDHIS,allIndicators,alldata;
int year,month,prevYear;
String reportDuration,duration,period,url,semi_annual,quarter,allDATAVal;
 dbConn conn = new dbConn();
    public String  GenerateJSonChartData(String facilityID,String duration, String jsonp) throws  JSONException, SQLException
    {

    String chartType=jsonp;
  
    JSONObject  finalJSonObj=new JSONObject();
 
    BarChart cd;
 
    ArrayList <BarChart> chartDataArray=new ArrayList<BarChart>(); 
 
//    loop thhrough  this
    
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
      
    cd=new BarChart();
    cd.setElementName(indicator);
    cd.setRecounted(recounted_Data);
    cd.setReported711A(reported_711A);
    cd.setReported731(reported_731);
    cd.setReportedDHIS(reported_DHIS);
    
    chartDataArray.add(cd);
 
         }
    JSONArray chartData=new JSONArray();
    JSONArray xaxisArr =new JSONArray();
 
     JSONObject xaxisObj = new JSONObject();
 
     JSONObject dataObj = new JSONObject();
 
     JSONArray RecountedData =new JSONArray();
     JSONArray Reported711 =new JSONArray();
     JSONArray Reported731 =new JSONArray();
     JSONArray ReportedDHIS =new JSONArray();
 
     for (int i=0; i<chartDataArray.size();i++)
     {
 
         System.out.println("Json data "+i);
 
         xaxisArr.put(chartDataArray.get(i).getElementName());
        RecountedData.put(chartDataArray.get(i).getRecounted());
        Reported711.put(chartDataArray.get(i).getReported711A());
        Reported731.put(chartDataArray.get(i).getReported731());
        ReportedDHIS.put(chartDataArray.get(i).getReportedDHIS());
 
     }
 
    xaxisObj.put("category", xaxisArr);
 
        chartData.put(xaxisObj);
 
        dataObj = new JSONObject();
        dataObj.put("name","Recounted Data");
        dataObj.put("color","#FF0000");
        dataObj.put("data",RecountedData);
        chartData.put(dataObj);
        
        dataObj = new JSONObject();
        dataObj.put("name","Reported 711A");
        dataObj.put("color","#FF0000");
        dataObj.put("data",Reported711);
        chartData.put(dataObj);
        
        dataObj = new JSONObject();
        dataObj.put("name","Reported 731");
        dataObj.put("color","#FF0000");
        dataObj.put("data",Reported731);
        chartData.put(dataObj);
        
        dataObj = new JSONObject();
        dataObj.put("name","Reported DHIS");
        dataObj.put("color","#FF0000");
        dataObj.put("data",ReportedDHIS);
        chartData.put(dataObj);
 
        System.out.println("Json data "+ chartData); 
 
        finalJSonObj.put(chartType, chartData);
 
        String  tempStr=jsonp+"("+finalJSonObj.toString()+")";
 
        return tempStr;
}
}
