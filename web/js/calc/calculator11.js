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
function htc_filled(){
    var total_marks=0; var i=1;var htc="";var filled="";
   while(i<=2){
     htc=document.getElementById("filled1"+i).value;
    filled=document.getElementById("filled_1"+i).value;
    total_marks=document.getElementById("fill_htctotal").value;
    if(htc=="YES" && filled==0){total_marks++; document.getElementById("filled_1"+i).value="1"}
    if(htc=="NO"  && filled==1){total_marks--; document.getElementById("filled_1"+i).value="0"}
    document.getElementById("filled_htc").innerHTML=total_marks;
    document.getElementById("fill_htctotal").value=total_marks;
i++;
}
}

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