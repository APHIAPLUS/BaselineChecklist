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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Site Checklist</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
 <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <script src="menu/prefix-free.js"></script>  
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>




<!--modal-->
<!--  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
  <script src="modal/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
   <script src="modal/jquery.modal.min.js" type="text/javascript" charset="utf-8"></script>
  <link rel="stylesheet" type="text/css" href="modal/jquery.modal.css"/>
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
    </head>
    <body onload="load_years();">
       
     <div id="container" style="height:auto;" >
 <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("0")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}}%>

              <div id="header" align="center">   
              </div>
            
<div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Enter Site Indicator Values.</div>     
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>  
                    
                </div> <%}%>
                <div id="midcontent" style="margin-left:50px ; height:auto; width: 950;" >
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
                    <form action="add_sitevalue" method="post" style="width: 1000px;">
                       
                      <table align="center" border="0" cellpadding="0" cellspacing="0">
                          
<tr><td> 
<!-- Tabs -->

                       <%if(session.getAttribute("hf_name")!=null){%> 
    <table style="width: 90%" class="table_style">
                 <tr><th><font color="black">Health Facility Name:</font><font color="blue-violet"> <%=session.getAttribute("hf_name").toString()%></font></th>
                    <th><font color="black">Name of MOH Staff:</font> <font color="blue-violet"><%=session.getAttribute("moh_staff_name").toString()%></font></th> 
                    <th><font color="black">Name of APHIA Staff:</font> <font color="blue-violet"><%=session.getAttribute("aphia_staff_name").toString()%></font></th> 
                    <th><font color="black">Date of Visit:</font> <font color="blue-violet"><%=session.getAttribute("date").toString()%></font></th> 
                </tr>
             </table>   
    <br><%}%>

  		<div id="ex9" class="modal">	
            <table style="width: 100%" class="table_style">
               <col width="900px">
    <col width="70px">
    <col width="70px">
    <col width="70px">
                <tr><th>Health Facility</th> <th>Year</th> <th>Month</th> <th>Status</th></tr>
               <tr><td>Nakuru Provincial General Hospital</td><td>2011</td><td>January</td><td>Yes</td></tr>
               <tr><td>Nakuru Provincial General Hospital</td><td>2011</td><td>January</td><td>Yes</td></tr>
              <tr><td>Nakuru Provincial General Hospital</td><td>2011</td><td>January</td><td>No</td></tr>
              <tr><td>Nakuru Provincial General Hospital</td><td>2011</td><td>January</td><td>No</td></tr>
              <tr><td>Nakuru Provincial General Hospital</td><td>2011</td><td>January</td><td>Yes</td></tr>
            </table>        			
   </div>

    <p><a href="#ex9" rel="modal:open">Open Modal</a></p>                   
                            
                           
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                        
                  
                    </form>
          
            </div>
<div id="footer">
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        </div>
            </div>
    </body>
</html>
