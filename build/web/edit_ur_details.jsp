<%-- 
    Document   : edit_ur_details
    Created on : May 19, 2014, 9:25:52 AM
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
        <title>Edit User Profile.</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  

         <!--calender-->
<script type ="text/javascript" src="js/datepicker/min.js"></script>
<script type ="text/javascript" src="js/datepicker/jquery.jdpicker.js"></script>
<link rel="stylesheet" href="js/datepicker/jdpicker.css" type="text/css" media="screen" /> 
 <script>
	$(function() {
                 var dateObject,day,month,year,current_date;
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=day+"/"+month+"/"+year; 
   
   
       $('#datepicker').jdPicker({
     date_format:"dd/mm/YYYY",
     show_week:1,
     week_label:"we",
     start_of_week:3,
     date_min:"19/05/2010",
     date_max:current_date,
     selectable_days:[1, 2, 3, 4, 5]
//     non_selectable:["May 24 2000"],
//     rec_non_selectable: ["Jan 01", "May 26"]
}); 
$('#test2').jdPicker({
     date_format:"dd/mm/YYYY",
     select_week:1,
     show_week:1,
     date_min:"19/05/2010",
     date_max:current_date
}); 
	});
        
        
  function phone_validator(){
    var phone=document.getElementById("phone").value;
var res=""
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
document.getElementById("phonez").textContent=xmlhttp.responseText;
res=document.getElementById("phonez").textContent;

                    if(res.match("The Phone Number is Ok") || phone==""){

    document.getElementById("sub").disabled=false;   
}
else{
 document.getElementById("sub").disabled=true;
 document.getElementById("phone").focus();
}
}
}
xmlhttp.open("POST","phone_validator?phone="+phone,true);
xmlhttp.send();   
  }          
	</script>  


<!--noty-->
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
 	<style type="text/css">
		label {margin-right:20px;}
	</style>   
        
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

    </head>
      <body onload="phone_validator();">

     <%
     String fname="",mname="",lname="",phone="",usern="";
//     String userid=session.getAttribute("userid").toString();
      String userid=session.getAttribute("userid").toString();
     dbConn conn = new dbConn();

     String select_user_details="SELECT username,fname,lname,phone "
 + "FROM users WHERE userid='"+userid+"'";
     conn.rs=conn.st.executeQuery(select_user_details);
    if(conn.rs.next()==true){
        fname=conn.rs.getString(2); 
        lname=conn.rs.getString(3);        
         usern=conn.rs.getString(1);
         phone=conn.rs.getString(4);
//         out.println(fname);
     }
             
conn.rs.close();
%>   
     <div id="container" style="height:640px;" >
    <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>


        <%if (session.getAttribute("det_update") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("det_update")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("det_update");
                            }

                        %>       
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Edit Your Profile. </div>
                   
            <div id="content" style="height:auto; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                
                <div id="midcontent" style="margin-left:200px ; height:auto;">
<h3><p align="center">All the fields marked <font color='red'>*</font> must be filled.</p></h3> 
    <br>
    <form action="save_ur_details" method="POST" style=" position: absolute; margin-left:-50px; width: 780px;">
        <input type="hidden" name="userid" value="<%=userid%>">
<table style="margin-left: 150px; font-size: 18px; width: 400px;">
    <tr><td>First Name <font color='red'>*</font></td><td colspan="2"><input type="text" name="fname" id="fname" value="<%=fname%>" class="textbox1" style="border-color: peru; width:200px;" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Last Name <font color='red'>*</font></td><td colspan="2"><input type="text" name="lname" id="lname" value="<%=lname%>" class="textbox1" style="border-color: peru; width:200px;" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Phone No.</td><td colspan="2"><input type="text" name="phone" maxlength="10" id="phone" pattern="[0-9]{10}" value="<%=phone%>" class="textbox1" style="border-color: peru; width:200px;" autocomplete="off" onkeyup="return phone_validator();"></td><td></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Username <font color='red'>*</font></td><td colspan="2"><input type="text" name="username" id="username" value="<%=usern%>" class="textbox1" style="border-color: peru; width:200px;" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>New Password <font color='red'>*</font></td><td colspan="2"><input type="password" name="pass" id="password" value="" class="textbox1" style="border-color: peru; width:200px;" oninput="checkPasswords()" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td>Confirm Password<font color='red'>*</font></td><td colspan="2"><input type="password" name="pass2" id="conf_password" class="textbox1" style="border-color: peru; width:200px;" oninput="checkPasswords()" value="" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td colspan="3"><input type="submit" id="sub" disabled="true" value="Save" style="margin-left: 130px; background-color: plum;" class="textbox1"></td></tr>
</table>
<p id="phonez" name="phonez" style=" top: 200px; left: 400px; color: red"></p> 
    </form><br><br>
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
