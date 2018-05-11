/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import checklist.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
/**
 *
 * @author Geofrey Nyabuto
 */
public class ProgramReportVisit extends HttpServlet {
HttpSession session;
String year, month;
String htc_Data1,htc_711A1,htc_7311,htc_DHIS1;
String pmtct_Data1,pmtct_711A1,pmtct_7311,pmtct_DHIS1;
String care_Data1,care_711A1,care_7311,care_DHIS1;
String tb_Data1,tb_711A1,tb_7311,tb_DHIS1;

int htc_Data,htc_711A,htc_731,htc_DHIS;
int pmtct_Data,pmtct_711A,pmtct_731,pmtct_DHIS;
int care_Data,care_711A,care_731,care_DHIS;
int tb_Data,tb_711A,tb_731,tb_DHIS;
String aphia_name,moh_name,date="";
String facility_id;
ArrayList allFacil = new ArrayList();
ArrayList allYear = new ArrayList();
ArrayList allMonth = new ArrayList();
int pos;
String county_name,district_name,facility_name,facility_level,month_name;
String visitYear,visitMonth,created_on;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
       visitYear=request.getParameter("year");
       visitMonth=request.getParameter("month");
       
//       visitYear="2014";
//       visitMonth="09";
       
       allFacil.clear();
       allYear.clear();
       allMonth.clear();
        IdGenerator IG = new IdGenerator();
       
       created_on=IG.Created_On();
       
       
       HSSFWorkbook wb=new HSSFWorkbook();
       HSSFSheet shet1=wb.createSheet("HTC-PITC & VCT");
       HSSFSheet shet2=wb.createSheet("PMTCT-ANC and Labour Delivery");
       HSSFSheet shet3=wb.createSheet("Care and Treatment");
       HSSFSheet shet4=wb.createSheet("TB/HIV");

       
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//    ^^^^^^^^^^^^HEADER STYLES^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     HSSFFont font_header=wb.createFont();
    font_header.setFontHeightInPoints((short)30);
    font_header.setFontName("Arial Black");
//    font.setItalic(true);
    font_header.setBoldweight((short)20);
    font_header.setColor(HSSFColor.BLACK.index);
    CellStyle style_header=wb.createCellStyle();
    style_header.setFont(font_header);
    style_header.setWrapText(true);
    style_header.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
style_header.setBorderBottom(CellStyle.BORDER_THIN);
style_header.setBorderLeft(CellStyle.BORDER_THIN);
style_header.setBorderRight(CellStyle.BORDER_THIN);
style_header.setBorderTop(CellStyle.BORDER_THIN);

   HSSFFont font_midheader=wb.createFont();
    font_midheader.setFontHeightInPoints((short)10);
    font_midheader.setFontName("Arial Black");
//    font.setItalic(true);
    font_midheader.setBoldweight((short)03);
    font_midheader.setColor(HSSFColor.BLACK.index);
    CellStyle style_midheader=wb.createCellStyle();
    style_midheader.setFont(font_midheader);
    style_midheader.setWrapText(true);
    style_midheader.setFillForegroundColor(HSSFColor.LIME.index);
style_midheader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
style_midheader.setBorderBottom(CellStyle.BORDER_THIN);
style_midheader.setBorderLeft(CellStyle.BORDER_THIN);
style_midheader.setBorderRight(CellStyle.BORDER_THIN);
style_midheader.setBorderTop(CellStyle.BORDER_THIN);


    shet1.setColumnWidth(0, 5000);
    shet1.setColumnWidth(1, 5000); 
    shet1.setColumnWidth(2, 3000);
    shet1.setColumnWidth(3, 3000);
    shet1.setColumnWidth(4, 3000);
    shet1.setColumnWidth(5, 3000);
    shet1.setColumnWidth(6, 8000);
    shet1.setColumnWidth(7, 3000);
    shet1.setColumnWidth(8, 5000);
    shet1.setColumnWidth(9, 3000);
    shet1.setColumnWidth(10, 3000);
    shet1.setColumnWidth(11, 3000);
    shet1.setColumnWidth(12, 3000);
    shet1.setColumnWidth(13, 3000);
    shet1.setColumnWidth(14, 3000);
    shet1.setColumnWidth(15, 3000);
    shet1.setColumnWidth(16, 3000);
    shet1.setColumnWidth(17, 3000);
    pos=0;
       
            HSSFRow rw0=shet1.createRow(pos);
            rw0.setHeightInPoints(60);
            for(int h=9;h<=92;h++){
              HSSFCell cell_rw0=rw0.createCell(h);
              cell_rw0.setCellStyle(style_header); 
            }
            HSSFCell cell_rw0=rw0.createCell(9);
            HSSFCell cell1_rw0=rw0.createCell(33);
            HSSFCell cell2_rw0=rw0.createCell(69);
            HSSFCell cell3_rw0=rw0.createCell(85);
         cell_rw0.setCellValue("HTC-PITC & VCT");
         cell1_rw0.setCellValue("PMTCT-ANC and Labour Delivery");
         cell2_rw0.setCellValue("Care and Treatment");
         cell3_rw0.setCellValue("TB/HIV");
         cell_rw0.setCellStyle(style_header); 
         cell1_rw0.setCellStyle(style_header);
         cell2_rw0.setCellStyle(style_header); 
         cell3_rw0.setCellStyle(style_header);
         
       shet1.addMergedRegion(new CellRangeAddress(pos,pos,9,32));
       shet1.addMergedRegion(new CellRangeAddress(pos,pos,33,68));
       shet1.addMergedRegion(new CellRangeAddress(pos,pos,69,84));
       shet1.addMergedRegion(new CellRangeAddress(pos,pos,85,92));
        
         
//         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//         ^^^^^^CREATE A NEW ROW AND ITS FORMATTINGS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         HSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)12);
    font.setFontName("Cambria");
//    font.setItalic(true);
    font.setBoldweight((short)02);
    font.setColor(HSSFColor.BLACK.index);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setWrapText(true);
    style.setFillForegroundColor(HSSFColor.GOLD.index);
style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
style.setBorderBottom(CellStyle.BORDER_THIN);
style.setBorderLeft(CellStyle.BORDER_THIN);
style.setBorderRight(CellStyle.BORDER_THIN);
style.setBorderTop(CellStyle.BORDER_THIN);

 CellStyle style_green=wb.createCellStyle();
// style_green.setFillForegroundColor(HSSFColor.GREEN.index);
// style_green.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
 style_green.setBorderBottom(CellStyle.BORDER_THIN);
style_green.setBorderLeft(CellStyle.BORDER_THIN);
style_green.setBorderRight(CellStyle.BORDER_THIN);
style_green.setBorderTop(CellStyle.BORDER_THIN);


   HSSFFont fontx=wb.createFont();
    fontx.setFontHeightInPoints((short)12);
    fontx.setFontName("Cambria");
//    font.setItalic(true);
    fontx.setBoldweight((short)02);
    fontx.setColor(HSSFColor.BLACK.index);
    CellStyle stylex=wb.createCellStyle();
    stylex.setFont(font);
    stylex.setWrapText(true);
    stylex.setFillForegroundColor(HSSFColor.CORAL.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
stylex.setBorderBottom(CellStyle.BORDER_THIN);
stylex.setBorderLeft(CellStyle.BORDER_THIN);
stylex.setBorderRight(CellStyle.BORDER_THIN);
stylex.setBorderTop(CellStyle.BORDER_THIN);

HSSFFont fontTOTAL=wb.createFont();
    fontTOTAL.setFontHeightInPoints((short)12);
    fontTOTAL.setFontName("Cambria");
//    font.setItalic(true);
    fontTOTAL.setBoldweight((short)02);
    fontTOTAL.setColor(HSSFColor.BLACK.index);
    CellStyle styleTOTAL=wb.createCellStyle();
    styleTOTAL.setFont(fontTOTAL);
    styleTOTAL.setWrapText(true);
    styleTOTAL.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
styleTOTAL.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
styleTOTAL.setBorderBottom(CellStyle.BORDER_THIN);
styleTOTAL.setBorderLeft(CellStyle.BORDER_THIN);
styleTOTAL.setBorderRight(CellStyle.BORDER_THIN);
styleTOTAL.setBorderTop(CellStyle.BORDER_THIN);
pos++;
 HSSFRow rwx=shet1.createRow(pos);
 rwx.setHeightInPoints(55);
int x=0;
     
      String getDataElement="SELECT indicator,id FROM indicators WHERE ";
      conn.rs=conn.st.executeQuery(getDataElement);
      while(conn.rs.next()){
         int from,to; 
          x=(conn.rs.getInt(2)*4)-4;
           
//          System.out.println("heeeeeeee..............................."+x);
     HSSFCell rw1cell10=rwx.createCell(9+x);
     HSSFCell rw1cell11=rwx.createCell(10+x);
     HSSFCell rw1cell12=rwx.createCell(11+x);
     HSSFCell rw1cell13=rwx.createCell(12+x);
//    x+=4;
      rw1cell10.setCellValue(conn.rs.getString(1));
      from=9+x; to=9+x+3;
     shet1.addMergedRegion(new CellRangeAddress(pos,pos,from,to)); 
//     System.out.println("MERGE FROM "+from+" TO "+to);
  rw1cell10.setCellStyle(style_midheader);
  rw1cell11.setCellStyle(style_midheader);
  rw1cell12.setCellStyle(style_midheader);
  rw1cell13.setCellStyle(style_midheader);
  
      }
     
      pos++;
      
        HSSFRow rw1=shet1.createRow(pos);
     rw1.setHeightInPoints(55);
     
     HSSFCell rw1cell0=rw1.createCell(0);
     HSSFCell rw1cell1=rw1.createCell(1);
     HSSFCell rw1cell2=rw1.createCell(2);
     HSSFCell rw1cell3=rw1.createCell(3);
     HSSFCell rw1cell4=rw1.createCell(4);
     HSSFCell rw1cell5=rw1.createCell(5);
     HSSFCell rw1cell6=rw1.createCell(6);
     HSSFCell rw1cell7=rw1.createCell(7);
     HSSFCell rw1cell8=rw1.createCell(8);
     HSSFCell rw1cell9=rw1.createCell(9);
     
     
      rw1cell0.setCellValue("Aphia Staff");
      rw1cell1.setCellValue("MOH Staff");
      rw1cell2.setCellValue("Date of Visit");
      rw1cell3.setCellValue("Facility Level");
      rw1cell4.setCellValue("Month");
      rw1cell5.setCellValue("Year");
      
      rw1cell6.setCellValue("Facility Name");
      rw1cell7.setCellValue("County");
      rw1cell8.setCellValue("District");
      int q=0;
      while (q<=80){
                
     HSSFCell rw1cell10=rw1.createCell(9+q);
     HSSFCell rw1cell11=rw1.createCell(10+q);
     HSSFCell rw1cell12=rw1.createCell(11+q);
     HSSFCell rw1cell13=rw1.createCell(12+q);
     
      rw1cell10.setCellValue("Recounted");
      rw1cell11.setCellValue("711A");
      rw1cell12.setCellValue("731");
      rw1cell13.setCellValue("DHIS");
      
  rw1cell10.setCellStyle(style);
  rw1cell11.setCellStyle(style);
  rw1cell12.setCellStyle(style);
  rw1cell13.setCellStyle(style);
  q+=4;
      }
      
  rw1cell0.setCellStyle(style);
  rw1cell1.setCellStyle(style);
  rw1cell2.setCellStyle(style);
  rw1cell3.setCellStyle(style);
  rw1cell4.setCellStyle(style);
  rw1cell5.setCellStyle(style);
  rw1cell6.setCellStyle(style);
  rw1cell7.setCellStyle(style);
  rw1cell8.setCellStyle(style);
  rw1cell9.setCellStyle(style);
  
 
  
  int herepos=0;
    System.out.println("visit year: "+visitYear+"   visit month   :  "+visitMonth);
     String  getAllFacil="SELECT  hfid,assessyear,assessmonth FROM visitdates WHERE visityear='"+visitYear+"' && visitmonth='"+visitMonth+"'";
      conn.rs=conn.st.executeQuery(getAllFacil);
      System.out.println("query : "+getAllFacil);
      while(conn.rs.next()){
          herepos++;
          allFacil.add(conn.rs.getString(1));
          allYear.add(conn.rs.getString(2));
          allMonth.add(conn.rs.getString(3));
//          System.out.println("here  :   "+herepos);
      }
       System.out.println("here facili size      ................"+allFacil.size());
//       LOOP AND GET ALL DATA.........................
      for(int i=0;i<allFacil.size();i++){
          pos++;
         HSSFRow rw2=shet1.createRow(pos); 
     HSSFCell rw1cell0x=rw2.createCell(0);
     HSSFCell rw1cell1x=rw2.createCell(1);
     HSSFCell rw1cell2x=rw2.createCell(2);
     HSSFCell rw1cell3x=rw2.createCell(3);
     HSSFCell rw1cell4x=rw2.createCell(4);
     HSSFCell rw1cell5x=rw2.createCell(5);
     HSSFCell rw1cell6x=rw2.createCell(6);
     HSSFCell rw1cell7x=rw2.createCell(7);
     HSSFCell rw1cell8x=rw2.createCell(8);
     System.out.println("here at position   :   "+i);
     year=allYear.get(i).toString();
     month=allMonth.get(i).toString();
     
      county_name=district_name=facility_name=facility_level=month_name="";
         aphia_name=moh_name=date="";
         String [] aphia_id={},moh_id={};
          facility_id=allFacil.get(i).toString();
          System.out.println("here facili id      ................"+facility_id);
          String getNames="SELECT aphia_staff,moh_staff,date FROM marked_checklist WHERE hf_id='"+facility_id+"' && year='"+year+"' && month='"+month+"'";
          conn.rs=conn.st.executeQuery(getNames);
          if(conn.rs.next()==true){
          aphia_id=conn.rs.getString(1).split(",");
          moh_id=conn.rs.getString(2).split(",");
          date=conn.rs.getString(3);
          }
           for (String aphia_id1 : aphia_id) {
               String getAPHIA = "SELECT fname,mname,lname FROM aphia_staff WHERE id='" + aphia_id1 + "'";
               conn.rs=conn.st.executeQuery(getAPHIA);
               if(conn.rs.next()==true){
                   aphia_name+=conn.rs.getString(1)+" "+conn.rs.getString(2)+" "+conn.rs.getString(3)+"/";
               }}
           for (String moh_id1 : moh_id) {
               String getMOH = "SELECT fname,mname,lname FROM moh_staff WHERE id='" + moh_id1 + "'";
               conn.rs=conn.st.executeQuery(getMOH);
               if(conn.rs.next()==true){
                   moh_name+=conn.rs.getString(1)+" "+conn.rs.getString(2)+" "+conn.rs.getString(3)+"/";
                 
               }}
           if(aphia_name.length()>0){
           aphia_name=aphia_name.substring(0, aphia_name.length()-1);
           }
            if(moh_name.length()>0){
              moh_name=moh_name.substring(0, moh_name.length()-1);
            }
//           System.out.println("APHIA STAFF..............."+aphia_name);
//           System.out.println("MOH STAFF..............."+moh_name);
//           System.out.println("visit date..............."+date);
           String generalDetails="SELECT county.county_name,district.district_name,health_facility.hf_name,"
                   + "health_facility.facility_level FROM county JOIN district ON county.county_id=district.county_id "
                   + "JOIN health_facility ON district.district_id=health_facility.district_id WHERE hf_id='"+facility_id+"'";
           conn.rs=conn.st.executeQuery(generalDetails);
           if(conn.rs.next()==true){
               county_name=conn.rs.getString(1);
               district_name=conn.rs.getString(2);
               facility_name=conn.rs.getString(3);
               facility_level=conn.rs.getString(4);
           }
           String getMonthName="SELECT month_name FROM month WHERE month_id='"+Integer.parseInt(month)+"'";
           conn.rs=conn.st.executeQuery(getMonthName);
           if(conn.rs.next()==true){
               month_name=conn.rs.getString(1);
           }
           
      rw1cell0x.setCellValue(aphia_name);
      rw1cell1x.setCellValue(moh_name);
      rw1cell2x.setCellValue(date);
      rw1cell3x.setCellValue(facility_level);
      rw1cell4x.setCellValue(month_name);
      rw1cell5x.setCellValue(year);
      rw1cell6x.setCellValue(facility_name);
      rw1cell7x.setCellValue(county_name);
      rw1cell8x.setCellValue(district_name);
           
           
       rw1cell0x.setCellStyle(style_green);
      rw1cell1x.setCellStyle(style_green);
      rw1cell2x.setCellStyle(style_green);
      rw1cell3x.setCellStyle(style_green);
      rw1cell4x.setCellStyle(style_green);
      rw1cell5x.setCellStyle(style_green);
      rw1cell6x.setCellStyle(style_green);
      rw1cell7x.setCellStyle(style_green);
      rw1cell8x.setCellStyle(style_green);   
           
           
           
           
//       GET ALL DATA FOR THIS MONTH............................
           int p=0;
       String getHTC="SELECT recounted_data,reported_711A,reported_731,reported_DHIS,indicator_id FROM htc_site WHERE hf_id='"+facility_id+"' && year='"+year+"' && month='"+month+"'";
         conn.rs=conn.st.executeQuery(getHTC);
         while(conn.rs.next()){
//               System.out.println("here htc......."+facility_id);
         htc_Data1=htc_711A1=htc_7311=htc_DHIS1="";
         htc_Data=htc_711A=htc_731=htc_DHIS=0;
          htc_Data1=conn.rs.getString(1);htc_711A1=conn.rs.getString(2);
          htc_7311=conn.rs.getString(3);htc_DHIS1=conn.rs.getString(4);
           p=(conn.rs.getInt(5)*4)-4;
//      System.out.println(" id  htc :  "+conn.rs.getString(5));          
     HSSFCell rw1cell10=rw2.createCell(9+p);
     HSSFCell rw1cell11=rw2.createCell(10+p);
     HSSFCell rw1cell12=rw2.createCell(11+p);
     HSSFCell rw1cell13=rw2.createCell(12+p);
     if(htc_Data1.equals("")){rw1cell10.setCellValue("");rw1cell10.setCellStyle(styleTOTAL);}
     if(!htc_Data1.equals("")){rw1cell10.setCellValue(Integer.parseInt(htc_Data1));rw1cell10.setCellStyle(style_green);}
     if(htc_711A1.equals("")){rw1cell11.setCellValue("");rw1cell11.setCellStyle(styleTOTAL);}
     if(!htc_711A1.equals("")){rw1cell11.setCellValue(Integer.parseInt(htc_711A1));rw1cell11.setCellStyle(style_green);}
     if(htc_7311.equals("")){rw1cell12.setCellValue("");rw1cell12.setCellStyle(styleTOTAL);}
     if(!htc_7311.equals("")){rw1cell12.setCellValue(Integer.parseInt(htc_7311));rw1cell12.setCellStyle(style_green);}
     if(htc_DHIS1.equals("")){rw1cell13.setCellValue("");rw1cell13.setCellStyle(styleTOTAL);}
     if(!htc_DHIS1.equals("")){rw1cell13.setCellValue(Integer.parseInt(htc_DHIS1));rw1cell13.setCellStyle(style_green);}

         }
         p=0;
       String getPMTC="SELECT recounted_data,reported_711A,reported_731,reported_DHIS,indicator_id FROM pmtct_site WHERE hf_id='"+facility_id+"' && year='"+year+"' && month='"+month+"'";
        conn.rs=conn.st.executeQuery(getPMTC);
         while(conn.rs.next()){
//               System.out.println("here pmtct......."+facility_id);
          pmtct_Data1=pmtct_711A1=pmtct_7311=pmtct_DHIS1="";
         pmtct_Data=pmtct_711A=pmtct_731=pmtct_DHIS=0;
         pmtct_Data1=conn.rs.getString(1);pmtct_711A1=conn.rs.getString(2);
         pmtct_7311=conn.rs.getString(3);pmtct_DHIS1=conn.rs.getString(4);
      p=(conn.rs.getInt(5)*4)-4;
            
     HSSFCell rw1cell10=rw2.createCell(9+p);
     HSSFCell rw1cell11=rw2.createCell(10+p);
     HSSFCell rw1cell12=rw2.createCell(11+p);
     HSSFCell rw1cell13=rw2.createCell(12+p);
     
           if(pmtct_Data1.equals("")){rw1cell10.setCellValue("");rw1cell10.setCellStyle(styleTOTAL);}
     if(!pmtct_Data1.equals("")){rw1cell10.setCellValue(Integer.parseInt(pmtct_Data1));rw1cell10.setCellStyle(style_green);}
     if(pmtct_711A1.equals("")){rw1cell11.setCellValue("");rw1cell11.setCellStyle(styleTOTAL);}
     if(!pmtct_711A1.equals("")){rw1cell11.setCellValue(Integer.parseInt(pmtct_711A1));rw1cell11.setCellStyle(style_green);}
     if(pmtct_7311.equals("")){rw1cell12.setCellValue("");rw1cell12.setCellStyle(styleTOTAL);}
     if(!pmtct_7311.equals("")){rw1cell12.setCellValue(Integer.parseInt(pmtct_7311));rw1cell12.setCellStyle(style_green);}
     if(pmtct_DHIS1.equals("")){rw1cell13.setCellValue("");rw1cell13.setCellStyle(styleTOTAL);}
     if(!pmtct_DHIS1.equals("")){rw1cell13.setCellValue(Integer.parseInt(pmtct_DHIS1));rw1cell13.setCellStyle(style_green);}


         }
       p=0;
       String getCARE="SELECT recounted_data,reported_711A,reported_731,reported_DHIS,indicator_id FROM caretreatment_site WHERE hf_id='"+facility_id+"' && year='"+year+"' && month='"+month+"'";
       conn.rs=conn.st.executeQuery(getCARE);
         while(conn.rs.next()){
//               System.out.println("here care......."+facility_id);
          care_Data1=care_711A1=care_7311=care_DHIS1="";
         care_Data=care_711A=care_731=care_DHIS=0;
          care_Data1=conn.rs.getString(1);care_711A1=conn.rs.getString(2);
         care_7311=conn.rs.getString(3);care_DHIS1=conn.rs.getString(4);
        p=(conn.rs.getInt(5)*4)-4;
                
      HSSFCell rw1cell10=rw2.createCell(9+p);
     HSSFCell rw1cell11=rw2.createCell(10+p);
     HSSFCell rw1cell12=rw2.createCell(11+p);
     HSSFCell rw1cell13=rw2.createCell(12+p);
   
           if(care_Data1.equals("")){rw1cell10.setCellValue("");rw1cell10.setCellStyle(styleTOTAL);}
     if(!care_Data1.equals("")){rw1cell10.setCellValue(Integer.parseInt(care_Data1));rw1cell10.setCellStyle(style_green);}
     if(care_711A1.equals("")){rw1cell11.setCellValue("");rw1cell11.setCellStyle(styleTOTAL);}
     if(!care_711A1.equals("")){rw1cell11.setCellValue(Integer.parseInt(care_711A1));rw1cell11.setCellStyle(style_green);}
     if(care_7311.equals("")){rw1cell12.setCellValue("");rw1cell12.setCellStyle(styleTOTAL);}
     if(!care_7311.equals("")){rw1cell12.setCellValue(Integer.parseInt(care_7311));rw1cell12.setCellStyle(style_green);}
     if(care_DHIS1.equals("")){rw1cell13.setCellValue("");rw1cell13.setCellStyle(styleTOTAL);}
     if(!care_DHIS1.equals("")){rw1cell13.setCellValue(Integer.parseInt(care_DHIS1));rw1cell13.setCellStyle(style_green);}

         } 
           p=0;    
       String getTB ="SELECT recounted_data,reported_711A,reported_731,reported_DHIS,indicator_id FROM tb_site WHERE hf_id='"+facility_id+"' && year='"+year+"' && month='"+month+"'";
         conn.rs=conn.st.executeQuery(getTB);
         while(conn.rs.next()){
//             System.out.println("here tb......."+facility_id);
           tb_Data1=tb_711A1=tb_7311=tb_DHIS1="";
           tb_Data=tb_711A=tb_731=tb_DHIS=0; 
           tb_Data1=conn.rs.getString(1);tb_711A1=conn.rs.getString(2);
           tb_7311=conn.rs.getString(3);tb_DHIS1=conn.rs.getString(4);
            p=(conn.rs.getInt(5)*4)-4;
             
      HSSFCell rw1cell10=rw2.createCell(9+p);
     HSSFCell rw1cell11=rw2.createCell(10+p);
     HSSFCell rw1cell12=rw2.createCell(11+p);
     HSSFCell rw1cell13=rw2.createCell(12+p);
     
     if(tb_Data1.equals("")){rw1cell10.setCellValue("");rw1cell10.setCellStyle(styleTOTAL);}
     if(!tb_Data1.equals("")){rw1cell10.setCellValue(Integer.parseInt(tb_Data1));rw1cell10.setCellStyle(style_green);}
     if(tb_711A1.equals("")){rw1cell11.setCellValue("");rw1cell11.setCellStyle(styleTOTAL);}
     if(!tb_711A1.equals("")){rw1cell11.setCellValue(Integer.parseInt(tb_711A1));rw1cell11.setCellStyle(style_green);}
     if(tb_7311.equals("")){rw1cell12.setCellValue("");rw1cell12.setCellStyle(styleTOTAL);}
     if(!tb_7311.equals("")){rw1cell12.setCellValue(Integer.parseInt(tb_7311));rw1cell12.setCellStyle(style_green);}
     if(tb_DHIS1.equals("")){rw1cell13.setCellValue("");rw1cell13.setCellStyle(styleTOTAL);}
     if(!tb_DHIS1.equals("")){rw1cell13.setCellValue(Integer.parseInt(tb_DHIS1));rw1cell13.setCellStyle(style_green);}


         } 
         
         
         
//      END OF FACIL LOOP.LOOP......................................................
      } 
      
      
      
      
      ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=SITE_CHECKLIST_RAW_DATA_ANALYSIS_PROGRAM_AREA_("+visitYear+"_"+visitMonth+")Created_On_"+created_on+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
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
        Logger.getLogger(ProgramReportVisit.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ProgramReportVisit.class.getName()).log(Level.SEVERE, null, ex);
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
