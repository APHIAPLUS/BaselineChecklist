<%-- 
    Document   : upload_facilities
    Created on : Jun 10, 2014, 10:25:23 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
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
        <title>Upload Facilities.</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript">
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }
            } 
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
      
       <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
                   <script type="text/javascript">
function Validator(){
    var filename=document.getElementById("upload").value;
//    alert("here"+filename);
   var ext = /^.+\.([^.]+)$/.exec(filename);
var extension = null ? "" : ext[1]; 
  
//  alert("extension is   :"+extension)
  
    
    if (extension=="xls") {
//        var filesize=parseInt(document.getElementById("upload").files[0].size);
//       
       document.getElementById("namer").hidden=true;
       document.getElementById("loader").hidden=false;
       document.getElementById("loads").hidden=true;
//       document.getElementById("load").hidden=true;
}
else{
  alertify.alert("Incorrect Excel File.Please Choose the correct Excel file.");
  document.getElementById("loads").hidden=true;
    return false;   
}
}
function mwas(){
    alertify.alert("Message");
}
                </script> 
    </head>
    <body onload="no_of_classes(this);">
       
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Upload Health Facilities.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div> 
                   
            <div id="content" style="height:auto; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
               
    <div id="dialog" title="Upload Facility Help." style=" font-size: 17px;">
<p><font color="red">NOTE:</font> Please Ensure the columns are as described in this system help.</p>
<p>There should be four columns in your excel sheet with the following order of labels.<br>1. county name.<br>2. district name<br>3. health facility name<br>4. MFL Code.<br> Save it as an <em>excel 97-2003 document(.xls)</em> and <em>not as an excel document(.xlsx)</em></p>
<!--<p>Select the excel sheet and then click on upload to upload the health facilities in the excel to the system.</p>-->
 <p>It should be as shown here with the column headings. <br>
     <img src="images/upload_facility.PNG" title="UPLOAD GUIDE" style="width: 100%; height: 150px;"></p>
    </div>
                <div id="midcontent" style="margin-left:200px ; height:auto;" >
                 <%if (session.getAttribute("upload_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("upload_success")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 6800});
                    
                </script> <%
                session.removeAttribute("upload_success");
                            }

                        %>
                       
               <form style="height: 200px;" action="ExcelUpload" method="post" enctype="multipart/form-data" onsubmit="return Validator();">
     <br><br><div id="loader" hidden="true" style="left: 300px; height: 10px;">
         <img src='images/loaderExcel.gif' width='150px' height='150px'><br>
    </div><div id="namer">
<input type="file" name="file_name" id="upload" value="" class="textbox" required>   
    <br><br><br><br>
    <input type="submit" value="Upload" class="submit">
    <br><br></div>
      <% if (session.getAttribute("saved_success") != null) {
      if (session.getAttribute("saved_success").toString().equals("success")) {
    %>
    <p id="loads" style="font-size: 22px; margin-top: -50px;"><img  src="images/suc1.png" height="60px" width="60px" style="margin-left:550px; top: -10px;">Success.</p>
     
     <%}else if (session.getAttribute("saved_success").toString().equals("fail")){%>
  <p id="loads" style="font-size: 22px; margin-top: -50px;"><img  src="images/warning.png" height="60px" width="60px" style="margin-left:550px; top: -10px;">Failed.</p>
  <%}  session.removeAttribute("saved_success");  }%>
</form>
                </div>
          
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
