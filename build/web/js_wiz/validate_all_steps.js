/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    	// Smart Wizard     	
  		$('#wizard').smartWizard({transitionEffect:'slideleft',onLeaveStep:leaveAStepCallback,onFinish:onFinishCallback,enableFinishButton:true});

      function leaveAStepCallback(obj){
        var step_num= obj.attr('rel');
        return validateSteps(step_num);
      }
      
      function onFinishCallback(){
       if(validateAllSteps()){
        $('form').submit();
       }
      }
            
		});
	   
    function validateAllSteps(){
       var isStepValid = true;
       
       if(validateStep1() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:false});
       }
       
       if(validateStep2() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:2,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:2,iserror:false});
       }
       if(validateStep3() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:false});
       }
        if(validateStep4() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:false});
       }
        if(validateStep5() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:false});
       }
 
       
       
       if(!isStepValid){
          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please correct the errors in the steps and continue');
       }
              
       return isStepValid;
    } 	
		
		
		function validateSteps(step){
		  var isStepValid = true;
      // validate step 1
      if(step == 1){
        if(validateStep1() == false ){
          isStepValid = false; 
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
       
         $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
        
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');

        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
     
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
 
        }
       else if(date==="" || date===null || date==="null"){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');

        }
          
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
     //  validate step2
      if(step == 2){
        if(validateStep2() == false ){
          isStepValid = false; 
          year=document.getElementById("year").value;
          month=document.getElementById("month").value;
       
          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
       var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        
         $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
        
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');

        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
     
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
 
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');

        }
        
         $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
            if(step == 3){
        if(validateStep3() == false ){
          isStepValid = false; 
         year=document.getElementById("year").value;
          month=document.getElementById("month").value;
        
          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
       var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        
         $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
        
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');

        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
     
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
 
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');

        }
        
        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
 if(step == 4){
        if(validateStep4() == false ){
          isStepValid = false; 
         year=document.getElementById("year").value;
          month=document.getElementById("month").value;
        
          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        
         $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
        
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');

        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
     
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
 
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');

        }
        
        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      return isStepValid;
    }
		
		function validateStep1(){
       var isValid = true;
        var rc="",sc711="",sc731="",dhis="";
        var el1=false,el2=false,el3=false,el4=false;
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');
          isValid=false;  
        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
          isValid=false;  
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
          isValid=false;  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
          isValid=false;  
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');
          isValid=false;  
        }
        
        else{
//        alert("here")
       //for(var i=1;i<=6;i++){
       for(var i=31;i<=42;i++){
       rc=sc711=sc731=dhis="";
       el1=false,el2=false,el3=false,el4=false;
var check_element=document.getElementById("rc1"+i);
var check_element2=document.getElementById("sc7111"+i);
//var check_element3=document.getElementById("sc7311"+i);
var check_element4=document.getElementById("dhis1"+i);

if(check_element!=null){rc=document.getElementById("rc1"+i).value; el1=true}
if(check_element2!=null){sc711=document.getElementById("sc7111"+i).value; el2=true}
//if(check_element3!=null){sc731=document.getElementById("sc7311"+i).value;el3=true}
if(check_element4!=null){dhis=document.getElementById("dhis1"+i).value; el4=true}
 
    if((rc=="" && el1==true)|| (sc711=="" && el2==true) /**|| (sc731=="" && el3==true)**/ || (dhis=="" && el4==true)){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Enter all the values and then click next.</b></font>');
     isValid=false;
     break;
    }
    
    }
    }
  return isValid;
        
    }
    
    function validateStep2(){
       var isValid = true;
        var rc="",sc711="",sc731="",dhis="";
        var el1=false,el2=false,el3=false,el4=false;
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');
          isValid=false;  
        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
          isValid=false;  
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
          isValid=false;  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
          isValid=false;  
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');
          isValid=false;  
        }
        
        else{
       //for(var i=7;i<=15;i++){
       for(var i=43;i<=47;i++){
       rc=sc711=sc731=dhis="";
       el1=false,el2=false,el3=false,el4=false;
var check_element=document.getElementById("rc2"+i);
var check_element2=document.getElementById("sc7112"+i);
//var check_element3=document.getElementById("sc7312"+i);
var check_element4=document.getElementById("dhis2"+i);

if(check_element!=null){rc=document.getElementById("rc2"+i).value; el1=true}
if(check_element2!=null){sc711=document.getElementById("sc7112"+i).value; el2=true}
//if(check_element3!=null){sc731=document.getElementById("sc7312"+i).value;el3=true}
if(check_element4!=null){dhis=document.getElementById("dhis2"+i).value; el4=true}  
    if((rc=="" && el1==true)|| (sc711=="" && el2==true) || /**(sc731=="" && el3==true) || */(dhis=="" && el4==true)){
     isValid=false;
     break;
    }
    
    }
    }
  return isValid;
   }
      function validateStep3(){
       var isValid = true;
        var rc="",sc711="",sc731="",dhis="";
        var el1=false,el2=false,el3=false,el4=false;
        
         var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');
          isValid=false;  
        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
          isValid=false;  
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
          isValid=false;  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
          isValid=false;  
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');
          isValid=false;  
        }
        
        else{
       //for(var i=16;i<=19;i++){
       for(var i=49;i<=50;i++){
       rc=sc711=sc731=dhis="";
       el1=false,el2=false,el3=false,el4=false;
var check_element=document.getElementById("rc3"+i);
var check_element2=document.getElementById("sc7113"+i);
//var check_element3=document.getElementById("sc7313"+i);
var check_element4=document.getElementById("dhis3"+i);

if(check_element!=null){rc=document.getElementById("rc3"+i).value; el1=true}
if(check_element2!=null){sc711=document.getElementById("sc7113"+i).value; el2=true}
//if(check_element3!=null){sc731=document.getElementById("sc7313"+i).value;el3=true}
if(check_element4!=null){dhis=document.getElementById("dhis3"+i).value; el4=true}  
    if((rc=="" && el1==true)|| (sc711=="" && el2==true) || /**(sc731=="" && el3==true) || */ (dhis=="" && el4==true)){
     isValid=false;
     break;
    }
    
    }
    }
  return isValid;
    }  
  

function validateStep4(){
       var isValid = true;
        var rc="",sc711="",sc731="",dhis="";
        var el1=false,el2=false,el3=false,el4=false;
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');
          isValid=false;  
        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
          isValid=false;  
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
          isValid=false;  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
          isValid=false;  
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');
          isValid=false;  
        }
        
        else{
       //for(var i=20;i<=21;i++){
       for(var i=55;i<=55;i++){
       rc=sc711=sc731=dhis="";
       el1=false,el2=false,el3=false,el4=false;
var check_element=document.getElementById("rc4"+i);
var check_element2=document.getElementById("sc7114"+i);
//var check_element3=document.getElementById("sc7314"+i);
var check_element4=document.getElementById("dhis4"+i);

if(check_element!==null){rc=document.getElementById("rc4"+i).value; el1=true;}
if(check_element2!==null){sc711=document.getElementById("sc7114"+i).value; el2=true;}
//if(check_element3!==null){sc731=document.getElementById("sc7314"+i).value;el3=true;}
if(check_element4!==null){dhis=document.getElementById("dhis4"+i).value; el4=true;}  
    if((rc==="" && el1===true)|| (sc711==="" && el2===true) /*||  (sc731==="" && el3===true) */ || (dhis==="" && el4===true)){
     isValid=false;
     break;
    }
    
    }
    }
  return isValid;
}


function validateStep5(){
       var isValid = true;
        var rc="",sc711="",sc731="",dhis="";
        var el1=false,el2=false,el3=false,el4=false;
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var hf_id=document.getElementById("hf_id").value;
        var aphia=document.getElementById("aphia").value;
        var date=document.getElementById("date").value;
        if(year==""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment year and then click next.</b></font>');
          isValid=false;  
        }
         else if(month==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select assessment month and then click next.</b></font>');
          isValid=false;  
        }
         else if(hf_id==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select health facility and then click next.</b></font>');
          isValid=false;  
        }
         else if(aphia==""){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select APHIA Staff and then click next.</b></font>');
          isValid=false;  
        }
       else if(date==""){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select date of visit and then click next.</b></font>');
          isValid=false;  
        }
        
        else{
       //for(var i=20;i<=21;i++){
       for(var i=56;i<=58;i++){
       rc=sc711=sc731=dhis="";
       el1=false,el2=false,el3=false,el4=false;
var check_element=document.getElementById("rc4"+i);
var check_element2=document.getElementById("sc7114"+i);
//var check_element3=document.getElementById("sc7314"+i);
var check_element4=document.getElementById("dhis4"+i);

if(check_element!==null){rc=document.getElementById("rc4"+i).value; el1=true;}
if(check_element2!==null){sc711=document.getElementById("sc7114"+i).value; el2=true;}
//if(check_element3!==null){sc731=document.getElementById("sc7314"+i).value;el3=true;}
if(check_element4!==null){dhis=document.getElementById("dhis4"+i).value; el4=true;}  
    if((rc==="" && el1===true)|| (sc711==="" && el2===true) /*||  (sc731==="" && el3===true) */ || (dhis==="" && el4===true)){
     isValid=false;
     break;
    }
    
    }
    }
  return isValid;
}