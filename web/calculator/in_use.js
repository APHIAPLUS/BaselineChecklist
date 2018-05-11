/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function htc_use(){
    var total_marks=0; var i=1;var htc="";var use="";
   while(i<=2){
     htc=document.getElementById("use1"+i).value;
    use=document.getElementById("use_1"+i).value;
    total_marks=document.getElementById("use_htctotal").value;
    if(htc=="YES" && use==0){total_marks++; document.getElementById("use_1"+i).value="1"}
    if(htc=="NO"  && use==1){total_marks--; document.getElementById("use_1"+i).value="0"}
    document.getElementById("use_htc").innerHTML=total_marks;
    document.getElementById("use_htctotal").value=total_marks;
i++;
}
}

function pmtct_use(){
    var total_marks=0; var i=3;var pmtct="";var use="";
   while(i<=11){
     pmtct=document.getElementById("use2"+i).value;
    use=document.getElementById("use_2"+i).value;
    total_marks=document.getElementById("use_pmtcttotal").value;
    if(pmtct=="YES" && use==0){total_marks++; document.getElementById("use_2"+i).value="1"}
    if(pmtct=="NO"  && use==1){total_marks--; document.getElementById("use_2"+i).value="0"}
    document.getElementById("use_pmtct").innerHTML=total_marks;
    document.getElementById("use_pmtcttotal").value=total_marks;
i++;
}
}


function art_use(){
    var total_marks=0; var i=12;var art="";var use="";
   while(i<=19){
     art=document.getElementById("use3"+i).value;
    use=document.getElementById("use_3"+i).value;
    total_marks=document.getElementById("use_arttotal").value;
    if(art=="YES" && use==0){total_marks++; document.getElementById("use_3"+i).value="1"}
    if(art=="NO"  && use==1){total_marks--; document.getElementById("use_3"+i).value="0"}
    document.getElementById("use_art").innerHTML=total_marks;
    document.getElementById("use_arttotal").value=total_marks;
    document.getElementById("use_art1").innerHTML=total_marks;
    document.getElementById("use_arttotal1").value=total_marks;
i++;
}
}
function art_use1(){
      var total_marks=0; var i=20;var art="";var use="";
      while(i<=26){
    art=document.getElementById("use4"+i).value;
    use=document.getElementById("use_4"+i).value;
    total_marks=document.getElementById("use_arttotal").value;
    if(art=="YES" && use==0){total_marks++; document.getElementById("use_4"+i).value="1"}
    if(art=="NO"  && use==1){total_marks--; document.getElementById("use_4"+i).value="0"}
    document.getElementById("use_art").innerHTML=total_marks;
    document.getElementById("use_arttotal").value=total_marks;
    document.getElementById("use_art1").innerHTML=total_marks;
    document.getElementById("use_arttotal1").value=total_marks;
i++;
}
}



function tb_use(){
    var total_marks=0; var i=27;var tb="";var use="";
   while(i<=29){
     tb=document.getElementById("use5"+i).value;
    use=document.getElementById("use_5"+i).value;
    total_marks=document.getElementById("use_tbtotal").value;
    if(tb=="YES" && use==0){total_marks++; document.getElementById("use_5"+i).value="1"}
    if(tb=="NO"  && use==1){total_marks--; document.getElementById("use_5"+i).value="0"}
    document.getElementById("use_tb").innerHTML=total_marks;
    document.getElementById("use_tbtotal").value=total_marks;
i++;
}
}
