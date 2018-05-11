/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import checklist.IdGenerator;
import database.dbConn;
import database.dbConn2;
import java.sql.SQLException;

/**
 *
 * @author Geofrey Nyabuto
 */
public class Merger {
   dbConn2 conn2 = new dbConn2();
   dbConn conn = new dbConn();
   String DBTables=",";
   String found="";
  String[] CurrentTables={"aphia_staff","art_tools","caretreatment_site","health_facility","htc_site","htc_tools","marked_checklist","me_toolsvalue","moh_staff","pmtct_site","pmtct_tools","tb_site","tb_tools","users"};
  
  public int CheckTables()throws SQLException{
 int found_tables=0;
 
 String allTables="SHOW TABLES FROM checklist_temp";
 conn2.rst=conn2.stt.executeQuery(allTables);
 while(conn2.rst.next()){
   DBTables+=conn2.rst.getString(1); 
 }
for(int i=0;i<CurrentTables.length;i++){
   if(DBTables.contains(CurrentTables[i])) {
   found_tables++;    
   }}
System.out.println("FOUND TABLES  :  "+found_tables);
    return found_tables;
}
  
public int aphia_staff() throws SQLException {
    int merged=0;
  String id,fname,mname,lname,phone,county_id,timestamp;
  
 String get_fromTemp="SELECT * FROM aphia_staff";
 conn2.rst=conn2.stt.executeQuery(get_fromTemp);
 while (conn2.rst.next()){
     found="";
       IdGenerator IG = new IdGenerator();
  id=conn2.rst.getString("id");
  fname=conn2.rst.getString("fname");
  mname=conn2.rst.getString("mname");
  lname=conn2.rst.getString("lname");
  phone=conn2.rst.getString("phone");
  county_id=conn2.rst.getString("county_id");
timestamp=IG.date_key();
//  CHECK FOR EXISTENCE
  String checker="SELECT id FROM aphia_staff WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
      found=conn.rs.getString(1);
  }   
  if(!found.equals("")){
//      IF FOUND UPDATE
      String update="UPDATE aphia_staff SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"',phone='"+phone+"',county_id='"+county_id+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
       conn.st.executeUpdate(update);
  }   
  if(found.equals("")){
//      IF MISSING INSERT
       String inserter="INSERT INTO aphia_staff (id,fname,mname,lname,phone,county_id,timestamp) VALUES('"+id+"','"+fname+"','"+mname+"','"+lname+"','"+phone+"','"+county_id+"','"+timestamp+"')";
    merged+=conn.st.executeUpdate(inserter);
   }  
 }
    
    return merged;
} 

public int art_tools() throws SQLException{
   int merged=0; 
 String id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey;

 
 String get_values="SELECT * FROM art_tools";
 conn2.rst=conn2.stt.executeQuery(get_values);
  while(conn2.rst.next())  {
      found="";
       IdGenerator IG = new IdGenerator();
    id=conn2.rst.getString("id");  
    hf_id=conn2.rst.getString("hf_id"); 
    specification_id=conn2.rst.getString("specification_id"); 
    available=conn2.rst.getString("available"); 
    in_use=conn2.rst.getString("in_use"); 
    filled_completely=conn2.rst.getString("filled_completely"); 
    correctly_used=conn2.rst.getString("correctly_used"); 
    year=conn2.rst.getString("year"); 
    month=conn2.rst.getString("month"); 
    date=conn2.rst.getString("date");
    datekey=conn2.rst.getString("datekey");
    timestamp=IG.date_key();
    
//    CHECK EXISTENCE
 String checker="SELECT id FROM art_tools WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
   found=conn.rs.getString(1);
  }
  
  if(!found.equals("")){
//   update if the values already e
      String update="UPDATE art_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',"
              + "correctly_used='"+correctly_used+"',month='"+month+"',year='"+year+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
      conn.st.executeUpdate(update);
  }
  if(found.equals("")){
//    Add the data as new data to the system.
      
      String inserter="INSERT INTO art_tools (id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
  }
  
  
  
  }
    return merged;
}


public int caretreatment_site() throws SQLException{
     int merged=0;
   String id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey;
   String get_values="SELECT * FROM caretreatment_site";
   conn2.rst=conn2.stt.executeQuery(get_values);
   while(conn2.rst.next()){
       found="";
           IdGenerator IG = new IdGenerator();
     id=conn2.rst.getString("id");  
     hf_id=conn2.rst.getString("hf_id");  
     indicator_id=conn2.rst.getString("indicator_id");  
     recounted_data=conn2.rst.getString("recounted_data");  
     reported_711A=conn2.rst.getString("reported_711A");  
     reported_731=conn2.rst.getString("reported_731");  
     reported_DHIS=conn2.rst.getString("reported_DHIS");  
     year=conn2.rst.getString("year");  
     month=conn2.rst.getString("month");  
     date=conn2.rst.getString("date");  
     datekey=conn2.rst.getString("datekey");  
     timestamp=IG.date_key();  
    
     
//     CHECK EXISTENCE
    String checker="SELECT id FROM caretreatment_site WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
     found=conn.rs.getString(1);
  }
  
  if(!found.equals("")){
//  UPDATE THE EXISTING DATA................................
      String updator="UPDATE caretreatment_site SET id='"+id+"',hf_id='"+hf_id+"',indicator_id='"+indicator_id+"',recounted_data='"+recounted_data+"',"
              + "reported_711A='"+reported_711A+"',reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"',year='"+year+"',month='"+month+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
     conn.st.executeUpdate(updator); 
  }
 if(found.equals("")){
//   INSERT AS NEW RECORDS..............
      String inserter="INSERT INTO caretreatment_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
      
  }
   }
   return merged;
 }

public int health_facility() throws SQLException{
   int merged=0; 
  String hf_id,hf_name,mflcode,facility_level,district_id,timestamp,userid,comp_name;
  
  String get_values="SELECT * FROM health_facility";
  conn2.rst=conn2.stt.executeQuery(get_values);
  while(conn2.rst.next()){
  found="";
    IdGenerator IG = new IdGenerator();
  hf_id=conn2.rst.getString("hf_id");
  hf_name=conn2.rst.getString("hf_name");
  mflcode=conn2.rst.getString("mflcode");
  facility_level=conn2.rst.getString("facility_level");
  district_id=conn2.rst.getString("district_id");
  userid=conn2.rst.getString("userid");
  comp_name=conn2.rst.getString("comp_name");
  
  timestamp=IG.date_key();
  
//  CHECK AVAILABILITY..................
  String checker="SELECT hf_id FROM health_facility WHERE hf_id='"+hf_id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
    found=conn.rs.getString(1);
  }
 if(!found.equals("")){
//    UPDATE THE VALUES//////////////////////////
      String updator="UPDATE health_facility SET hf_name='"+hf_name+"',timestamp='"+timestamp+"',mflcode='"+mflcode+"',"
              + "facility_level='"+facility_level+"' WHERE hf_id='"+hf_id+"'";
      conn.st.executeUpdate(updator);
 }
  if(found.equals("")){
//   INSERT VALUES TO THE DATABASE AS NEW ENTRIES..............................
      String inserter="INSERT INTO health_facility (hf_id,hf_name,mflcode,facility_level,district_id,timestamp,userid,comp_name) "
              + " VALUES('"+hf_id+"','"+hf_name+"','"+mflcode+"','"+facility_level+"','"+district_id+"','"+timestamp+"','"+userid+"','"+comp_name+"')";
     merged+=conn.st.executeUpdate(inserter);
      
  }
  }
   return merged;
}


public int htc_site() throws SQLException{
     int merged=0;
   String id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey;
   String get_values="SELECT * FROM htc_site";
   conn2.rst=conn2.stt.executeQuery(get_values);
   while(conn2.rst.next()){
       found="";
       IdGenerator IG = new IdGenerator();
     id=conn2.rst.getString("id");  
     hf_id=conn2.rst.getString("hf_id");  
     indicator_id=conn2.rst.getString("indicator_id");  
     recounted_data=conn2.rst.getString("recounted_data");  
     reported_711A=conn2.rst.getString("reported_711A");  
     reported_731=conn2.rst.getString("reported_731");  
     reported_DHIS=conn2.rst.getString("reported_DHIS");  
     year=conn2.rst.getString("year");  
     month=conn2.rst.getString("month");  
     date=conn2.rst.getString("date");  
     datekey=conn2.rst.getString("datekey");  
     timestamp=IG.date_key();  
    
     
//     CHECK EXISTENCE
    String checker="SELECT id FROM htc_site WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
    found=conn.rs.getString(1);
  }
  
 if(!found.equals("")){
//  UPDATE THE EXISTING DATA................................
      String updator="UPDATE htc_site SET id='"+id+"',hf_id='"+hf_id+"',indicator_id='"+indicator_id+"',recounted_data='"+recounted_data+"',"
              + "reported_711A='"+reported_711A+"',reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"',year='"+year+"',month='"+month+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
     conn.st.executeUpdate(updator); 
  }
  if(found.equals("")){
//   INSERT AS NEW RECORDS..............
      String inserter="INSERT INTO htc_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
      
  }
   }
   return merged;
 }


public int htc_tools() throws SQLException{
   int merged=0; 
 String id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey;

 
 String get_values="SELECT * FROM htc_tools";
 conn2.rst=conn2.stt.executeQuery(get_values);
  while(conn2.rst.next())  {
      found="";
       IdGenerator IG = new IdGenerator();
    id=conn2.rst.getString("id");  
    hf_id=conn2.rst.getString("hf_id"); 
    specification_id=conn2.rst.getString("specification_id"); 
    available=conn2.rst.getString("available"); 
    in_use=conn2.rst.getString("in_use"); 
    filled_completely=conn2.rst.getString("filled_completely"); 
    correctly_used=conn2.rst.getString("correctly_used"); 
    year=conn2.rst.getString("year"); 
    month=conn2.rst.getString("month"); 
    date=conn2.rst.getString("date");
    datekey=conn2.rst.getString("datekey");
    timestamp=IG.date_key();
    
//    CHECK EXISTENCE
 String checker="SELECT id FROM htc_tools WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
     found=conn.rs.getString(1);
  }
  
 if(!found.equals("")){
//   update if the values already e
      String update="UPDATE htc_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',"
              + "correctly_used='"+correctly_used+"',month='"+month+"',year='"+year+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
      conn.st.executeUpdate(update);
  }
if(found.equals("")){
//    Add the data as new data to the system.
      
      String inserter="INSERT INTO htc_tools (id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
  }
  
  
  
  }
    return merged;
}

public int marked_checklist() throws SQLException{
 int merged=0;   
  
 String id,hf_id,aphia_staff,moh_staff,year,month,date,timestamp,datekey;
 
 String get_values="SELECT * FROM marked_checklist";
 conn2.rst=conn2.stt.executeQuery(get_values);
 while(conn2.rst.next()){
  found="";  
   IdGenerator IG = new IdGenerator();
   
 id=conn2.rst.getString("id");  
 hf_id=conn2.rst.getString("hf_id"); 
 aphia_staff=conn2.rst.getString("aphia_staff"); 
 moh_staff=conn2.rst.getString("moh_staff"); 
 year=conn2.rst.getString("year"); 
 month=conn2.rst.getString("month"); 
 date=conn2.rst.getString("date"); 
 datekey=conn2.rst.getString("datekey"); 
 timestamp=IG.date_key(); 
 
 String checker="SELECT id FROM marked_checklist WHERE id='"+id+"' LIMIT 1";
 conn.rs=conn.st.executeQuery(checker);
 while(conn.rs.next()){
    found=conn.rs.getString(1);
 }
 if(!found.equals("")){
//  UPDATE RECORDS............................
//     String updator="UPDATE marked_checklist SET "
  }
if(found.equals("")){
//  INSERT AS NEW RECORDS.....................
     String inserter="INSERT INTO marked_checklist (id,hf_id,aphia_staff,moh_staff,year,month,date,timestamp,datekey) "
             + " VALUES('"+id+"','"+hf_id+"','"+aphia_staff+"','"+moh_staff+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
 merged+=conn.st.executeUpdate(inserter);
 }
 
 }
    
    return merged;
}

public int me_toolsvalue() throws SQLException{
  int merged=0;  
  String id,tool_id,hf_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey;
  
  String get_values="SELECT * FROM me_toolsvalue";
  conn2.rst=conn2.stt.executeQuery(get_values);
  while(conn2.rst.next()){
     found="";  
   IdGenerator IG = new IdGenerator(); 
   
    id=conn2.rst.getString("id");
    tool_id=conn2.rst.getString("tool_id");
    hf_id=conn2.rst.getString("hf_id");
    available=conn2.rst.getString("available");
    in_use=conn2.rst.getString("in_use");
    filled_completely=conn2.rst.getString("filled_completely"); 
    correctly_used=conn2.rst.getString("correctly_used");
    year=conn2.rst.getString("year");
    month=conn2.rst.getString("month");
    date=conn2.rst.getString("date");
    datekey=conn2.rst.getString("datekey");
    timestamp=IG.date_key();
         
         
//    CHECK EXISTENCE...........................................................
    String checker="SELECT id FROM me_toolsvalue WHERE id='"+id+"' LIMIT 1";
 conn.rs=conn.st.executeQuery(checker);
 while(conn.rs.next()){
 found=conn.rs.getString(1);
 }
   if(!found.equals("")){
//     UPDATE EXISTING VALUES......................................
       String updator="UPDATE me_toolsvalue SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',correctly_used='"+correctly_used+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
       conn.st.executeUpdate(updator);
   }  
 if(found.equals("")){
//    INSERT AS NEW ENTRIES TO THE SYSTEM...................................
       String inserter="INSERT INTO me_toolsvalue (id,tool_id,hf_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
               + " VALUES('"+id+"','"+tool_id+"','"+hf_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);  
   }
   
   
  }
    
 return merged;
}

public int moh_staff() throws SQLException {
    
  int merged=0;
  String id,fname,mname,lname,phone,county_id,timestamp;
  
 String get_fromTemp="SELECT * FROM moh_staff";
 conn2.rst=conn2.stt.executeQuery(get_fromTemp);
 while (conn2.rst.next()){
     found="";
  IdGenerator IG = new IdGenerator();
       
  id=conn2.rst.getString("id");
  fname=conn2.rst.getString("fname");
  mname=conn2.rst.getString("mname");
  lname=conn2.rst.getString("lname");
  phone=conn2.rst.getString("phone");
  county_id=conn2.rst.getString("county_id");
timestamp=IG.date_key();
//  CHECK FOR EXISTENCE
  String checker="SELECT id FROM moh_staff WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
found=conn.rs.getString(1);
  }   
  if(!found.equals("")){
//      IF FOUND UPDATE
      String update="UPDATE moh_staff SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"',phone='"+phone+"',county_id='"+county_id+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
       conn.st.executeUpdate(update);
  }   
  if(found.equals("")){
//      IF MISSING INSERT
       String inserter="INSERT INTO moh_staff (id,fname,mname,lname,phone,county_id,timestamp) VALUES('"+id+"','"+fname+"','"+mname+"','"+lname+"','"+phone+"','"+county_id+"','"+timestamp+"')";
    merged+=conn.st.executeUpdate(inserter);
   }  
 }
    
    return merged;
} 



public int pmtct_site() throws SQLException{
     int merged=0;
   String id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey;
   String get_values="SELECT * FROM pmtct_site";
   conn2.rst=conn2.stt.executeQuery(get_values);
   while(conn2.rst.next()){
       found="";
       IdGenerator IG = new IdGenerator();
     id=conn2.rst.getString("id");  
     hf_id=conn2.rst.getString("hf_id");  
     indicator_id=conn2.rst.getString("indicator_id");  
     recounted_data=conn2.rst.getString("recounted_data");  
     reported_711A=conn2.rst.getString("reported_711A");  
     reported_731=conn2.rst.getString("reported_731");  
     reported_DHIS=conn2.rst.getString("reported_DHIS");  
     year=conn2.rst.getString("year");  
     month=conn2.rst.getString("month");  
     date=conn2.rst.getString("date");  
     datekey=conn2.rst.getString("datekey");  
     timestamp=IG.date_key();  
    
     
//     CHECK EXISTENCE
    String checker="SELECT id FROM pmtct_site WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
found=conn.rs.getString(1);
  }
  
  if(!found.equals("")){
//  UPDATE THE EXISTING DATA................................
      String updator="UPDATE pmtct_site SET id='"+id+"',hf_id='"+hf_id+"',indicator_id='"+indicator_id+"',recounted_data='"+recounted_data+"',"
              + "reported_711A='"+reported_711A+"',reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"',year='"+year+"',month='"+month+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
     conn.st.executeUpdate(updator); 
  }
 if(found.equals("")){
//   INSERT AS NEW RECORDS..............
      String inserter="INSERT INTO pmtct_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
      
  }
   }
   return merged;
 }


public int pmtct_tools() throws SQLException{
   int merged=0; 
 String id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey;
 
 String get_values="SELECT * FROM pmtct_tools";
 conn2.rst=conn2.stt.executeQuery(get_values);
  while(conn2.rst.next())  {
      found="";
      IdGenerator IG = new IdGenerator();
       
    id=conn2.rst.getString("id");  
    hf_id=conn2.rst.getString("hf_id"); 
    specification_id=conn2.rst.getString("specification_id"); 
    available=conn2.rst.getString("available"); 
    in_use=conn2.rst.getString("in_use"); 
    filled_completely=conn2.rst.getString("filled_completely"); 
    correctly_used=conn2.rst.getString("correctly_used"); 
    year=conn2.rst.getString("year"); 
    month=conn2.rst.getString("month"); 
    date=conn2.rst.getString("date");
    datekey=conn2.rst.getString("datekey");
    timestamp=IG.date_key();
    
//    CHECK EXISTENCE
 String checker="SELECT id FROM pmtct_tools WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
     found=conn.rs.getString(1);
  }
  
 if(!found.equals("")){
//   update if the values already e
      String update="UPDATE pmtct_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',"
              + "correctly_used='"+correctly_used+"',month='"+month+"',year='"+year+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
      conn.st.executeUpdate(update);
  }
 if(found.equals("")){
//    Add the data as new data to the system.
      
      String inserter="INSERT INTO pmtct_tools (id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
  }
  
  
  
  }
    return merged;
}



public int tb_site() throws SQLException{
     int merged=0;
   String id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey;

   String get_values="SELECT * FROM tb_site";
   conn2.rst=conn2.stt.executeQuery(get_values);
   while(conn2.rst.next()){
       found="";
       IdGenerator IG = new IdGenerator();
     id=conn2.rst.getString("id");  
     hf_id=conn2.rst.getString("hf_id");  
     indicator_id=conn2.rst.getString("indicator_id");  
     recounted_data=conn2.rst.getString("recounted_data");  
     reported_711A=conn2.rst.getString("reported_711A");  
     reported_731=conn2.rst.getString("reported_731");  
     reported_DHIS=conn2.rst.getString("reported_DHIS");  
     year=conn2.rst.getString("year");  
     month=conn2.rst.getString("month");  
     date=conn2.rst.getString("date");  
     datekey=conn2.rst.getString("datekey"); 
     timestamp=IG.date_key();  
    
     
//     CHECK EXISTENCE
    String checker="SELECT id FROM tb_site WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
     found=conn.rs.getString(1);
  }
  
if(!found.equals("")){
//  UPDATE THE EXISTING DATA................................
      String updator="UPDATE tb_site SET id='"+id+"',hf_id='"+hf_id+"',indicator_id='"+indicator_id+"',recounted_data='"+recounted_data+"',"
              + "reported_711A='"+reported_711A+"',reported_731='"+reported_731+"',reported_DHIS='"+reported_DHIS+"',year='"+year+"',month='"+month+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
     conn.st.executeUpdate(updator); 
  }
 if(found.equals("")){
//   INSERT AS NEW RECORDS..............
      String inserter="INSERT INTO tb_site (id,hf_id,indicator_id,recounted_data,reported_711A,reported_731,reported_DHIS,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+indicator_id+"','"+recounted_data+"','"+reported_711A+"','"+reported_731+"','"+reported_DHIS+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
      
  }
   }
   return merged;
 }


public int tb_tools() throws SQLException{
   int merged=0; 
 String id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey;

 
 String get_values="SELECT * FROM tb_tools";
 conn2.rst=conn2.stt.executeQuery(get_values);
  while(conn2.rst.next())  {
      found="";
       IdGenerator IG = new IdGenerator();
    id=conn2.rst.getString("id");  
    hf_id=conn2.rst.getString("hf_id"); 
    specification_id=conn2.rst.getString("specification_id"); 
    available=conn2.rst.getString("available"); 
    in_use=conn2.rst.getString("in_use"); 
    filled_completely=conn2.rst.getString("filled_completely"); 
    correctly_used=conn2.rst.getString("correctly_used"); 
    year=conn2.rst.getString("year"); 
    month=conn2.rst.getString("month"); 
    date=conn2.rst.getString("date");
    datekey=conn2.rst.getString("datekey");
    timestamp=IG.date_key();
    
//    CHECK EXISTENCE
 String checker="SELECT id FROM tb_tools WHERE id='"+id+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
     found=conn.rs.getString(1);
  }
  
if(!found.equals("")){
//   update if the values already e
      String update="UPDATE tb_tools SET available='"+available+"',in_use='"+in_use+"',filled_completely='"+filled_completely+"',"
              + "correctly_used='"+correctly_used+"',month='"+month+"',year='"+year+"',date='"+date+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
      conn.st.executeUpdate(update);
  }
if(found.equals("")){
//    Add the data as new data to the system.
      String inserter="INSERT INTO tb_tools (id,hf_id,specification_id,available,in_use,filled_completely,correctly_used,year,month,date,timestamp,datekey) "
              + " VALUES('"+id+"','"+hf_id+"','"+specification_id+"','"+available+"','"+in_use+"','"+filled_completely+"','"+correctly_used+"','"+year+"','"+month+"','"+date+"','"+timestamp+"','"+datekey+"')";
     merged+=conn.st.executeUpdate(inserter);
  }
  
  
  
  }
    return merged;
}
 public int users() throws SQLException{
 int merged=0;
     String userid,fname,mname,lname,phone,username,password,level,timestamp,computer_name;
     String get_value="SELECT * FROM users";
     conn2.rst=conn2.stt.executeQuery(get_value);
     while(conn2.rst.next()){
      found="";
        IdGenerator IG = new IdGenerator();
        userid=conn2.rst.getString("userid");
        fname=conn2.rst.getString("fname");
        mname=conn2.rst.getString("mname");
        lname=conn2.rst.getString("lname");
        phone=conn2.rst.getString("phone");
        username=conn2.rst.getString("username");
        password=conn2.rst.getString("password");
        level=conn2.rst.getString("level");
        computer_name=conn2.rst.getString("computer_name");
        timestamp=IG.date_key();
      
//        CHECK EXISTENCE..............................................
   String checker="SELECT userid FROM users WHERE userid='"+userid+"' LIMIT 1";
  conn.rs=conn.st.executeQuery(checker);
  while(conn.rs.next()){
     found=conn.rs.getString(1);
  }
   if(!found.equals("")){
//     UPDATE ALREADY EXISTING DATA.....................
       String updator="UPDATE users SET fname='"+fname.replace("'","")+"',mname='"+mname.replace("'","")+"',lname='"+lname.replace("'","")+"',phone='"+phone+"',username='"+username.replace("'","")+"',password='"+password+"', timestamp='"+timestamp+"' WHERE userid='"+userid+"'";
       conn.st.executeUpdate(updator);
   }
  if(found.equals("")){
//   INSERT AS NEW ENTRIES.........................
      String inserter="INSERT INTO users (userid,fname,mname,lname,phone,username,password,level,timestamp,computer_name) "
              + " VALUES ('"+userid+"','"+fname+"','"+mname+"','"+lname+"','"+phone+"','"+username+"','"+password+"','"+level+"','"+timestamp+"','"+computer_name+"')";
      merged+=conn.st.executeUpdate(inserter);
  }
  }
     
     return merged;
 }

}
