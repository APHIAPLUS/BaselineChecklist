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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class report_facility extends HttpServlet {
HttpSession session;
String countyName,facilityName,year,currentid,previd,createdOn;
int pos,month,monthPos,currentMonth,currentYear;
double percentegeSubmitted=0,prevCounter=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       dbConn conn = new dbConn();
       session=request.getSession();
       
        year=request.getParameter("year"); 
        
//        year="2015";
 IdGenerator IG = new IdGenerator();
      createdOn= IG.Created_On();
        
      currentMonth=IG.currentMonth();
      currentYear=IG.currentYear();
      if(Integer.parseInt(year)<currentYear){
      currentMonth=12;    
      }
      
        HSSFWorkbook wb=new HSSFWorkbook();
       HSSFSheet shet1=wb.createSheet();

       
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//    ^^^^^^^^^^^^HEADER STYLES^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     HSSFFont font_header=wb.createFont();
    font_header.setFontHeightInPoints((short)10);
//    font_header.setFontName("Arial Black");
//    font.setItalic(true);
   
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
//    font_midheader.setBoldweight((short)03);
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


    shet1.setColumnWidth(0, 2000);
    shet1.setColumnWidth(1, 4000); 
    shet1.setColumnWidth(2, 9000);
    shet1.setColumnWidth(3, 2000);
    shet1.setColumnWidth(4, 2000);
    shet1.setColumnWidth(5, 2000);
    shet1.setColumnWidth(6, 2000);
    shet1.setColumnWidth(7, 2000);
    shet1.setColumnWidth(8, 2000);
    shet1.setColumnWidth(9, 2000);
    shet1.setColumnWidth(10, 2000);
    shet1.setColumnWidth(11, 2000);
    shet1.setColumnWidth(12, 2000);
    shet1.setColumnWidth(13, 2000);
    shet1.setColumnWidth(14, 2000);
    shet1.setColumnWidth(15, 3000);
    shet1.setColumnWidth(16, 4000);
    shet1.setColumnWidth(17, 4000);
    pos=0;
       
            HSSFRow rw0=shet1.createRow(pos);
            rw0.setHeightInPoints(25);
            
            HSSFCell cell_rw0=rw0.createCell(0);
            HSSFCell cell1_rw0=rw0.createCell(1);
            HSSFCell cell2_rw0=rw0.createCell(2);
            HSSFCell cell3_rw0=rw0.createCell(3);
            HSSFCell cell4_rw0=rw0.createCell(4);
            HSSFCell cell5_rw0=rw0.createCell(5);
            HSSFCell cell6_rw0=rw0.createCell(6);
            HSSFCell cell7_rw0=rw0.createCell(7);
            HSSFCell cell8_rw0=rw0.createCell(8);
            HSSFCell cell9_rw0=rw0.createCell(9);
            HSSFCell cell10_rw0=rw0.createCell(10);
            HSSFCell cell11_rw0=rw0.createCell(11);
            HSSFCell cell12_rw0=rw0.createCell(12);
            HSSFCell cell13_rw0=rw0.createCell(13);
            HSSFCell cell14_rw0=rw0.createCell(14);
            HSSFCell cell15_rw0=rw0.createCell(15);
            HSSFCell cell16_rw0=rw0.createCell(16);
            HSSFCell cell17_rw0=rw0.createCell(17);
            
            
        cell_rw0.setCellValue("NO");
        cell1_rw0.setCellValue("COUNTY");
        cell2_rw0.setCellValue("HEALTH FACILITY ( "+year+" ) ");
        cell3_rw0.setCellValue("Jan");
        cell4_rw0.setCellValue("Feb");
        cell5_rw0.setCellValue("Mar");
        cell6_rw0.setCellValue("Apr");
        cell7_rw0.setCellValue("May");
        cell8_rw0.setCellValue("Jun");
        cell9_rw0.setCellValue("Jul");
        cell10_rw0.setCellValue("Aug");
        cell11_rw0.setCellValue("Sept");
        cell12_rw0.setCellValue("Oct");
        cell13_rw0.setCellValue("Nov");
        cell14_rw0.setCellValue("Dec");
        cell15_rw0.setCellValue("Grand total");
        cell16_rw0.setCellValue("% Reports Submitted");
        cell17_rw0.setCellValue("Officer Resp.");
        
        
          cell_rw0.setCellStyle(style_header); 
         cell1_rw0.setCellStyle(style_header);
         cell2_rw0.setCellStyle(style_header); 
         cell3_rw0.setCellStyle(style_header);
         cell4_rw0.setCellStyle(style_header); 
        cell5_rw0.setCellStyle(style_header); 
        cell6_rw0.setCellStyle(style_header); 
        cell7_rw0.setCellStyle(style_header); 
        cell8_rw0.setCellStyle(style_header); 
        cell9_rw0.setCellStyle(style_header); 
        cell10_rw0.setCellStyle(style_header); 
        cell11_rw0.setCellStyle(style_header); 
        cell12_rw0.setCellStyle(style_header); 
        cell13_rw0.setCellStyle(style_header); 
        cell14_rw0.setCellStyle(style_header); 
        cell15_rw0.setCellStyle(style_header); 
        cell16_rw0.setCellStyle(style_header); 
        cell17_rw0.setCellStyle(style_header); 

        
         
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
    stylex.setFillForegroundColor(HSSFColor.WHITE.index);
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

 
 previd="";
        
        String getData=" SELECT county.county_name,health_facility.hf_name,month,marked_checklist.hf_id "
        + "FROM health_facility LEFT  JOIN marked_checklist ON  health_facility.hf_id=marked_checklist.hf_id "
        + "LEFT JOIN district ON health_facility.district_id=district.district_id "
        + "LEFT JOIN county ON county.county_id=district.county_id "
        + "WHERE marked_checklist.year='"+year+"' ORDER BY county.county_name,health_facility.hf_name,month" ;
        conn.rs=conn.st.executeQuery(getData);
        while(conn.rs.next()==true){
       countyName=conn.rs.getString(1);
       facilityName=conn.rs.getString(2);
       month=conn.rs.getInt(3);
       currentid=conn.rs.getString(4);
       percentegeSubmitted=0.00;
       
       monthPos=month+2;
       if(previd.equals(currentid)){
//           getCurentRow
        HSSFRow rw1=shet1.getRow(pos);
        
     HSSFCell cell_rwX=rw1.createCell(monthPos); 
     cell_rwX.setCellValue(1);
     cell_rwX.setCellStyle(stylex); 
      prevCounter++;
       }
       else{
         if(pos>0){
//         output totals=================================
     percentegeSubmitted=((100*prevCounter)/currentMonth);
     DecimalFormat df = new DecimalFormat("####0.00");
percentegeSubmitted= Double.valueOf(df.format(percentegeSubmitted));
System.out.println("perc : "+percentegeSubmitted);
     HSSFRow rw1=shet1.getRow(pos);
     HSSFCell cell_rwX=rw1.createCell(15); 
     cell_rwX.setCellValue(prevCounter);
     cell_rwX.setCellStyle(stylex);  
     
     rw1=shet1.getRow(pos);
     cell_rwX=rw1.createCell(16); 
     cell_rwX.setCellValue(percentegeSubmitted+"%");
     cell_rwX.setCellStyle(stylex);  
         
         }  
//         else{     
//           insert a new row
      pos++;
      prevCounter=1;
     HSSFRow rw1=shet1.createRow(pos);
     rw0.setHeightInPoints(25);
     
     for(int j=3;j<=17;j++){
     HSSFCell cell_rwBORDER=rw1.createCell(j); 
    cell_rwBORDER.setCellStyle(stylex);      
     }
     
     HSSFCell cell_rw1=rw1.createCell(0); 
     cell_rw1.setCellValue(pos);
     cell_rw1.setCellStyle(stylex); 
     
     HSSFCell cell_rw2=rw1.createCell(1); 
     cell_rw2.setCellValue(countyName);
     cell_rw2.setCellStyle(stylex); 
     
     HSSFCell cell_rw3=rw1.createCell(2); 
     cell_rw3.setCellValue(facilityName);
     cell_rw3.setCellStyle(stylex); 
     
     
     HSSFCell cell_rwX=rw1.createCell(monthPos); 
     cell_rwX.setCellValue(1);
     cell_rwX.setCellStyle(stylex); 
     
     }
    
       if(pos>0){
//         output totals=================================
         percentegeSubmitted=((100*prevCounter)/currentMonth);
     DecimalFormat df = new DecimalFormat("####0.00");
percentegeSubmitted= Double.valueOf(df.format(percentegeSubmitted));

     HSSFRow rw1=shet1.getRow(pos);
     HSSFCell cell_rwX=rw1.createCell(15); 
     cell_rwX.setCellValue(prevCounter);
     cell_rwX.setCellStyle(stylex);  
     
     rw1=shet1.getRow(pos);
     cell_rwX=rw1.createCell(16); 
     cell_rwX.setCellValue(percentegeSubmitted+"%");
     cell_rwX.setCellStyle(stylex);  
     
         }   
       
//       }  
       System.out.println("county : "+countyName+" hf : "+facilityName+" month : "+month);
       previd=currentid;
       
        }
       
      
      ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=me_performance_for_year_("+year+")_Created_On_"+createdOn+".xls");
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
        Logger.getLogger(report_facility.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(report_facility.class.getName()).log(Level.SEVERE, null, ex);
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
