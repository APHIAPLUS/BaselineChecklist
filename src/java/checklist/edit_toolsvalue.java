/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class edit_toolsvalue extends HttpServlet {
HttpSession session;
 public String id, tool_name,section,tool_id,av,in_use,filled,used; 
 ArrayList editTools = new ArrayList();
 String nextpage="",year="",month="",date="",hf_id="";
 String t1,t2,t3,t4;
int found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    session=request.getSession();
    dbConn conn = new dbConn();
    editTools.clear();
    found=0;
      year=request.getParameter("year");
           if(!year.equals("")){
       month=request.getParameter("month");
         hf_id=request.getParameter("hf_id");
          session.setAttribute("hf_id", hf_id);
         session.setAttribute("month", month);
         session.setAttribute("year", year);
         
     String checker="SELECT COUNT(id) FROM me_toolsvalue WHERE year='"+year+"' && month='"+month+"' && hf_id='"+hf_id+"'"; 
      conn.rs=conn.st.executeQuery(checker);
      if(conn.rs.next()==true){
         found=conn.rs.getInt(1); 
         System.out.println("found value  :   "+found);
      }
     if(found==0){
//         DATA MISSING,, VALUES..................................
         nextpage="all_tools";
     }
     else{

  
//         HTC TOOLS....................
         
        String get_htcTools="SELECT htc_tools.id,htc_tools.specification_id,tools.specification,htc_tools.available,htc_tools.in_use,htc_tools.filled_completely,htc_tools.correctly_used,tools.section FROM "
                + " htc_tools JOIN tools ON htc_tools.specification_id=tools.id WHERE htc_tools.year='"+year+"' && htc_tools.month='"+month+"' && htc_tools.hf_id='"+hf_id+"'";
        conn.rs=conn.st.executeQuery(get_htcTools);
        while(conn.rs.next()){
        id=tool_name=section=tool_id=av=in_use=filled=used=t1=t2=t3=t4="";
            edit_toolBean etbean = new edit_toolBean();
            id=conn.rs.getString(1);
            tool_id=conn.rs.getString(2);
            tool_name=conn.rs.getString(3);
            t1=conn.rs.getString(4);
            t2=conn.rs.getString(5);
            t3=conn.rs.getString(6);
            t4=conn.rs.getString(7);
            section=conn.rs.getString(8);
            String getv="SELECT * FROM selectbox";
            conn.rs1=conn.st1.executeQuery(getv);
            while(conn.rs1.next()){
          if(!conn.rs1.getString("value").equals(t1) && conn.rs1.getString("value").equals("")){ 
            }
          else{
           if(conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+t1+"\" selected>"+t1+"</option>";    
            }  
            if(!conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            } 
            
            if(conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+t2+"\" selected>"+t2+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
             
            if(conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+t3+"\" selected>"+t3+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
              
            if(conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+t4+"\" selected>"+t4+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
            }   
            }
            
            etbean.setAv(av);
            etbean.setFilled(filled);
            etbean.setId(id);
            etbean.setIn_use(in_use);
            etbean.setSection(section);
            etbean.setTool_id(tool_id);
            etbean.setTool_name(tool_name);
            etbean.setUsed(used);
            
            editTools.add(etbean);
        }
//        PMTCT TOOLS.............................
                String get_pmtctTools="SELECT pmtct_tools.id,pmtct_tools.specification_id,tools.specification,pmtct_tools.available,pmtct_tools.in_use,pmtct_tools.filled_completely,pmtct_tools.correctly_used,tools.section FROM "
                + " pmtct_tools JOIN tools ON pmtct_tools.specification_id=tools.id WHERE pmtct_tools.year='"+year+"' && pmtct_tools.month='"+month+"' && pmtct_tools.hf_id='"+hf_id+"'";
        conn.rs=conn.st.executeQuery(get_pmtctTools);
        while(conn.rs.next()){
        id=tool_name=section=tool_id=av=in_use=filled=used=t1=t2=t3=t4="";
            edit_toolBean etbean = new edit_toolBean();
            id=conn.rs.getString(1);
            tool_id=conn.rs.getString(2);
            tool_name=conn.rs.getString(3);
            t1=conn.rs.getString(4);
            t2=conn.rs.getString(5);
            t3=conn.rs.getString(6);
            t4=conn.rs.getString(7);
            section=conn.rs.getString(8);
            String getv="SELECT * FROM selectbox";
            conn.rs1=conn.st1.executeQuery(getv);
          while(conn.rs1.next()){
          if(!conn.rs1.getString("value").equals(t1) && conn.rs1.getString("value").equals("")){ 
            }
          else{
           if(conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+t1+"\" selected>"+t1+"</option>";    
            }  
            if(!conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            } 
            
            if(conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+t2+"\" selected>"+t2+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
             
            if(conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+t3+"\" selected>"+t3+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
              
            if(conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+t4+"\" selected>"+t4+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
            }   
            }
            
            etbean.setAv(av);
            etbean.setFilled(filled);
            etbean.setId(id);
            etbean.setIn_use(in_use);
            etbean.setSection(section);
            etbean.setTool_id(tool_id);
            etbean.setTool_name(tool_name);
            etbean.setUsed(used);
            
            editTools.add(etbean);
        }
        
        //        PMTCT TOOLS.............................
                String get_artTools="SELECT art_tools.id,art_tools.specification_id,tools.specification,art_tools.available,art_tools.in_use,art_tools.filled_completely,art_tools.correctly_used,tools.section FROM "
                + " art_tools JOIN tools ON art_tools.specification_id=tools.id WHERE art_tools.year='"+year+"' && art_tools.month='"+month+"' && art_tools.hf_id='"+hf_id+"'";
        conn.rs=conn.st.executeQuery(get_artTools);
        while(conn.rs.next()){
        id=tool_name=section=tool_id=av=in_use=filled=used=t1=t2=t3=t4="";
            edit_toolBean etbean = new edit_toolBean();
            id=conn.rs.getString(1);
            tool_id=conn.rs.getString(2);
            tool_name=conn.rs.getString(3);
            t1=conn.rs.getString(4);
            t2=conn.rs.getString(5);
            t3=conn.rs.getString(6);
            t4=conn.rs.getString(7);
            section=conn.rs.getString(8);
            String getv="SELECT * FROM selectbox";
            conn.rs1=conn.st1.executeQuery(getv);
              while(conn.rs1.next()){
          if(!conn.rs1.getString("value").equals(t1) && conn.rs1.getString("value").equals("")){ 
            }
          else{
           if(conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+t1+"\" selected>"+t1+"</option>";    
            }  
            if(!conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            } 
            
            if(conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+t2+"\" selected>"+t2+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
             
            if(conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+t3+"\" selected>"+t3+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
              
            if(conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+t4+"\" selected>"+t4+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
            }   
            }
            
            etbean.setAv(av);
            etbean.setFilled(filled);
            etbean.setId(id);
            etbean.setIn_use(in_use);
            etbean.setSection(section);
            etbean.setTool_id(tool_id);
            etbean.setTool_name(tool_name);
            etbean.setUsed(used);
            
            editTools.add(etbean);
        }
        
//        TB TOOLS..........................
        //        PMTCT TOOLS.............................
                String get_tbTools="SELECT tb_tools.id,tb_tools.specification_id,tools.specification,tb_tools.available,tb_tools.in_use,tb_tools.filled_completely,tb_tools.correctly_used,tools.section FROM "
                + " tb_tools JOIN tools ON tb_tools.specification_id=tools.id WHERE tb_tools.year='"+year+"' && tb_tools.month='"+month+"' && tb_tools.hf_id='"+hf_id+"'";
        conn.rs=conn.st.executeQuery(get_tbTools);
        while(conn.rs.next()){
        id=tool_name=section=tool_id=av=in_use=filled=used=t1=t2=t3=t4="";
            edit_toolBean etbean = new edit_toolBean();
            id=conn.rs.getString(1);
            tool_id=conn.rs.getString(2);
            tool_name=conn.rs.getString(3);
            t1=conn.rs.getString(4);
            t2=conn.rs.getString(5);
            t3=conn.rs.getString(6);
            t4=conn.rs.getString(7);
            section=conn.rs.getString(8);
            String getv="SELECT * FROM selectbox";
            conn.rs1=conn.st1.executeQuery(getv);
             while(conn.rs1.next()){
          if(!conn.rs1.getString("value").equals(t1) && conn.rs1.getString("value").equals("")){ 
            }
          else{
           if(conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+t1+"\" selected>"+t1+"</option>";    
            }  
            if(!conn.rs1.getString("value").equals(t1)){
            av+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            } 
            
            if(conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+t2+"\" selected>"+t2+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t2)){
            in_use+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
             
            if(conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+t3+"\" selected>"+t3+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t3)){
            filled+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
              
            if(conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+t4+"\" selected>"+t4+"</option>";    
            }  
           if(!conn.rs1.getString("value").equals(t4)){
            used+="<option value=\""+conn.rs1.getString("value")+"\">"+conn.rs1.getString("value")+"</option>";    
            }
            }   
            }
             etbean.setAv(av);
            etbean.setFilled(filled);
            etbean.setId(id);
            etbean.setIn_use(in_use);
            etbean.setSection(section);
            etbean.setTool_id(tool_id);
            etbean.setTool_name(tool_name);
            etbean.setUsed(used);
            
            editTools.add(etbean);
        }
     }
     String hf_name="";
         String hf_namegetter="SELECT hf_name FROM health_facility WHERE hf_id='"+hf_id+"'";
         conn.rs=conn.st.executeQuery(hf_namegetter);
         while(conn.rs.next()){
             hf_name=conn.rs.getString(1);
         }
         session.setAttribute("hf_name", hf_name);
         String dist="SELECT district.district_name FROM health_facility JOIN district ON health_facility.district_id=district.district_id WHERE health_facility.hf_id='"+hf_id+"'" ;
         conn.rs=conn.st.executeQuery(dist);
         while(conn.rs.next()){
           session.setAttribute("district_name", conn.rs.getString(1));   
         }
     String get_date="SELECT date FROM me_toolsvalue WHERE year='"+year+"' && month='"+month+"' && hf_id='"+hf_id+"' limit 1";
         conn.rs=conn.st.executeQuery(get_date);
         while(conn.rs.next()){
             session.setAttribute("date", conn.rs.getString(1));
         }
        session.setAttribute("editTools", editTools);
        nextpage="edit_toolsChecklist.jsp";
       }
    else{
        nextpage="entry_type.jsp";
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
            Logger.getLogger(edit_toolsvalue.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_toolsvalue.class.getName()).log(Level.SEVERE, null, ex);
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
