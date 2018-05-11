<%-- 
    Document   : variationReport
    Created on : Aug 21, 2015, 7:31:11 PM
    Author     : Nyabuto Geofrey
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
<!DOCTYPE HTML>
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
<!--        <link rel="stylesheet" type="text/css" href="css/select.css"/>-->
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>variation Charts Reports.</title>
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
        
        <style type="text/css">
${demo.css}
		</style>
           
    </head>  
    
    
	<body onload="load_counties();load_hf();">
       
     <div id="container" style="height:auto;margin-left:20px;margin-right:20px; min-width: 1450px; max-width: 1550px;" >
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
    <table>
     <tr>
     <td>
              <div style="margin-left:0px; float: left;"> 
                  <!--<button id="save">Get Image</button>-->
                  
             <!--<form action="select_toolschecklist" method="post" style="margin-left:0px;margin-right:0px;" >-->
                      
                        <table cellpadding="2px" cellspacing="1px" border="0px" style="margin-left:0px;">
                       <tr>
                                <!--<td class=""><label for="first_name">County:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="county_id" id="county" required class="textbox2" onchange="return load_districts(this);" style="width:150px;"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td>
                                 </tr>
                                <tr>
                       <!--<td class=""><label for="first_name">District:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="district_id" id="district" required class="textbox2" onchange="return load_hf();" style="width:150px;"> 
                                        <option value="">Choose District</option>
                                 </select>
                                </td>
                                 </tr>
                                <tr>
                       <!--<td class=""><label for="first_name">Facility:<font color="red">*</font></label></td>-->
                                <td>
                                    <select name="hf_id" id="hf" required class="textbox2" style="width:150px;" onchange="getChart();"> 
                                        <option value="">Choose facility</option>
                                 </select>
                                  </td>
                                   </tr>

                                    <tr>
                                  <!--<td class="" id="duration1"><label for="first_name">Duration<font color="red">*</font></label></td>-->
                                <td id="duration2"><font color="red">*</font>
                                    <select name="variationType" id="variationType" required class="textbox2" style="width:150px;" onchange="getChart();"> 
                                        <option value="">Choose variance fields</option>
                                        
                                        <option value="1">Recounted vs Reported</option>
                                        <option value="2">Recounted vs DHIS</option>
                                        <option value="3">Reported vs DHIS</option>
                                        
                                 </select>
                                  </td>                      
                                  
                       </tr>
                       

                                  <tr>
                                     <!--<td class="" id="indicator"><label for="first_name">Indicator <font color="red">*</font></label></td>-->
                                     <td id="programElement"><font color="red">*</font>
                                    <select name="dataElement" id="dataElement" multiple="true" required style="width:150px;" onchange="getChart();"> 
                                        <option value="">Select Data Element</option>
                                 </select>
                                  </td>
                                </tr>
                                  <tr>
                                  <!--<td class="" id="duration1"><label for="first_name">Duration<font color="red">*</font></label></td>-->
                                <td id="duration2"><font color="red">*</font>
                                    <select name="reportDuration" id="reportDuration" required class="textbox2" style="width:150px;" onchange="getChart();"> 
                                        <option value="">Choose report duration</option>
                                        <option value="monthly">Monthly</option>
                                        <option value="quarterly">Quarterly</option>
                                 </select>
                                  </td>                      
<!-- <td>
                                       <button id="getImage" onclick="return getImage();">Download Image</button> 
                                  </td>-->
                                  
                       </tr>
                          </table>
                            
  <!--</form>-->
              </div>
     </td>
     <td>
     <div id="graphArea" style="min-width: 1250px; max-width: 1500px; min-height: 600px; max-height: 1700px; margin: 0 auto; float:right;"></div>                   
  </td>
 </tr>
 </table>  
     
         <br>
                
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
<!--<script src="highcharts/js/modules/exporting.js"></script>-->
<script src="canvas/html2canvas.js"></script>
<script src="canvas/html2canvas.svg.js"></script>
<script src="canvas/FileSaver.js"></script>

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
    
    $(function() { 
    $("#save").click(function() {
 
//        html2canvas($("#container"), {
//            onrendered: function(canvas) {
//                theCanvas = canvas;
//
//
//                canvas.toBlob(function(blob) {
//                    saveAs(blob, "Dashboard.png"); 
//                });
//            }
//        });
        
        
        
    });
});

    
    $(document).ready(function(){

    });
    
    function getChart(){
     var facilityID="",dataElement="",reportDuration="",variationType;
     facilityID=$("#hf").val();
     dataElement=$("#dataElement").val();
     reportDuration=$("#reportDuration").val();
     variationType=$("#variationType").val();
     if(dataElement!=="" && reportDuration!=="" && variationType!==""){
      drawGraph();
    }
        
    }
    </script>
       
        <script type="text/javascript">
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
//          load elements 
//        alert("here");
getAreas();

         $.ajax({
        url:"loadElement",
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
//            alert("here");
        $("#dataElement").html(data); 
          $('#dataElement').select2();    
         }
         });
         
    $("#area").change(function (){
        var areaid=$("#area").val();
   $.ajax({
        url:"loadElement?areaid="+areaid,
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
//            alert("here");
        $("#dataElement").html(data); 
          $('#dataElement').select2();    
         }
         });     
        
    })    
         
         });   
            
            
            
         function drawGraph(){
$("#graphArea").html("<br><br><br><br><div style='margin-left:0%;'>Loading data <img src='images/utube.gif' alt=''></div>");
     var facilityID="",dataElement="",countyid="",districtid="",reportDuration="",variationType;
    
     facilityID=$("#hf").val();
     dataElement=document.getElementById("dataElement");
     countyid=$("#county").val();
     districtid=$("#district").val();
     variationType=$("#variationType").val();
     reportDuration=$("#reportDuration").val();
     
     var selectedElements ="";
    for (var i = 0; i < dataElement.length; i++) {
        if (dataElement.options[i].selected){
            selectedElements+=dataElement.options[i].value+",";
        }
    }
     
//   alert("selected elements : "+selectedElements) ; 
     
     var elementSize,programArea,facility,reportType;
     var allData="";
     $.ajax({
        url:"variationReport?facilityID="+facilityID+"&&dataElement="+selectedElements+"&&districtid="+districtid+"&&countyid="+countyid+"&&reportDuration="+reportDuration+"&&variationType="+variationType,
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
facility=allData[3];
reportType=allData[4];

//alert("title : "+titles);
//alert("values : "+datavalues);    
//   alert("element size : "+elementSize) ;    
     var options={
            chart: {
            type: 'line'
        },
        title: {
            text: reportType+' '+facility
        },
        subtitle: {
            text: 'PARAMETERS : '+programArea
        },
        
        xAxis: {
//            categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
//            categories: ,
            categories: titles,
                title: {
                text: " PERIODS "
            }
        },
        
         yAxis: {
            title: {
                text: '% Variance'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '%'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        
        legend: {
            layout: 'horizontal',
            align: 'centre',
            verticalAlign: 'bottom',
            borderWidth: 2
        },

        credits: {
            enabled: false
        },
        series: []
    }; 
    
    for(var a=0;a<elementSize;a++){
        var jsonobj=datavalues[a].split("XX");
//alert("element size : "+elementSize);
var variableName=jsonobj[0];
//alert("variable name  : "+variableName);
var variableValues=JSON.parse(jsonobj[1].split(","));
    options.series.push({
    name: variableName,
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
    
//    $("#graphArea")
//  .highcharts()                                 // Fetches the Chart instance associated with this container.
//  .exportChartLocal({                           // All attributes are optionals (defaut type is "image/png").
//    type: Highcharts.exporting.MIME_TYPES.JPEG     // For your convenience, MIME_TYPES are stored in an object.
//  });
  
         }      
         
         
         function getImage(){
             alert("called");
             html2canvas(document.body, {
      onrendered: function(canvas) {
        document.body.appendChild(canvas);
      }
    });    
         }
        </script>
        </body>
</html>
