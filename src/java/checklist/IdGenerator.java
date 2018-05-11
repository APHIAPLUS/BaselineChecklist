/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Geofrey Nyabuto
 */
public class IdGenerator {
//    CURRENT DATE
 Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
public int sec=cal.get(Calendar.SECOND);
public int micro=cal.get(Calendar.MILLISECOND);
String yr,mnth,dater,hr,mn,sc,action="";
String date2="";
String month2="";
String hour2="";
String min2="";
                       //RANDOM NUMBER GENERATOR
  Random random = new Random();
 long fraction = (long) ((925674 - 19 ) * random.nextDouble());
 

 
// CURRENT ID METHOD
    public String current_id(){
       
String full_date2=""+micro;
String db=Double.toString(fraction);
String tableID=(db+""+full_date2).replace(".", ""); 
      System.out.println("table id : "+tableID);
       return  tableID;
    }
       public String Created_On(){
       
String full_date2=year+"_"+month+"_"+date+"_"+hour+"_"+min+"_"+sec;
//String db=Double.toString(fraction);
//String tableID=(db+""+full_date2).replace(".", ""); 
      
       return  full_date2;
    }
    
//   TODAY METHOD 
    public String toDay(){
  if(date<10){ date2="0"+date;}
  if(date>=10){date2=""+date;}
  if(month<10){ month2="0"+month;}
  if(month>=10){month2=""+month;} 
   String full_date=year+"-"+month2+"-"+date2;     
   return full_date;
    }
 
    
//    return date key.
     public String date_key(){
  if(date<10){ date2="0"+date;}
  if(date>=10){date2=""+date;}
  if(month<10){ month2="0"+month;}
  if(month>=10){month2=""+month;} 
  if(hour<10){ hour2="0"+hour;}
  if(hour>=10){hour2=""+hour;}
  if(min<10){ min2="0"+min;}
  if(min>=10){min2=""+min;} 
     String date_key=year+""+month2+""+date2+""+hour2+""+min2;   
   return date_key;

    }
     
         public String CreatedOn(){
  if(date<10){ date2="0"+date;}
  if(date>=10){date2=""+date;}
  if(month<10){ month2="0"+month;}
  if(month>=10){month2=""+month;} 
  if(hour<10){ hour2="0"+hour;}
  if(hour>=10){hour2=""+hour;}
  if(min<10){ min2="0"+min;}
  if(min>=10){min2=""+min;} 
     String date_key=year+"_"+month2+"_"+date2+"_"+hour2+"_"+min2;   
   return date_key;

    }
         
         
         public int currentMonth(){
             return month;
         }
     public int currentYear(){
             return year;
         }
}
