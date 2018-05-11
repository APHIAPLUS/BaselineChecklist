<%-- 
    Document   : select_editSite
    Created on : May 25, 2014, 8:21:33 PM
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
<!--        <link rel="stylesheet" type="text/css" href="css/select.css"/>-->
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Edit Site Checklist 1/2</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  

   <link rel="stylesheet" href="select2/css/select2.css">
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
	</script> 
        
        
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
  
              function load_aphia(){
                
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
document.getElementById("aphia").innerHTML=xmlhttp.responseText;
 $('#aphia').select2();  
}
}
xmlhttp.open("POST","load_APHIAStaff",true);
xmlhttp.send();
            }
            
                        function load_moh(){
                
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
document.getElementById("moh").innerHTML=xmlhttp.responseText;
 $('#moh').select2();  
}
}
xmlhttp.open("POST","load_MOHStaff",true);
xmlhttp.send();
            }
            
            
            
            function load_districts(ct){
    var county_id=ct.value;   
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
document.getElementById("district").innerHTML=xmlhttp.responseText;
 $('#district').select2();  
}
}
xmlhttp.open("POST","load_districts?county_id="+county_id,true);
xmlhttp.send();
            }   
            
            function load_hf(ct){
    var dist_id=ct.value;   
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
document.getElementById("hf").innerHTML=xmlhttp.responseText;
 $('#hf').select2();  
}
}
xmlhttp.open("POST","load_facilities?district_id="+dist_id,true);
xmlhttp.send();
            }   
            
        </script>
         <script type="text/javascript">
             function load_years(hf){
           var hf_id=hf.value;     
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
 $('#year').select2();  
}
}
xmlhttp.open("POST","data_years?hf_id="+hf_id+"&&source=site",true);
xmlhttp.send();
            }
            
                         function load_months(yr){
 var year=yr.value; 
 var hf_id=document.getElementById("hf").value;
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
xmlhttp.open("POST","data_month?year="+year+"&&hf_id="+hf_id+"&&source=site",true);
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
xmlhttp.open("POST","check_siteExistence?year="+year+"&&month="+month+"&&source=site",true);
xmlhttp.send();
            }
        </script>
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
    <body onload="load_counties();load_moh();load_aphia();">
       
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Select Details to edit Site Checklist.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>
                   
            <div id="content" style="height:auto; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%> <br><br>
                
                <div id="midcontent" style="margin-left:200px ; height:auto;" class="containers" >
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
                        <!--creating random user id-->
                         <%!
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
%>  
                        
 <div id="dialog" title="Edit Site Checklist Help." style=" font-size: 17px;">
<p>Select the details appropriately as indicated.</p>
<p><font color="red">NOTE</font>: The Years and months that appear are those whose data has already been entered to the system. If no data has been entered, go to the menu and select checklist form entry.</p>
<p>Click Next to move to the edit page.</p>
</div>                       
          
<form action="edit_sitevalue" method="post" onsubmit="return validator();">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:150px ;">
                       <tr>
                                <td class="align_button_left"><label for="first_name">Choose County:<font color="red">*</font></label></td>
                                <td>
                                    <select name="county_id" id="county" required class="textbox2" onchange="return load_districts(this);"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td></tr><tr> </tr>
                       
                       
                         <tr>
                                <td class="align_button_left"><label for="first_name">Choose District:<font color="red">*</font></label></td>
                                <td>
                                    <select name="district_id" id="district" required class="textbox2" onchange="return load_hf(this);"> 
                                        <option value="">Choose District</option>
                                 </select>
                                </td></tr><tr> </tr>
                        

                           <tr>
                                <td class="align_button_left"><label for="first_name">Choose Health Facility:<font color="red">*</font></label></td>
                                <td>
                                    <select name="hf_id" id="hf" required class="textbox2" onchange="return load_years(this);"> 
                                        <option value="">Choose facility</option>
                                 </select>
                                </td></tr><tr> </tr>
                          
                               
                                 <tr>
                                <td class="align_button_left"><label for="first_name">Choose Year:<font color="red">*</font></label></td>
                                <td><select name="year" id="year" required="true" class="textbox2" onchange="return load_months(this);"> 
                                        <option value="">Choose Assessed Year</option>
                                 </select></td></tr><tr> </tr>
                        
                                 <tr>
                                <td class="align_button_left"><label for="first_name">Choose Month:<font color="red">*</font></label></td>
                                <td><select name="month" id="month" required="true" class="textbox2" onchange=""> 
                                        <option value="">Choose Assessed Month</option>
                                 </select></td></tr><tr> </tr>
            
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                           <tr> 
                               <td class="align_button_left"></td> <td class="align_button_right">
                               <input type="submit" class="submit" value="Next" class="textbox"/></td>
                            </tr>
                        </table>
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
