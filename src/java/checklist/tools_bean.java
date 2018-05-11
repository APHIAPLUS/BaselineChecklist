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
public class tools_bean implements Serializable{
 public String tool_name,tool_id,tool_sp,section;

    public String getTool_name() {
        return tool_name;
    }

    public void setTool_name(String too_name) {
        this.tool_name = too_name;
    }

    public String getTool_id() {
        return tool_id;
    }

    public void setTool_id(String tool_id) {
        this.tool_id = tool_id;
    }

    public String getTool_sp() {
        return tool_sp;
    }

    public void setTool_sp(String tool_sp) {
        this.tool_sp = tool_sp;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    
}
