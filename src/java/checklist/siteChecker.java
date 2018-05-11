/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class siteChecker extends HttpServlet {

    HttpSession session;
    String nextPage, year, month, hfid;
    String found, data;
    String indicator_name, indicator_id, datamonthlyreport, program_area, source_doc, recounted_data, reported_711A, reported_731, reported_DHIS, section,tibu,emr,eid;
    int position;
    String sectionHTC, sectionPMTCT, sectionCARE, sectionTB, sectionVMMC, id, formAction, hf_name,sectionCondoms,sectionGbv,isrecounted_data,isreported_DHIS,isdatamonthlyreport,istibu,isemr,iseid;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
       HashMap<String, String> isrecounted_data = new HashMap<String, String>();
       HashMap<String, String> isreported_DHIS = new HashMap<String, String>();
       HashMap<String, String> isdatamonthlyreport = new HashMap<String, String>();
       HashMap<String, String> istibu = new HashMap<String, String>();
       HashMap<String, String> isemr = new HashMap<String, String>();
       HashMap<String, String> iseid = new HashMap<String, String>();
       
       session = request.getSession();
            dbConn conn = new dbConn();

     
                    String all_selector = "SELECT * FROM indicators where active='yes' order by id";
                    conn.rs = conn.st.executeQuery(all_selector);
                    while (conn.rs.next()) {
                        position++;
//tibu	emr	eid
                        indicator_id = conn.rs.getString("id");
                        
                        
                    isdatamonthlyreport.put(indicator_id,conn.rs.getString("datamonthlyreport"));
                       isrecounted_data.put(indicator_id,conn.rs.getString("recounted_data"));
                        isreported_DHIS.put(indicator_id,conn.rs.getString("reported_DHIS"));
                                 istibu.put(indicator_id,conn.rs.getString("tibu"));
                                  isemr.put(indicator_id,conn.rs.getString("emr"));
                                  iseid.put(indicator_id,conn.rs.getString("eid"));  
    }
       
       
        
        try {
            
            
            System.out.println(" visit year : " + session.getAttribute("year_visit") + " visit month : " + session.getAttribute("month_visit"));
            if (session.getAttribute("year_visit") != null && session.getAttribute("month_visit") != null) {
                year = session.getAttribute("assessYear").toString();
                month = session.getAttribute("assessMonth").toString();
                hfid = session.getAttribute("hf_id").toString();
                found = formAction = hf_name = "";
                sectionHTC = sectionPMTCT = sectionCARE = sectionTB = "";

                System.out.println("year : " + year + " month : " + month + " hfid : " + hfid);

                sectionHTC = "<div id=\"step-3\" style=\"width:98%;\">"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font>HTC register (MOH 362)</h2>"
                        + "            <table style=\"width:100%\" class=\"table_style\">"
                        + "<col >"
                        + "<col width=\"70px\">"
                        + "<col width=\"70px\">"
                        + //"    <col width=\"70px\">" +
                        "    <col width=\"70px\">";

                sectionPMTCT = "<div id=\"step-4\" style=\"width:98%;\" >"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font>ANC register [405]</h2>"
                        + "            <table style=\"width: 100%\" class=\"table_style\">"
                        + "               <col >"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + //"    <col width=\"70px\">" +
                        "    <col width=\"70px\">";

                sectionCARE = " <div id=\"step-6\" style=\"width:98%;\" >"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font>Daily activity register for ART and MOH 731.</h2>"
                        + "            <table style=\"width: 100%\" class=\"table_style\">"
                        + "               <col >"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + //"    <col width=\"70px\">" +
                        "    <col width=\"70px\">";

                sectionVMMC = " <div id=\"step-1\" style=\"width:98%;\" >"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font> VMMC.</h2>"
                        + "            <table style=\"width: 100%\" class=\"table_style\">"
                        + "               <col >"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + //"    <col width=\"70px\">" +
                        "    <col width=\"70px\"> ";

                sectionTB = " <div id=\"step-7\" style=\"width:98%;\" >"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font> TB register and MOH 711A.</h2>"
                        + "            <table style=\"width: 100%\" class=\"table_style\">"
                        + "               <col >"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\"> ";
                
                 sectionCondoms = " <div id=\"step-2\" style=\"width:98%;\" >"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font>Condoms.</h2>"
                        + "            <table style=\"width: 100%\" class=\"table_style\">"
                        + "               <col >"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\"> ";
                 
                 
                 sectionGbv = " <div id=\"step-5\" style=\"width:98%;\" >"
                        + "            <h2 class=\"StepTitle\"><font color=\"green\">Source Documents => </font>GBV</h2>"
                        + "            <table style=\"width: 100%\" class=\"table_style\">"
                        + "               <col >"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\">"
                        + "    <col width=\"70px\"> ";
                

                String dataHeader = "<tr>"
                        + "<th>Verifiable data elements. </th>"
                        + "<th>Recounted</th>"
                        //+ "<th>Reported in 711A. </th>"
                        //+ "<th>Reported in 731. </th>"
                        + "<th>Reported MOH731/711A</th>"
                        + "<th>DHIS2</th>"
                        + "<th>TIBU</th>"
                        + "<th>EMR</th>"
                        + "<th>EID Website</th>"
                        + "</tr>";

                sectionCARE += dataHeader;
                sectionPMTCT += dataHeader;
                sectionTB += dataHeader;
                sectionHTC += dataHeader;
                sectionVMMC += dataHeader;
                sectionCondoms += dataHeader;
                sectionGbv += dataHeader;

                String Checker = "SELECT id FROM marked_checklist WHERE hf_id='" + hfid + "' && year='" + year + "' && month=" + month + "";
                conn.rs = conn.st.executeQuery(Checker);
                if (conn.rs.next() == true) {
                    found = conn.rs.getString(1);
                }
                if (found.length() == 0) {

                    String getFacilityName = "SELECT hf_name FROM health_facility WHERE hf_id='" + hfid + "'";
                    conn.rs = conn.st.executeQuery(getFacilityName);
                    if (conn.rs.next() == true) {
                        hf_name = conn.rs.getString(1);
                    }
                    session.setAttribute("hf_name", hf_name);
//      load sections

//       SELECT ALL INDICATORS AND ADD THEM AS OBJECTS TO THE ARRAYLIST.
                     all_selector = "SELECT * FROM indicators where active='yes' order by id";
                    conn.rs = conn.st.executeQuery(all_selector);
                    while (conn.rs.next()) {
                        position++;
//tibu	emr	eid
                        indicator_id = conn.rs.getString("id");
                        program_area = conn.rs.getString("program_area");
                        source_doc = conn.rs.getString("source_doc");
                        indicator_name = conn.rs.getString("indicator");
                        recounted_data = conn.rs.getString("recounted_data");
                        reported_711A = conn.rs.getString("reported_711A");
                        reported_731 = conn.rs.getString("reported_731");
                        reported_DHIS = conn.rs.getString("reported_DHIS");
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu=conn.rs.getString("tibu");
                        emr=conn.rs.getString("emr");
                        eid=conn.rs.getString("eid");
                        
                        
                    isdatamonthlyreport.put(indicator_id,conn.rs.getString("datamonthlyreport"));
                       isrecounted_data.put(indicator_id,conn.rs.getString("recounted_data"));
                        isreported_DHIS.put(indicator_id,conn.rs.getString("reported_DHIS"));
                                 istibu.put(indicator_id,conn.rs.getString("tibu"));
                                  isemr.put(indicator_id,conn.rs.getString("emr"));
                                  iseid.put(indicator_id,conn.rs.getString("eid"));
                        
                        
                        section = conn.rs.getString("section");
                        

                        if (section.equals("1")) {
                            sectionHTC += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\"><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\">";
                            if (recounted_data.equals("1")) {
                                sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionHTC += "<td> </td> ";
                            }

                            if (datamonthlyreport.equals("1")) {
                                sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionHTC += "<td> </td> ";
                            }
                            
                            
                           
                            
                            /*

           if(reported_711A.equals("1")){
            sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionHTC+="<td> </td> ";
           }

            if(reported_731.equals("1")){
           sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionHTC+="<td> </td> ";
               }
                             */
                            if (reported_DHIS.equals("1")) {
                                sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionHTC += "<td> </td> ";
                            }
                            
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionHTC += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionHTC += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionHTC += "<td> </td> ";
                            }
                            
                            
                            
                            sectionHTC += "</tr>";
                        } else if (section.equals("2")) {
                            sectionPMTCT += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                            if (recounted_data.equals("1")) {
                                sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionPMTCT += "<td> </td> ";
                            }
                            if (datamonthlyreport.equals("1")) {
                                sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionPMTCT += "<td> </td> ";
                            }
                            
                            
                            
                            
                            /*

           if(reported_711A.equals("1")){
            sectionPMTCT+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionPMTCT+="<td> </td> ";
           }

            if(reported_731.equals("1")){
           sectionPMTCT+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionPMTCT+="<td> </td> ";
               }
                             */
                            if (reported_DHIS.equals("1")) {
                                sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionPMTCT += "<td> </td> ";
                            }
                            
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionPMTCT += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionPMTCT += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionPMTCT += "<td> </td> ";
                            }
                            
                            
                            
                            sectionPMTCT += "</tr>";

                        }

                        if (section.equals("3")) {

                            sectionCARE += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                            if (recounted_data.equals("1")) {
                                sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCARE += "<td> </td> ";
                            }

                            if (datamonthlyreport.equals("1")) {
                                sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCARE += "<td> </td> ";
                            }
                            /*

           if(reported_711A.equals("1")){
            sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionHTC+="<td> </td> ";
           }

            if(reported_731.equals("1")){
           sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionHTC+="<td> </td> ";
               }
                             */

                            if (reported_DHIS.equals("1")) {
                                sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCARE += "<td> </td> ";
                            }
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCARE += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCARE += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCARE += "<td> </td> ";
                            }
                            
                            
                            
                            sectionCARE += "</tr>";
                        } else if (section.equals("4")) {
                            sectionTB += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                            if (recounted_data.equals("1")) {
                                sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionTB += "<td> </td> ";
                            }

                            if (datamonthlyreport.equals("1")) {
                                sectionTB += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionTB += "<td> </td> ";
                            }
                            /*

           if(reported_711A.equals("1")){
            sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionHTC+="<td> </td> ";
           }

            if(reported_731.equals("1")){
           sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionHTC+="<td> </td> ";
               }
                             */

                            if (reported_DHIS.equals("1")) {
                                sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionTB += "<td> </td> ";
                            }
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionTB += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionTB += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionTB += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionTB += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionTB += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionTB += "<td> </td> ";
                            }
                            
                            
                            sectionTB += "</tr>";
                        } else if (section.equals("5")) {
                            sectionVMMC += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                            if (recounted_data.equals("1")) {
                                sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionVMMC += "<td> </td> ";
                            }

                            if (datamonthlyreport.equals("1")) {
                                sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionVMMC += "<td> </td> ";
                            }
                            

                            if (reported_DHIS.equals("1")) {
                                sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionVMMC += "<td> </td> ";
                            }
                            
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionVMMC += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionVMMC += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionVMMC += "<td> </td> ";
                            }
                            
                            
                            sectionVMMC += "</tr>";
                        } 
                        
                        
                         else if (section.equals("6")) {
                            sectionCondoms += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                            if (recounted_data.equals("1")) {
                                sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCondoms += "<td> </td> ";
                            }

                            if (datamonthlyreport.equals("1")) {
                                sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCondoms += "<td> </td> ";
                            }
                            

                            if (reported_DHIS.equals("1")) {
                                sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCondoms += "<td> </td> ";
                            }
                            
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCondoms += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCondoms += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionCondoms += "<td> </td> ";
                            }
                            
                            
                            sectionCondoms += "</tr>";
                        } 
                        
                          else if (section.equals("7")) {
                            sectionGbv += " <tr>"
                                    + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                            if (recounted_data.equals("1")) {
                                sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionGbv += "<td> </td> ";
                            }

                            if (datamonthlyreport.equals("1")) {
                                sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionGbv += "<td> </td> ";
                            }
                            

                            if (reported_DHIS.equals("1")) {
                                sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionGbv += "<td> </td> ";
                            }
                            
                            
                             //tb
                            if (tibu.equals("1")) {
                                sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionGbv += "<td> </td> ";
                            }
                            //emr
                            if (emr.equals("1")) {
                                sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else {
                                sectionGbv += "<td> </td> ";
                            }
                            //eid
                             if (eid.equals("1")) {
                                sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                            } else 
                             {
                                sectionGbv += "<td> </td> ";
                            }
                            
                              sectionGbv += "</tr>";
                            } 
                        
                        
                        
                        else {
//              no section found
                        }

                    }
                    formAction = "add_sitevalue";

                } else if (found.length() > 0) {
//         marked
                    nextPage = "edit_sitevalue?hf_id=" + hfid + "&&year=" + year + "&&month=" + month + "";

//      LOAD HTC DATA TO A BEAN................
                    String get_htcSite = "SELECT htc_site.id,htc_site.indicator_id,indicators.indicator,htc_site.recounted_data,htc_site.reported_711A,htc_site.reported_731,htc_site.reported_DHIS,indicators.section,htc_site.datamonthlyreport,htc_site.tibu,htc_site.emr,htc_site.eid "
                            + " FROM htc_site JOIN indicators ON htc_site.indicator_id=indicators.id WHERE htc_site.year='" + year + "' && htc_site.month=" + month + " && htc_site.hf_id='" + hfid + "' ORDER BY htc_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_htcSite);
                    System.out.println("%%%%%" + get_htcSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
                        //reported_711A=conn.rs.getString(5);
                        //reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");

                        System.out.println("711 : " + reported_711A + " reported 731 : " + reported_731);
                        sectionHTC += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionHTC += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionHTC += "<td> </td> ";
                        }

                        /* 
           if(!reported_711A.equals("")){
            sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionHTC+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionHTC+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionHTC+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionHTC += "<td> </td> ";
                        }
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionHTC += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionHTC += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionHTC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionHTC += "<td> </td> ";
                        }
                        
                        sectionHTC += "</tr>";

                    }
                    //      LOAD PMTCT DATA TO A BEAN................
                    String get_pmtctSite = "SELECT pmtct_site.id,pmtct_site.indicator_id,indicators.indicator,pmtct_site.recounted_data,pmtct_site.reported_711A,pmtct_site.reported_731,pmtct_site.reported_DHIS,indicators.section "
                            + ",pmtct_site.datamonthlyreport,pmtct_site.tibu,pmtct_site.emr,pmtct_site.eid FROM pmtct_site JOIN indicators ON pmtct_site.indicator_id=indicators.id WHERE pmtct_site.year='" + year + "' && pmtct_site.month=" + month + " && pmtct_site.hf_id='" + hfid + "'  ORDER BY pmtct_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_pmtctSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
                        // reported_711A=conn.rs.getString(5);
                        // reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");

                        sectionPMTCT += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionPMTCT += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionPMTCT += "<td> </td> ";
                        }

                        /* 
           if(!reported_711A.equals("")){
            sectionPMTCT+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionPMTCT+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionPMTCT+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionPMTCT+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionPMTCT += "<td> </td> ";
                        }
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionPMTCT += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionPMTCT += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionPMTCT += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionPMTCT += "<td> </td> ";
                        }
                        
                        sectionPMTCT += "</tr>";

                    }

                    //      LOAD CARE AND TREATMENT DATA TO A BEAN................
                    String get_caretreatmentSite = "SELECT caretreatment_site.id,caretreatment_site.indicator_id,indicators.indicator,caretreatment_site.recounted_data,caretreatment_site.reported_711A,caretreatment_site.reported_731,caretreatment_site.reported_DHIS,indicators.section "
                            + ",caretreatment_site.datamonthlyreport,caretreatment_site.tibu,caretreatment_site.emr,caretreatment_site.eid FROM caretreatment_site JOIN indicators ON caretreatment_site.indicator_id=indicators.id WHERE caretreatment_site.year='" + year + "' && caretreatment_site.month=" + month + " && caretreatment_site.hf_id='" + hfid + "'  ORDER BY caretreatment_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_caretreatmentSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
                        //reported_711A=conn.rs.getString(5);
                        //reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");
                        sectionCARE += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCARE += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCARE += "<td> </td> ";
                        }
                        /*

           if(!reported_711A.equals("")){
            sectionCARE+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionCARE+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionCARE+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionCARE+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCARE += "<td> </td> ";
                        }
                        
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCARE += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCARE += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionCARE += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCARE += "<td> </td> ";
                        }
                        
                        
                        sectionCARE += "</tr>";

                    }

                    //      LOAD TB AND HIV DATA TO A BEAN................
                    String get_tbSite = "SELECT tb_site.id,tb_site.indicator_id,indicators.indicator,tb_site.recounted_data,tb_site.reported_711A,tb_site.reported_731,tb_site.reported_DHIS,indicators.section "
                            + ",tb_site.datamonthlyreport,tb_site.tibu,tb_site.emr,tb_site.eid FROM tb_site JOIN indicators ON tb_site.indicator_id=indicators.id WHERE tb_site.year='" + year + "' && tb_site.month=" + month + " && tb_site.hf_id='" + hfid + "'  ORDER BY tb_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_tbSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
//             reported_711A=conn.rs.getString(5);
//             reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");

                        sectionTB += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionTB += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionTB += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionTB += "<td> </td> ";
                        }

                        /* 
           if(!reported_711A.equals("")){
            sectionTB+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionTB+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionTB+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionTB+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionTB += "<td> </td> ";
                        }
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionTB += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionTB += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionTB += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionTB += "<td> </td> ";
                        }
                        
                        
                        sectionTB += "</tr>";

                    }

                    //      LOAD VMMC TO A BEAN................
                    String get_vmmcSite = "SELECT vmmc_site.id,vmmc_site.indicator_id,indicators.indicator,vmmc_site.recounted_data,vmmc_site.reported_711A,vmmc_site.reported_731,vmmc_site.reported_DHIS,indicators.section "
                            + ",vmmc_site.datamonthlyreport,vmmc_site.tibu,vmmc_site.emr,vmmc_site.eid FROM vmmc_site JOIN indicators ON vmmc_site.indicator_id=indicators.id WHERE vmmc_site.year='" + year + "' && vmmc_site.month=" + month + " && vmmc_site.hf_id='" + hfid + "'  ORDER BY vmmc_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_vmmcSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
//             reported_711A=conn.rs.getString(5);
//             reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");

                        sectionVMMC += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionVMMC += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionVMMC += "<td> </td> ";
                        }

                        /* 
           if(!reported_711A.equals("")){
            sectionVMMC+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionVMMC+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionVMMC+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionVMMC+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionVMMC += "<td> </td> ";
                        }
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionVMMC += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionVMMC += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionVMMC += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionVMMC += "<td> </td> ";
                        }
                        
                        
                        sectionVMMC += "</tr>";

                    }
                    //end of VMMC section

                                        String get_condomSite = "SELECT condom_site.id,condom_site.indicator_id,indicators.indicator,condom_site.recounted_data,condom_site.reported_711A,condom_site.reported_731,condom_site.reported_DHIS,indicators.section "
                            + ",condom_site.datamonthlyreport,condom_site.tibu,condom_site.emr,condom_site.eid FROM condom_site JOIN indicators ON condom_site.indicator_id=indicators.id WHERE condom_site.year='" + year + "' && condom_site.month=" + month + " && condom_site.hf_id='" + hfid + "'  ORDER BY condom_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_condomSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
//             reported_711A=conn.rs.getString(5);
//             reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");

                        sectionCondoms += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCondoms += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCondoms += "<td> </td> ";
                        }

                        /* 
           if(!reported_711A.equals("")){
            sectionCondoms+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionCondoms+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionCondoms+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionCondoms+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCondoms += "<td> </td> ";
                        }
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCondoms += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCondoms += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionCondoms += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionCondoms += "<td> </td> ";
                        }
                        
                        
                        sectionCondoms += "</tr>";

                    }
                    //end of Condom section

                 
                    
                    
                    
                                        String get_gbvSite = "SELECT gbv_site.id,gbv_site.indicator_id,indicators.indicator,gbv_site.recounted_data,gbv_site.reported_711A,gbv_site.reported_731,gbv_site.reported_DHIS,indicators.section "
                            + ",gbv_site.datamonthlyreport,gbv_site.tibu,gbv_site.emr,gbv_site.eid FROM gbv_site JOIN indicators ON gbv_site.indicator_id=indicators.id WHERE gbv_site.year='" + year + "' && gbv_site.month=" + month + " && gbv_site.hf_id='" + hfid + "'  ORDER BY gbv_site.indicator_id";
                    conn.rs = conn.st.executeQuery(get_gbvSite);
                    while (conn.rs.next() == true) {

                        id = conn.rs.getString(1);
                        indicator_id = conn.rs.getString(2);
                        indicator_name = conn.rs.getString(3);
                        recounted_data = conn.rs.getString(4);
//             reported_711A=conn.rs.getString(5);
//             reported_731=conn.rs.getString(6);
                        reported_DHIS = conn.rs.getString(7);
                        section = conn.rs.getString(8);
                        datamonthlyreport = conn.rs.getString("datamonthlyreport");
                        tibu= conn.rs.getString("tibu");
                        emr= conn.rs.getString("emr");
                        eid= conn.rs.getString("eid");

                        sectionGbv += " <tr>"
                                + "<td>" + indicator_name + "</td><input type=\"hidden\" name=\"program_id" + indicator_id + "\" value=\"" + section + "\"><input type=\"hidden\" name=\"indicator_id" + indicator_id + "\" value=\"" + indicator_id + "\">";
                        if (!recounted_data.equals("") || isrecounted_data.get(indicator_id).equals("1")) {
                            sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"recounted_data" + indicator_id + "\" id=\"rc" + section + "" + indicator_id + "\" required value=\"" + recounted_data + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionGbv += "<td> </td> ";
                        }

                        if (!datamonthlyreport.equals("") || isdatamonthlyreport.get(indicator_id).equals("1")) {
                            sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A" + indicator_id + "\" id=\"sc711" + section + "" + indicator_id + "\" required value=\"" + datamonthlyreport + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionGbv += "<td> </td> ";
                        }

                        /* 
           if(!reported_711A.equals("")){
            sectionGbv+="<td><input type=\"text\" autocomplete=\"off\"   class=\"textbox\" required name=\"reported_711A"+indicator_id+"\" id=\"sc711"+section+""+indicator_id+"\" required value=\""+reported_711A+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
           sectionGbv+="<td> </td> ";
           }

            if(!reported_731.equals("")){
           sectionGbv+="<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_731"+indicator_id+"\" id=\"sc731"+section+""+indicator_id+"\" required value=\""+reported_731+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
           } else{
                sectionGbv+="<td> </td> ";
               }
                         */
                        if (!reported_DHIS.equals("") || isreported_DHIS.get(indicator_id).equals("1")) {
                            sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"reported_DHIS" + indicator_id + "\" id=\"dhis" + section + "" + indicator_id + "\" required value=\"" + reported_DHIS + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionGbv += "<td> </td> ";
                        }
                        
                        if (!tibu.equals("") || istibu.get(indicator_id).equals("1")) {
                            sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"tibu" + indicator_id + "\" id=\"tibu" + section + "" + indicator_id + "\" required value=\"" + tibu + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionGbv += "<td> </td> ";
                        }
                        
                         if (!emr.equals("") || isemr.get(indicator_id).equals("1")) {
                            sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"emr" + indicator_id + "\" id=\"emr" + section + "" + indicator_id + "\" required value=\"" + emr + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionGbv += "<td> </td> ";
                        }
                        
                          if (!eid.equals("") || iseid.get(indicator_id).equals("1")) {
                            sectionGbv += "<td><input type=\"text\" autocomplete=\"off\"  class=\"textbox\" required name=\"eid" + indicator_id + "\" id=\"eid" + section + "" + indicator_id + "\" required value=\"" + eid + "\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\"> </td> ";
                        } else {
                            sectionGbv += "<td> </td> ";
                        }
                        
                        
                        sectionGbv += "</tr>";

                        System.out.println("@@@@__"+sectionGbv);
                        
                    }
                    //end of GBV
                    
                    
                    String moh_staff, aphia_staff, moh_name = "", aphia_name = "", moh_array[];
                    String hf_namegetter = "SELECT hf_name FROM health_facility WHERE hf_id='" + hfid + "'";
                    conn.rs = conn.st.executeQuery(hf_namegetter);
                    while (conn.rs.next()) {
                        hf_name = conn.rs.getString(1);
                    }
                    session.setAttribute("hf_name", hf_name);
                    String get_date = "SELECT * FROM marked_checklist WHERE year='" + year + "' && month=" + month + " && hf_id='" + hfid + "' limit 1";
                    conn.rs = conn.st.executeQuery(get_date);
                    if (conn.rs.next() == true) {
                        session.setAttribute("date", conn.rs.getString("date"));
                        moh_staff = conn.rs.getString("moh_staff");
                        aphia_staff = conn.rs.getString("aphia_staff");
                        moh_array = moh_staff.split(",");
                        session.setAttribute("moh_staff", moh_staff);
                        session.setAttribute("aphia_staff", aphia_staff);
                        for (String staffer : moh_array) {

                            String get_moh = "SELECT * FROM moh_staff WHERE id='" + staffer + "'";
                            conn.rs1 = conn.st1.executeQuery(get_moh);
                            while (conn.rs1.next()) {
                                moh_name += conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + ",";
                            }
                        }
                        String get_aphia = "SELECT * FROM aphia_staff WHERE id='" + aphia_staff + "'";
                        conn.rs1 = conn.st1.executeQuery(get_aphia);
                        if (conn.rs1.next() == true) {
                            aphia_name = conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname");
                        }
                        session.setAttribute("moh_staff_name", moh_name);
                        session.setAttribute("aphia_staff_name", aphia_name);
                    }

                    formAction = "save_editedSite";
                } else {
                    System.out.println("nothing to be done");
                }

                System.out.println("found : " + found);

                data = "<form action=\"" + formAction + "\" method=\"post\" style=\"width: 100%;\" id=\"form\">"
                        + "                       "
                        + " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"data\" >"
                        + "                          "
                        + "<tr><td> "
                        + "<!-- Tabs -->"
                        + "<div id=\"wizard\" class=\"swMain\" style=\"height: auto;width:200%;margin-left:-300px;\">";
                if (session.getAttribute("hf_name") != null) {
                    data += "    <table style=\"width: 100%\" class=\"table_style\">"
                            + "                 <tr><th><font color=\"black\">Health Facility Name:</font><font color=\"blue-violet\"> " + session.getAttribute("hf_name").toString() + "</font></th>"
                            + "                    <th><font color=\"black\">Name of MOH Staff:</font> <font color=\"blue-violet\"> " + session.getAttribute("moh_staff_name").toString() + "</font></th> "
                            + "                    <th><font color=\"black\">Name of APHIA Staff:</font> <font color=\"blue-violet\"> " + session.getAttribute("aphia_staff_name").toString() + "</font></th> "
                            + "                    <th><font color=\"black\">Date of Visit:</font> <font color=\"blue-violet\"> " + session.getAttribute("date").toString() + "</font></th> "
                            + "                </tr>"
                            + "             </table>   "
                            + "    <br>"
                            + "";
                }
                data += "<br>"
                        + " 	"
                        + "        <ul>"
                        + "                            <li style=\"font-size: 15px;font-family: sans-serif; color: black; margin-left: 10px;\">PROGRAM AREA.</li>"
                        
                          + "  				<li style=\"height: auto;\"><a href=\"#step-1\">"
                        + "                <label class=\"stepNumber\">1</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 1<br />-->"
                        + "                  <small style=\"font-size: 14px;\"><b>VMMC</b></small>"
                        + "                </span>                   "
                        + "            </a></li>"
                        
                        
                          + "  				<li style=\"height: auto;\"><a href=\"#step-2\">"
                        + "                <label class=\"stepNumber\">2</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 2<br />-->"
                        + "                  <small style=\"font-size: 14px;\"><b>Condoms</b></small>"
                        + "                </span>                   "
                        + "            </a></li>"
                        
                        
                         + "                            <li style=\"height: auto;\"><a href=\"#step-3\">"
                        + "                <label class=\"stepNumber\">3</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 3<br />-->"
                        + "<small style=\"font-size: 14px;\"><b>HTS</b></small>"
                        + "                </span>"
                        + "            </a></li>"
                        
                        
                        + "  				<li style=\"height: auto;\"><a href=\"#step-4\">"
                        + "                <label class=\"stepNumber\">4</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 4<br />-->"
                        + "            <small style=\"font-size: 14px;\"><b>PMTCT</b></small>"
                        + "                </span>"
                        + "            </a></li>"
                        
                         + "                            <li style=\"height: auto;\"><a href=\"#step-5\">"
                        + "                <label class=\"stepNumber\">5</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 5<br />-->"
                        + "<small style=\"font-size: 14px;\"><b>GBV</b></small>"
                        + "                </span>"
                        + "            </a></li>"
                        
                        
                        + "  				<li style=\"height: auto;\"><a href=\"#step-6\">"
                        + "                <label class=\"stepNumber\">6</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 6<br />-->"
                        + "                   <small style=\"font-size: 14px;\"><b>Care and Treatment</b></small>"
                        + "                </span>                   "
                        + "             </a></li>"
                        
                       
                        
                      
                        
                        + "  				<li style=\"height: auto;\"><a href=\"#step-7\">"
                        + "                <label class=\"stepNumber\">7</label>"
                        + "                <span class=\"stepDesc\">"
                        + "<!--                   Step 7<br />-->"
                        + "                  <small style=\"font-size: 14px;\"><b>TB</b></small>"
                        + "                </span>                   "
                        + "            </a></li>"
                        
                        + "  			</ul>";

                sectionHTC += "</table></div>";
                sectionPMTCT += "</table></div>";
                sectionCARE += "</table></div>";
                sectionVMMC += "</table></div>";
                sectionTB += "</table></div>";
                sectionCondoms += "</table></div>";
                sectionGbv += "</table></div>";

                data += sectionVMMC + sectionCondoms +""+ sectionHTC + "" + sectionPMTCT + ""+sectionGbv+"" + sectionCARE + "" + sectionTB + "</div>"
                        + "<!-- End SmartWizard Content -->"
                        + "</td></tr>"
                        + "</table>  "
                        + " </form>";

            } else {
                data = "<p style=\"margin-top: 100px;\"><font color=\"red\">ERROR : </font> Visit Year and Month cant be verified, Please go back and select these details.</p>";
            }

            session.removeAttribute("savedChecklist");
            session.setAttribute("justLoaded", "yes");
            System.out.println("@@"+data);
            session.setAttribute("data", data);
            out.println(data);
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
            Logger.getLogger(siteChecker.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(siteChecker.class.getName()).log(Level.SEVERE, null, ex);
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
