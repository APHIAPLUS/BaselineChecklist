/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checklist;

import java.io.Serializable;

/**
 *
 * @author Geofrey Nyabuto
 */
public class getFacilities implements Serializable{
  String  county_name,district_name,hf_name,hf_id,level,mfl_code;

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }

    public String getHf_id() {
        return hf_id;
    }

    public void setHf_id(String hf_id) {
        this.hf_id = hf_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMfl_code() {
        return mfl_code;
    }

    public void setMfl_code(String mfl_code) {
        this.mfl_code = mfl_code;
    }

   
}
