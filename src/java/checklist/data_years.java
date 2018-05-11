/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geofrey Nyabuto
 */
public class data_years extends HttpServlet {
String correct_years,hf_id,source;
int found=0,year=0;
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            hf_id=request.getParameter("hf_id");
            source=request.getParameter("source");
               //    CURRENT DATE
           dbConn conn = new dbConn();
           System.out.println("hf id  :  "+hf_id+"  source  :  "+source);
           correct_years="<option value=\"\">Choose Assessed Year</option>";
           String get_years="SELECT year FROM years";
           conn.rs= conn.st.executeQuery(get_years);
           while(conn.rs.next()){
               year=conn.rs.getInt(1);
               if(source.equals("site")){
             String get_datad="SELECT COUNT(year) FROM marked_checklist WHERE hf_id='"+hf_id+"' && year='"+year+"' ";
          System.out.println(get_datad);
             conn.rs1=conn.st1.executeQuery(get_datad);
           if(conn.rs1.next()==true){
               found=conn.rs1.getInt(1);
           }
               }
           if(source.equals("tools")){
             String get_datad="SELECT COUNT(year) FROM me_toolsvalue WHERE hf_id='"+hf_id+"' && year='"+year+"' ";
            System.out.println(get_datad);
             conn.rs1=conn.st1.executeQuery(get_datad);
           if(conn.rs1.next()==true){
               found=conn.rs1.getInt(1);
           }
               }
           System.out.println("found   :  "+found);
           
           if(found>0){
                 correct_years+="<option value=\""+year+"\">*"+year+"</option>";
             }
            found=0; 
           }
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet years_loader</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet years_loader at " +correct_years+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
            Logger.getLogger(data_years.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(data_years.class.getName()).log(Level.SEVERE, null, ex);
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
