<%-- 
    Document   : MergeData
    Created on : May 27, 2014, 2:37:47 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null || (session.getAttribute("level").toString().equals("2") || session.getAttribute("level").toString().equals("5"))) {
      
        response.sendRedirect("index.jsp");
           }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.PNG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

<script src="prefix-free.js"></script>
   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>







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
  
//  alert("extension is   :  "+extension)
  
    
    if (extension=="sql" || extension=="SQL") {
        var filesize=parseInt(document.getElementById("upload").files[0].size);
//       
          document.getElementById("namer").hidden=true;
       document.getElementById("loader").hidden=false;
       document.getElementById("loads").hidden=true;
       document.getElementById("load").hidden=true;
}
else{
  alertify.alert("Incorrect Data File.Please Choose the correct data file.");
  document.getElementById("loads").hidden=true;
    return false;   
}
}
function mwas(){
    alertify.alert("Message");
}
                </script> 
        
<title>Merge Data</title>


</head>
<body>

    <div id="container" style=" height: auto">
   <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>

<div id="header" align="center">
<br/>
</div>
 <%if (session.getAttribute("datasend1") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend1")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800
                       } );
                    
                </script> <%
                session.removeAttribute("datasend1");
                            }

                        %>
                        

<div id="content" style="height:400px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Merge M&E Checklist Data.</div>


<div id="midcontent" style="margin-left:230px ; height: 300px;">
<div >    <h4 style=" font-size: 23px;">Merge Data.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div> 
    <div id="dialog" title="Merge Data Help." style=" font-size: 17px;">
<p>Choose the file that you need to upload, the file must be a .sql file. Click on merge to merge the data.</p>

</div>
<form style="height: 200px;" action="FileUploadServlet" method="post" enctype="multipart/form-data" onsubmit="return Validator();">
     <br><br><div id="loader" hidden="true" style="left: 300px; height: 10px;">
         <img src='images/ld.gif' width='150px' height='130px'><br>
    </div><div id="namer">
<input type="file" name="file_name" id="upload" value="" class="textbox" required>   
    <br><br><br><br>
    <input type="submit" value="Merge" class="submit">
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
               <p align="center" style=" font-size: 18px;"> &copyPWP System Aphia Plus | USAID <%=year%></p>
            </div>
</div>    

</body>
</html>
