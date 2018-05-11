/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function htcf(){
    var total_marks=0;;var htc="";var f="";
   for(var i=1;i<=2;i++){
     htc=document.getElementById("htcf1"+i).value;
     f=document.getElementById("htcf_1"+i).value;
    total_marks=document.getElementById("htcf_to").value;
    if(htc=="YES" && f==0){total_marks++; document.getElementById("htcf_1"+i).value="1"}
    if(htc=="NO"  && f==1){total_marks--; document.getElementById("htcf_1"+i).value="0"}
    document.getElementById("htcf").innerHTML=total_marks;
    document.getElementById("htcf_to").value=total_marks;
}
}



function pmtct_filled(){
    var total_marks=0; var i=3;var pmtct="";var filled="";
   while(i<=11){
     pmtct=document.getElementById("filled2"+i).value;
    filled=document.getElementById("filled_2"+i).value;
    total_marks=document.getElementById("filled_pmtcttotal").value;
    if(pmtct=="YES" && filled==0){total_marks++; document.getElementById("filled_2"+i).value="1"}
    if(pmtct=="NO"  && filled==1){total_marks--; document.getElementById("filled_2"+i).value="0"}
    document.getElementById("filled_pmtct").innerHTML=total_marks;
    document.getElementById("filled_pmtcttotal").value=total_marks;
i++;
}
}





function art_filled(){
    var total_marks=0; var i=12;var art="";var filled="";
   while(i<=19){
     art=document.getElementById("filled3"+i).value;
    filled=document.getElementById("filled_3"+i).value;
    total_marks=document.getElementById("filled_arttotal").value;
    if(art=="YES" && filled==0){total_marks++; document.getElementById("filled_3"+i).value="1"}
    if(art=="NO"  && filled==1){total_marks--; document.getElementById("filled_3"+i).value="0"}
    document.getElementById("filled_art").innerHTML=total_marks;
    document.getElementById("filled_arttotal").value=total_marks;
    document.getElementById("filled_art1").innerHTML=total_marks;
    document.getElementById("filled_arttotal1").value=total_marks;
i++;
}
}
function art_filled1(){
    var total_marks=0; var i=20;var art="";var filled="";
   while(i<=26){
     art=document.getElementById("filled4"+i).value;
    filled=document.getElementById("filled_4"+i).value;
    total_marks=document.getElementById("filled_arttotal").value;
        if(art=="YES" && filled==0){total_marks++; document.getElementById("filled_4"+i).value="1"}
    if(art=="NO"  && filled==1){total_marks--; document.getElementById("filled_4"+i).value="0"}
    document.getElementById("filled_art").innerHTML=total_marks;
    document.getElementById("filled_arttotal").value=total_marks;
    document.getElementById("filled_art1").innerHTML=total_marks;
    document.getElementById("filled_arttotal1").value=total_marks;
i++;
}
}

// SECTION 4



function tb_filled(){
    var total_marks=0; var i=27;var tb="";var filled="";
   while(i<=29){
     tb=document.getElementById("filled5"+i).value;
    filled=document.getElementById("filled_5"+i).value;
    total_marks=document.getElementById("filled_tbtotal").value;
    if(tb=="YES" && filled==0){total_marks++; document.getElementById("filled_5"+i).value="1"}
    if(tb=="NO"  && filled==1){total_marks--; document.getElementById("filled_5"+i).value="0"}
    document.getElementById("filled_tb").innerHTML=total_marks;
    document.getElementById("filled_tbtotal").value=total_marks;
i++;
}
}
