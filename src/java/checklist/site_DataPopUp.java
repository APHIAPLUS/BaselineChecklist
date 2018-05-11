/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class site_DataPopUp extends HttpServlet {
String hf_id,hf_names,month_name,year,all_months,facility_name,fmonth;
 HttpSession session;  
 int month,counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
            dbConn conn = new dbConn();
            
            facility_name="No Facility";
            month=counter=0;
            all_months=" <table style=\"width: 100%\" class=\"table_style\">";
            all_months+=" <col width=\"600px\">";
            all_months+="<col width=\"200px\">";
            all_months+=" <col width=\"200px\">";
            all_months+=" <col width=\"100px\">";
            all_months+=" <tr><th>Health Facility</th> <th>Year</th> <th>Month</th> <th>Status</th></tr>";
            
            hf_id=request.getParameter("hf_id");
            
            String get_name="SELECT hf_name FROM health_facility WHERE hf_id='"+hf_id+"'";
            conn.rs=conn.st.executeQuery(get_name);
            if(conn.rs.next()==true){
             facility_name=conn.rs.getString(1);   
            }
            
            
            
            String get_data="SELECT year,month FROM marked_checklist WHERE hf_id='"+hf_id+"' ORDER BY year,month";
            conn.rs=conn.st.executeQuery(get_data);
            while(conn.rs.next()){
            year=conn.rs.getString(1);
            month=conn.rs.getInt(2);
            fmonth=conn.rs.getString(2);
            counter++;
            String get_monthname="SELECT month_name from month WHERE month_id='"+month+"'";
            conn.rs1=conn.st1.executeQuery(get_monthname);
            if(conn.rs1.next()==true){
                month_name=conn.rs1.getString(1);
            }
//            for (int i=0;i<=1;i++){
//             Add all this to the table rows
           all_months+=" <tr><td>"+facility_name+"</td> <td>"+year+"</td> <td>"+month_name+"</td> <td><a class=\"linkstyle1\" href=\"edit_sitevalue?hf_id="+hf_id+"&&year="+year+"&&month="+fmonth+"\">Edit</a></td></tr>";
            
//            }
            }
            all_months+="</table>";
            if(counter==0){
                all_months="No Marked Data";
            }
            System.out.println("all data  :  "+all_months);
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>"+all_months+"</h3>");
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
            Logger.getLogger(site_DataPopUp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(site_DataPopUp.class.getName()).log(Level.SEVERE, null, ex);
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
