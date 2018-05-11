<%-- 
    Document   : edit_MOH
    Created on : May 21, 2014, 4:21:15 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
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
<!--        <link rel="stylesheet" type="text/css" href="css/select.css"/>-->
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Edit MOH Member details</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  
        
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
        
        
  <!----datatable--Maureens---->
  
<!--
        <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>-->
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
        <!--<script src="media/js/jquery-ui.js" type="text/javascript"></script>-->
        <!--   <script src="media/js/jquery.validate.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-ui.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.js" type="text/javascript"></script>
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
  
  
 <script type="text/javascript">
    
            $(document).ready( function () {
                $('#example').dataTable().makeEditable({
                                  
									
                    sUpdateURL: "save_editedMOH",
                    //                                                                        sAddURL: "AddCounty",
                    sDeleteURL: "deletefacil",
                    "aoColumns": [ null,null,   

//============
                    									
                        {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
//                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                        
                        
                        //================
                        
  {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
//                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        } , {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
//                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        }, {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
//                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        }, {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
//                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        }                   									
                    ]									

                });
				
            } );
            
           
            
        </script>
        <script type="text/javascript">

            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-17838786-2']);
            _gaq.push(['_trackPageview']);

//            (function() {
//                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
//                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
//                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
//            })();

        </script>
<!--clerk menu-->



  <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<script src="js/jquery-ui-1.10.3.custom.js"></script>  
    </head>
    <body onload="">
       
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
            
       <div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Edit MOH Staff Details. </div>
                   
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>              
                <div id="demo" style="margin-left: 100px;">
           
           <p id="msg"><p>
<!--                          <button  title="Add chw" value="Ok">Add new chw</button> -->
<!--                        <button id="btnDeleteRow" value="cancel">Delete Facility</button>-->

                        <table cellpadding="4px" cellspacing="4px" style="padding-top: 100px;" border="0" class="display" id="example">
                            <thead>
                                <tr  >
    <th>No.</th>
     <th>County</th>
  <th>First Name</th>   
   <th>Middle Name</th> 
   <th>Last Name</th>
   <th>Phone Number</th> 
     
</tr>
    </thead>

    <c:forEach items="${moh}" var="moh">
<tr id="${moh.id}">
    <td  class="sorting_1">${moh.pos}</td>
    <td  class="sorting_1">${moh.county}</td> 
    <td  class="sorting_1">${moh.fname}</td> 
    <td  class="sorting_1">${moh.mname}</td>  
    <td  class="sorting_1">${moh.lname}</td> 
    <td  class="sorting_1">${moh.phone}</td>  
    
    
</tr>

</c:forEach>
</table>

       </div>
                </div>

           
<div id="footer">
     <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>

