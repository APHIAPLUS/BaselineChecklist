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
public class tools_DataPopUp extends HttpServlet {
HttpSession session;
String hf_id,hf_name,all_details,year,month_name,fmonth;
int month,counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         dbConn conn = new dbConn();
         
          hf_name="No Facility";
            month=counter=0;
            all_details=" <table style=\"width: 100%\" class=\"table_style\">";
            all_details+=" <col width=\"600px\">";
            all_details+="<col width=\"200px\">";
            all_details+=" <col width=\"200px\">";
            all_details+=" <col width=\"100px\">";
            all_details+=" <tr><th>Health Facility</th> <th>Year</th> <th>Month</th> <th>Status</th></tr>";
            
         hf_id=request.getParameter("hf_id");
        
         String get_name="SELECT hf_name FROM health_facility WHERE hf_id='"+hf_id+"'";
            conn.rs=conn.st.executeQuery(get_name);
            if(conn.rs.next()==true){
             hf_name=conn.rs.getString(1);   
            }
            String get_marked="SELECT year,month FROM me_toolsvalue WHERE hf_id='"+hf_id+"'&& tool_id='1' ORDER BY year,month";
            conn.rs=conn.st.executeQuery(get_marked);
            while(conn.rs.next()){
                counter++;
                year=conn.rs.getString(1);
                month=conn.rs.getInt(2);
                fmonth=conn.rs.getString(2);
                
               String get_monthname="SELECT month_name from month WHERE month_id='"+month+"'";
            conn.rs1=conn.st1.executeQuery(get_monthname);
            if(conn.rs1.next()==true){
                month_name=conn.rs1.getString(1);
            }
//            for (int i=0;i<=1;i++){
//             Add all this to the table rows
           all_details+=" <tr><td>"+hf_name+"</td> <td>"+year+"</td> <td>"+month_name+"</td> <td><a class=\"linkstyle1\" href=\"edit_toolsvalue?hf_id="+hf_id+"&&year="+year+"&&month="+fmonth+"\">Edit</a></td></tr>";
          System.out.println("THE MONTH IS  :  "+fmonth);  
//            }
            }
            all_details+="</table>";
            if(counter==0){
                all_details="No Marked Data";
            }
            
            
            
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>" +all_details+ "</h3>");
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
            Logger.getLogger(tools_DataPopUp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(tools_DataPopUp.class.getName()).log(Level.SEVERE, null, ex);
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
