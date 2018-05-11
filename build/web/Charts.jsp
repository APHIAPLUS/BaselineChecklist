<%-- 
    Document   : Charts
    Created on : Jul 16, 2015, 10:01:22 AM
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
        <title>Charts Reports.</title>
      <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
	<style type="text/css">
${demo.css}
		</style>	
		<script src="menu/prefix-free.js"></script>  
   <link rel="stylesheet" href="select2/css/select2.css">
         <!--calender-->
<script type ="text/javascript" src="js/datepicker/min.js"></script>
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
        
        
        
                <!--modal-->
<!--  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
  <script src="modal/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
   <script src="modal/jquery.modal.min.js" type="text/javascript" charset="utf-8"></script>
  <link rel="stylesheet" type="text/css" href="modal/jquery.modal.css"/>
 <script>
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
			show: {
				effect: "blind",
				duration: 1500
			},
			hide: {
				effect: "explode",
				duration: 1700
			}
		});

		$( "#opener" ).click(function() {
			$( "#dialog" ).dialog( "open" );
		});
	});
      </script>   

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
  
           
            function load_districts(ct){
                getChart();
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
 document.getElementById("modal_p").innerHTML="";  
            }   
            
            function load_hf(){
                getChart();
    var dist_id=document.getElementById("district").value;
//alert("dist : "+dist_id)
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
    </head>
    <body onload="load_counties();load_hf();">
       
     <div id="container" style="height:auto;margin-left:20px;margin-right:20px; min-width: 1300px; max-width: 1450px;" >
   <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>

             <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
 <div id="content" style="height:auto;">
              <div style="margin-left:0px;">   
             <!--<form action="select_toolschecklist" method="post" style="margin-left:0px;margin-right:0px;" >-->
                      
                        <table cellpadding="2px" cellspacing="1px" border="0px" style="margin-left:0px;">
                       <tr>
                                <!--<td class=""><label for="first_name">County:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="county_id" id="county" required class="textbox2" onchange="return load_districts(this);" style="width:150px;"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td>
                       <!--<td class=""><label for="first_name">District:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="district_id" id="district" required class="textbox2" onchange="return load_hf();" style="width:180px;"> 
                                        <option value="">Choose District</option>
                                 </select>
                                </td>
                       <!--<td class=""><label for="first_name">Facility:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="hf_id" id="hf" required class="textbox2" style="width:230px;" onchange="getChart();"> 
                                        <option value="">Choose facility</option>
                                 </select>
                                  </td>
                                <!--<td class=""><label for="first_name">Year<font color="red">*</font></label></td>-->
                               
                                <td id="programArea">
                                    <select name="area" id="area" required style="width:180px;" onchange="getChart();"> 
                                        <option value="">Program Area</option>
                                 </select>
                                  </td>
                                
                                <td>
                                    <select name="year" id="year" required class="textbox2" style="width:120px;"> 
                                        <option value="">Year</option>
                                 </select>
                                  </td>  
                                  
                                  <!--<td class="" id="duration1"><label for="first_name">Duration<font color="red">*</font></label></td>-->
                                <td id="duration2">
                                    <select name="duration" id="duration" required class="textbox2" style="width:160px;" onchange="getChart();"> 
                                        <option value="">Choose Duration</option>
                                 </select>
                                  </td>
                                  
                                  <!--<td class="" id="semi1"><label for="first_name">Semi-Annual<font color="red">*</font></label></td>-->
                                <td id="semi2">
                                    <select name="semi_annual" id="semi_annual" required class="textbox2" style="width:180px;" onchange="getChart();"> 
                                        <option value="">Semi annual</option>
                                 </select>
                                  </td>
                                 <!--<td class="" id="quarter1"><label for="first_name">Quarterly:<font color="red">*</font></label></td>-->
                                <td id="quarter2">
                                    <select name="quarter" id="quarter" required class="textbox2" style="width:180px;" onchange="getChart();"> 
                                        <option value="">Quarter</option>
                                 </select>
                                  </td> 
                                  
                                  <!--<td class="" id="month1"><label for="first_name">Monthly:<font color="red">*</font></label></td>-->
                                <td id="month2">
                                    <select name="month" id="month" required style="width:180px;" onchange="getChart();"> 
                                        <option value="">Month</option>
                                 </select>
                                  </td>
<!--                                  
                        <td class=""></td> <td class="align_button_right">
                        <input type="submit" class="submit" value="View" class="textbox"/></td>-->
                       </tr>
                          </table>
                            
  <!--</form>-->
              </div>
         
                
                <div id="midcontent" style="margin-left:0px ; height:auto; min-width: 1300px; max-width: 1450px;" class="containers" >
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
                         
  <div id="dialog" title="Tools Checklist Help." style=" font-size: 17px;">
<p>Select the details appropriately as indicated.</p>
<p><font color="red">NOTE</font>: After selecting the health facility, there is a button that comes up indicated <font color="blue">View Marked</font>, you can click on this button to view those years and months that has there data entered to the system.</p>
<p>You can click on that link so as you can edit the entered data.</p>
<p>Else select the other details as required and then click on Next.</p>
</div> 
                        
     <div id="graphArea" style="min-width: 1000px; max-width: 1300px; height: 600px; margin: 0 auto"></div>                   
                        
                        
                </div>
                                     <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
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
<script src="highcharts/js/highcharts.js"></script>

<!--USE OFLINE SERVER TO EXPORT THE DATA-->
<!--https://github.com/A----/highcharts-export-clientside-->
<!--http://jsfiddle.net/pscjzhe4/1/-->

<script src="canvas/exporting.js"></script>
<script src="canvas/canvas-tools.js"></script>
<script src="canvas/export-csv.js"></script>
<script src="canvas/jspdf.min.js"></script>

<!-- This module, after the dependencies -->
<script src="canvas/highcharts-export-clientside.js"></script>



<script type="text/javascript">
    function getMonths(year){
      $.ajax({
        url:"loadPepfarMonths?year="+year,
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
          $("#month").html(data);
          $('#month').select2();
         }
     });
    }
    
     function getYears(){
         $.ajax({
        url:"loadYear",
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
           $("#year").html(data);  
            $('#year').select2(); 
         }
     });
    }
    
     function getSemiAnnual(year){
         $.ajax({
        url:"loadSemiAnnual?year="+year,
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
         $("#semi_annual").html(data); 
          $('#semi_annual').select2(); 
         }
     });
    }
   
     function getQuarters(year){
         $.ajax({
        url:"loadQuaters?year="+year,
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
         $("#quarter").html(data);
          $('#quarter').select2(); 
         }
     });
    }
     function getDuration(){
         $.ajax({
        url:"loadDuration",
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
         $("#duration").html(data); 
          $('#duration').select2(); 
         }
     });
    }
    
     function getAreas(){
         $.ajax({
        url:"loadProgramArea",
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
         $("#area").html(data); 
          $('#area').select2(); 
         }
     });
    }
    
    $(document).ready(function(){
     getYears();
     getDuration();
     getAreas();
//     $("#duration1").hide();
     $("#duration2").hide();
//     $("#semi1").hide();
     $("#semi2").hide();
//     $("#quarter1").hide();
     $("#quarter2").hide();
//     $("#month1").hide();
     $("#month2").hide();
     
     var year="",duration="";
     $("#year").change(function(){
     year= $("#year").val();
     if(year!==""){
      getQuarters(year);   
      getSemiAnnual(year);
      getMonths(year);
      getChart();
//     $("#duration1").show();
     $("#duration2").show();
     }
        
     });
     
      $("#duration").change(function(){
     duration= $("#duration").val();
     if(duration==="1"){
//     $("#semi1").hide();
     $("#semi2").hide();
//     $("#quarter1").hide();
     $("#quarter2").hide();
//     $("#month1").hide();
     $("#month2").hide();
     }
     else if(duration==="2"){
//      $("#semi1").show();
     $("#semi2").show();
//     $("#quarter1").hide();
     $("#quarter2").hide();
//     $("#month1").hide();
     $("#month2").hide();    
     }
     else if(duration==="3"){
//      $("#semi1").hide();
     $("#semi2").hide();
//     $("#quarter1").show();
     $("#quarter2").show();
//     $("#month1").hide();
     $("#month2").hide();     
     }
     else if(duration==="4"){
//    $("#semi1").hide();
     $("#semi2").hide();
//     $("#quarter1").hide();
     $("#quarter2").hide();
//     $("#month1").show();
     $("#month2").show();     
     }
     else{
//     $("#semi1").hide();
     $("#semi2").hide();
//     $("#quarter1").hide();
     $("#quarter2").hide();
//     $("#month1").hide();
     $("#month2").hide();   
     }   
     });
     
     
    });
    
    function getChart(){
     var facilityID="",duration="",year="",month="",quarter="",semi_annual="",area="";
     facilityID=$("#hf").val();
     duration=$("#duration").val();
     year=$("#year").val();
     semi_annual=$("#semi_annual").val();
     quarter=$("#quarter").val();
     month=$("#month").val();
     area=$("#area").val();
     if(duration!==""){
      if(duration==="1" && year!=="" && area!==""){
         drawGraph();
//         call graph function......................................... 
      }
      else if(duration==="2" && semi_annual!=="" && year!=="" && area!==""){
    drawGraph();
      }
      
      else if(duration==="3" && quarter!=="" && year!=="" && area!==""){
       drawGraph();
      }
      
     else if(duration==="4" && month!=="" && year!=="" && area!==""){
   drawGraph();
      }
      else{
//          do nothing
      }
      
     }
        
    }
    </script>
    
    <script type="text/javascript">
         function drawGraph(){
$("#graphArea").html("<br><br><br><br><div style='margin-left:0%;'>Loading data <img src='images/utube.gif' alt=''></div>");
     var facilityID="",duration="",year="",month="",quarter="",semi_annual="",area="",countyid="",districtid="";
     facilityID=$("#hf").val();
     duration=$("#duration").val();
     year=$("#year").val();
     semi_annual=$("#semi_annual").val();
     quarter=$("#quarter").val();
     month=$("#month").val();
     area=$("#area").val();
     countyid=$("#county").val();
     districtid=$("#district").val();
     var elementSize,programArea,period;
     var allData="";
     $.ajax({
        url:"facilityBarChart?year="+year+"&&facilityID="+facilityID+"&&reportDuration="+duration+"&&semi_annual="+semi_annual+"&&quarter="+quarter+"&&month="+month+"&&programID="+area+"&&districtid="+districtid+"&&countyid="+countyid,
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
//             alert(data);
       if(data.indexOf("failed to load report")<0){
//         alert("called");
         allData=data.split("<br>");
         
var titles = allData[1].split('^^');
var datavalues=allData[0].split("@@");
elementSize=datavalues.length;
programArea=allData[2];
period=allData[3];
//alert("title : "+titles);
//alert("values : "+datavalues);    
//   alert("element size : "+elementSize) ;    
     var options={
            chart: {
            type: 'column'
        },
        title: {
            text: period
        },
        subtitle: {
            text: 'PROGRAM AREA : '+programArea
        },
        
        xAxis: {
//            categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
//            categories: ,
            categories: titles,
                title: {
                text: "INDICATOR NAMES "
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Number Achieved',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' People'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        
//        legend: {
//            layout: 'horizontal',
//            align: 'right',
//            verticalAlign: 'top',
//            x: -40,
//            y: 70,
//            floating: true,
//            borderWidth: 1,
//            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
//            shadow: true
//        },
        credits: {
            enabled: false
        },
        series: []
    }; 
    
    for(var a=0;a<elementSize;a++){
        var jsonobj=datavalues[a].split("XX");

var variableName=jsonobj[0];
var variableValues=JSON.parse(jsonobj[1].split(","));
//alert(variableValues);
//var variableName='Recounted Data';
//var variableValues=[1,135,16,1,0,151];
    options.series.push({
    name: variableName,
//    data: JSON.parse("[" + datavalues[a].data+ "]")
    data: variableValues
});
            
        }    
        $('#graphArea').highcharts(options);
    }
    else{
     $('#graphArea').html("No report to be plotted.");    
    }
         }
    });
         }            
        </script>
       
        
    </body>
</html>
