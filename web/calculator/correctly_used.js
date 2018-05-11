/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function htc_used(){
    var total_marks=0; var i=1;var htc="";var used="";
   while(i<=2){
     htc=document.getElementById("used1"+i).value;
    used=document.getElementById("used_1"+i).value;
    total_marks=document.getElementById("used_htctotal").value;
    if(htc=="YES" && used==0){total_marks++; document.getElementById("used_1"+i).value="1"}
    if(htc=="NO"  && used==1){total_marks--; document.getElementById("used_1"+i).value="0"}
    document.getElementById("used_htc").innerHTML=total_marks;
    document.getElementById("used_htctotal").value=total_marks;
i++;
}
}



function pmtct_used(){
    var total_marks=0; var i=3;var pmtct="";var used="";
   while(i<=11){
     pmtct=document.getElementById("used2"+i).value;
    used=document.getElementById("used_2"+i).value;
    total_marks=document.getElementById("used_pmtcttotal").value;
    if(pmtct=="YES" && used==0){total_marks++; document.getElementById("used_2"+i).value="1"}
    if(pmtct=="NO"  && used==1){total_marks--; document.getElementById("used_2"+i).value="0"}
    document.getElementById("used_pmtct").innerHTML=total_marks;
    document.getElementById("used_pmtcttotal").value=total_marks;
i++;
}
}


//SECTION 3



function art_used(){
    var total_marks=0; var i=12;var art="";var used="";
   while(i<=19){
    art=document.getElementById("used3"+i).value;
    used=document.getElementById("used_3"+i).value;
    total_marks=document.getElementById("used_arttotal").value;
    if(art=="YES" && used==0){total_marks++; document.getElementById("used_3"+i).value="1"}
    if(art=="NO"  && used==1){total_marks--; document.getElementById("used_3"+i).value="0"}
    document.getElementById("used_art").innerHTML=total_marks;
    document.getElementById("used_arttotal").value=total_marks;
        document.getElementById("used_art1").innerHTML=total_marks;
    document.getElementById("used_arttotal1").value=total_marks;
i++;
}
}
function art_used1(){
    var total_marks=0; var i=20;var art="";var used="";
   while(i<=26){
    art=document.getElementById("used4"+i).value;
    used=document.getElementById("used_4"+i).value;
    total_marks=document.getElementById("used_arttotal").value;
    if(art=="YES" && used==0){total_marks++; document.getElementById("used_4"+i).value="1"}
    if(art=="NO"  && used==1){total_marks--; document.getElementById("used_4"+i).value="0"}
    document.getElementById("used_art").innerHTML=total_marks;
    document.getElementById("used_arttotal").value=total_marks;
        document.getElementById("used_art1").innerHTML=total_marks;
    document.getElementById("used_arttotal1").value=total_marks;
i++;
}
}


function tb_used(){
    var total_marks=0; var i=27;var tb="";var used="";
   while(i<=29){
     tb=document.getElementById("used5"+i).value;
    used=document.getElementById("used_5"+i).value;
    total_marks=document.getElementById("used_tbtotal").value;
    if(tb=="YES" && used==0){total_marks++; document.getElementById("used_5"+i).value="1"}
    if(tb=="NO"  && used==1){total_marks--; document.getElementById("used_5"+i).value="0"}
    document.getElementById("used_tb").innerHTML=total_marks;
    document.getElementById("used_tbtotal").value=total_marks;
i++;
}
}