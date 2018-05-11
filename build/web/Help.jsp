<%-- 
    Document   : Help
    Created on : May 27, 2014, 9:09:21 PM
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
        <title>M&E System Help</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  
                
                <!-- JavaScript -->
  <!-- <script type="text/javascript" src="combine.php?type=javascript&files=prototype.js,effects.js,accordion.js,code_highlighter.js,javascript.js,html.js"></script> -->

	<script type="text/javascript" src="Help/javascript/prototype.js"></script>
	<script type="text/javascript" src="Help/javascript/effects.js"></script>
	<script type="text/javascript" src="Help/javascript/accordion.js"></script>
	<script type="text/javascript" src="Help/javascript/code_highlighter.js"></script>
	<script type="text/javascript" src="Help/javascript/javascript.js"></script>
	<script type="text/javascript" src="Help/javascript/html.js"></script>

	<!-- CSS -->
  <link rel="stylesheet" type="text/css" href="css/default.css" />
    <link rel="stylesheet" type="text/css" href="Help/css/main.css"/>
<link rel="shortcut icon" href="Help/images/header.JPG"/>
	<script type="text/javascript">
			
		//
		//  In my case I want to load them onload, this is how you do it!
		// 
		Event.observe(window, 'load', loadAccordions, false);
	
		//
		//	Set up all accordions
		//
		function loadAccordions() {
			var topAccordion = new accordion('horizontal_container', {
				classNames : {
					toggle : 'horizontal_accordion_toggle',
					toggleActive : 'horizontal_accordion_toggle_active',
					content : 'horizontal_accordion_content'
				},
				defaultSize : {
					width : 525
				},
				direction : 'horizontal'
			});
			
			var bottomAccordion = new accordion('vertical_container');
			
			var nestedVerticalAccordion = new accordion('vertical_nested_container', {
			  classNames : {
					toggle : 'vertical_accordion_toggle',
					toggleActive : 'vertical_accordion_toggle_active',
					content : 'vertical_accordion_content'
				}
			});
			
			// Open first one
			bottomAccordion.activate($$('#vertical_container .accordion_toggle')[0]);
			
			// Open second one
			topAccordion.activate($$('#horizontal_container .horizontal_accordion_toggle')[2]);
		}
		
	</script>
	
	<!-- CSS -->
	<style type="text/css" >
		
		/*
			Vertical Accordions
		*/
		
		.accordion_toggle {
			display: block;
			height: 30px;
			width: 680px;
			background: url(images/accordion_toggle.jpg) no-repeat top right #a9d06a;
			padding: 0 10px 0 10px;
			line-height: 30px;
			color: #ffffff;
			font-weight: normal;
			text-decoration: none;
			outline: none;
			font-size: 12px;
			color: #000000;
			border-bottom: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}
		
		.accordion_toggle_active {
			background: url(images/accordion_toggle_active.jpg) no-repeat top right #e0542f;
			color: #ffffff;
			border-bottom: 1px solid #f68263;
		}
		
		.accordion_content {
			background-color: #ffffff;
			color: #444444;
			overflow: hidden;
		}
			
			.accordion_content h2 {
				margin: 15px 0 5px 10px;
				color: #0099FF;
			}
			
			.accordion_content p {
				line-height: 150%;
				padding: 5px 10px 15px 10px;
			}
			
		.vertical_accordion_toggle {
			display: block;
			height: 30px;
			width: 600px;
			background: url(images/accordion_toggle.jpg) no-repeat top right #a9d06a;
			padding: 0 10px 0 10px;
			line-height: 30px;
			color: #ffffff;
			font-weight: normal;
			text-decoration: none;
			outline: none;
			font-size: 12px;
			color: #000000;
			border-bottom: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}

		.vertical_accordion_toggle_active {
			background: url(images/accordion_toggle_active.jpg) no-repeat top right #e0542f;
			color: #ffffff;
			border-bottom: 1px solid #f68263;
		}

		.vertical_accordion_content {
			background-color: #ffffff;
			color: #444444;
			overflow: hidden;
		}

			.vertical_accordion_content h2 {
				margin: 15px 0 5px 10px;
				color: #0099FF;
			}

			.vertical_accordion_content p {
				line-height: 150%;
				padding: 5px 10px 15px 10px;
			}
  			
		/*
			Horizontal Accordion
		*/
		
		.horizontal_accordion_toggle {
			/* REQUIRED */
			float: left;	/* This make sure it stays horizontal */
			/* REQUIRED */

			display: block;
			height: 100px;
			width: 30px;
			background: url(images/h_accordion_toggle.jpg) no-repeat top left #a9d06a;
			color: #ffffff;
			text-decoration: none;
			outline: none;
			border-right: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}
		
		.horizontal_accordion_toggle_active {
			background: url(images/h_accordion_toggle_active.jpg) no-repeat top left #e0542f;
			border-right: 1px solid #f68263;
		}
		
		.horizontal_accordion_content {
			/* REQUIRED */
			height: 100px;	/* We need to define a height for the accordion as it stretches the width */
			float: left;	/* This make sure it stays horizontal */
			/* REQUIRED */
			
			overflow: hidden;
			background-color: #ffffff;
			color: #444444;
		}
			
			.horizontal_accordion_content p {
				width: 450px;
				line-height: 150%;
				padding: 5px 10px 15px 10px;
			}
					
					
    /* Container styling*/
    #horizontal_container {
      margin: 20px auto 20px auto;
      width: 680px;   
      height: 100px;    
    }
    
    #vertical_nested_container {
      margin: 20px auto 20px auto;
      width: 620px;
    }

	</style>
        
    </head>
    <body onload="load_counties();load_moh();load_aphia();">
       
     <div id="container" style="height:auto;" >
   <%if(session.getAttribute("level")!=null){ if(session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if(session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/user_menu.jsp" %>
         
         <%}else{}}%>

         <div id="content" style="height:auto; width: 100%;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
    <p class="description" style=" color: green; margin-left: 350px; margin-top: 0px; font-size: 30px;" >M&E CHECKLIST SYSTEM HELP.</p>
        
	<div id="vertical_container" >

            
            
            
            
            
   	<h1 class="accordion_toggle"  style="margin-left: 5%;width: 90%; font-size: 18px;">Entries</h1>
		<div class="accordion_content" style="margin-left: 5%;width: 90%;">   
                    <h2 style="font-size: 20px; margin-left: 400px;">Manage Entries</h2>
                    <p style=" font-size: 18px;">Within this Section, The Administrator will be guided on how he/she can be 
                        able to add and edit new districts, health facilities,Aphia Staff ,MOH Staff.</p>
       <div id="vertical_nested_container" >
      <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Add Entries</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>Within this section the user is able to add districts,add health facilities, add MOH Staffs, add APHIA Staffs.<br>
                    When the user clicks on the <font color="blue-violet"><b>Add Entries</b></font> link, the user will be directed to page where he/she can be able to make all the above entries.<br>
                    <img src="Help/images/add_entries.PNG" width="500px" height="150px;" alt="Add Entries">.<br>
                    As the image shows , click on the correct button to make the right entries.
                    </p>  	
                </div>
       <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Edit Entries</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>The User has also the ability to edit all the entries made above incase of an error.<br>
                        1. Click on edit district to edit district data (<font color="violet">Currently Under Development</font>).<br>
                        2. Click On Edit Health Facility to enter the correct details as pertained to health facility data.(<font color="violet">Currently Under Development</font>).<br>
                        3. Click On Edit MOH/APHIA Staff Button to edit either any of the staff details.
                    </p>  
                     <h4 class="" style="margin-left: 250px;width: 130%;">Choose County and category to edit details.</h4>
                     <img src="Help/images/edit_moh_aphia.PNG" width="500px" height="150px;" alt="Edit MOH/APHIA Entries" style="margin-left: 200px;">.<br>
                     <p>After Selecting the county and selecting the right category, click on next to see a list as the one shown below.<br>
                     
                     </p>
                    <h4 class="" style="margin-left: 250px;width: 130%;">Edit Details Page.</h4>
                     <img src="Help/images/edit_moh_aphia2.PNG" width="700px" height="250px;" alt="Edit MOH/APHIA Entries" style="margin-left: 100px;">.<br>
                   <p>On this page, click on the text that you want to change ,Then a pop up box will appear where you will enter the correct name and then click on save changes.
               <font color="red">   e.g if you want to change the name kingsley to a name like tom, click on kingsley enter name tom and then click on save changes.</font>
                   </p>   
                </div>
    </div>
    </div>
        
        
        
        
        
        
        <h1 class="accordion_toggle" style="margin-left: 5%;width: 90%; font-size: 18px;">Checklist Form Entry</h1>
		<div class="accordion_content" style="margin-left: 5%;width: 90%;">
	              <p style=" font-size: 18px;">This is the commonly used section in the System.<BR>
       <img src="Help/images/checklist_form.png" width="400px" height="160px;" alt="Checklist Form">.   </p>                           
                      <div id="vertical_nested_container" >
      <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Checklist Form Entry</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>
                     This page is divided into three sections,as explained below.<br>
                     <br>1. <b>Section 1</b> This Section enables the user to select the correct form he want to enter, if the user has two forms to enter, he/she chooses both. <br>
                        <img src="Help/images/select_entryform.PNG" width="600px" height="180px;" alt="Form Selection Image">.   
                             <br>2. <b>Section  2</b> This Section enables the user to Select the right health facility and date of visit together with other details based on the form earlier selected.<br>
                             <img src="Help/images/select_siteentry.PNG" width="800px" height="400px;" alt="Detail Selection Image">. <br>
                                
                    <br>3. <b>Section  3</b> This is the form entry section.<br>
                    If the form chosen is Site checklist, the user will be expected first to choose the assessed year and month if the label indicates Data Exist, 
                    then the data for that month year and health facility already exist and it can only be edited. If the label indicated OK, It means the user is allowed to enter the form data.<br>
                    The user must provide valid data before moving to the next section, a user cannot be able to move forward or backward if he/she has entered wrong data in the current section.<br>
                    <font color="red"> <b>NOTE</b></font>:All the fields that appear are required.<br>
                    i.)<b>Site Checklist Form Entry.</b><br>    
                    
                    <img src="Help/images/site_entry.PNG" width="800px" height="500px;" alt="Site Checklist Form">. <br>
                       
                    ii.)<b>Tools Checklist Form Entry.</b><br>
                    
                       <img src="Help/images/tools_entry.PNG" width="800px" height="400px;" alt="Tools Checklist Form">. <br>
                   Enter all the details as required and then Click on finish for each form.
                    </p>  	
                </div>
       <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Edit Checklist Form</h3>
             <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>
                    Incase you made an error during form entry and you need to make correction to the form, Click on the  Edit Checklist form:
                     <br>1. <b>Section 1</b> : This Section enables the user to select the form which he/she needs to edit as shown below <br>
                        <img src="Help/images/edit_checklistform.PNG" width="600px" height="200px;" alt="Edit Health Facility Image">.   
                             <br>2. <b>Section  2</b> : This section enables the user to select the the health facility year and month where he/she wants to edit data.
                    <img src="Help/images/edit_site_select.PNG" width="800px" height="300px;" alt="Edit Health Facility Image">. <br>
                                 </p> 

                                 <p>Click Next and edit all details as you click on finish to save all details.</p>
                </div>
    </div>
		</div>
		
        
        
        
        
        
        
		<h1 class="accordion_toggle"  style="margin-left: 5%;width: 90%; font-size: 18px;">Management</h1>
		<div class="accordion_content" style="margin-left: 5%;width: 90%;">   
                    <h2 style="font-size: 20px; margin-left: 400px;">Manage Maintenance</h2>
                    <p style=" font-size: 18px;">In this section the user is able to set m&e email and also edit his / her details. For the administrators you are also provided with extra privileges of adding other legible system users. </p>
                  <div id="vertical_nested_container" >
                  <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">M&E Officer email</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>This modules assists the user to set a valid M&E mail where he wants his backed up data goes on creating data back up.<br>
                        <font color="red"><b>NOTE</b></font> : The user MUST set this mail before creating any data back up.<br>
                  <img src="Help/images/update_email.PNG" width="600px" height="140px;" alt="Edit Health Facility Image">. <br>
                    </p>  	
                </div>
       <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Add Users</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>
                        The administrator has the right of adding new users to the system, the fields marked <font color="red">*</font> are mandatory fields.</p>  	
               <img src="Help/images/add_users.PNG" width="800px" height="350px;" alt="Edit Health Facility Image">. <br>
                </div>
       <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Edit Profile</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>
                        All System Users are able to edit their own profile. By clicking on edit profile, the user can enter his/her the right details<br>
                            
                            <img src="Help/images/edit_profile.PNG" width="700px" height="300px;" alt="Edit profile image">.
                   </p>  	
                </div>
    </div>
    </div>
             
                
                
                
                
               <h1 class="accordion_toggle"  style="margin-left: 5%;width: 90%; font-size: 18px;">Data</h1>
		<div class="accordion_content" style="margin-left: 5%;width: 90%;">   
                    <h2 style="font-size: 20px; margin-left: 400px;">Data Management Module</h2>
                 <p>Within this module, the M&E Officer is able to merge Data coming from different users. Also the Officer is able to send his/her data back up to the main office.<br>
                 For the other system users you need to set the valid email of your M&E Officer so as the back up you create is directed to this mail.<p>
                    <div id="vertical_nested_container" >
      <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Merge Data</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p>This is a critical section of the system only accessible by M&E Officers and the system administrators.<br>
                    The Officer is supposed to click and select a data file from wherever it is located , then he/she is required to click on <b>Merge</b> button.<br>
                    The System will automatically load the file and merge all the data in the file.<br>
                   <img src="Help/images/MergeData.PNG" width="700px" height="200px;" alt="Merge Checklist System Data Image">.
                    </p>
                    
              </div>
       <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Send Data</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
   	          <p>All the system users are supposed to create there data back up on a regular basis. 
                      This is the module that enable the users to create and send data backups via mail automatically.<br><br>
                   
                   <img src="Help/images/BackUp.PNG" width="700px" height="250px;" alt="Merge Checklist System Data Image">.
                    </p>
                </div>
    </div>
    </div>
                
           
                
                
                
                
                
                <h1 class="accordion_toggle"  style="margin-left: 5%;width: 90%; font-size: 18px;">Reports</h1>
		<div class="accordion_content" style="margin-left: 5%;width: 90%;">   
                    <h2 style="font-size: 20px; margin-left: 400px;"> Types of Reports...</h2>
                    <p>This Module is under development.</p>
                    <div id="vertical_nested_container" >
      <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Reports 1</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                 
                    <p></p>  	
                </div>
       <h3 class="vertical_accordion_toggle" style="margin-left: -15%;width: 130%;">Reports 2</h3>
                <div class="vertical_accordion_content" style="margin-left: -15%;width: 130%; font-size: 15px;">
                    <p></p> </div>
    </div>
    </div>
    
   	
                
                
                
                
		<h1 class="accordion_toggle"  style="margin-left: 5%;width: 90%; font-size: 18px;">Help</h1>
		<div class="accordion_content" style="margin-left: 5%;width: 90%;">
<h3> This is the system Help</h3>
    
   	</div>
   	
		
		<h1 class="accordion_toggle"  style="margin-left: 5%;width: 90%; font-size: 18px;">Log Out.</h1>
		 <div id="horizontal_container" >
            
                <h3 class="horizontal_accordion_toggle">1</h3>
                <div class="horizontal_accordion_content">
                    <p style="font-size: 18px;">
                        Click on the <b>Log Out</b> button to log out.
                    </p>  	
                </div>
                
                <h3 class="horizontal_accordion_toggle">2</h3>
                <div class="horizontal_accordion_content">
                    <p style="font-size: 18px;">
                        <b>Reminder : </b>Please remember to Log out of the system.
                    </p>   	
                </div>
                    
            </div>

        					
	</div><br><br><br>

            </div>
<div id="footer">
                                          <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
   <script type="text/javascript" >
  
	//
	// You can hide the accordions on page load like this, it maintains accessibility
	//
	// Special thanks go out to Will Shaver @ http://primedigit.com/
	//
	var verticalAccordions = $$('.accordion_toggle');
	verticalAccordions.each(function(accordion) {
		$(accordion.next(0)).setStyle({
		  height: '0px'
		});
	});

	
</script>     
    </body>
</html>
