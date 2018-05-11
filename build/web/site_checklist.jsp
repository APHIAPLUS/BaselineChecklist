<%-- 
    Document   : site_checklist
    Created on : May 9, 2014, 8:30:26 AM
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
    <%
    int maxYear=0,maxMonth=0;
    if(session.getAttribute("year_visit")!=null){
     maxYear=Integer.parseInt(session.getAttribute("year_visit").toString());
     maxMonth=Integer.parseInt(session.getAttribute("month_visit").toString());
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Site Checklist</title>
      
	<!--<script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
        <script type ="text/javascript" src="js/datepicker/min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
 <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <script src="menu/prefix-free.js"></script>  
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
   <link rel="stylesheet" href="select2/css/select2.css">
<!--WIZARD-->

<link href="styles_wiz/demo_style.css" rel="stylesheet" type="text/css">

<link href="styles_wiz/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="js_wiz/jquery-2.0.0.min.js"></script>-->
<script type="text/javascript" src="js_wiz/jquery.smartWizard.js"></script>

<script type="text/javascript" src="js_wiz/validate_all_steps.js"></script>
<script type="text/javascript">
   
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>
 
 <!--calender-->
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
//   alert("current date : "+current_date);
    current_date="00/"+<%=maxMonth+1%>+"/"+<%=maxYear%>; 
//   alert("current date : "+current_date);
       $('#date').jdPicker({
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
//$('#test2').jdPicker({
//     date_format:"dd/mm/YYYY",
//     select_week:1,
//     show_week:1,
//     date_min:"19/05/2010",
//     date_max:current_date
//}); 
	});
	</script> 
        
        


<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript"> 
$(function(){
$( "#datepicker" ).datepicker();
});

            
        </script> 
      <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
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
$('#year').select2(); 
load_months();

}
}
xmlhttp.open("POST","load_years",true);
xmlhttp.send();
            }
            
                         function load_months(){
 var year=document.getElementById("year").value;   
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
// setSession();
}
}
xmlhttp.open("POST","load_months?year="+year,true);
xmlhttp.send();
            }
          function load_dates(){ 
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
document.getElementById("date").value=xmlhttp.responseText;
//alert("called");
//setSession();
}
}
xmlhttp.open("POST","loadFacilDATE",true);
xmlhttp.send();
            }  
   
   
    function loadData(){
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
 document.getElementById("data").innerHTML=xmlhttp.responseText;   
$('#wizard').smartWizard({transitionEffect:'slide'});
location.reload();
}
}
xmlhttp.open("POST","siteChecker",true);
xmlhttp.send();
  
  
    }
  
    
     function getFacility(){
var district=document.getElementById("district").value;
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

document.getElementById("hf_id").innerHTML=xmlhttp.responseText;    
 $('#hf_id').select2();
setSession();

}
}
xmlhttp.open("POST","facilDistrict?district="+district,true);
xmlhttp.send();
            
    }
  
  
  function getMOH(){
   
var district_id=document.getElementById("district").value;
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
xmlhttp.open("POST","loadFacilMOH?district_id="+district_id,true);
xmlhttp.send();
            
    }
  
      function getAPHIA(){
          
var district_id=document.getElementById("district").value;

$.ajax({
                    url:"loadFaciAPHIA?district_id="+district_id,                            
                    type:'post',  
                    dataType: 'html',  
                    success: function(data) {
                        
                        document.getElementById("aphia").innerHTML=data;    
                      $('#aphia').select2(); 
                        
                    }});


            
    }
    
    function setMaxYearMonth(){
var date=document.getElementById("date").value;
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
load_years();
load_months();
}
}
xmlhttp.open("POST","setMaxYearMonth?date="+date,true);
xmlhttp.send();
            
    }
    
    function getDistricts(){
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
getFacility();
$('#district').select2();
}
}
xmlhttp.open("POST","getDistricts",true);
xmlhttp.send();
            
    }
    
    function setSession(){

var year="",month="",hf_id="",moh="",aphia="",date="",dist="";
year=document.getElementById("year").value;
month=document.getElementById("month").value;
hf_id=document.getElementById("hf_id").value;
moh=document.getElementById("moh");
aphia=document.getElementById("aphia").value;
date=document.getElementById("date").value;
dist=document.getElementById("district").value;

var selectedMOH ="";
    for (var i = 0; i < moh.length; i++) {
        if (moh.options[i].selected){
            selectedMOH+=moh.options[i].value+",";
        }
    }
    
var yearSession,monthSession,hf_idSession,mohSession,aphiaSession,dateSession,dist_idSession;
yearSession=monthSession=hf_idSession=mohSession=aphiaSession=dateSession=dist_idSession="";

<%if(session.getAttribute("assessYear")!=null){%>
yearSession="<%=session.getAttribute("assessYear").toString()%>";
<%}%>
    
    <%if(session.getAttribute("assessMonth")!=null){%>
monthSession="<%=session.getAttribute("assessMonth").toString()%>";
<%}%>
    
    <%if(session.getAttribute("hf_id")!=null){%>
hf_idSession="<%=session.getAttribute("hf_id").toString()%>";
<%}%>
    
     <%if(session.getAttribute("dist_id")!=null){%>
dist_idSession="<%=session.getAttribute("dist_id").toString()%>";
<%}%>
    
    <%if(session.getAttribute("moh_staff")!=null){%>
mohSession="<%=session.getAttribute("moh_staff")%>";
<%}%>
    
    
    <%if(session.getAttribute("aphia_staff")!=null){%>
aphiaSession="<%=session.getAttribute("aphia_staff")%>";
<%}%>
 
 <%if(session.getAttribute("date")!=null){%>
dateSession="<%=session.getAttribute("date")%>";
<%}else{}%>  
//  alert("hf: "+hf_id+" hf sess : "+hf_idSession+" year : "+year+" year sess : "+yearSession+" month : "+month+" month sess :  "+monthSession+" moh : "+selectedMOH+" moh sess : "+mohSession+" aphia : "+aphia+" aphia sess : "+aphiaSession+" date : "+date+" date sess : "+dateSession);

//alert("select 1 : "+moh+" all selected : "+selectedMOH);
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
//alert("returned");
//if(year!=="" && month!=="" && hf_id!=="" && aphia!=="" && date!==""){
if(hf_id===""){
document.getElementById("data").innerHTML="<p style=\"margin-top:100px;\">No data to display. Please select Health facility.</p>"; 
}    

if(year!=="" && month!=="" && hf_id!==""){
  if(aphia===""){
             document.getElementById("data").innerHTML="<p style=\"margin-top:100px;\">No data to display. Please select APHIA Staff.</p>"; 
                }
                else{
     if(hf_id!==hf_idSession || year!==yearSession || month!==monthSession || selectedMOH!==mohSession || aphia!==aphiaSession || date!==dateSession){   
loadData();  
}
  }
}
}
}
xmlhttp.open("POST","setSessions?year="+year+"&&month="+month+"&&hf_id="+hf_id+"&&moh="+selectedMOH+"&&aphia="+aphia+"&&date="+date+"&&district="+dist, true);
xmlhttp.send();

 }

    
    function getData(){
        <%if(session.getAttribute("savedChecklist")!=null){%>
      loadData();    
     <% }%>  
    }

  function load_message(){
 var year,month;
 var rd,r731,rdhis;
 year=document.getElementById("year").value;
  month=document.getElementById("month").value;
  if(month==="3" || month==="6" || month==="9" || month==="12"){
      document.getElementById("q118").innerHTML="<input type=\"text\"  class=\"textbox\" name=\"recounted_data18\" id=\"rc318\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\">";   
      document.getElementById("q318").innerHTML="<input type=\"text\"  class=\"textbox\" name=\"reported_73118\" id=\"sc318\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\">";
      document.getElementById("q418").innerHTML="<input type=\"text\"  class=\"textbox\" name=\"reported_DHIS18\" id=\"dhis318\" required value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\" border-color: plum; width: 80px; height: 20px;\">";
      
  }
  else{
     
     document.getElementById("q118").innerHTML="";  
     document.getElementById("q318").innerHTML="";  
     document.getElementById("q418").innerHTML="";
  }
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

       
       function confirmMOH(){
           if (confirm('Some message')) {
    alert('Thanks for confirming');
} else {
    alert('Why did you press cancel? You should have confirmed');
}
       }
//             window.setInterval(refresher, 10000)
        </script>
      <!--<script type="text/javascript" src="js_wiz/validate_all_steps.js"></script>-->
  
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
      
      
          <script>
              function getAnother(){
  $(function() {
    $( "#dialog-confirm" ).dialog({
      resizable: false,
//      height:140,
width:500,
      modal: true,
      buttons: {
        "Yes": function() {
          $( this ).dialog( "close" );
          
        },
        "NO": function() {
          $( this ).dialog( "close" );
         setSession(); 
        }
      }
    });
  });
              }
              
              
              
              function setDist(){
                  
                 var facil=$("#hf_id").val();
                 $.ajax({
                     url:"loaddist?facilid="+facil,
                     type:'POST',
                     dataType:'html',r
                     success: function(data){
                         
                      $("#district").html(data);   
                      //document.getElementById("district").value=data;   
                        $('#district').select2();    
                     }
                 })
                 
                  
                  
              }
  </script>
    </head>
    <body>
       
     <div id="container" style="height:auto;  width:98%;" >
   <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>


              <div id="header" align="center">   
              </div>
            
<div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #3dff70; font-size: 24px; text-align: center;">Enter Site Indicator Values.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>    
            <div id="content" style="height:auto; width: 98%;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>  
                    
                </div> <%}%>
                <a href="all_tools" style="width: 250px; margin-left: 800px;" class="linkstyle">Add Tools Checklist</a>
                <div id="midcontent" style="margin-left:10px ; height:auto; width:98%;" >
                 <%if (session.getAttribute("sites_added") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("sites_added")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("sites_added");
                            }
%>
<p id="showMessage" style="background-color: black"></p>
<!--<br>-->
<div id="dialog" title="Site Checklist Help(1/2)." style=" font-size: 17px;">
<p>This is the main entry page for the site checklist form.</p>
<p><font color="red">NOTE</font>: Ensure you have selected the year and the month before making any data entry.</p>
<p> The site checklist form has 4 sections (Program areas). Upon filling each section, click next to fill another section.</p>
<p>Upon filling all sections, click Finish to save the data .</p>
<p>The form will re- appear again twice for data entry. If you don't want to enter the form trice, click on add tools checklist to enter the tools form.</p>
</div>
<div id="dialog-confirm" title="Add/Remove MOH" hidden="true" style="height:auto;">
  <p><span class="ui-icon ui-icon-alert"></span>Do you want to add/remove another MOH staff?</p>

</div>

    <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                      <tr>
                           
                           
                                <td>
                               <b>Date of Verification.</b> </td>
                                <!--<td class="align_button_left"><label for="first_name">Choose Year:<font color="red">*</font></label></td>-->
                                <td><b>
                               Assessment Year.</b>
                                </td>
                                
                                <!--<td class="align_button_left"><label for="first_name">Choose Month<font color="red">*</font></label></td>-->
                                
                       <td>
                        <b>Assessment Month.</b>
                       </td>
                       
                       
                       <td>
                      <b>District.</b>
                                </td>
                                
                                
                        <td>
                           <b>Health Facility.</b>
                                </td>
                       
                                <td>
                                 <b>MOH Staff.</b>
                                </td>
                            
                                 <td>
                                 <b>APHIA Staff.</b>
                                </td>
                            
                       
                       <td><p id="info"></p> </td>
                       
                       </tr>
        
        
        
        <tr>
                           
                           
                                <td>
                                    <input type="text" name="date" id="date" value="" placeholder="select date" style="width:130px;" class="datepicker" onchange="setSession();setMaxYearMonth();">
                                </td>
                                <!--<td class="align_button_left"><label for="first_name">Choose Year:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="year" id="year" required="true" class="textbox2" style="width:130px;" onchange="load_months();setSession();"> 
                                        <!--<option value="">Assessment Year</option>-->
                                 </select>
                                </td>
                                
                                <!--<td class="align_button_left"><label for="first_name">Choose Month<font color="red">*</font></label></td>-->
                                
                       <td>
                           <select name="month" id="month" required="true" class="textbox2" style="width:130px;" onchange="setSession();"> 
                                        <!--<option value="">Assessment Month</option>-->
                                 </select>
                       </td>
                       
                       
                       <td>
                        <select name="district" id="district" required="true" class="textbox2" style="width:170px;" onchange="getFacility();getMOH();getAPHIA();setSession();"> 
                                        <!--<option value="">Facility...</option>-->
                                 </select>
                                </td>
                                
                                
                        <td>
                                    <select name="hf_id" id="hf_id" required="true" class="textbox2" style="width:170px;" onchange="setSession();setDist();"> 
                                        <!--<option value="">Facility...</option>-->
                                 </select>
                                </td>
                       
                                <td>
                                    <select name="moh" id="moh" class="textbox2" style="width:170px;" multiple="true" onchange="getAnother();"> 
                                        <!--<option value="">MOH Staff </option>-->
                                 </select>
                                </td>
                            
                                 <td>
                                    <select name="aphia" id="aphia" class="textbox2" style="width:170px;" onchange="setSession();"> 
                                        <!--<option value="">APHIA Staff </option>-->
                                 </select>
                                </td>
                            
                       
                       <td><p id="info"></p> </td>
                       
                       </tr>
    
    
    </table>

<div id="data" style="width:98%; min-height:200px; text-align: center;">
<!--Loaded data here-->
<%if(session.getAttribute("data")!=null){out.println(session.getAttribute("data").toString());}else{out.println("<p style=\"margin-top:100px;\"><font color=\"red\">Sorry :</font> Please select all the required parameters.</p>");}%>
    </div>
                              <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>   
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
  <script>
      
      $(document).ready(function(){
       load_years();
       getData();
       getFacility();
       getDistricts();
       getMOH();
       getAPHIA();
       load_dates();   
      });
      </script>
    </body>
</html>
