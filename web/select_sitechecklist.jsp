<%-- 
    Document   : select_sitechecklist
    Created on : May 12, 2014, 11:15:04 AM
    Author     : Geofrey Nyabuto
--%>
<%if(session.getAttribute("moh_staff")!=null){session.removeAttribute("moh_staff");}%>
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
        <title>Site Checklist 1/2</title>
      
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
        
        <!--modal-->
<!--  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
  <script src="modal/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
   <script src="modal/jquery.modal.min.js" type="text/javascript" charset="utf-8"></script>
  <link rel="stylesheet" type="text/css" href="modal/jquery.modal.css"/>
  
   <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
       
       
  <script type="text/javascript" charset="utf-8">
  $(function() {

    function log_modal_event(event, modal) {
      if(typeof console != 'undefined' && console.log) console.log("[event] " + event.type);
    };

    $(document).on($.modal.BEFORE_BLOCK, log_modal_event);
    $(document).on($.modal.BLOCK, log_modal_event);
    $(document).on($.modal.BEFORE_OPEN, log_modal_event);
    $(document).on($.modal.OPEN, log_modal_event);
    $(document).on($.modal.BEFORE_CLOSE, log_modal_event);
    $(document).on($.modal.CLOSE, log_modal_event);
    $(document).on($.modal.AJAX_SEND, log_modal_event);
    $(document).on($.modal.AJAX_SUCCESS, log_modal_event);
    $(document).on($.modal.AJAX_COMPLETE, log_modal_event);

    $('#more').click(function() {
      $(this).parent().after($(this).parent().next().clone());
      $.modal.resize();
      return false;
    });

    $('#manual-ajax').click(function(event) {
      event.preventDefault();
      $.get(this.href, function(html) {
        $(html).appendTo('body').modal();
      });
    });

    $('a[href="#ex5"]').click(function(event) {
      event.preventDefault();
      $(this).modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
      });
    });

    $('a[href="#ex7"]').click(function(event) {
      event.preventDefault();
      $(this).modal({
        fadeDuration: 250
      });
    });

    $('a[href="#ex8"]').click(function(event) {
      event.preventDefault();
      $(this).modal({
        fadeDuration: 1000,
        fadeDelay: 0.50
      });
    });

    $('a[href="#ex9"]').click(function(event) {
      event.preventDefault();
      $(this).modal({
        fadeDuration: 1000,
        fadeDelay: 0.05
      });
    });

    $('a[href="#ex10"]').click(function(event){
      event.preventDefault();
      $(this).modal({
        closeClass: 'icon-remove',
        closeText: '!'
      });
    });

  });
</script>
        
     <script>
	$(function() {
            
            var         fname = $( "#fname" ), 
			lname = $( "#lname" ),
                        county3 = $( "#county3" ),
                         validate_hf=$("#validator_hf"),
                        validate_moh=$("#validator_moh"),
                        allFields = $( [] ).add( fname ).add( lname ),
			tips = $( ".validateTips" );
                      var selectedEffect = 'blind';

                var options = {};  
		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}

		function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}
            
            
            
            
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
                
                  //        ADD MOH STAFF...........................
		$( "#dialog-form3" ).dialog({
			autoOpen: false,
//			height: 350,
			width: 500,
			modal: true,
			buttons: {
				"Save": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
                           //VALIDATING THE FORM BEFORE SUBMISSION
				if(county3.val()==""){
                                            bValid=false;
                                            $('#validator_moh').show();
                                     validate_moh.html("Please Choose County.");
                                             setTimeout(function(){
                                                 $('#validator_moh').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }	
                                     else if(fname.val()==""){
                                            bValid=false;
                                              $('#validator_moh').show();
                                             validate_moh.html("Please Enter First Name.");
                                                     setTimeout(function(){
                                                    $('#validator_moh').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
//                                         else  if(lname.val()==""){
//                                            bValid=false;
//                                              $('#validator_moh').show();
//                                             validate_moh.html("Please Enter Last Name.");
//                                                     setTimeout(function(){
//                                                    $('#validator_moh').hide(selectedEffect, options, 500);
//                                                    }, 5000);
//                                                    
//                                        }
                                       else{
                                         validate_hf.html("");   
                                       } 
                                        
                                        if ( bValid ) {
						var data = $('form').serialize();
                                                    $.post('save_moh', data);
                                                    var full_name= $("#fname").val()+" "+ $("#mname").val()+" "+ $("#lname").val();
                                                 $("#fname").val("");
                                                 $("#mname").val("");
                                                  $("#lname").val("");
                                                  
                                                $('#validator_moh').show();
                                             validate_moh.html("<font color=\"green\">"+full_name+"</font>    Added Successfully");
                                                     setTimeout(function(){
                                                    $('#validator_moh').hide(selectedEffect, options, 500);
                                                    }, 5000);  
                                                  
                                                  
                                                  
                                                  
						$( this ).dialog( "open" );
                                              
                                                  
					}
                                    
                                  $("#moh").removeAttr('required');      
				},
				Cancel: function() {
//                                      $("#hidder").show();
//                                       $("#moh").attr('required',true);
					$( this ).dialog( "close" );
                                       
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
//                              $("#hidder").show();   
			}
		});

		$( "#moh_cat" ).change(function() {
                    var value=$( "#moh_cat" ).val();
                    if(value==2){
                        $("#hidder").hide();
//                        $("#moh").removeAttr('required'); 
                        $("#staffs").html(''); 
                        $("#hidderx").hide(); 
                        $("#selectedMOH").val(','); 
//                        $("#moh").removeAttr('required'); 
                        
			$( "#dialog-form3" ).dialog( "open" );}
                             else if(value==1){
                            $("#hidder").show();
                            $("#hidderx").hide(); 
//                            $("#moh").attr('required',true);
//                        var=$("selectedMOH").val();
                             }
                             else{
                               $("#hidder").hide();
//                        $("#moh").removeAttr('required'); 
                        $("#staffs").html(''); 
                        $("#hidderx").hide(); 
                        $("#selectedMOH").val(','); 
                             }
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
//document.getElementById("county3").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_counties",true);
xmlhttp.send();
            }
  
              function load_aphia(){
   var county=document.getElementById("county").value;    
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
xmlhttp.open("POST","load_APHIAStaff?county="+county,true);
xmlhttp.send();
            }
            
                        function load_moh(){
    var county=document.getElementById("county").value;                
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
xmlhttp.open("POST","load_MOHStaff?county="+county,true);
xmlhttp.send();
 document.getElementById("modal_p").innerHTML="";  
            }
            
            
            
            function load_districts(ct){
    var county_id=ct.value;
    load_moh();
    load_aphia();
    document.getElementById("county3").value=county_id;
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
            
            function load_hf(ct){
    var dist_id=ct.value;   
var xmlhttp;
 document.getElementById("modal_p").innerHTML="";  
 
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
            function load_markedPeriods(id){
               var hf_id=id.value;
               
//               alert("the id is  :  "+hf_id);
               if(hf_id!="") {
  document.getElementById("modal_p").innerHTML="<a href=\"#ex9\" rel=\"modal:open\" class=\"linkstyle1\" style=\"height:30px; background-color:plum; margin-top:5px;\">View Marked</a>";         
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
document.getElementById("hf_details").innerHTML=xmlhttp.responseText;
// $('select').select2();
}
}
xmlhttp.open("POST","site_DataPopUp?hf_id="+hf_id,true);
xmlhttp.send();     
       }
               else{
                document.getElementById("modal_p").innerHTML="";     
               }
                
            }
//            function addMoh(added){
//                var newlyadded=added.value;
//                
//            var selected=document.getElementById("selectedMOH").value;
////            alert("here2");
//if(added!="-"){
//            selected+=newlyadded+",";
//             addnewMOH(newlyadded);
//            }
////            alert("called");
//            document.getElementById("selectedMOH").value=selected;
//            var county=document.getElementById("county3").value; 
////            alert("here3");
////            alert(selected+"-----------"+county);
//            selected=document.getElementById("selectedMOH").value;
//                         var xmlhttp;    
//if (window.XMLHttpRequest)
//{// code for IE7+, Firefox, Chrome, Opera, Safari
//xmlhttp=new XMLHttpRequest();
//}
//else
//{// code for IE6, IE5
//xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//}
//xmlhttp.onreadystatechange=function()
//{
//if (xmlhttp.readyState==4 && xmlhttp.status==200)
//{
//document.getElementById("moh").innerHTML=xmlhttp.responseText;
// $('select').select2();
//}
//}
//xmlhttp.open("POST","loadRemainingMOH?county="+county+"&&selected="+selected,true);
//xmlhttp.send();     
//                
//            }
//            function addnewMOH(staffid){
//           var id=staffid;  
//         if(id!=""){
//             $("#hidderx").show(); 
//      var xmlhttp;    
//if (window.XMLHttpRequest)
//{// code for IE7+, Firefox, Chrome, Opera, Safari
//xmlhttp=new XMLHttpRequest();
//}
//else
//{// code for IE6, IE5
//xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//}
//xmlhttp.onreadystatechange=function()
//{
//if (xmlhttp.readyState==4 && xmlhttp.status==200)
//{
//document.getElementById("hidderx").hidden=false; 
////alert(xmlhttp.responseText);
//var data=document.getElementById("staffs").innerHTML;
//data+=xmlhttp.responseText;
//document.getElementById("staffs").innerHTML=data;
//}
//}
//xmlhttp.open("POST","getMOHName?id="+id,true);
//xmlhttp.send();         
//             
//   }
//            }
//            function deleter(deleteid){
// var selected=document.getElementById("selectedMOH").value;
// var newlyselected=selected.replace(deleteid+",","");
// document.getElementById("selectedMOH").value=newlyselected;
//  document.getElementById("img_"+deleteid).remove();
// document.getElementById("br_"+deleteid).remove();
// document.getElementById("p_"+deleteid).remove();
//
// if(newlyselected==","){
//     document.getElementById("hidderx").hidden=true;
//     $("#hidderx").hide(); 
////      document.getElementById("choosen").innerHTML="";
// }
// addMoh("-");
//            }
            
//            function validator(){
//               
//                var rtn=false;
//                var catname=document.getElementById("moh_cat").value;
//                var selected=document.getElementById("selectedMOH").value;
//              
//                if(catname=="1" && selected==","){
//                    rtn=false;
//                    alertify.alert("Select MOH Staff");
//                }
//                else{
//                    rtn=true;
//                 }
//                return rtn;
//            }
        </script>
        
       <script>
      $(document).ready(function(){
        
       $("#moh_cat").change(function (){
        var mohcat=$("#moh_cat").val();
          if(mohcat=="1") {
//          $("#moh").show();
            $("#moh").prop("required",true);  
         }
         else{
//             $("#moh").hide();
          $("#moh").removeAttr("required");
         }
           
       }); 
        
        
      }) ;    
           
           
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Enter Site Checklist.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>
                   
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
    
<div id="ex9" class="modal" >
<p id="hf_details" style=" max-width: 100%; overflow-y: auto; overflow-x: hidden; max-height:400px; max-width: 600px;">
   
    

</p></div>    
<div id="dialog" title="Site Checklist Help." style=" font-size: 17px;">
<p>Select the details appropriately as indicated.</p>
<p><font color="red">NOTE</font>: After selecting the health facility, there is a button that comes up indicated <font color="blue">View Marked</font>, you can click on this button to view those years and months that has there data entered to the system.</p>
<p>You can click on that link so as you can edit the entered data.</p>
<p>Else select the other details as required and then click on Next.</p>
</div>
 
<div id="dialog-form3" title="Add MOH Staff" style="font-size:70%;">
    
   <p class="validateTips" id="validator_moh" style="color: red; font-size: 16px;"></p>

	<form action="save_moh">
	<fieldset>
            <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                <input type="hidden" name="county_id3" id="county3" required>
            <tr>
                                <td class="align_button_left"><label for="first_name">Enter First Name:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="fname" id="fname" value="" class="textbox">
                                </td></tr><tr> </tr>
            
              <tr>
                                <td class="align_button_left"><label for="first_name">Enter Middle Name:<font color="red"></font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="mname" id="mname" value="" class="textbox">
                                </td></tr><tr> </tr>
              
                <tr>
                                <td class="align_button_left"><label for="first_name">Enter Last Name:<font color="red"></font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="lname" id="lname" value="" class="textbox">
                                </td></tr><tr> </tr>
                
            
            </table>
        
        </fieldset>
	</form>
   
   </div> 
<!--<button id="add_moh" class="textbox1">Add MOH Staff</button>-->
<form action="select_sitechecklist" method="post" onsubmit="return validator();">
    <input type="text" name="selectedMOH" hidden="true" id="selectedMOH" value=",">
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
                                    <select name="hf_id" id="hf" required class="textbox2" onchange="return load_markedPeriods(this);"> 
                                        <option value="">Choose facility</option>
                                 </select>
                                </td><td><p id="modal_p"></p> </td></tr><tr> </tr>
                           
                             <tr>
                                <td class="align_button_left"><label for="first_name">Choose APHIA Staff:<font color="red">*</font></label></td>
                                <td>
                                    <select name="aphia_staff" id="aphia" required class="textbox2"> 
                                        <option value="">Choose Staff</option>
                                 </select>
                                </td></tr><tr> </tr>
                             
                             
                             <tr>
                                <td class="align_button_left"><label for="first_name">Choose MOH Category:<font color="red">*</font></label></td>
                                <td>
                                    <select name="moh_cat" id="moh_cat" required class="textbox2"> 
                                        <option value="">Choose Category</option>
                                        <option value="3">No MOH Staff</option>
                                        <option value="1">Existing MOH Staff</option>
                                        <option value="2">New MOH Staff</option>
                                 </select>
                                </td></tr><tr> </tr>
<!--                             <tr id="hidderx" hidden="true" style="background-color: #cca8f3;">
                                <td class="align_button_left"><label for="first_name"><p id="choosen">Choosen MOH Staff:</p><font color="red">*</font></label></td>
                                <td id="staffs">
                                    <p id="1"> data</p><p>data 2</p>
                                </td></tr>
                               <tr> </tr>
                               
                               -->
                               <tr id="hidder" hidden="true">
                                <td class="align_button_left"><label for="first_name">Choose MOH Staff (s):<font color="red">*</font></label></td>
                                <td>
                                    <select name="moh_staff" id="moh" style="height: 30px;" class="textbox2" multiple="true"> 
                                        <!--<option value="">Choose staff</option>-->
                                 </select>
                                </td></tr>
                               <tr> </tr>
                               
                               
                                 <tr>
                                <td class="align_button_left"><label for="first_name">Choose Date of Visit:<font color="red">*</font></label></td>
                                <td><input type="text" name="date" id="datepicker" placeholder="Choose Visit Date" readonly="" required class="textbox">
                                </td></tr><tr> </tr>
                        
            
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
