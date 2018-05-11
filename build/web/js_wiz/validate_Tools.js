/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
         if(year=="" || month==""){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select Year and month and then click next.</b></font>');   
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
       
          if(year=="" || month==""){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select Year and month and then click next.</b></font>');   
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
        
          if(year=="" || month==""){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select Year and month and then click next.</b></font>');   
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
        
       if(year=="" || month==""){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select Year and month and then click next.</b></font>');   
        }
        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
       if(step == 5){
        if(validateStep5() == false ){
          isStepValid = false; 
         year=document.getElementById("year").value;
          month=document.getElementById("month").value;
       if(year=="" || month==""){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Select Year and month and then click next.</b></font>');   
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
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        if(year=="" || month==""){
           isValid=false;  
        }
    
    

  return isValid;
    }
    
    function validateStep2(){
       var isValid = true;
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        if(year=="" || month==""){
           isValid=false;  
        }

  return isValid;
   }
      function validateStep3(){
       var isValid = true;
        
         var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        if(year=="" || month==""){
           isValid=false;  
        }

  return isValid;
    }  
  

function validateStep4(){
       var isValid = true;
        
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        if(year=="" || month==""){
           isValid=false;  
        }

  return isValid;
}


function validateStep5(){
       var isValid = true;
 var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        if(year=="" || month==""){
           isValid=false;  
        }

  return isValid;
}
