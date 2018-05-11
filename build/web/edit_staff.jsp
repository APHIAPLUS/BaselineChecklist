<%-- 
    Document   : edit_staff
    Created on : May 23, 2014, 9:00:20 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="database.dbConn"%>
<%@page import="java.util.Calendar"%>
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
<!--        <link rel="stylesheet" type="text/css" href="css/select.css"/>-->
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Edit Both MOH and APHIA Staff details.</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  

         <!--calender-->
<script type ="text/javascript" src="js/datepicker/min.js"></script>
<script type ="text/javascript" src="js/datepicker/jquery.jdpicker.js"></script>
<link rel="stylesheet" href="js/datepicker/jdpicker.css" type="text/css" media="screen" /> 
 
<!--noty-->
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
 	<style type="text/css">
		label {margin-right:20px;}
	</style>   
        <script type="text/javascript">
             function load_counties(){
                
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
document.getElementById("county").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_counties",true);
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
        function hidder(id){
          var  staffType=id.value;
     if(staffType=="1"){
       document.getElementById("tr2").setAttribute("hidden",true);
       document.getElementById("county").removeAttribute("required");
     }
     else{
       document.getElementById("county").setAttribute("required",true);
       document.getElementById("tr2").removeAttribute("hidden");   
     }
        }
      </script>  
    </head>
    <body onload="load_counties();">
       
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Select County and Type Of Staff to edit details. <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>
                   
            <div id="content" style="height:auto; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%> <br><br>
                
                <div id="midcontent" style="margin-left:200px ; height:auto;" class="containers" >
                
                    <%if (session.getAttribute("mail") != null) {



                    %>
                    <script type="text/javascript"> 
                    
                                       var n = noty({
                                           text: '<%=session.getAttribute("mail")%>',
                                           layout: 'center',
                                           type: 'Success',
                                           timeout:3000});
                    
                    </script>
                    <%
                            session.removeAttribute("mail");
                        }

                    %>
             
<div id="dialog" title="Edit Staff Help." style=" font-size: 17px;">
<p>Choose the county and the staff type you need to edit e.g APHIA or MOH Staff that you want to edit. </p>

</div>
                    <form action="edit_staff" method="post" style="margin-left:100px;height:auto; width: 500px;">
                        <br/>

                        <table> 
                            
                            
                         <tr>
                                <td class="align_button_left"><label for="first_name">Choose Staff Type:<font color="red">*</font></label></td>
                                <td>
                                    <select name="staff_type" id="staff_type" required class="textbox2" onchange="hidder(this);"> 
                                        <option value="">Choose Staff Type</option>
                                        <option value="1">APHIA Staff</option>
                                        <option value="2">MOH Staff</option>
                                 </select>
                                </td></tr> <tr> </tr>
                         
                             <tr id="tr2" hidden="true">
                                <td class="align_button_left"><label for="first_name">Choose County:<font color="red">*</font></label></td>
                                <td>
                                    <select name="county_id" id="county" required class="textbox2"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td></tr><tr> </tr>
                       
                       
                            <tr>          
                                <td></td>
                               
                                <td>
                                    <input type="submit" value="Next" style="height:30px; width:100px; background-color: plum;" class="textbox"/>
                                </td>
                            </tr>
                            
                             <tr>          
                                <td></td>
                               
                                <td>
                                   
                                </td>
                            </tr>
                        </table>
                    </form><br><br>
                </div>
<br><br>
            </div>
<div id="footer">
                                          <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>
