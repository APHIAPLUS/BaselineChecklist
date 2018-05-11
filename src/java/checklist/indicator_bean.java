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
public class indicator_bean implements Serializable{
  public String  indicator_name,indicator_id,program_area,source_doc,recounted_data,reported_711A,reported_731,reported_DHIS,section;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
  int position;

    public String getIndicator_name() {
        return indicator_name;
    }

    public void setIndicator_name(String indicator_name) {
        this.indicator_name = indicator_name;
    }

    public String getIndicator_id() {
        return indicator_id;
    }

    public void setIndicator_id(String indicator_id) {
        this.indicator_id = indicator_id;
    }

    public String getProgram_area() {
        return program_area;
    }

    public void setProgram_area(String program_area) {
        this.program_area = program_area;
    }

    public String getSource_doc() {
        return source_doc;
    }

    public void setSource_doc(String source_doc) {
        this.source_doc = source_doc;
    }

    public String getRecounted_data() {
        return recounted_data;
    }

    public void setRecounted_data(String recounted_data) {
        this.recounted_data = recounted_data;
    }

    public String getReported_711A() {
        return reported_711A;
    }

    public void setReported_711A(String reported_711A) {
        this.reported_711A = reported_711A;
    }

    public String getReported_731() {
        return reported_731;
    }

    public void setReported_731(String reported_731) {
        this.reported_731 = reported_731;
    }

    public String getReported_DHIS() {
        return reported_DHIS;
    }

    public void setReported_DHIS(String reported_DHIS) {
        this.reported_DHIS = reported_DHIS;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
