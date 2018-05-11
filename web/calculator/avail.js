/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function htc_av(){
    var av_total_marks=0;
    var i=1;    var htc="";var av="";
    
    while(i<=2){
     htc=document.getElementById("available1"+i).value;
    av=document.getElementById("av_1"+i).value;
    av_total_marks=document.getElementById("av_htctotal").value;
    if(htc=="YES" && av==0){av_total_marks++; document.getElementById("av_1"+i).value="1"}
    if(htc=="NO"  && av==1){av_total_marks--; document.getElementById("av_1"+i).value="0"}
    document.getElementById("av_htc").innerHTML=av_total_marks;
    document.getElementById("av_htctotal").value=av_total_marks;
i++;
}
}


function pmtct_av(){
    var av_total_marks=0;
    var i=3;    var pmtct="";var av="";
    while(i<=11){
     pmtct=document.getElementById("available2"+i).value;
    av=document.getElementById("av_2"+i).value;
    av_total_marks=document.getElementById("av_pmtcttotal").value;
    if(pmtct=="YES" && av==0){av_total_marks++; document.getElementById("av_2"+i).value="1"}
    if(pmtct=="NO"  && av==1){av_total_marks--; document.getElementById("av_2"+i).value="0"}
    document.getElementById("av_pmtct").innerHTML=av_total_marks;
    document.getElementById("av_pmtcttotal").value=av_total_marks;
i++;
}
}


function art_av(){
    var av_total_marks=0;
    var i=12;    var art="";var av="";
    while(i<=19){
    art=document.getElementById("available3"+i).value;
    av=document.getElementById("av_3"+i).value;
    av_total_marks=document.getElementById("av_arttotal").value;
    if(art=="YES" && av==0){av_total_marks++; document.getElementById("av_3"+i).value="1"}
    if(art=="NO"  && av==1){av_total_marks--; document.getElementById("av_3"+i).value="0"}
    document.getElementById("av_art").innerHTML=av_total_marks;
    document.getElementById("av_arttotal").value=av_total_marks;
    document.getElementById("av_art1").innerHTML=av_total_marks;
    document.getElementById("av_arttotal1").value=av_total_marks;
i++;
}
}
function art_av1(){
     var av_total_marks=0;
    var i=20;    var art="";var av=""; 
      while(i<=26){
    art=document.getElementById("available4"+i).value;
    av=document.getElementById("av_4"+i).value;
    av_total_marks=document.getElementById("av_arttotal").value;
    if(art=="YES" && av==0){av_total_marks++; document.getElementById("av_4"+i).value="1"}
    if(art=="NO"  && av==1){av_total_marks--; document.getElementById("av_4"+i).value="0"}
    document.getElementById("av_art").innerHTML=av_total_marks;
    document.getElementById("av_arttotal").value=av_total_marks;
    document.getElementById("av_art1").innerHTML=av_total_marks;
    document.getElementById("av_arttotal1").value=av_total_marks;
i++;
}
}
function tb_av(){
    var av_total_marks=0;
    var i=27;    var tb="";var av="";
    
    while(i<=29){
     tb=document.getElementById("available5"+i).value;
    av=document.getElementById("av_5"+i).value;
    av_total_marks=document.getElementById("av_tbtotal").value;
    if(tb=="YES" && av==0){av_total_marks++; document.getElementById("av_5"+i).value="1"}
    if(tb=="NO"  && av==1){av_total_marks--; document.getElementById("av_5"+i).value="0"}
    document.getElementById("av_tb").innerHTML=av_total_marks;
    document.getElementById("av_tbtotal").value=av_total_marks;
i++;
}
}
