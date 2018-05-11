/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

import checklist.IdGenerator;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)  

public class ExcelUpload extends HttpServlet {
   String county_name,county_id, district_name,district_id,hf_name,hf_id;
String full_path="";
 String fileName="";
   int checker_dist,checker_hf,mflcode;
   File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "excels";
  String nextpage="";
  int a,b;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      
        dbConn conn=new dbConn();
        
        String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
        if(!fileName.endsWith(".xls")){
         nextpage="upload_facilities.jsp"; 
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose the correct File.</font>");   
        }
        else{
            
        
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 a=b=0;
 
  FileInputStream fileInputStream = new FileInputStream(full_path);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");
			Iterator rowIterator = worksheet.iterator();
                        
                        int i=1,y=0;
			while(rowIterator.hasNext()) {
			HSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                nextpage="upload_facilities.jsp";
                         break;
                        }
                         HSSFCell cell1 = rowi.getCell((short) 0);
			county_name = cell1.getStringCellValue();
			HSSFCell cell2 = rowi.getCell((short) 1);
			district_name = cell2.getStringCellValue();
                        HSSFCell cell3 = rowi.getCell((short) 2);
			hf_name = cell3.getStringCellValue();
                        HSSFCell cell4 = rowi.getCell((short) 3);
			mflcode = (int) cell4.getNumericCellValue();

                   district_name = district_name.toUpperCase();
                  IdGenerator IG = new IdGenerator();
                 hf_id=IG.sec+""+IG.micro;
                 
                   String get_id="SELECT district_id FROM district WHERE district_name=?";
                 conn.prest=conn.conn.prepareStatement(get_id);
                   conn.prest.setString(1,district_name);
                 conn.rs=conn.prest.executeQuery();
                   if(conn.rs.next()==true){
                       checker_dist=conn.rs.getInt(1);
                   }
                    if(checker_dist>0) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................
                        
                        String check_hf="SELECT COUNT(hf_id) FROM health_facility WHERE hf_name=? && district_id=?";
                         conn.prest=conn.conn.prepareStatement(check_hf);
                   conn.prest.setString(1,hf_name);
                   conn.prest.setInt(2,checker_dist);
                 conn.rs=conn.prest.executeQuery();
                        if(conn.rs.next()==true){
                           checker_hf=conn.rs.getInt(1); 
                        }
                      if(checker_hf==0){
//                       ADD THE NEW HEALTH FACILITY TO THE SYSTEM.........................
                          String inserter="INSERT INTO health_facility (hf_id,hf_name,mflcode,district_id) "
                                  + " VALUES(?,?,?,?)";
                           conn.prest=conn.conn.prepareStatement(inserter);
                   conn.prest.setString(1,hf_id);
                   conn.prest.setString(2,hf_name);
                   conn.prest.setInt(3,mflcode);
                   conn.prest.setInt(4,checker_dist);
                   conn.prest.executeUpdate();
                   a++;
                          System.out.println(""+i+"    added  :   "+hf_name);
                     }  
                    else{
                        System.out.println("HEALTH FACILITY AT POSITION :  "+i+" AL READY ADDED  :   "+hf_name);
                        b++;
                    }    
                    } 
                    else{
                        System.out.println("MISSING DISTRICT AT POSITION  :  "+i+"  for the district   "+district_name);
                    }
                        
                        i++;
                        }
 if(a==0){
  session.setAttribute("upload_success", "<font color=\"black\">"+b+"</font><font color=\"red\"> Health facilities already exist.</font>");   
 }
 if(b==0){
  session.setAttribute("upload_success", "<font color=\"black\">"+a+"</font><font color=\"green\"> Health facilities added Successfully.</font>");   
 }
 if(a>0 && b>0){
 session.setAttribute("upload_success", "<font color=\"black\">"+a+"</font> <font color=\"green\">Health facilities added Successfully<br> <font color=\"black\">"+b+"</font><font color=\"red\"> Health facilities already exist.</font>");
 
 }
        }
 response.sendRedirect(nextpage);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
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


  private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return contentDisp;
    }
}
