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
public class add_sitevalue extends HttpServlet {
HttpSession session;
String hf_id,indicator_id,date,month,year,timestamp,recounted_data,reported_711A,reported_731,reported_DHIS,id,tibu,emr,eid;
String found="",entry_type="",nextpage,aphia_staff,moh_staff,marked_found="";
String datekey,visitmonth,visityear,userid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
     dbConn conn = new dbConn();
     marked_found="";
      tibu=emr=eid="";
    
     if(session.getAttribute("date")!=null && session.getAttribute("month_visit") !=null){
         visitmonth=session.getAttribute("month_visit").toString();
         visityear=session.getAttribute("year_visit").toString();
        
       year=session.getAttribute("assessYear").toString();
       month=session.getAttribute("assessMonth").toString();
       
        int mnth=Integer.parseInt(month);
        if(mnth<10){
            month="0"+mnth;
        }
        
       nextpage="site_checklist.jsp";   
         
         userid=session.getAttribute("userid").toString();
   AddTrails trails = new AddTrails();
   
       String dates [] = session.getAttribute("date").toString().split("/");
datekey=dates[2]+""+dates[1]+""+dates[0];
                entry_type=session.getAttribute("entry_type").toString();
//         if(entry_type.equals("both")){nextpage="all_tools";}
//          if(entry_type.equals("site")){nextpage="entry_type.jsp";}
                
       if(session.getAttribute("counter")==null){
         session.setAttribute("counter", 1);  
       }
       else {
       
       if(session.getAttribute("counter").toString().equals("2") && entry_type.equals("both")) { 
        nextpage="all_tools"; 
         session.setAttribute("counter", 3);  
      }
       if(session.getAttribute("counter").toString().equals("2") && entry_type.equals("site")) { 
         nextpage="entry_type.jsp";
          session.setAttribute("counter", 3);  
      }
      if(session.getAttribute("counter").toString().equals("1")) {
        session.setAttribute("counter", 2);   
      } 
       }
    
//       else if(session.getAttribute("counter").toString().equals("3") && entry_type.equals("both")) {
//       nextpage="all_tools"; 
//      }
//       else if(session.getAttribute("counter").toString().equals("3") && entry_type.equals("site")) {
//       nextpage="entry_type.jsp"; 
//      }
       
     date=session.getAttribute("date").toString();
     hf_id=session.getAttribute("hf_id").toString();
     aphia_staff=session.getAttribute("aphia_staff").toString();
     if(session.getAttribute("moh_staff")!=null){
     moh_staff=session.getAttribute("moh_staff").toString();
     }
     else{
         moh_staff="";
     }
     String check_status="SELECT id FROM marked_checklist WHERE hf_id='"+hf_id+"' && year='"+year+"' && month='"+month+"'";
     conn.rs=conn.st.executeQuery(check_status);
     if(conn.rs.next()==true){
         marked_found=conn.rs.getString(1);
     }
     IdGenerator IDGN = new IdGenerator();
     id=IDGN.current_id();
     timestamp=IDGN.date_key();
     if(marked_found.equals("")){
         
//         INSERT AS NEW ENTRY..........................
         String insert_mchecklist="INSERT INTO marked_checklist (id,hf_id,aphia_staff,moh_staff,year,month,date,timestamp,datekey) VALUES "
                 + " ('"+id+"','"+hf_id+"','"+aphia_staff+"','"+moh_staff+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
    conn.st.executeUpdate(insert_mchecklist);
    
//  ADD VISIT DATES====================  
    
   String Inserter="insert into visitdates(id,visityear,visitmonth,assessyear,assessmonth,hfid,timestamp)"
           + " VALUES('"+id+"','"+visityear+"','"+visitmonth+"','"+year+"','"+month+"','"+hf_id+"','"+timestamp+"')" ;
           conn.st.executeUpdate(Inserter);
    
    
//    ADD TRAILS TO THE DATABASE
    trails.addTrails(userid, "added site checklist data for health facility: "+hf_id+" data year : "+year+"  and data month : "+month+" : visit year : "+visityear+" : visit month "+visitmonth+" APHIA Staff : "+aphia_staff+" and moh staff is : "+moh_staff);   
 
    
    
     }
         if(!marked_found.equals("")){
//         UPDATE ENTRIES..........................
         String update_mchecklist="UPDATE marked_checklist SET aphia_staff='"+aphia_staff+"',moh_staff='"+moh_staff+"',date='"+date+"',timestamp='"+timestamp+"' WHERE hf_id='"+hf_id+"' && year='"+year+"' && month='"+month+"'";
    conn.st.executeUpdate(update_mchecklist);
   trails.addTrails(userid, "edited site checklist data for health facility: "+hf_id+" data year : "+year+"  and data month : "+month+" : visit year : "+visityear+" : visit month "+visitmonth+" APHIA Staff : "+aphia_staff+" and moh staff is : "+moh_staff);   
 
         }
     for(int i=59;i<=87;i++){
         
         found=indicator_id=recounted_data=reported_711A=reported_731=reported_DHIS="";
         String sectionid="";
             IdGenerator IG = new IdGenerator();
        if(request.getParameter("indicator_id"+i)!=null){ indicator_id = request.getParameter("indicator_id"+i);}
        if(request.getParameter("recounted_data"+i)!=null){ recounted_data = request.getParameter("recounted_data"+i);}
         if(request.getParameter("reported_711A"+i)!=null){ reported_711A = request.getParameter("reported_711A"+i);}
         //if(request.getParameter("reported_731"+i)!=null){  reported_731 = request.getParameter("reported_731"+i);}
        if(request.getParameter("reported_DHIS"+i)!=null){  reported_DHIS = request.getParameter("reported_DHIS"+i);}
        
        if(request.getParameter("tibu"+i)!=null){  tibu = request.getParameter("tibu"+i);}
        if(request.getParameter("emr"+i)!=null){  emr = request.getParameter("emr"+i);}
        if(request.getParameter("eid"+i)!=null){  eid = request.getParameter("eid"+i);}
        
        
        if(request.getParameter("program_id"+i)!=null){  sectionid = request.getParameter("program_id"+i);}
         timestamp=IG.date_key();
         id=IG.current_id();
        System.out.println(" id  : "+indicator_id+"  rc  :  "+recounted_data+"   711a  :   "+reported_711A+" 731  :  "+reported_731+"   dhis  :  "+reported_DHIS);

         
//       FOR HTC-PITC & VCT  
         //if(i>=1 && i<=6){
         if(sectionid.equals("1")){
            String htc_checker="SELECT id FROM htc_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(htc_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
               //String update_htc="UPDATE htc_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"' , reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               String update_htc="UPDATE htc_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"' ,reported_DHIS='"+reported_DHIS+"', tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               conn.st.executeUpdate(update_htc);
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
              //  String insert_htc="INSERT INTO htc_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
               //         + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
         
                String insert_htc="INSERT INTO htc_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
         
                conn.st.executeUpdate(insert_htc);
            } 
             
         }
         
         //       FOR PMTCT-ANC and Labour and Delivery  
         //if(i>=7 && i<=15){
         if(sectionid.equals("2")){
            String pmtct_checker="SELECT id FROM pmtct_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(pmtct_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
              // String update_pmtct="UPDATE pmtct_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"', reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               //conn.st.executeUpdate(update_pmtct);
          
               String update_pmtct="UPDATE pmtct_site SET recounted_data='"+recounted_data+"', datamonthlyreport='"+reported_711A+"',reported_DHIS='"+reported_DHIS+"' , tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"' , timestamp='"+timestamp+"' WHERE id='"+found+"'"; 
               conn.st.executeUpdate(update_pmtct);
          
           
           
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
               // String insert_pmtct="INSERT INTO pmtct_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
                //        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
        
                String insert_pmtct="INSERT INTO pmtct_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
        
                
                
                conn.st.executeUpdate(insert_pmtct);
            } 
             
         }
         
         
                  
         //       FOR Care and treatment  
         //if(i>=16 && i<=19){
         if(sectionid.equals("3")){
            String caretreatment_checker="SELECT id FROM caretreatment_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(caretreatment_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
              // String update_caretreatment="UPDATE caretreatment_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"', reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               String update_caretreatment="UPDATE caretreatment_site SET recounted_data='"+recounted_data+"', datamonthlyreport='"+reported_711A+"',reported_DHIS='"+reported_DHIS+"', tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               conn.st.executeUpdate(update_caretreatment);
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
               // String insert_caretreatment="INSERT INTO caretreatment_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
                 //       + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
         
                String insert_caretreatment="INSERT INTO caretreatment_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
         
                conn.st.executeUpdate(insert_caretreatment);
            } 
             
         }
         
                       
         //       FOR TB / HIV 
        // if(i>=20 && i<=21){
         if(sectionid.equals("4")){
            String tb_checker="SELECT id FROM tb_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(tb_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
               //String update_tb="UPDATE tb_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"', reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               String update_tb="UPDATE tb_site SET recounted_data='"+recounted_data+"', datamonthlyreport='"+reported_711A+"',reported_DHIS='"+reported_DHIS+"', tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               conn.st.executeUpdate(update_tb);
              session.setAttribute("sites_added", "<font color=\"blue\">Site Checklist Values Updated Successfully.</font>");
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
               // String insert_tb="INSERT INTO tb_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
                //        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
                String insert_tb="INSERT INTO tb_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
         conn.st.executeUpdate(insert_tb);
              session.setAttribute("sites_added", "<font color=\"green\">Site Checklist Values Added Successfully.</font>");
            } 
             
         }//end of htc
         
         
         
         
         if(sectionid.equals("5")){
            String vmmc_checker="SELECT id FROM vmmc_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(vmmc_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
              // String update_vmmc="UPDATE vmmc_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"', reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               String update_vmmc="UPDATE vmmc_site SET recounted_data='"+recounted_data+"', datamonthlyreport='"+reported_711A+"',reported_DHIS='"+reported_DHIS+"', tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               conn.st.executeUpdate(update_vmmc);
              session.setAttribute("sites_added", "<font color=\"blue\">Site Checklist Values Updated Successfully.</font>");
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
               // String insert_vmmc="INSERT INTO vmmc_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
               //         + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
         
                String insert_vmmc="INSERT INTO vmmc_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
         
                conn.st.executeUpdate(insert_vmmc);
              session.setAttribute("sites_added", "<font color=\"green\">Site Checklist Values Added Successfully.</font>");
            } 
             
         } 
         
         
     if(sectionid.equals("6")){
            String condom_checker="SELECT id FROM condom_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(condom_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
              // String update_condom="UPDATE condom_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"', reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               String update_condom="UPDATE condom_site SET recounted_data='"+recounted_data+"', datamonthlyreport='"+reported_711A+"',reported_DHIS='"+reported_DHIS+"', tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               conn.st.executeUpdate(update_condom);
              session.setAttribute("sites_added", "<font color=\"blue\">Site Checklist Values Updated Successfully.</font>");
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
               // String insert_condom="INSERT INTO condom_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
               //         + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
         
                String insert_condom="INSERT INTO condom_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
         
                conn.st.executeUpdate(insert_condom);
              session.setAttribute("sites_added", "<font color=\"green\">Site Checklist Values Added Successfully.</font>");
            } 
             
         }      
         
      if(sectionid.equals("7")){
            String gbv_checker="SELECT id FROM gbv_site WHERE hf_id='"+hf_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"' && month='"+month+"'";
             conn.rs=conn.st.executeQuery(gbv_checker);
             if(conn.rs.next()==true){
               found=conn.rs.getString(1);  
             }
           if(!found.equals("")){
//           UPDATE ENTRIES
              // String update_gbv="UPDATE gbv_site SET recounted_data='"+recounted_data+"', reported_711A='"+reported_711A+"', reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               String update_gbv="UPDATE gbv_site SET recounted_data='"+recounted_data+"', datamonthlyreport='"+reported_711A+"',reported_DHIS='"+reported_DHIS+"', tibu='"+tibu+"', emr='"+emr+"',eid='"+eid+"', timestamp='"+timestamp+"' WHERE id='"+found+"'";
               conn.st.executeUpdate(update_gbv);
              session.setAttribute("sites_added", "<font color=\"blue\">Site Checklist Values Updated Successfully.</font>");
           }
            if(found.equals("")){
//             INSERTED AS A NEW ENTRY   
               // String insert_gbv="INSERT INTO gbv_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) VALUES"
               //         + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
         
                String insert_gbv="INSERT INTO gbv_site (id,hf_id,indicator_id,recounted_data,datamonthlyreport,reported_DHIS,year,month,date,timestamp,datekey,tibu,emr,eid) VALUES"
                        + "('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"','"+tibu+"','"+emr+"','"+eid+"')";
         
                conn.st.executeUpdate(insert_gbv);
              session.setAttribute("sites_added", "<font color=\"green\">Site Checklist Values Added Successfully.</font>");
            } 
             
         }        
         
         
     }
    }
   else{
       session.setAttribute("checklist_added", "NO SESSION");
          session.setAttribute("sites_added", "<font color=\"red\">Failed To Add Site Checklist Value. Try Again.</font>");
   }
     System.out.println("the session counter is  "+session.getAttribute("counter"));
//    response.sendRedirect(nextpage); 
     if(session.getAttribute("counter").toString().equals("3")){
         session.removeAttribute("counter");
          session.removeAttribute("sites_added");
     }
     if(conn.rs!=null){conn.rs.close();}
     if(conn.st!=null){conn.st.close();}
     
     session.setAttribute("savedChecklist", "savedChecklist");
     
     session.removeAttribute("hasRefreshed");
     
     response.sendRedirect("all_indicators"); 
     
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
            Logger.getLogger(add_sitevalue.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_sitevalue.class.getName()).log(Level.SEVERE, null, ex);
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
