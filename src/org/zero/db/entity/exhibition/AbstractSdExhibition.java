package org.zero.db.entity.exhibition;

import java.sql.Timestamp;


/**
 * AbstractSdExhibition entity provides the base persistence definition of the SdExhibition entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdExhibition  implements java.io.Serializable {


    // Fields    

     private Integer sdExId;
     private String sdExSubject;
     private String sdExHtml;
     private Timestamp sdExStart;
     private Timestamp sdExEnd;


    // Constructors

    /** default constructor */
    public AbstractSdExhibition() {
    }

    
    /** full constructor */
    public AbstractSdExhibition(String sdExSubject, String sdExHtml, Timestamp sdExStart, Timestamp sdExEnd) {
        this.sdExSubject = sdExSubject;
        this.sdExHtml = sdExHtml;
        this.sdExStart = sdExStart;
        this.sdExEnd = sdExEnd;
    }

   
    // Property accessors

    public Integer getSdExId() {
        return this.sdExId;
    }
    
    public void setSdExId(Integer sdExId) {
        this.sdExId = sdExId;
    }

    public String getSdExSubject() {
        return this.sdExSubject;
    }
    
    public void setSdExSubject(String sdExSubject) {
        this.sdExSubject = sdExSubject;
    }

    public String getSdExHtml() {
        return this.sdExHtml;
    }
    
    public void setSdExHtml(String sdExHtml) {
        this.sdExHtml = sdExHtml;
    }

    public Timestamp getSdExStart() {
        return this.sdExStart;
    }
    
    public void setSdExStart(Timestamp sdExStart) {
        this.sdExStart = sdExStart;
    }

    public Timestamp getSdExEnd() {
        return this.sdExEnd;
    }
    
    public void setSdExEnd(Timestamp sdExEnd) {
        this.sdExEnd = sdExEnd;
    }
   








}