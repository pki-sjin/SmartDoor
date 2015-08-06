package org.zero.db.entity.exhibition;

import java.sql.Timestamp;


/**
 * SdExhibition entity. @author MyEclipse Persistence Tools
 */
public class SdExhibition extends AbstractSdExhibition implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public SdExhibition() {
    }

    
    /** full constructor */
    public SdExhibition(String sdExSubject, String sdExHtml, Timestamp sdExStart, Timestamp sdExEnd) {
        super(sdExSubject, sdExHtml, sdExStart, sdExEnd);        
    }
   
}
