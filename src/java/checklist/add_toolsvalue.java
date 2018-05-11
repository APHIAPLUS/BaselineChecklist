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
public class add_toolsvalue extends HttpServlet{
HttpSession session;
String available,in_use,filled_completely,correctly_used,date,month,year,timestamp,specification_id;
String table_id,id,found,hf_id;
String tool_id,av,use,filled,used,tool_found,id_t,time_t;
String nextpage="",datekey,userid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
   dbConn conn = new dbConn();
   session=request.getSession();
   AddTrails trails = new AddTrails();
   
   if(session.getAttribute("date")!=null){
       year=request.getParameter("year");
     month=request.getParameter("month");
     int mnth=Integer.parseInt(month);
        if(mnth<10){
            month="0"+mnth;
        }
        nextpage="tools_checklist.jsp";
       tool_found="";
       String dates [] = session.getAttribute("date").toString().split("/");
datekey=dates[2]+""+dates[1]+""+dates[0];
              if(session.getAttribute("counter_tools")==null){
         session.setAttribute("counter_tools", 1);  
       }
       else if(session.getAttribute("counter_tools").toString().equals("1")) {
        session.setAttribute("counter_tools", 2);   
      }
       else if(session.getAttribute("counter_tools").toString().equals("2")) {
       nextpage="entry_type.jsp"; 
        session.setAttribute("counter_tools", 3);   
      }
     
       
     userid=session.getAttribute("userid").toString();   
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
       id_t=idg.current_id();
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
       if(tool_found.equals("")){
//           INSERT AS NEW ENTRY
           String insert_htc="INSERT INTO me_toolsvalue(id,tool_id,hf_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
                   + " VALUES ('"+id_t+"','"+tool_id+"','"+hf_id+"','"+av+"','"+use+"','"+filled+"','"+used+"','"+year+"','"+month+"','"+date+"','"+time_t+"','"+datekey+"')";
           conn.st.executeUpdate(insert_htc);
           if(k==1){
           trails.addTrails(userid, "added tools checklist data for health facility: "+hf_id+" data year : "+year+"  and data month : "+month+" : tool od : "+tool_id);   
           }
       }
   
   }
   
   
   
   for(int i=1;i<=29;i++){
       found="";
          IdGenerator IG = new IdGenerator();
       specification_id=request.getParameter("specification"+i);
       available=request.getParameter("available"+i);
       in_use=request.getParameter("in_use"+i);
       filled_completely=request.getParameter("filled_completely"+i);
       correctly_used=request.getParameter("correctly_used"+i);
       timestamp=IG.date_key();
       id=IG.current_id();
       
//       IF IT IS HTC TOOL.......................
       if(i>0 && i<=2){
//         CHECK AND INSERT THE HTC TOOLS
           String check_htc="SELECT id FROM htc_tools WHERE hf_id='"+hf_id+"' && specification_id='"+specification_id+"' && year='"+year+"' && month='"+month+"'";
           conn.rs=conn.st.executeQuery(check_htc);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_htc="UPDATE htc_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_htc);
       }
       if(found.equals("")){
//           INSERT AS NEW ENTRY
           String insert_htc="INSERT INTO htc_tools(id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
                   + " VALUES ('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
           conn.st.executeUpdate(insert_htc);
       }
       }
       
       //       IF IT IS PMTCT TOOL.......................
       if(i>2 && i<=11){
//         CHECK AND INSERT THE HTC TOOLS
           String check_pmtct="SELECT id FROM pmtct_tools WHERE hf_id='"+hf_id+"' && specification_id='"+specification_id+"' && year='"+year+"' && month='"+month+"'";
           conn.rs=conn.st.executeQuery(check_pmtct);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_pmtct="UPDATE pmtct_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_pmtct);
       }
       if(found.equals("")){
//           INSERT AS NEW ENTRY
           String insert_pmtct="INSERT INTO pmtct_tools(id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
                   + " VALUES ('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
           conn.st.executeUpdate(insert_pmtct);
       }
       }
       
       //       IF IT IS ART TOOL.......................
       if(i>=12 && i<=26){
//         CHECK AND INSERT THE HTC TOOLS
           String check_art="SELECT id FROM art_tools WHERE hf_id='"+hf_id+"' && specification_id='"+specification_id+"' && year='"+year+"' && month='"+month+"'";
           conn.rs=conn.st.executeQuery(check_art);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_art="UPDATE art_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_art);
       session.setAttribute("tools_added", "<font color=\"blue\">Tools Values Updated Successfully.</font>");
           }
       if(found.equals("")){
//           INSERT AS NEW ENTRY
           String insert_art="INSERT INTO art_tools(id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
                   + " VALUES ('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
           conn.st.executeUpdate(insert_art);
     session.setAttribute("tools_added", "<font color=\"green\">Tools Values Added Successfully.</font>");
       
       }
       }
  
              //       IF IT IS TB TOOL.......................
       if(i>=27 && i<=29){
//         CHECK AND INSERT THE HTC TOOLS
           String check_tb="SELECT id FROM tb_tools WHERE hf_id='"+hf_id+"' && specification_id='"+specification_id+"' && year='"+year+"' && month='"+month+"'";
           conn.rs=conn.st.executeQuery(check_tb);
           if(conn.rs.next()==true){
           found=conn.rs.getString(1);    
           }
           if(!found.equals("")){
//           IF DATA EXIST UPDATE IT.
           String update_tb="UPDATE tb_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+found+"'";
          conn.st.executeUpdate(update_tb);
       }
       if(found.equals("")){
//           INSERT AS NEW ENTRY
           String insert_tb="INSERT INTO tb_tools(id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
                   + " VALUES ('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
           conn.st.executeUpdate(insert_tb);
       }
       }
       
   }  
   
   }
   else{
       session.setAttribute("checklist_added", "NO SESSION");
         session.setAttribute("tools_added", "<font color=\"red\" >Failed To Add Tools Value. Try Again.</font>");
   }
//   response.sendRedirect("entry_type.jsp"); 
   if(session.getAttribute("counter_tools").toString().equals("3")){
         session.removeAttribute("counter_tools");
       session.removeAttribute("tools_added");  
     }
     if(conn.rs!=null){conn.rs.close();}
     if(conn.st!=null){conn.st.close();}
     
   response.sendRedirect("all_tools");
        
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
            Logger.getLogger(add_toolsvalue.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_toolsvalue.class.getName()).log(Level.SEVERE, null, ex);
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
