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
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_facilityNames extends HttpServlet {
   String county_name,county_id, district_name,district_id,hf_name,hf_id;

   int checker_dist,checker_hf,mflcode;
   File file_source;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
   session=request.getSession();
    dbConn conn=new dbConn();
    
    
        county_name=county_id=district_name=district_id=hf_name=hf_id;
       checker_dist=checker_hf= mflcode=0;
        file_source= new File("C:\\Users\\Geofrey Nyabuto\\Desktop\\hf\\supported_hf.xls");
                       System.out.println(" The file path is: "+file_source); 
                       
                        FileInputStream fileInputStream = new FileInputStream(file_source);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");
			Iterator rowIterator = worksheet.iterator();
                        
                        int i=1,y=0;
			while(rowIterator.hasNext()) {
			HSSFRow rowi = worksheet.getRow(i);
                        
                         HSSFCell cell1 = rowi.getCell((short) 1);
			county_name = cell1.getStringCellValue();
			HSSFCell cell2 = rowi.getCell((short) 2);
			district_name = cell2.getStringCellValue();
                        HSSFCell cell3 = rowi.getCell((short) 3);
			hf_name = cell3.getStringCellValue();
                        HSSFCell cell4 = rowi.getCell((short) 4);
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
                          System.out.println(""+i+"    added  :   "+hf_name);
                      }  
                    else{
                        System.out.println("HEALTH FACILITY AT POSITION :  "+i+" AL READY ADDED  :   "+hf_name);
                    }    
                    } 
                    else{
                        System.out.println("MISSING DISTRICT AT POSITION  :  "+i+"  for the district   "+district_name);
                    }
                        
                        i++;
                        }
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
            Logger.getLogger(load_facilityNames.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_facilityNames.class.getName()).log(Level.SEVERE, null, ex);
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
