<%-- 
    Document   : edit_siteChecklist
    Created on : May 14, 2014, 3:42:53 PM
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
        <title>Edit Site Checklist</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<!--WIZARD-->

<link href="styles_wiz/demo_style.css" rel="stylesheet" type="text/css">

<link href="styles_wiz/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="js_wiz/jquery-2.0.0.min.js"></script>-->
<script type="text/javascript" src="js_wiz/jquery.smartWizard.js"></script>
<script type="text/javascript" src="js_wiz/validateSite.js"></script>
 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script> 
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
    <body onload="">
       
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Enter Indicator Values.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>          
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                <div id="dialog" title="Edit Site Checklist Help(2/2)." style=" font-size: 17px;">
<p>Make Correction to the required element and then click on Finish to Save the data.</p>
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
                    <form action="save_editedSite" method="post" style="width: 1000px;">
                       
                      <table align="center" border="0" cellpadding="0" cellspacing="0">
                          
<tr><td> 
<!-- Tabs -->
<div id="wizard" class="swMain" style="height: auto;">
                         <%if(session.getAttribute("hf_name")!=null){%> 
    <table style="width: 100%" class="table_style">
                 <tr><th><font color="black">Health Facility Name:</font><font color="blue-violet"> <%=session.getAttribute("hf_name").toString()%></font></th>
                    <th><font color="black">Name of MOH Staff:</font> <font color="blue-violet"><%=session.getAttribute("moh_staff_name").toString()%></font></th> 
                    <th><font color="black">Name of APHIA Staff:</font> <font color="blue-violet"><%=session.getAttribute("aphia_staff_name").toString()%></font></th> 
                    <th><font color="black">Date of Visit:</font> <font color="blue-violet"><%=session.getAttribute("date").toString()%></font></th> 
                </tr>
             </table>   
    <br>
    
 	<%}%>		
    
    <ul>
                            <li style="font-size: 25px;font-family: sans-serif; color: black; margin-left: 10px;">PROGRAM AREA.</li>
                            <li style="height: auto;"><a href="#step-1">
                <label class="stepNumber">1</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small style="font-size: 22px;"><b>HTC-PITC & VCT</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-2">
                <label class="stepNumber">2</label>
                <span class="stepDesc">
<!--                   Step 2<br />-->
            <small style="font-size: 14px;"><b>PMTCT-ANC and Labour and Delivery</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-3">
                <label class="stepNumber">3</label>
                <span class="stepDesc">
<!--                   Step 3<br />-->
                   <small style="font-size: 22px;"><b>Care and Treatment</b></small>
                </span>                   
             </a></li>
  				<li style="height: auto;"><a href="#step-4">
                <label class="stepNumber">4</label>
                <span class="stepDesc">
<!--                   Step 4<br />-->
                  <small style="font-size: 22px;"><b>TB/HIV</b></small>
                </span>                   
            </a></li>
  			</ul>
  			<div id="step-1">	
            <h2 class="StepTitle"><font color="green">Source Documents => </font> HTC register (MOH 362) and MOH 711A & 731.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Indicator Name</th> <th>Recounted Data</th> <th>Reported in 711A</th> <th>Reported in 731</th> <th>Reported in DHIS</th></tr>
            <c:forEach items="${editSite}" var="editSite">
            <c:set var="section" value="${editSite.section}"></c:set>
            <c:set var="rc" value="${editSite.recounted_data}"></c:set>
            <c:set var="sc711" value="${editSite.re_711A}"></c:set>
            <c:set var="sc731" value="${editSite.re_731}"></c:set>
            <c:set var="sec_dhis" value="${editSite.re_DHIS}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("1")){%>
            <tr> 
                <td>${editSite.indicator_name} </td><input type="hidden" name="indicator_id${editSite.indicator_id}" value="${editSite.indicator_id}">
                <% if(!pageContext.getAttribute("rc").toString().equals("")){%>
                <td><input type="text" class="textbox" name="recounted_data${editSite.indicator_id}" id="rc${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.recounted_data}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc711").toString().equals("")){%>
                <td><input type="text"  class="textbox" name="reported_711A${editSite.indicator_id}" id="sc711${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_711A}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc731").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_731${editSite.indicator_id}" id="sc731${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_731}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sec_dhis").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_DHIS${editSite.indicator_id}" id="dhis${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_DHIS}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
            </tr>
            
            <%}%>
            </c:forEach>
            </table>        			
        </div>
  			<div id="step-2">
         
            <h2 class="StepTitle"><font color="green">Source Documents => </font> ANC register [405], Maternity Register [MOH 433] and  MOH 711A &731.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Indicator Name</th> <th>Recounted Data</th> <th>Reported in 711A</th> <th>Reported in 731</th> <th>Reported in DHIS</th></tr>
            <c:forEach items="${editSite}" var="editSite">
            <c:set var="section" value="${editSite.section}"></c:set>
            <c:set var="rc" value="${editSite.recounted_data}"></c:set>
            <c:set var="sc711" value="${editSite.re_711A}"></c:set>
            <c:set var="sc731" value="${editSite.re_731}"></c:set>
            <c:set var="sec_dhis" value="${editSite.re_DHIS}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("2")){%>
            <tr>
                <td>${editSite.indicator_name} </td><input type="hidden" name="indicator_id${editSite.indicator_id}" value="${editSite.indicator_id}">
                <% if(!pageContext.getAttribute("rc").toString().equals("")){%>
              <td><input type="text" class="textbox" name="recounted_data${editSite.indicator_id}" id="rc${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.recounted_data}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc711").toString().equals("")){%>
                <td><input type="text"  class="textbox" name="reported_711A${editSite.indicator_id}" id="sc711${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_711A}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc731").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_731${editSite.indicator_id}" id="sc731${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_731}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sec_dhis").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_DHIS${editSite.indicator_id}" id="dhis${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_DHIS}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
             </tr>
            
            <%}%>
            </c:forEach>
            </table>           
        </div>                      
  			<div id="step-3">
            <h2 class="StepTitle"><font color="green">Source Documents => </font> Daily activity register for ART and MOH 731.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Indicator Name</th> <th>Recounted Data</th> <th>Reported in 711A</th> <th>Reported in 731</th> <th>Reported in DHIS</th></tr>
            <c:forEach items="${editSite}" var="editSite">
            <c:set var="section" value="${editSite.section}"></c:set>
            <c:set var="rc" value="${editSite.recounted_data}"></c:set>
            <c:set var="sc711" value="${editSite.re_711A}"></c:set>
            <c:set var="sc731" value="${editSite.re_731}"></c:set>
            <c:set var="sec_dhis" value="${editSite.re_DHIS}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("3")){%>
            <tr>
                <td>${editSite.indicator_name} </td><input type="hidden" name="indicator_id${editSite.indicator_id}" value="${editSite.indicator_id}">
                <% if(!pageContext.getAttribute("rc").toString().equals("")){%>
                <td><input type="text" class="textbox" name="recounted_data${editSite.indicator_id}" id="rc${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.recounted_data}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc711").toString().equals("")){%>
                <td><input type="text"  class="textbox" name="reported_711A${editSite.indicator_id}" id="sc711${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_711A}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc731").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_731${editSite.indicator_id}" id="sc731${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_731}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sec_dhis").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_DHIS${editSite.indicator_id}" id="dhis${editSite.section}${editSite.indicator_id}" required="true" value="${editSite.re_DHIS}" maxlength="10" onkeypress="return numbers(event)" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
            </tr>
            
            <%}%>
            </c:forEach>
            </table>                         				          
        </div>
  			<div id="step-4">
            <h2 class="StepTitle"><font color="green">Source Documents => </font> TB register and MOH 711A.</h2>
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
    <col width="70px"> 
                <tr><th>Indicator Name</th> <th>Recounted Data</th> <th>Reported in 711A</th> <th>Reported in 731</th> <th>Reported in DHIS</th></tr>
            <c:forEach items="${editSite}" var="editSite">
            <c:set var="section" value="${editSite.section}"></c:set>
            <c:set var="rc" value="${editSite.recounted_data}"></c:set>
            <c:set var="sc711" value="${editSite.re_711A}"></c:set>
            <c:set var="sc731" value="${editSite.re_731}"></c:set>
            <c:set var="sec_dhis" value="${editSite.re_DHIS}"></c:set>
            <% if(pageContext.getAttribute("section").toString().equals("4")){%>
            <tr>
                <td>${editSite.indicator_name} </td><input type="hidden" name="indicator_id${editSite.indicator_id}" value="${editSite.indicator_id}">
                <% if(!pageContext.getAttribute("rc").toString().equals("")){%>
                <td><input type="text" class="textbox" name="recounted_data${editSite.indicator_id}" id="rc${editSite.section}${editSite.indicator_id}"  value="${editSite.recounted_data}" maxlength="10" onkeypress="return numbers(event)" required="true" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc711").toString().equals("")){%>
                <td><input type="text"  class="textbox" name="reported_711A${editSite.indicator_id}" id="sc711${editSite.section}${editSite.indicator_id}"  value="${editSite.re_711A}" maxlength="10" onkeypress="return numbers(event)" required="true" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sc731").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_731${editSite.indicator_id}" id="sc731${editSite.section}${editSite.indicator_id}"  value="${editSite.re_731}" maxlength="10" onkeypress="return numbers(event)" required="true" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
                
                <% if(!pageContext.getAttribute("sec_dhis").toString().equals("")){%>
                <td><input type="text" class="textbox" name="reported_DHIS${editSite.indicator_id}" id="dhis${editSite.section}${editSite.indicator_id}"  value="${editSite.re_DHIS}" maxlength="10" onkeypress="return numbers(event)" required="true" style=" border-color: plum; width: 80px; height: 20px;"> </td> <%} else{%><td> </td> <%}%>
            </tr>
            
            <%}%>
            </c:forEach>
            </table>                        			
        </div>
  		</div>
  		
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
