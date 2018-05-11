<%-- 
    Document   : add_users
    Created on : May 8, 2014, 10:50:50 AM
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
        <title>Add New User</title>
      
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Add New Users.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>
                   
            <div id="content" style="height:auto; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
    <div id="dialog" title="Add User Help." style=" font-size: 17px;">
<p>The fields marked (<font color="red">*</font>) are must enter fields.</p>
<p>The user id is system auto-generated.</p>

</div>
                <div id="midcontent" style="margin-left:200px ; height:auto;" >
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
                        
                        
                        
                        
                   
                   
                    <p><font color="red">*</font> indicates must fill fields</p>
                    <form action="add_users" method="post">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:150px ;">
                       
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Userid<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text readonly value="<%=generateRandomNumber(1000,10888)%>" required name=userid class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Surname<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="s_name" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="f_name" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                           
                            
                            
                            <tr>
                               
                            <td class="align_button_left"><label for="email">Phone Number</label></td>
                            <td><input  type="text" name="phoneno" pattern="[0-9]{10,10}" class="textbox" /></td>
                            
                           
                            
                            </tr>
                            <tr>
                                <td>
                                    
                                    
                                                           
                                </td>
                                 
                            </tr>
                          
                     

                            
                         
                           <tr> <td class="align_button_left"><label for="town">Username <font color="red">*</font></label></td>
                            <td><input id="town" type=text required name=username class="textbox"/></td></tr>
                            
                           <tr>
                            <td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
                            <td><input id="password" type=password required name=pass oninput="checkPasswords()" class="textbox"/></td>
                           </tr>
                           <tr>
                           
                                <td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
                                <td ><input id="conf_password" type=password required name=conf_password oninput="checkPasswords()" class="textbox"/></td>
                                <td></td>
                           </tr>  
                           
                           <tr>
                           
                                <td class="align_button_left"><label for="">Choose Level <font color="red">*</font></label></td>
                                <td ><select name="level" id="level" class="textbox2" required>
                                         <option value="">Choose Level</option>
                                        <option value="1">Administrator</option>
                                        <option value="3">M&E Officer</option>
                                        <option value="2">User</option>
                                        
                                    </select>
                                </td>
                                <td></td>
                           </tr> 
            
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                           <tr> 
                               <td class="align_button_left"><input  size="12px"  type="reset" value="clear" /></td> <td class="align_button_right">
                               <input type="submit" class="submit" value="Add" onmouseover="getAge();"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
          
            </div>
<div id="footer">
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>
