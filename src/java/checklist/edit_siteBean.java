/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import java.io.Serializable;

/**
 *
 * @author Geofrey Nyabuto
 */
public class edit_siteBean implements Serializable{
   public String id,indicator_id,indicator_name,section,recounted_data,re_711A,re_731,re_DHIS; 

    public String getIndicator_name() {
        return indicator_name;
    }

    public void setIndicator_name(String indicator_name) {
        this.indicator_name = indicator_name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndicator_id() {
        return indicator_id;
    }

    public void setIndicator_id(String indicator_id) {
        this.indicator_id = indicator_id;
    }

    public String getRecounted_data() {
        return recounted_data;
    }

    public void setRecounted_data(String recounted_data) {
        this.recounted_data = recounted_data;
    }

    public String getRe_711A() {
        return re_711A;
    }

    public void setRe_711A(String re_711A) {
        this.re_711A = re_711A;
    }

    public String getRe_731() {
        return re_731;
    }

    public void setRe_731(String re_731) {
        this.re_731 = re_731;
    }

    public String getRe_DHIS() {
        return re_DHIS;
    }

    public void setRe_DHIS(String re_DHIS) {
        this.re_DHIS = re_DHIS;
    }
   
}
