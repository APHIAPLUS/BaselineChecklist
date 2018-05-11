<%-- 
    Document   : tools_checklist
    Created on : May 9, 2014, 3:37:14 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    if (session.getAttribute("userid")==null || (session.getAttribute("level").toString().equals("2") || session.getAttribute("level").toString().equals("5"))) {
      
//        response.sendRedirect("index.jsp");
           }  
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Tools Checklist</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="calculator/avail.js"></script>
<script type="text/javascript" src="calculator/correctly_used.js"></script>
<script type="text/javascript" src="calculator/filled_completely.js"></script>
<script type="text/javascript" src="calculator/in_use.js"></script>

<!--WIZARD-->

<link href="styles_wiz/demo_style.css" rel="stylesheet" type="text/css">

<link href="styles_wiz/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="js_wiz/jquery-2.0.0.min.js"></script>-->
<script type="text/javascript" src="js_wiz/jquery.smartWizard.js"></script>
<!--<script type="text/javascript" src="js_wiz/validateSteps.js"></script>-->
<script type="text/javascript">
   
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript"> 
$(function() {
$( "#datepicker" ).datepicker();
});

  function load_v(){
 htc_av();
 pmtct_av();
 art_use1();
 art_av1();
  htc_used();
 pmtct_used();
  art_used1();
   htcf();
 pmtct_filled();
  art_filled1();
   htc_use();
 pmtct_use();
 
 art_av();
 tb_av();
 art_used();
 tb_used();
 art_filled();
 tb_filled();
 art_use();
 tb_use();
// 
//alert("all_called")
}          
        </script> 
         <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
<!--	<script src="js/jquery-1.9.1.js"></script>-->
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.mouse.js"></script>
	<script src="ui/jquery.ui.draggable.js"></script>
	<script src="ui/jquery.ui.position.js"></script>
	<script src="ui/jquery.ui.resizable.js"></script>
	<script src="ui/jquery.ui.button.js"></script>
	<script src="ui/jquery.ui.dialog.js"></script>
	<script src="ui/jquery.ui.effect.js"></script>
	<script src="ui/jquery.ui.effect-blind.js"></script>
	<script src="ui/jquery.ui.effect-explode.js"></script>
	<link rel="stylesheet" href="ui-essentials/demos.css">
                <script>
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
			show: {
				effect: "blind",
				duration: 500
			},
			hide: {
				effect: "explode",
				duration: 700
			}
		});

		$( "#opener" ).click(function() {
			$( "#dialog" ).dialog( "open" );
		});
	});
      </script> 
    </head>
    <body onload="return load_v();">
       
     <div id="container" style="height:auto;" >
     <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>

              <div id="header" align="center">   
              </div>
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Choose the Correct Specification for each tool.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>    
            <br>       
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                <div id="dialog" title="Edit Tools Checklist.." style=" font-size: 17px;">
<p>Make Correction on the required element for any of the sections and then click on Finish to save the details.</p>
</div>
                <div id="midcontent" style="margin-left:50px ; height:auto; width: 950;" >
                 <%if (session.getAttribute("clerk_added") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("clerk_added")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("clerk_added");
                            }

                        %>
<p style="color: red" id="showMessage"></p>
<br>
                    <form action="save_editedTools" method="post" style="width: 1000px;">
                       
                      <table align="center" border="0" cellpadding="0" cellspacing="0">
                          
<tr><td> 
<!-- Tabs -->
<div id="wizard" class="swMain" style="height: auto;">
  	                      <%if(session.getAttribute("hf_name")!=null){%> 
    <table style="width: 100%" class="table_style">
                 <tr><th><font color="black">District Name:</font> <font color="blue-violet"><%=session.getAttribute("district_name").toString()%></font></th> 
                       <th>  <font color="black">Health Facility Name:</font><font color="blue-violet"> <%=session.getAttribute("hf_name").toString()%></font></th>
                    <th><font color="black">Date of Visit:</font> <font color="blue-violet"><%=session.getAttribute("date").toString()%></font></th> 
                </tr>
             </table>   
    <%}%> 	
    
    <ul>
                            <li style="font-size: 25px;font-family: sans-serif; color: black; margin-left: 10px;">M&E TOOLS.</li>
                            <li style="height: 13%;"><a href="#step-1">
                <label class="stepNumber" style="font-size: 30px;">1</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small style="font-size: 14px;"><b>M&E HTC tools</b></small>
                </span>
            </a></li>
  				<li style="height: 13%;"><a href="#step-2">
                <label class="stepNumber" style="font-size: 30px;">2</label>
                <span class="stepDesc">
<!--                   Step 2<br />-->
            <small style="font-size: 14px;"><b>M&E PMTCT tools</b></small>
                </span>
            </a></li>
  				<li style="height: 13%;"><a href="#step-3">
                <label class="stepNumber" style="font-size: 30px;">3</label>
                <span class="stepDesc">
<!--                   Step 3<br />-->
                   <small style="font-size: 14px;"><b>(a). ART M&E tools</b></small>
                </span>                   
             </a></li>
  				<li style="height: 13%;"><a href="#step-4">
                <label class="stepNumber" style="font-size: 30px;">3</label>
                <span class="stepDesc">
<!--                   Step 4<br />-->
                  <small style="font-size: 14px;"><b>(b). ART M&E tools</b></small>
                </span>                   
            </a></li>
            <li style="height: 13%;"><a href="#step-5">
                <label class="stepNumber" style="font-size: 30px;">4</label>
                <span class="stepDesc">
<!--                   Step 4<br />-->
                  <small style="font-size: 14px;"><b>TB M&E tools</b></small>
                </span>                   
            </a></li>     

  			</ul>
  			<div id="step-1">	
            <h2 class="StepTitle"><font color="green">Tool Name => </font> Status of standard M&E HTC tools.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Specification of M&E Tools Required</th> <th>Available</th> <th>In Use</th> <th>Filled Completely</th> <th>Correctly Used</th></tr>
         <c:forEach items="${editTools}" var="editTools">
            <c:set var="section" value="${editTools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("1")){%>
            <tr> <input type="hidden" value="0" id="av_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="use_${editTools.section}${editTools.tool_id}">
             <input type="hidden" value="0" id="htcf_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="used_${editTools.section}${editTools.tool_id}">
                <td>${editTools.tool_name}</td>
                <input type="hidden" name="specification${editTools.tool_id}" value="${editTools.id}">
                <td><select name="available${editTools.tool_id}" id="available${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htc_av();">
                    ${editTools.av}</select>
                </td>
                <td><select name="in_use${editTools.tool_id}" id="use${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htc_use();">
                      ${editTools.in_use}</select>
                </td>
                <td><select name="filled_completely${editTools.tool_id}" id="htcf${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htcf();">
                        ${editTools.filled}</select>
                </td>
                <td><select name="correctly_used${editTools.tool_id}" id="used${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htc_used();">
                    ${editTools.used}</select>
                </td>
                
             </tr>
            
            <%}%>
            </c:forEach>
             <tr> 
                <td>TOTAL SCORE:</td>
                <td><p id="av_htc" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="av_htctotal" name="av1"></td>
                <td><p id="use_htc" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="use_htctotal" name="use1"></td>
                <td><p id="htcf" style="color: #fb3500; font-size: large; margin-top: -5px;"></p> <input type="hidden" value="0" id="htcf_to" name="filled1" ></td>
                <td><p id="used_htc" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="used_htctotal" name="used1"></td>
              <br>  
             </tr>
             <tr></tr>
            </table>        			
        </div>
    
    <div id="step-2">	
            <h2 class="StepTitle"><font color="green">Tool Name => </font> Status of standard M&E PMTCT tools.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Specification of M&E Tools Required</th> <th>Available</th> <th>In Use</th> <th>Filled Completely</th> <th>Correctly Used</th></tr>
            <c:forEach items="${editTools}" var="editTools">
            <c:set var="section" value="${editTools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("2")){%>
            <tr> <input type="hidden" value="0" id="av_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="use_${editTools.section}${editTools.tool_id}">
             <input type="hidden" value="0" id="filled_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="used_${editTools.section}${editTools.tool_id}">
          <input type="hidden" name="specification${editTools.tool_id}" value="${editTools.id}">
                <td>${editTools.tool_name} </td>
                <td><select name="available${editTools.tool_id}" id="available${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_av();">
                      ${editTools.av}</select>
                </td>
                <td><select name="in_use${editTools.tool_id}" id="use${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_use();">
                        ${editTools.in_use}</select>
                </td>
                <td><select name="filled_completely${editTools.tool_id}" id="filled${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_filled();">
                         ${editTools.filled}</select>
                </td>
                <td><select name="correctly_used${editTools.tool_id}" id="used${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_used();">
                   ${editTools.used}</select>
                </td>
                
             </tr>
            
            <%}%>
            </c:forEach>
              <tr> 
                <td>TOTAL SCORE:</td>
                <td><p id="av_pmtct" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="av_pmtcttotal" name="av2"></td>
                <td><p id="use_pmtct" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="use_pmtcttotal" name="use2"></td>
                <td><p id="filled_pmtct" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="filled_pmtcttotal" name="filled2"></td>
                <td><p id="used_pmtct" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="used_pmtcttotal" name="used2"></td>
              <br>  
             </tr>
            </table>        			
        </div>
    
    <div id="step-3">	
            <h2 class="StepTitle"><font color="green">Tool Name => </font> Status of standard ART M&E tools.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Specification of M&E Tools Required</th> <th>Available</th> <th>In Use</th> <th>Filled Completely</th> <th>Correctly Used</th></tr>
            <c:forEach items="${editTools}" var="editTools">
            <c:set var="section" value="${editTools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("3")){%>
            <tr>  <input type="hidden" value="0" id="av_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="use_${editTools.section}${editTools.tool_id}">
             <input type="hidden" value="0" id="filled_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="used_${editTools.section}${editTools.tool_id}">
      <input type="hidden" name="specification${editTools.tool_id}" value="${editTools.id}">
                <td>${editTools.tool_name} </td>
                <td><select name="available${editTools.tool_id}" id="available${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_av();">
                         ${editTools.av}</select>
                </td>
                <td><select name="in_use${editTools.tool_id}" id="use${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_use();">
                        ${editTools.in_use}</select>
                </td>
                <td><select name="filled_completely${editTools.tool_id}" id="filled${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_filled();">
                       ${editTools.filled}</select>
                </td>
                <td><select name="correctly_used${editTools.tool_id}" id="used${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_used();">
                      ${editTools.used}</select>
                </td>
                
             </tr>
            
            <%}%>
            </c:forEach>
             
             <tr> 
                <td>TOTAL SCORE:</td>
                <td><p id="av_art" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="av_arttotal" name="av3"></td>
                <td><p id="use_art" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="use_arttotal" name="use3"></td>
                <td><p id="filled_art" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="filled_arttotal" name="filled3"></td>
                <td><p id="used_art" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="used_arttotal" name="used3"></td>
              <br>  
             </tr>
             
            </table>        			
        </div>
    
    <div id="step-4">	
            <h2 class="StepTitle"><font color="green">Tool Name => </font> Status of standard ART M&E tools.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Specification of M&E Tools Required</th> <th>Available</th> <th>In Use</th> <th>Filled Completely</th> <th>Correctly Used</th></tr>
            <c:forEach items="${editTools}" var="editTools">
            <c:set var="section" value="${editTools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("4")){%>
            <tr>  <input type="hidden" value="0" id="av_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="use_${editTools.section}${editTools.tool_id}">
             <input type="hidden" value="0" id="filled_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="used_${editTools.section}${editTools.tool_id}">
      <input type="hidden" name="specification${editTools.tool_id}" value="${editTools.id}">
                <td>${editTools.tool_name} </td>
                <td><select name="available${editTools.tool_id}" id="available${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_av1();">
                         ${editTools.av}</select>
                </td>
                <td><select name="in_use${editTools.tool_id}" id="use${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_use1();">
                        ${editTools.in_use}</select>
                </td>
                <td><select name="filled_completely${editTools.tool_id}" id="filled${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_filled1();">
                       ${editTools.filled}</select>
                </td>
                <td><select name="correctly_used${editTools.tool_id}" id="used${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_used1();">
                      ${editTools.used}</select>
                </td>
                
             </tr>
            
            <%}%>
            </c:forEach>
             <tr> 
                <td>TOTAL SCORE:</td>
                <td><p id="av_art1" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="av_arttotal1" name="av_arttotal"></td>
                <td><p id="use_art1" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="use_arttotal1" name="use_arttotal"></td>
                <td><p id="filled_art1" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="filled_arttotal1" name="filled_arttotal"></td>
                <td><p id="used_art1" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="used_arttotal1" name="used_arttotal"></td>
              <br>  
             </tr>
            </table>        			
        </div>
    
    <div id="step-5">	
            <h2 class="StepTitle"><font color="green">Tool Name => </font> Status of standard TB M&E tools.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Specification of M&E Tools Required</th> <th>Available</th> <th>In Use</th> <th>Filled Completely</th> <th>Correctly Used</th></tr>
            <c:forEach items="${editTools}" var="editTools">
            <c:set var="section" value="${editTools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("5")){%>
            <tr>  <input type="hidden" value="0" id="av_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="use_${editTools.section}${editTools.tool_id}">
             <input type="hidden" value="0" id="filled_${editTools.section}${editTools.tool_id}">
            <input type="hidden" value="0" id="used_${editTools.section}${editTools.tool_id}">
            <input type="hidden" name="specification${editTools.tool_id}" value="${editTools.id}">
                <td>${editTools.tool_name} </td>
                <td><select name="available${editTools.tool_id}" id="available${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_av();">
                        ${editTools.av}</select>
                </td>
                <td><select name="in_use${editTools.tool_id}" id="use${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_use();">
                         ${editTools.in_use}</select>
                </td>
                <td><select name="filled_completely${editTools.tool_id}" id="filled${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_filled();">
                        ${editTools.filled}</select>
                </td>
                <td><select name="correctly_used${editTools.tool_id}" id="used${editTools.section}${editTools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_used();">
                       ${editTools.used}</select>
                </td>
                
             </tr>
            
            <%}%>
            </c:forEach>
               <tr> 
                <td>TOTAL SCORE:</td>
                <td><p id="av_tb" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="av_tbtotal" name="av4"></td>
                <td><p id="use_tb" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="use_tbtotal" name="use4"></td>
                <td><p id="filled_tb" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="filled_tbtotal" name="filled4"></td>
                <td><p id="used_tb" style="color: #fb3500; font-size: large; margin-top: -5px;"></p><input type="hidden" value="0" id="used_tbtotal" name="used4"></td>
              <br>  
             </tr>
            </table>        			
        </div>
    
  		<br><br></div>
  		
<!-- End SmartWizard Content -->  		
  		
</td></tr>
</table>  
                       
                            
                           
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                        
                  
                    </form>
                </div>
          
            </div>
<div id="footer">
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>
