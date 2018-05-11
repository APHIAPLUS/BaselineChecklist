<%-- 
    Document   : super_admin
    Created on : Mar 13, 2014, 10:03:38 PM
    Author     : Geofrey Nyabuto
--%>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='css/styles.css' />
        <link rel='stylesheet' type='text/css' href='css/buttons.css' />
<!--         <script type="text/javascript" src="js/1.10.2.min.js"></script>-->
</head>
<body>
<div id='cssmenu'>
<ul>
   
   <li class='has-sub'><a href='#'><span class="btn_header">Entries</span></a>
      <ul>
          <li class='has-sub'><a href='add_entries.jsp'><span>Add Entries</span></a>
          </li>
          
          <li class='has-sub'><a href='#'><span>Edit Entries</span></a>
          <ul>
             <!--<li><a href='#'><span>Edit District</span></a></li>-->
               <li><a href='LoadFacilities'><span>Edit Health Facility</span></a></li>
               <li><a href='edit_staff.jsp'><span>Edit APHIA/MOH Staff</span></a></li>
               
            </ul>
          </li> 
          <li><a href='add_users.jsp'><span>Add Users</span></a></li>
         </li>
      </ul>
   </li>
        <li class='has-sub'><a href='entry_type.jsp'><span class="btn_header">Checklist Form Entry</span></a>
      <ul>
         <li class='has-sub'><a href='entry_type.jsp'><span>Checklist Form Entry</span></a></li>
        <!--<li class='has-sub'><a href='edit_type.jsp'><span>Edit Checklist Form</span></a></li>-->
      </ul>
   </li>
    
    <li class='has-sub'><a href='#'><span class="btn_header">Management</span></a>
 <ul>
            <li><a href='set_mail.jsp'>M&E Officer Email</a></li>
<!--            <li><a href='add_users.jsp'>Add Users</a></li>-->
<!--            <li><a href='edit_ur_details.jsp'>Edit Profile</a></li>-->
      </ul>
   </li>
      <li class='has-sub'><a href='#'><span class="btn_header">Data</span></a>
 <ul>
             <li class='has-sub'><a href='MergeData.jsp'><span>Merge Data</span></a></li>


               <li class='last'><a href='BackUp_Data.jsp'><span>Send Data</span></a></li>
                 <li class='last'><a href='upload_facilities.jsp'><span>Upload Facilities.</span></a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span class="btn_header">Reports</span></a>
 <ul>
<li class='last'><a href='visitRawData.jsp'><span> Checklist Raw Data (Analysis)</span></a></li>
<!--<li class='last'><a href='monthlySiteRawData.jsp'><span> Checklist Monthly Data</span></a></li>-->
<li class='last'><a href='report_facility.jsp'><span> Performance Reports</span></a></li>
   <li class='has-sub'><a href='#'><span> Charts</span></a>
  <ul>
  <li class='last'><a href='lineGraph.jsp'><span>Variance Report (One indicator)</span></a></li>  
   <li class='last'><a href='variationReport.jsp'><span>Variance Report(Many indicators)</span></a></li>  
    <li class='last'><a href='Charts.jsp'><span>Program Area</span></a></li>  
      </ul>
   
   </li> 
 </ul>
   </li> 
     <li class='has-sub'><a href='#'><span class="btn_header">Help</span></a>
         <ul>
         <li><a href='Help.jsp'><span>System Help</span></a></li>
            
         <li><a href='menu/checklist.pdf' target="_blank"><span>Download Help</span></a></li>
      </ul>
   </li>
   <li class='last'><a href='logout.jsp'><span class="btn_header">Log Out</span></a></li>
</ul>
</div>
</body>
</html>