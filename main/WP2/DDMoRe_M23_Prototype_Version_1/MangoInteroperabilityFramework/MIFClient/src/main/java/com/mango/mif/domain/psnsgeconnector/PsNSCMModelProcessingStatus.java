/* File			:  PsNSCMModelProcessingStatus.java
 * Project		:  MIFServer
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.psnsgeconnector;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * scmlog file model row content
 */
@XmlRootElement(name = "PsNSCMModelProcessingStatus")
public class PsNSCMModelProcessingStatus {
    /*
     * columns found in the scmlog file
     */
    private String model;
    private String test;
    private String baseOFV;
    private String newOFV;
    private String testOFV;
    private String goal;
    private String dDF;
    private String significant;
    private String pVAL;
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
    }
    
    public String getBaseOFV() {
        return baseOFV;
    }
    
    public void setBaseOFV(String baseOFV) {
        this.baseOFV = baseOFV;
    }
    
    public String getNewOFV() {
        return newOFV;
    }
    
    public void setNewOFV(String newOFV) {
        this.newOFV = newOFV;
    }
    
    public String getTestOFV() {
        return testOFV;
    }
    
    public void setTestOFV(String testOFV) {
        this.testOFV = testOFV;
    }
    
    public String getGoal() {
        return goal;
    }
    
    public void setGoal(String goal) {
        this.goal = goal;
    }
    
    public String getdDF() {
        return dDF;
    }
    
    public void setdDF(String dDF) {
        this.dDF = dDF;
    }
    
    public String getSignificant() {
        return significant;
    }
    
    public void setSignificant(String significant) {
        this.significant = significant;
    }
    
    public String getpVAL() {
        return pVAL;
    }
    
    public void setpVAL(String pVAL) {
        this.pVAL = pVAL;
    }
    
}
