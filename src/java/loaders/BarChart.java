/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loaders;

/**
 *
 * @author Geofrey Nyabuto
 */
public class BarChart {
 int recounted =0;
 int reported711A=0;
 int reported731=0;
 int reportedDHIS=0;
 String elementName="";   

    public int getRecounted() {
        return recounted;
    }

    public void setRecounted(int recounted) {
        this.recounted = recounted;
    }

    public int getReported711A() {
        return reported711A;
    }

    public void setReported711A(int reported711A) {
        this.reported711A = reported711A;
    }

    public int getReported731() {
        return reported731;
    }

    public void setReported731(int reported731) {
        this.reported731 = reported731;
    }

    public int getReportedDHIS() {
        return reportedDHIS;
    }

    public void setReportedDHIS(int reportedDHIS) {
        this.reportedDHIS = reportedDHIS;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
}
