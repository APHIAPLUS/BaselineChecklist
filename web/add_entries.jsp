<%-- 
    Document   : add_entries
    Created on : May 19, 2014, 3:22:47 PM
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
        <title>Add Entries</title>
      
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
        <style>
		body { font-size: 100%; }
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:15px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: auto; height: auto; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; margin-left: 50px; font-size: 20px; }
	</style>
	<script>
	$(function() {
		var county = $( "#county" ), 
			district = $( "#district" ),
                        validate_dist=$("#validator_dist"),
                        validator_county=$("#validator_county"),
                        county2 = $( "#county2" ),
                        cname = $( "#cname" ),
			district2 = $( "#district2" ),
                        validate_hf=$("#validator_hf"),
                        county3 = $( "#county3" ),
                        county4=$("#county4"),
                        fname = $( "#fname" ), 
			lname = $( "#lname" ),
                        phone=$("#phone"),
                        fname2 = $( "#fname2" ), 
			lname2 = $( "#lname2" ),
                        mfl = $( "#mfl" ), 
			level = $( "#level" ),
                        phone2=$("#phone2"),
                        hf=$("#hf_name"),
                        validate_moh=$("#validator_moh"),
                        validate_aphia=$("#validator_aphia"),
			allFields = $( [] ).add( county ).add( cname ).add( validator_county ).add( district ).add( county2 ).add( district2 ).add( hf ).add( county3 ).add( county4 ).add( fname ).add( lname ).add( phone ).add( fname2 ).add( lname2 ).add( mfl ).add( level ).add( phone2 ),
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

		$( "#dialog-form" ).dialog({
			autoOpen: false,
//			height: 350,
			width: 500,
			modal: true,
			buttons: {
				"Add District": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
                           //VALIDATING THE FORM BEFORE SUBMISSION
					if(county.val()==""){
                                            bValid=false;
                                            $('#validator_dist').show();
                                     validate_dist.html("Choose County Please.");
                                             setTimeout(function(){
                                                 $('#validator_dist').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                      else  if(district.val()==""){
                                            bValid=false;
                                              $('#validator_dist').show();
                                             validate_dist.html("Please Enter District Name.");
                                                     setTimeout(function(){
                                                    $('#validator_dist').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                       else{
                                         validate_dist.html("");   
                                       } 
                                        
                                        if ( bValid ) {
						var data = $('form').serialize();
                                                    $.post('save_district', data);
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#add_district" )
			.button()
			.click(function() {
                            load_counties();
				$( "#dialog-form" ).dialog( "open" );
			});
        
        
//        ADD HEALTH FACILITIES...........................
		$( "#dialog-form2" ).dialog({
			autoOpen: false,
//			height: 350,
			width: 500,
			modal: true,
			buttons: {
				"Save": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
                           //VALIDATING THE FORM BEFORE SUBMISSION
					if(county2.val()===""){
                                            bValid=false;
                                            $('#validator_hf').show();
                                     validate_hf.html("Please Choose County.");
                                             setTimeout(function(){
                                                 $('#validator_hf').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                      else  if(district2.val()===""){
                                            bValid=false;
                                              $('#validator_hf').show();
                                             validate_hf.html("Please Choose District.");
                                                     setTimeout(function(){
                                                    $('#validator_hf').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                         else  if(hf.val()===""){
                                            bValid=false;
                                              $('#validator_hf').show();
                                             validate_hf.html("Please Enter Health Facility Name.");
                                                     setTimeout(function(){
                                                    $('#validator_hf').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                        else  if(mfl.val()===""){
                                            bValid=false;
                                              $('#validator_hf').show();
                                             validate_hf.html("Please Enter facility mfl code.");
                                                     setTimeout(function(){
                                                    $('#validator_hf').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                        
                                        else  if(level.val()===""){
                                            bValid=false;
                                              $('#validator_hf').show();
                                             validate_hf.html("Please Choose facility level.");
                                                     setTimeout(function(){
                                                    $('#validator_hf').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                        
                                        
                                       else{
                                         validate_hf.html("");   
                                       } 
                                        
                                        if ( bValid ) {
						var data = $('form').serialize();
                                                    $.post('save_hf', data);;
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
                               
			}
		});

		$( "#add_hf" )
			.button()
			.click(function() {
                            load_counties();
				$( "#dialog-form2" ).dialog( "open" );
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
                                      else  if(fname.val()==""){
                                            bValid=false;
                                              $('#validator_moh').show();
                                             validate_moh.html("Please Enter First Name.");
                                                     setTimeout(function(){
                                                    $('#validator_moh').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                         else  if(lname.val()==""){
                                            bValid=false;
                                              $('#validator_moh').show();
                                             validate_moh.html("Please Enter Last Name.");
                                                     setTimeout(function(){
                                                    $('#validator_moh').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                                    
                                        }
                                          else  if(phone.val()==""){
                                            bValid=false;
                                              $('#validator_moh').show();
                                             validate_moh.html("Please Enter Phone Number.");
                                                     setTimeout(function(){
                                                    $('#validator_moh').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                                    
                                        }
                                       else{
                                         validate_hf.html("");   
                                       } 
                                        
                                        if ( bValid ) {
						var data = $('form').serialize();
                                                    $.post('save_moh', data);;
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
                               
			}
		});

		$( "#add_moh" )
			.button()
			.click(function() {
				$( "#dialog-form3" ).dialog( "open" );
			});       
                        
                        
                        
                        
                          //        ADD APHIA STAFF...........................
		$( "#dialog-form4" ).dialog({
			autoOpen: false,
//			height: 350,
			width: 500,
			modal: true,
			buttons: {
				"Save": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
                           //VALIDATING THE FORM BEFORE SUBMISSION
					if(county4.val()==""){
                                            bValid=false;
                                            $('#validator_aphia').show();
                                     validate_aphia.html("Please Choose County.");
                                             setTimeout(function(){
                                                 $('#validator_aphia').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                      else  if(fname2.val()==""){
                                            bValid=false;
                                              $('#validator_aphia').show();
                                             validate_aphia.html("Please Enter First Name.");
                                                     setTimeout(function(){
                                                    $('#validator_aphia').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                         else  if(lname2.val()==""){
                                            bValid=false;
                                              $('#validator_aphia').show();
                                             validate_aphia.html("Please Enter Last Name.");
                                                     setTimeout(function(){
                                                    $('#validator_aphia').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                                    
                                        }
//                                          else  if(phone2.val()==""){
//                                            bValid=false;
//                                              $('#validator_aphia').show();
//                                             validate_aphia.html("Please Enter Phone Number.");
//                                                     setTimeout(function(){
//                                                    $('#validator_aphia').hide(selectedEffect, options, 500);
//                                                    }, 5000);
//                                                    
//                                        }
                                       else{
                                         validate_hf.html("");   
                                       } 
                                        
                                        if ( bValid ) {
						var data = $('form').serialize();
                                                    $.post('save_aphia', data);;
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
                               
			}
		});

		$( "#add_aphia" )
			.button()
			.click(function() {
				$( "#dialog-form4" ).dialog( "open" );
			});       
                 
        
           $( "#add_county" )
			.button()
			.click(function() {
				$( "#dialog-form5" ).dialog( "open" );
			}); 
//                     ADD COUNTY=======================================   
          $( "#dialog-form5" ).dialog({
			autoOpen: false,
//			height: 350,
			width: 500,
			modal: true,
			buttons: {
				"Add County": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
                           //VALIDATING THE FORM BEFORE SUBMISSION
					if(cname.val()==""){
                                            bValid=false;
                                            $('#validator_county').show();
                                     validator_county.html("<b>Error </b>: Enter county Name");
                                             setTimeout(function(){
                                                 $('#validator_county').hide(selectedEffect, options, 500);
                                                    }, 5000);
                                        }
                                     
                                       else{
                                         validator_county.html("");   
                                       } 
                                        
                                        if ( bValid ) {
						var data = $('form').serialize();
                                                    $.post('save_county', data);
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
                
	});

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
document.getElementById("county2").innerHTML=xmlhttp.responseText;
document.getElementById("county3").innerHTML=xmlhttp.responseText;
document.getElementById("county4").innerHTML=xmlhttp.responseText;
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
document.getElementById("district2").innerHTML=xmlhttp.responseText;
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
}
}
xmlhttp.open("POST","load_facilities?district_id="+dist_id,true);
xmlhttp.send();
            }   
            
        </script>
    </head>
    <body onload="load_counties();">
       
     <div id="container" style="height:500px;" >
   <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>

              <div id="header" align="center">   
              </div>
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Add New Entries. <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>
                   
            <div id="content" style="height:350px; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%> <br><br>
                
                <div id="midcontent" style="margin-left:200px ; height:300px;" class="containers" >
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
 <div id="dialog" title="Add Entries Help." style=" font-size: 17px;">
<p>Click on each of the buttons shows by their respective label to add the various items.</p>
<p><font color="red">NOTE:</font> The Fields marked (<font color="red">*</font>) are mandatory field and they must be filled.</p>
<p>Upon successfully adding, the pop up will disappear when save is clicked.</p>
</div>                       
<div id="dialog-form" title="Add New District" style="font-size:70%;">
    
   <p class="validateTips" id="validator_dist" style="color: red; font-size: 16px;"></p>

	<form action="save_district">
	<fieldset>
            <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                       <tr>
                                <td class="align_button_left"><label for="first_name">Choose County:<font color="red">*</font></label></td>
                                <td>
                                    <select name="county" id="county" required class="textbox2" onchange="return load_districts(this);"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td></tr><tr> </tr>
            
            <tr>
                                <td class="align_button_left"><label for="first_name">Enter District Name:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="district" id="district" value="" class="textbox">
                                </td></tr><tr> </tr>
            </table>
        
        </fieldset>
	</form>
   
   </div>
   
<div id="dialog-form2" title="Add New Health Facility" style="font-size:70%;">
    
   <p class="validateTips" id="validator_hf" style="color: red; font-size: 16px;"></p>

	<form action="save_hf">
	<fieldset>
            <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                        <tr>
                                <td class="align_button_left"><label for="first_name">Choose County:<font color="red">*</font></label></td>
                                <td>
                                    <select name="county_id2" id="county2" required class="textbox2" onchange="return load_districts(this);"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td></tr><tr> </tr>
                       
                       
                         <tr>
                                <td class="align_button_left"><label for="first_name">Choose District:<font color="red">*</font></label></td>
                                <td>
                                    <select name="district_id2" id="district2" required class="textbox2" onchange="return load_hf(this);"> 
                                        <option value="">Choose District</option>
                                 </select>
                                </td></tr><tr> </tr>
                       
            
            <tr>
                                <td class="align_button_left"><label for="first_name">Enter Health Facility Name:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="hf_name" id="hf_name" value="" class="textbox">
                                </td></tr><tr> </tr>
            <tr>
                                <td class="align_button_left"><label for="first_name">MFL Code:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="mfl" id="mfl" value="" onkeypress="return numbers(event)" class="textbox">
                                </td></tr><tr> </tr>
            
             <tr>
                                <td class="align_button_left"><label for="first_name">Choose Level : <font color="red">*</font></label></td>
                                <td>
                                    <select name="level" id="level" required class="textbox2"> 
                                        <option value="">Choose Level</option>
                                        <option value="Level 2">Level 2</option>
                                        <option value="Level 3">Level 3</option>
                                        <option value="Level 4">Level 4</option>
                                        <option value="Level 5">Level 5</option>
                                        
                                 </select>
                                </td></tr><tr> </tr>
             
            </table>
        
        </fieldset>
	</form>
   
   </div>       

<div id="dialog-form3" title="Add MOH Staff" style="font-size:70%;">
    
   <p class="validateTips" id="validator_moh" style="color: red; font-size: 16px;"></p>

	<form action="save_moh">
	<fieldset>
            <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                        <tr>
                                <td class="align_button_left"><label for="first_name">Choose County:<font color="red">*</font></label></td>
                                <td>
                                    <select name="county_id3" id="county3" required class="textbox2" onchange="return load_districts(this);"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td></tr><tr> </tr>
<!--                       
                       
                         <tr>
                                <td class="align_button_left"><label for="first_name">Choose District:<font color="red">*</font></label></td>
                                <td>
                                    <select name="district_id2" id="district2" required class="textbox2" onchange="return load_hf(this);"> 
                                        <option value="">Choose District</option>
                                 </select>
                                </td></tr><tr> </tr>-->
                       
            
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
                                <td class="align_button_left"><label for="first_name">Enter Last Name:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="lname" id="lname" value="" class="textbox">
                                </td></tr><tr> </tr>
                
                  <tr>
                                <td class="align_button_left"><label for="first_name">Enter Phone Number:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="10" name="phone" id="phone" onkeypress="return numbers(event)" value="" class="textbox">
                                </td></tr><tr> </tr>
            
            </table>
        
        </fieldset>
	</form>
   
   </div>       


<div id="dialog-form4" title="Add Aphia Staff" style="font-size:70%;">
    
   <p class="validateTips" id="validator_aphia" style="color: red; font-size: 16px;"></p>

	<form action="save_aphia">
	<fieldset>
            <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                        <tr>
                                <td class="align_button_left"><label for="first_name">Choose County:<font color="red">*</font></label></td>
                                <td>
                                    <select name="county_id4" id="county4" required class="textbox2" onchange="return load_districts(this);"> 
                                        <option value="">Choose county</option>
                                 </select>
                                </td></tr><tr> </tr>
                       
<!--                       
                         <tr>
                                <td class="align_button_left"><label for="first_name">Choose District:<font color="red">*</font></label></td>
                                <td>
                                    <select name="district_id2" id="district2" required class="textbox2" onchange="return load_hf(this);"> 
                                        <option value="">Choose District</option>
                                 </select>
                                </td></tr><tr> </tr>-->
                       
           <tr>
                                <td class="align_button_left"><label for="first_name">Enter First Name:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="fname2" id="fname2" value="" class="textbox">
                                </td></tr><tr> </tr>
            
              <tr>
                                <td class="align_button_left"><label for="first_name">Enter Middle Name:<font color="red"></font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="mname2" id="mname2" value="" class="textbox">
                                </td></tr><tr> </tr>
              
                <tr>
                                <td class="align_button_left"><label for="first_name">Enter Last Name:<font color="red">*</font></label></td>
                                <td>
                                    <input type="text" maxlength="30" name="lname2" id="lname2" value="" class="textbox">
                                </td></tr><tr> </tr>
                
                  <tr>
                                <td class="align_button_left"><label for="first_name">Enter Phone Number:<font color="red"></font></label></td>
                                <td>
                                    <input type="text" maxlength="10" name="phone2" id="phone2" onkeypress="return numbers(event)" value="" class="textbox">
                                </td></tr><tr> </tr>
            </table>
        
        </fieldset>
	</form>
   
   </div>       

 <div id="dialog-form5" title="Add County" style="font-size:70%;">
    
   <p class="validateTips" id="validator_county" style="color: red; font-size: 16px;"></p>

	<form action="save_county">
	<fieldset>
            <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                  <tr>
                                <td class="align_button_left"><label for="first_name">Enter County Name:<font color="red"></font></label></td>
                                <td>
                                    <input type="text" maxlength="10" name="cname" id="cname" value="" class="textbox">
                                </td></tr><tr></tr>
            </table>
        <br>
        </fieldset>
	</form>
   
   </div>

                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                       <tr>
                           <td class="align_button_left"><button id="add_county" class="textbox1" style="width: 100px;">Add County</button>
                                </td>
                                <td class="align_button_left"><button id="add_district" class="textbox1" style="width: 100px;">Add District</button>
                                </td> <td class="center"><button id="add_hf" class="textbox1" style="width: 200px;">Add Health Facility</button>
                                </td>
                                 <td class="center"><button id="add_moh" class="textbox1" style="width: 150px;">Add MOH Staff</button>
                                </td>
                                <td class="center"><button id="add_aphia" class="textbox1" style="background-color: red;width: 150px;" >Add APHIA Staff</button>
                                </td>
                                <td></td></tr><tr> </tr>
                                         </table>       
            
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
        
    </body>
</html>
