/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loaders;

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
 * @author Nyabuto Geofrey
 */
public class setSessions extends HttpServlet {
HttpSession session;
String year,month,faciliID,mohStaff,aphiaStaff,dateOfAssessment,name_moh,name_aphia,district="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session=request.getSession();
        
           year=month=faciliID=mohStaff=aphiaStaff=dateOfAssessment=name_moh=name_aphia="";
//           System.out.println("returned{{{{{{}}}}}}}}}}}}}}}}}}}}}{{{{{{{{{{{{{{}}}}}}}}}}}}}");
           if(request.getParameter("year")!=null && !request.getParameter("year").equals("")){year=request.getParameter("year");}
           if(request.getParameter("month")!=null && !request.getParameter("month").equals("")){month=request.getParameter("month");}
           if(request.getParameter("hf_id")!=null && !request.getParameter("hf_id").equals("")){faciliID=request.getParameter("hf_id");}
           if(request.getParameter("moh")!=null){mohStaff=request.getParameter("moh");}
           if(request.getParameter("aphia")!=null && !request.getParameter("aphia").equals("")){aphiaStaff=request.getParameter("aphia");}
           if(request.getParameter("date")!=null && !request.getParameter("date").equals("")){dateOfAssessment=request.getParameter("date");}
           if(request.getParameter("district")!=null && !request.getParameter("district").equals("")){district=request.getParameter("district");}
           
//            System.out.println("year  "+year);
//            System.out.println("month   "+month);
//            System.out.println("hf  "+faciliID);
//            System.out.println("moh "+mohStaff);
//            System.out.println("aphia "+aphiaStaff);
//            System.out.println("date "+dateOfAssessment);
if(!mohStaff.equals("")){
    String mohids[]=mohStaff.split(",");
             for(String moh_staff1:mohids){
          
    String  get_moh="SELECT * FROM moh_staff WHERE id='"+moh_staff1+"'";
   conn.rs=conn.st.executeQuery(get_moh);
   while(conn.rs.next()){
      name_moh+=conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("lname")+","; 
  }
        }
             System.out.println("moh session : "+mohStaff);
}
else{
 name_moh=" NO MOH Staff";   
}
if(!aphiaStaff.equals("")){
    
   String  get_aphia="SELECT * FROM aphia_staff WHERE id='"+aphiaStaff+"'";
   conn.rs=conn.st.executeQuery(get_aphia);
   if(conn.rs.next()==true){
      name_aphia=conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("lname"); 
   } 
   session.setAttribute("aphia_staff_name", name_aphia);  
}
           
           if(!year.equals("")){session.setAttribute("assessYear", year);}
           if(!month.equals("")){session.setAttribute("assessMonth", month);}
           if(!faciliID.equals("")){session.setAttribute("hf_id", faciliID);}
           if(!district.equals("")){session.setAttribute("dist_id", district);}
           session.setAttribute("moh_staff", mohStaff);
           if(!aphiaStaff.equals("")){session.setAttribute("aphia_staff", aphiaStaff);}
           if(!dateOfAssessment.equals("")){session.setAttribute("date", dateOfAssessment);}
           else{session.setAttribute("date", "");}
           if(!name_aphia.equals("")){ session.setAttribute("aphia_staff_name", name_aphia);}
           else{session.setAttribute("aphia_staff_name", "");}
           if(!name_moh.equals("")){session.setAttribute("moh_staff_name", name_moh);}
           else{session.setAttribute("moh_staff_name", "");}
//         System.out.println("returned{{{{{{}}}}}}}}}}}}}}}}}}}}}{{{{{{{{{{{{{{}}}}}}}}}}}}}");  
            System.out.println("session moh 2 :   "+session.getAttribute("moh_staff"));
            session.removeAttribute("hasRefreshed");

 
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
        Logger.getLogger(setSessions.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(setSessions.class.getName()).log(Level.SEVERE, null, ex);
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
