/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
 
import database.AddTrails;
import java.io.File;
import java.io.IOException;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import database.dbConn;
import database.dbConn2;
import java.util.Arrays;
 

@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB

public class FileUploadServlet extends HttpServlet {
 String full_path="",userid;
 String fileName="";
 String created_db="";
 String master_db, temp_db,found_folder,path;
 String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
 HttpSession session;
 String dbname="",dbuser="",dbpassword="";
 String localhost="";
String[] localhostsplit;
  private static final long serialVersionUID = 205242440643911308L;
	 // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "root";
   String PASS = "";
   String  executeCmd="";  

    /**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
         String[] executeCmd1=null;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        dbpassword="";
               dbConn conn = new dbConn();
               
//               GET CREDENTIALS FOR THE TEMPORARY DATABASE.
            if (conn.dbsetup[3] != null) {
                dbpassword = conn.dbsetup[3];
                System.out.println("Password::"+dbpassword);
}
            
                      if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit = localhost.split(":");

            }
            DB_URL = "jdbc:mysql://"+conn.dbsetup[0]+"/";
 dbname="checklist_temp";
 dbuser="root";  
PASS=dbpassword;
//dbpassword="";
System.out.println("pass  :  "+dbpassword);

    userid=session.getAttribute("userid").toString();
   AddTrails trails = new AddTrails();
       
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
        try {      
           created_db=CreateDB();
           System.out.println("created db temp"); 
        } catch (SQLException ex) {
            Logger.getLogger(FileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("CREATED DB IS   :  "+created_db);
        System.out.println("FULL PATH IS  :  "+full_path); 
        temp_db=created_db;    
        System.out.println("temporary db  :  "+temp_db);
       String executer_path="";
    
       //        CREATE AN OBJECT OF MERGER CLASS.
        Merger merger = new Merger();
       
        for (int i=0;i<myalphabet.length;i++){
                    System.out.println("at position  :  "+myalphabet[i]);
              String current_drive=myalphabet[i];
              File f =  new File(current_drive+":\\wamp\\mysql\\bin\\");
              File f1 = new File(current_drive+":\\wamp\\bin\\mysql\\mysql5.6.17\\bin");
              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
            //  File f3 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.6\\bin");
             

        
  
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             
 
              
             if (f.exists() && f.isDirectory()){
  executer_path =current_drive+":\\wamp\\mysql\\bin\\mysql --user=" + dbuser+ "--password=" + dbpassword+ " " + dbname+ "-e source "+full_path;
     System.out.println("executer path    :  "+executer_path); 
executeCmd1 = new String[]{current_drive+":\\wamp\\mysql\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  
System.out.print(Arrays.toString(executeCmd1));
found_folder="it is old wamp";
break;
}
  
                          if (f1.exists() && f1.isDirectory()){
executeCmd1 = new String[]{current_drive+":\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  
// executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname, "-e", " source "+dbpath};  

found_folder="it is new wamp";
//System.out.println(executeCmd1);
System.out.print(Arrays.toString(executeCmd1));
break;
}
             
              if (f2.exists() && f2.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  


found_folder="it is workbench 5.5";
System.out.print(Arrays.toString(executeCmd1));
break;
}
              
 
 
              

              
             
}      
             
             
             
             
System.out.println(found_folder);
System.out.println("Execute command ");

Process runtimeProcess;
        try {
 System.out.println("\ntrying to import the data.");

 //if the selected database is biometric_master_db,do not import a database since 
 
 
runtimeProcess = Runtime.getRuntime().exec(executeCmd1);

System.out.println("process started"+runtimeProcess);
             int processComplete = runtimeProcess.waitFor();
            System.out.println("at 1 is "+processComplete);
            if (processComplete == 0) {
//            READ THE SENT DATA AND MERGE IT TO THE MASTER DATABASE.
                int found_tables=merger.CheckTables();
              if(found_tables==14){
                  
                int aphia_staff= merger.aphia_staff();
                System.out.println("MERGED APHIA STAFF  "+aphia_staff);
                int art_tools=merger.art_tools();
                System.out.println("MERGED art tools  "+art_tools);
                int caretreatment_site=merger.caretreatment_site();
                System.out.println("MERGED care tratment site  "+caretreatment_site);
                int health_facility=merger.health_facility();
                System.out.println("MERGED health facility  "+health_facility);
                int htc_site=merger.htc_site();
                System.out.println("MERGED htc site  "+htc_site);
                int htc_tools=merger.htc_tools();
                System.out.println("MERGED htc tools  "+htc_tools);
                int marked_checklist=merger.marked_checklist();
                System.out.println("MERGED marked checklist "+marked_checklist);
                int me_toolsvalue=merger.me_toolsvalue();
                System.out.println("MERGED tools value  "+me_toolsvalue);
                int moh_staff=merger.moh_staff();
                System.out.println("MERGED moh staff  "+moh_staff);
                int pmtct_site=merger.pmtct_site();
                System.out.println("MERGED pmtct site  "+pmtct_site);
                int pmtct_tools=merger.pmtct_tools();
                System.out.println("MERGED pmtct tools  "+pmtct_tools);
                int tb_site=merger.tb_site();
                System.out.println("MERGED tb site  "+tb_site);
                int tb_tools=merger.tb_tools();
                System.out.println("MERGED tb tools  "+tb_tools);
                int users=merger.users();
                System.out.println("MERGED users  "+users);
                
                System.out.println("Import completed successfully");
           
                session.setAttribute("datasend1", "<font color=\"green\">Data has been imported successfully</font>");
                session.setAttribute("saved_success", "success"); 
                
                 trails.addTrails(userid, "Merged Data : with file name : "+fileName+"");
                }
                else{
                  session.setAttribute("saved_success", "fail");   
                 session.setAttribute("datasend1", "<font color=\"red\">Failed To Import. Choose The Correct Database File.</font>");   
                }                 
//               DROP TEMPORARY DATABASE.
                DropDB();
             
             } else {
                 session.setAttribute("saved_success", "fail"); 
                System.out.println("Could not import data");
	session.setAttribute("datasend1", "<font color=\"red\">Data Not Imported.</font>");
            }
            response.sendRedirect("MergeData.jsp");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
     }
//        request.setAttribute("message", "File uploaded successfully!");
//        getServletContext().getRequestDispatcher("/response.jsp").forward(
//                request, response);
     catch (InterruptedException ex) {
     }
//        request.setAttribute("message", "File uploaded successfully!");
//        getServletContext().getRequestDispatcher("/response.jsp").forward(
//                request, response);
     catch (SQLException ex) {
     }
//        request.setAttribute("message", "File uploaded successfully!");
//        getServletContext().getRequestDispatcher("/response.jsp").forward(
//                request, response);
     
        
//        request.setAttribute("message", "File uploaded successfully!");
//        getServletContext().getRequestDispatcher("/response.jsp").forward(
//                request, response);
        
    }
 
    /**
     * Utility method to get fil+ name from HTTP header content-disposition
     */
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
    public String CreateDB() throws SQLException, ClassNotFoundException{
        Connection conn = null;
   Statement stmt = null;
   String checklist_temp="checklist_temp";
//STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();
     
      String sql_temp = "CREATE DATABASE IF NOT EXISTS "+checklist_temp+"";
      stmt.executeUpdate(sql_temp);
      System.out.println("Database created successfully...");
      
      
      return checklist_temp;
}
    
     public void DropDB() throws SQLException, ClassNotFoundException{
        Connection conn = null;
   Statement stmt = null;
   String checklist_temp="checklist_temp";
//STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Dropping Database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      String drop_temp="DROP DATABASE "+checklist_temp+"";    
      stmt.executeUpdate(drop_temp); 
      System.out.println("Database Droped successfully...");
      
}
    
}
