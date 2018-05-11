<%-- 
    Document   : entry_type
    Created on : May 12, 2014, 8:54:07 AM
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
        <link rel="stylesheet" type="text/css" href="css/radio.css"/>
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Entry Type</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
	
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
   <link rel="stylesheet" href="select2/css/select2.css">
   
<script type="text/javascript" src="js/noty/themes/default.js"></script>
 	<style type="text/css">
		label {margin-right:20px;}
	</style>    
        
        <script type="text/javascript">
            function validator(){
                var i=0;
                for (i=0;i<=3;i++){
                    var valu=document.getElementById("radio"+i).value;
                    alert(" radio   "+valu);
                    
                }
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
        $(document).ready(function(){
       $.ajax({
        url:"loadYear",
        type:"post",
        dataType:"html",
        success:function(data){
         $("#year").html(data);
          $("#year").select2(); 
        }
    });    
      
        $("#year").change(function(){
         var year=$("#year").val();
              $.ajax({
        url:"loadMonths?year="+year,
        type:"post",
        dataType:"html",
        success:function(data){
         $("#month").html(data);
          $("#month").select2(); 
       }
    });  
        });
            
        });
      </script>  
    </head>
    <body>
       
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #3dff70; font-size: 24px; text-align: center;"> Choose Form(s) to Enter.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>
                   
            <div id="content" style="height:auto; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%> <br><br>
                 <div id="dialog" title="Choose Form Help." style=" font-size: 17px;">
<p>Basically there are two forms to be entered by this system.<font color="green">Facility Site Checklist</font> and <font color="green">Tools status checklist</font>.</p>
<p>You need to select the form that you want to enter its data.</p>
<p>if you have both forms and need to enter all, click on both.</p>
</div>
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
                        
                        
          
<form action="entry_type" method="post" onsubmit="return validator();">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:150px ;">
                       
                            <tr>
                                <td class="">Visit Year</td>
                                <td><select name ="year" id="year" required="true" class="textbox2" style="width:150px;">
                                <option value="">Loading years...</option>
                                </select> 
                                </td></tr>
                            <tr>
                                <td class="">Visit Month</td>
                                <td>
                                 <select name ="month" id="month" required="true" class="textbox2" style="width:150px;">
                                <option value="">Choose Month</option>
                              
                                    </select></td></tr>
                            <tr>
                                <td class="align_button_left"><input type="radio" id="radio1" name="etype" value="site" tabindex="1" class="css_radio"><label for="radio1">Site Checklist.</label></td>
                                <td></td></tr>
                            <tr>
                                <td class="align_button_left"><input type="radio" id="radio2" name="etype" value="tools" tabindex="2" class="css_radio"><label for="radio2">Tools Checklist.</label></td>
                                <td></td></tr>
                             <tr>
                                <td class="align_button_left"><input type="radio" checked="checked" id="radio3" name="etype" value="both" tabindex="3" class="css_radio"><label for="radio3">Both.</label></td>
                                <td></td></tr>
                            <tr></tr>

                        
            
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
