<%-- 
    Document   : index
    Created on : May 7, 2014, 7:40:15 PM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>M&E Checklist</title>
        <link rel="shortcut icon" href="images/header.PNG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link rel='stylesheet' type='text/css' href='css/main.css' />
        
   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
<script type="text/javascript">
           $(document).ready(function(){
    $.ajax({
        url:"BackUpReminder",
        type:"post",
        dataType:"html",
        success:function(data){
            var res=data;
            var len=parseInt(res.length);
            if(res<=2){
                 $("#header").html("");
            }
            else{
                $("#header").html("<div class=\"ui-widget\" style=\"width:1100px; margin-left: -140px;\" ><div class=\"ui-state-error ui-corner-all\" style=\"padding: 0 .7em;\"><p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin-right: .3em;\"></span><p style=\"text-align: center\" id=\"reminder\"></p></div>");
             $("#content").hide();
             $("#reminder").html(data);
//             $("#header").animate({'marginTop':"+=100px"});
             $("#header").effect( "shake",{times:4,direction:'left',distance:20}, 10000 );
//             $("#header").delay(4000).fadeIn();
//             $("#header").animate({'marginTop':"-=100px"});
//$("#header").html("");
     setInterval(function(){  $("#content").show(5000); }, 10000);
            
//             $("#header").hide(2000);
            }
        }
    });
       $.ajax({
        url:"ManualBackUp",
        type:"post",
//               data:""
        dataType:"html"
    });
  });
    </script>
    </head>
    <body>
        <div id="container" style=" height: auto">
<div id="header">
               </div>
            
            <div id="content" style=" height:auto">
                
                <div id="midcontent" style=" height: auto">
                      
                    <img src="images/index.PNG" width="700px" alt="M$E Checklist System Image" align="centre"
                                               style="position: relative; top:20px; left: 210px; height: 190px;"/>
                                              
                       <div style="position: relative; top: 40px; margin-left: 200px;">                                       
                           <form action="login" method="post" style=" margin-left: 100px;width: 500px;">
                       <p align="center">Login Here.</p>
                        <table style="margin-left: 100px; width: 500px;" >
                           
                            <tr>
                                <td></td>
                                <td style="text-align: left">
                                     <!--  username -->
                                    <input id="username"  type=text required name=uname placeholder="Username" autofocus class="textbox"/></td>
                                 </tr>
                            <tr><td></td> </tr>
                            <tr><td></td> </tr> <tr><td></td> </tr>
                            <tr><td></td>
                                <!--password-->
                                <td style="text-align: left"><input id="password"  type=password required name=pass placeholder="Password" class="textbox"></td>
                            </tr>
                              <tr><td></td> </tr> <tr><td></td> </tr> <tr><td></td> </tr>
                            <tr>
                                <td style="text-align: left"> </td>
                                <td style="text-align: left"><input type="submit" class="submit" value="Log In"/>
                                   
                                    
                                </td>
                            </tr>
                             <tr><td style="text-align: left" colspan="2"  class="linkstyle"></td> </tr>
<!--                             <tr>
                                   <td style="text-align: left" colspan="2"  class="linkstyle">Don't Have An Account? 
                                    <a href="add_end_users.jsp" style="">Register Here.</a>
                                </td> 
                            </tr>-->
                        </table>
                       <h4>
                        <%
                            if (session.getAttribute("error_login") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("error_login")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2800});
                    
                </script> <%
                
                session.removeAttribute("error_login");
                            }

                        %>
                        </h4>
                    </form>
                     </div>
                        <br> <br> <br>
                        <img src="images/aphia_logo.png" width="300px" height="70px" style="margin-left: 400px;">
                       </div>
            </div>

           
<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System  <%=year%> USAID || APHIAPlus</p>
            </div>
        </div>
       
    </body>
</html>
