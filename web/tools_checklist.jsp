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
   <link rel="stylesheet" href="select2/css/select2.css">
   
<script type="text/javascript" src="calculator/avail.js"></script>
<script type="text/javascript" src="calculator/correctly_used.js"></script>
<script type="text/javascript" src="calculator/filled_completely.js"></script>
<script type="text/javascript" src="calculator/in_use.js"></script>

<!--WIZARD-->

<link href="styles_wiz/demo_style.css" rel="stylesheet" type="text/css">

<link href="styles_wiz/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="js_wiz/jquery-2.0.0.min.js"></script>-->
<script type="text/javascript" src="js_wiz/jquery.smartWizard.js"></script>
<script type="text/javascript" src="js_wiz/validate_Tools.js"></script>
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
 }        
        </script> 
              <script type="text/javascript">
             function load_years(){
                
var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("year").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_years",true);
xmlhttp.send();
            }
            
                         function load_months(yr){
 var year=yr.value;               
var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("month").innerHTML=xmlhttp.responseText;
 $('#month').select2(); 
}
}
xmlhttp.open("POST","load_months?year="+year,true);
xmlhttp.send();
            }
  function load_message(){
 var year,month;
 year=document.getElementById("year").value;
  month=document.getElementById("month").value;
var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("info").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","check_siteExistence?year="+year+"&&month="+month+"&&source=tools",true);
xmlhttp.send();
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
    <body onload="return load_years();">
       
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
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                <!--<a href="all_indicators" style="width: 250px; margin-left: 800px;" class="linkstyle">Add Site Checklist</a>-->  
                <div id="midcontent" style="margin-left:50px ; height:auto; width: 950;" >
                 <%if (session.getAttribute("tools_added") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("tools_added")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("tools_added");
                            }

                        %>
<p style="color: red" id="showMessage"></p>
<div id="dialog" title="Tools Checklist Help(2/2)." style=" font-size: 17px; width: 300px;">
<p>This is the main entry page for the tools checklist form.</p>
<p><font color="red">NOTE</font>: Ensure you have selected the yea and the month before making any data entry.</p>
<p> The tools checklist form has 4 sections (M&E Tools). Upon filling each section, click next to fill another section.</p>
<p>Upon filling all sections, click Finish to save the data .</p>
<p>By default, the values are YES, Click on each to un-check as required.</p>
</div>
<form action="add_toolsvalue" method="post" style="width: 1000px;" onsubmit="return load_v();">
                       
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
                <br><%}%> 
    <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                       <tr>
                                <td class="align_button_left"><label for="first_name">Choose Assesment Year:<font color="red">*</font></label></td>
                                <td>
                                    <select name="year" id="year" required class="textbox2" onchange="return load_months(this);"> 
                                        <option value="">Choose Assessment Year</option>
                                 </select>
                                </td> <td class="align_button_left"><label for="first_name">Choose Assesment Month<font color="red">*</font></label></td>
                                
                       <td>
                           <select name="month" id="month" required class="textbox2" onchange="return load_message();"> 
                                        <option value="">Choose Assessment Month</option>
                                 </select>
                       </td><td><p id="info"></p></td>
                       
                       </tr></table><br>
    			
    
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
            <c:forEach items="${tools}" var="tools">
            <c:set var="section" value="${tools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("1")){%>
            <tr> <input type="hidden" value="0" id="av_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="use_${tools.section}${tools.tool_id}">
             <input type="hidden" value="0" id="htcf_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="used_${tools.section}${tools.tool_id}">
                <td>${tools.tool_sp}</td>
                <input type="hidden" name="specification${tools.tool_id}" value="${tools.tool_id}">
                <td><select name="available${tools.tool_id}" id="available${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htc_av();">
                        
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="in_use${tools.tool_id}" id="use${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htc_use();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="filled_completely${tools.tool_id}" id="htcf${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htcf();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="correctly_used${tools.tool_id}" id="used${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return htc_used();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
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
            <c:forEach items="${tools}" var="tools">
            <c:set var="section" value="${tools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("2")){%>
            <tr> <input type="hidden" value="0" id="av_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="use_${tools.section}${tools.tool_id}">
             <input type="hidden" value="0" id="filled_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="used_${tools.section}${tools.tool_id}">
          <input type="hidden" name="specification${tools.tool_id}" value="${tools.tool_id}">
                <td>${tools.tool_sp} </td>
                <td><select name="available${tools.tool_id}" id="available${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_av();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="in_use${tools.tool_id}" id="use${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_use();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="filled_completely${tools.tool_id}" id="filled${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_filled();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="correctly_used${tools.tool_id}" id="used${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return pmtct_used();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
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
            <c:forEach items="${tools}" var="tools">
            <c:set var="section" value="${tools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("3")){%>
            <tr>  <input type="hidden" value="0" id="av_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="use_${tools.section}${tools.tool_id}">
             <input type="hidden" value="0" id="filled_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="used_${tools.section}${tools.tool_id}">
      <input type="hidden" name="specification${tools.tool_id}" value="${tools.tool_id}">
                <td>${tools.tool_sp} </td>
                <td><select name="available${tools.tool_id}" id="available${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_av();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="in_use${tools.tool_id}" id="use${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_use();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="filled_completely${tools.tool_id}" id="filled${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true"  onchange="return art_filled();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="correctly_used${tools.tool_id}" id="used${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_used();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
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
            <c:forEach items="${tools}" var="tools">
            <c:set var="section" value="${tools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("4")){%>
             <tr>  <input type="hidden" value="0" id="av_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="use_${tools.section}${tools.tool_id}">
             <input type="hidden" value="0" id="filled_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="used_${tools.section}${tools.tool_id}">
            <input type="hidden" name="specification${tools.tool_id}" value="${tools.tool_id}">
                <td>${tools.tool_sp} </td>
                <td><select name="available${tools.tool_id}" id="available${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_av1();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="in_use${tools.tool_id}" id="use${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_use1();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="filled_completely${tools.tool_id}" id="filled${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_filled1();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="correctly_used${tools.tool_id}" id="used${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return art_used1();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
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
            <c:forEach items="${tools}" var="tools">
            <c:set var="section" value="${tools.section}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("5")){%>
            <tr>  <input type="hidden" value="0" id="av_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="use_${tools.section}${tools.tool_id}">
             <input type="hidden" value="0" id="filled_${tools.section}${tools.tool_id}">
            <input type="hidden" value="0" id="used_${tools.section}${tools.tool_id}">
            <input type="hidden" name="specification${tools.tool_id}" value="${tools.tool_id}">
                <td>${tools.tool_sp} </td>
                <td><select name="available${tools.tool_id}" id="available${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_av();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="in_use${tools.tool_id}" id="use${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_use();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="filled_completely${tools.tool_id}" id="filled${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_filled();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
                </td>
                <td><select name="correctly_used${tools.tool_id}" id="used${tools.section}${tools.tool_id}" class="textbox2" style="width: 80px;height: 30px; border-color: peru;" required="true" onchange="return tb_used();">
<!--                        <option value="">Choose</option>-->
                        <option value="YES">YES</option>
                        <option value="NO">NO</option></select>
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
               <script src="select2/js/select2.js"></script>
   <script type="text/javascript">
 $(document).ready(function(){
 $('select').select2();    
 });   
</script>  
    </body>
</html>
