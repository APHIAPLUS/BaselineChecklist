/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import checklist.IdGenerator;
import database.dbConn;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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
public class ManualBackUp extends HttpServlet {
HttpSession session;
String lastbackup,today,status,maxid,timestamp,year,month,day,lastdate;
int days,entries;
String start,end;
String path,ExistingPath,lastBackUp,filePath;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
          IdGenerator IG = new IdGenerator();
          today=IG.toDay();days=entries=0;
          maxid=timestamp=year=month=day=lastdate="";status="";
        
          String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
          path=mydrive+":\\APHIAPLUS\\CHECKLIST\\DO_NOT_DELETE\\_\\_\\."; 
          ExistingPath=mydrive+":\\APHIAPLUS\\CHECKLIST\\DO_NOT_DELETE\\_\\_\\LastBackUp.txt";   
          File f = new File(ExistingPath);
           if(f.isFile() && !f.isDirectory()){
           System.out.println("The file exist");
           
           String fpath = mydrive+ ":/APHIAPLUS/CHECKLIST/DO_NOT_DELETE/_/_/./LastBackUp.txt";
  
            FileInputStream fstream = new FileInputStream(fpath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stLine;
            //Read File Line By Line
            int count = 0;
            if((stLine = br.readLine()) != null) {
            lastBackUp=stLine;
          
            lastbackup=lastBackUp.replace("-", "");
            if(!lastBackUp.equals(today)){
//          BACK UP THIS DATA AND UPDATE THE TIMESTAMP-------------------------      
           //      CHEK IF DATA EXISTS===========================================================
       start=lastbackup.replace("-", "")+"0000";
       end=today.replace("-", "")+"0000";
     String checkData="SELECT COUNT(id) FROM visitdates WHERE timestamp>='"+start+"' && timestamp<='"+end+"'";
     System.out.println(checkData);
     conn.rs=conn.st.executeQuery(checkData);
     if(conn.rs.next()==true){
  entries=conn.rs.getInt(1);
  System.out.println("existing entries  :   "+entries);
     }
     if(entries>=1){
 autoBackUp backup = new autoBackUp();
    backup.CreateBackUp();
    System.out.println("manual back up for existing timestamp");
    //    UPDATE LAST BACK UP DATE------------------------------------
   filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out2 = new PrintWriter(outFile);

out2.print(today);
out2.close();
 } catch (IOException e) {}
      }     
        }
        } 
        }
           else{
               System.out.println("File not found");
              new File(path).mkdirs();
             filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out2 = new PrintWriter(outFile);

out2.print(today);
out2.close();

   } catch (IOException e) {}
             
   autoBackUp backup = new autoBackUp();
    backup.CreateBackUp(); 
     System.out.println("manual back up for new timestamp");
           }
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
        Logger.getLogger(ManualBackUp.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(ManualBackUp.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ManualBackUp.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(ManualBackUp.class.getName()).log(Level.SEVERE, null, ex);
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
