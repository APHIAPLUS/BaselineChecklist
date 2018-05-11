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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Geofrey Nyabuto
 */
public class officer_reports extends HttpServlet {
HttpSession session;
 String startPeriod,endPeriod,createdOn;
 int pepfaryear,prevYear,year;
 String county,monthName,officerName;
 ArrayList allStaffs = new ArrayList();
 int pos,pos2,currentMonth,currentYear,months;
 double percentageSubmitted,expectedReports,reports;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
       
          dbConn conn = new dbConn();
          session=request.getSession();
          allStaffs.clear();
          startPeriod=endPeriod="";
          pepfaryear=prevYear=0;
//         pepfaryear=2015;
          
         pepfaryear=Integer.parseInt(request.getParameter("year"));
         prevYear=pepfaryear-1;
         //this pepfar calender format isnot correct since the saved year is for normal calender
         //startPeriod=prevYear+"10";
         //endPeriod=pepfaryear+"09";
         
         startPeriod=pepfaryear+"01";
         endPeriod=pepfaryear+"12";
         
          IdGenerator IG = new IdGenerator();
      createdOn= IG.Created_On();
        
      currentMonth=IG.currentMonth();
      currentYear=IG.currentYear();
      
      if(1==2){
      if(pepfaryear>=currentYear){
      if(currentMonth>9){
          if(pepfaryear==currentYear){
       months=12; 
          }
          else{
       months=currentMonth-9; 
          }
      }
      else{
        if(pepfaryear==currentYear){
       months=currentMonth+3; 
          } 
        else{
      months=currentMonth;    
      }
      }
      }
     else if(pepfaryear<currentYear){
      months=12;    
      }
     else{
         
     }
      
    }//end of disabled comment 2017jan
      
      months=12; 
     expectedReports=months*10;
      
         Path original = Paths.get(getServletContext().getRealPath("/reportCounter.xlsm")); //original file
   Path destination = Paths.get(getServletContext().getRealPath("/reportCounter_1.xlsm")); //new file
   System.out.println("origin :  "+original+" destination    :  "+destination);
try {
       Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
       System.out.println("file copied----------------");
    } catch (IOException x) {
       //catch all for IO problems
        System.out.println("fine not copied");
    }
    
    
        String allpath = getServletContext().getRealPath("/reportCounter_1.xlsm");

    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
  XSSFWorkbook wb;
 OPCPackage pkg = OPCPackage.open(allpath);
 
wb = new XSSFWorkbook(pkg);
      
//        HSSFWorkbook wb=new HSSFWorkbook();
//       HSSFSheet shet1=wb.createSheet();
        

  XSSFSheet shet1=wb.getSheet("sheet0");
  XSSFSheet shet2=wb.createSheet("Reporting Rates");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     XSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
    XSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.LIME.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);
         
   //  HSSFSheet sheet1 = wb.getSheetAt(0);
    shet1.setColumnWidth(0, 6000); 
    shet1.setColumnWidth(1, 6000); 
    shet1.setColumnWidth(2, 6000); 
    shet1.setColumnWidth(3, 6000);
    shet1.setColumnWidth(4, 4000);
    
    shet2.setColumnWidth(0, 6000); 
    shet2.setColumnWidth(1, 6000); 
    shet2.setColumnWidth(2, 6000); 
    shet2.setColumnWidth(3, 6000);
    shet2.setColumnWidth(4, 4000);
    
       XSSFRow rw4=shet1.createRow(0);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    XSSFCell cell0,cell1,cell2,cell3,cell4;
   
    cell0=rw4.createCell(0);
    cell1=rw4.createCell(1);
   cell2=rw4.createCell(2);
   cell3=rw4.createCell(3);
   cell4=rw4.createCell(4);
   
 cell0 .setCellValue("COUNTY NAME");
 cell1.setCellValue("M&E OFFICER");
 cell2.setCellValue("YEAR");
 cell3.setCellValue("MONTH");
 cell4.setCellValue("REPORTS");
 
 cell0 .setCellStyle(stylex); 
 cell1.setCellStyle(stylex);
 cell2.setCellStyle(stylex);
 cell3.setCellStyle(stylex);
 cell4.setCellStyle(stylex);
 
     
 XSSFRow rwLShet2=shet2.createRow(0);
    rwLShet2.setHeightInPoints(45);
    rwLShet2.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    XSSFCell c0,c1,c2,c3,c4,c5;
   
    c0=rwLShet2.createCell(0);
    c1=rwLShet2.createCell(1);
   c2=rwLShet2.createCell(2);
   c3=rwLShet2.createCell(3);
   c4=rwLShet2.createCell(4);
   c5=rwLShet2.createCell(5);
   
 c0 .setCellValue("COUNTY NAME");
 c1.setCellValue("M&E OFFICER");
 c2.setCellValue("PEPFAR YEAR");
 c3.setCellValue("SUBMITTED REPORTS");
 c4.setCellValue("EXPECTED REPORTS");
 c5.setCellValue("% REPORTED");
 
 c0.setCellStyle(stylex); 
 c1.setCellStyle(stylex);
 c2.setCellStyle(stylex);
 c3.setCellStyle(stylex);
 c4.setCellStyle(stylex);
 c5.setCellStyle(stylex);
 
        pos2=1;
 
 String getCumulatives="SELECT SUM(temp.id),temp.countyName,temp.NAME FROM (SELECT COUNT(marked_checklist.id) AS id ,county.county_name as countyName," +
"       CONCAT(aphia_staff.fname,\" \", aphia_staff.mname,\" \", aphia_staff.lname) as NAME," +
"	CONCAT(marked_checklist.year,marked_checklist.month) AS YEARMONTH  " +
"       FROM marked_checklist JOIN health_facility ON marked_checklist.hf_id=health_facility.hf_id " +
"       JOIN district ON health_facility.district_id=district.district_id " +
"       JOIN county ON county.county_id=district.county_id " +
"	LEFT JOIN aphia_staff ON marked_checklist.aphia_staff=aphia_staff.id " +
"       GROUP BY countyName,NAME,YEARMONTH  " +
"	HAVING YEARMONTH BETWEEN "+startPeriod+" AND "+endPeriod+" ) AS temp " +
"       GROUP BY temp.countyName,temp.NAME";

System.out.println(getCumulatives);
 conn.rs=conn.st.executeQuery(getCumulatives);
while(conn.rs.next()){
  reports=conn.rs.getDouble(1);
  county=conn.rs.getString(2);
  officerName=conn.rs.getString(3);
  System.out.println("here "+county+" officer : "+officerName+"   reported : "+reports);
  
  XSSFRow rw4D=shet2.createRow(pos2);
    rw4D.setHeightInPoints(25);
    rw4D.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    XSSFCell cell0D,cell1D,cell2D,cell3D,cell4D,cell5D;
   percentageSubmitted=(float)Math.round(((reports*100)/expectedReports));
  
   cell0D=rw4D.createCell(0);
   cell1D=rw4D.createCell(1);
   cell2D=rw4D.createCell(2);
   cell3D=rw4D.createCell(3);
   cell4D=rw4D.createCell(4);
   cell5D=rw4D.createCell(5);
   
 cell0D .setCellValue(county);
 cell1D.setCellValue(officerName);
 cell2D.setCellValue(pepfaryear);
 cell3D.setCellValue(reports);
 cell4D.setCellValue(expectedReports);
 cell5D.setCellValue(percentageSubmitted);
      
         pos2++;               
}

        pos=1;
    
      
        String getYears="SELECT COUNT(marked_checklist.hf_id),county.county_name,"
        + "aphia_staff.fname,aphia_staff.mname,aphia_staff.lname,marked_checklist.year,month.month_name "
        + "FROM marked_checklist JOIN month ON marked_checklist.month=month.month_id JOIN"
        + " health_facility ON marked_checklist.hf_id=health_facility.hf_id "
        + "JOIN district ON health_facility.district_id=district.district_id "
        + "JOIN county ON county.county_id=district.county_id"
        + " LEFT JOIN aphia_staff ON marked_checklist.aphia_staff=aphia_staff.id "
         + " GROUP BY marked_checklist.year,marked_checklist.month,marked_checklist.aphia_staff "
        + " HAVING CONCAT(marked_checklist.year,marked_checklist.month) BETWEEN "+startPeriod+" AND "+endPeriod+" ";
          conn.rs=conn.st.executeQuery(getYears);
          while(conn.rs.next()){
           reports=conn.rs.getDouble(1);
           county=conn.rs.getString(2);
           officerName=conn.rs.getString(3)+" "+conn.rs.getString(4)+" "+conn.rs.getString(5);
           year=conn.rs.getInt(6);
           monthName=conn.rs.getString(7);
     
    XSSFRow rw4D=shet1.createRow(pos);
    rw4D.setHeightInPoints(25);
    rw4D.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    XSSFCell cell0D,cell1D,cell2D,cell3D,cell4D;
   
   cell0D=rw4D.createCell(0);
   cell1D=rw4D.createCell(1);
   cell2D=rw4D.createCell(2);
   cell3D=rw4D.createCell(3);
   cell4D=rw4D.createCell(4);
   
 cell0D .setCellValue(county);
 cell1D.setCellValue(officerName);
 cell2D.setCellValue(year);
 cell3D.setCellValue(monthName);
 cell4D.setCellValue(reports);
      
         pos++; 
         
          System.out.println("county : "+county+" year : "+year+" month : "+monthName+" reports : "+reports+" officer : "+officerName);
          }
      createdOn= IG.Created_On();
      
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=Submitted_Reports_FOR_"+pepfaryear+"_CREATED_ON_"+createdOn+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();

pkg.close();
          
          
      
//      ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//wb.write(outByteStream);
//byte [] outArray = outByteStream.toByteArray();
//response.setContentType("application/ms-excel");
//response.setContentLength(outArray.length);
//response.setHeader("Expires:", "0"); // eliminates browser caching
//response.setHeader("Content-Disposition", "attachment; filename=reports_On_"+createdOn+".xls");
//OutputStream outStream = response.getOutputStream();
//outStream.write(outArray);
//outStream.flush(); 
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
        Logger.getLogger(officer_reports.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(officer_reports.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(officer_reports.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(officer_reports.class.getName()).log(Level.SEVERE, null, ex);
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
