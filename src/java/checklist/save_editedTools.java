/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.AddTrails;
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
public class save_editedTools extends HttpServlet {
HttpSession session;
String available,in_use,filled_completely,correctly_used,date,month,year,timestamp,specification_id;
String table_id,id,found,hf_id;
String tool_id,av,use,filled,used,tool_found,id_t,time_t,userid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
   dbConn conn = new dbConn();
   session=request.getSession();
   AddTrails trails = new AddTrails();
   
   if(session.getAttribute("date")!=null){
       
       tool_found="";
//       String dates [] = session.getAttribute("date").toString().split("/");
        userid=session.getAttribute("userid").toString();
       year=session.getAttribute("year").toString();
       month=session.getAttribute("month").toString();
   date=session.getAttribute("date").toString();
   hf_id=session.getAttribute("hf_id").toString();
//   ADD TOOLS TOTALS
   for(int k=1;k<=4;k++){
       tool_id=av=use=filled=used=tool_found=id_t=time_t="";
       IdGenerator idg = new IdGenerator();
       tool_id=""+k;
       av=request.getParameter("av"+k);
       use=request.getParameter("use"+k);
       filled=request.getParameter("filled"+k);
       used=request.getParameter("used"+k);
//       id_t=idg.current_id();
       time_t=idg.date_key();
       //         CHECK AND INSERT THE  M&E  TOOLS
      String check_htc="SELECT id FROM me_toolsvalue WHERE hf_id='"+hf_id+"' && tool_id='"+tool_id+"' && year='"+year+"' && month='"+month+"'";
           conn.rs=conn.st.executeQuery(check_htc);
           if(conn.rs.next()==true){
           tool_found=conn.rs.getString(1);    
           }
           if(!tool_found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_htc="UPDATE me_toolsvalue SET available='"+av+"',in_use='"+use+"',filled_completely='"+filled+"',correctly_used='"+used+"',timestamp='"+time_t+"' WHERE id='"+tool_found+"'";
          conn.st.executeUpdate(update_htc);
     if(k==1){
          trails.addTrails(userid, "edited tools checklist data for health facility: "+hf_id+" data year : "+year+"  and data month : "+month+" : tool od : "+tool_id);   
      }
           }
   
   }
   
   
   
   for(int i=1;i<=29;i++){
       found="";
          IdGenerator IG = new IdGenerator();
       id=request.getParameter("specification"+i);
       available=request.getParameter("available"+i);
       in_use=request.getParameter("in_use"+i);
       filled_completely=request.getParameter("filled_completely"+i);
       correctly_used=request.getParameter("correctly_used"+i);
       timestamp=IG.date_key();
       
//       IF IT IS HTC TOOL.......................
       if(i>0 && i<=2){
//         CHECK AND INSERT THE HTC TOOLS
           String check_htc="SELECT id FROM htc_tools WHERE id='"+id+"'";
           conn.rs=conn.st.executeQuery(check_htc);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_htc="UPDATE htc_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_htc);
       }
       }
       
       //       IF IT IS PMTCT TOOL.......................
       if(i>2 && i<=11){
//         CHECK AND INSERT THE HTC TOOLS
           String check_pmtct="SELECT id FROM pmtct_tools WHERE id='"+id+"'";
           conn.rs=conn.st.executeQuery(check_pmtct);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_pmtct="UPDATE pmtct_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_pmtct);
       }
       }
       
       //       IF IT IS ART TOOL.......................
       if(i>=12 && i<=26){
//         CHECK AND INSERT THE HTC TOOLS
           String check_art="SELECT id FROM art_tools WHERE id='"+id+"'";
           conn.rs=conn.st.executeQuery(check_art);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_art="UPDATE art_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_art);
       }
       }
  
              //       IF IT IS TB TOOL.......................
       if(i>=27 && i<=29){
//         CHECK AND INSERT THE HTC TOOLS
           String check_tb="SELECT id FROM tb_tools WHERE id='"+id+"'";
           conn.rs=conn.st.executeQuery(check_tb);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_tb="UPDATE tb_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_tb);
       }
       }
       
   }  
   
   }
   else{
       session.setAttribute("checklist_added", "NO SESSION");
   }
   session.setAttribute("saved_success", "<font color=\"blue\">Tools Checklist Details Edited Successfully.</font>");
   response.sendRedirect("edit_type.jsp");     
        
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
            Logger.getLogger(save_editedTools.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_editedTools.class.getName()).log(Level.SEVERE, null, ex);
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
