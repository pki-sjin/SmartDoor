package org.zero.db.entity.ticket;



/**
 * SdTicketType entity. @author MyEclipse Persistence Tools
 */
public class SdTicketType extends AbstractSdTicketType implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public SdTicketType() {
    }

	/** minimal constructor */
    public SdTicketType(String code, String description, Integer sysrecord, Integer valid) {
        super(code, description, sysrecord, valid);        
    }
    
    /** full constructor */
    public SdTicketType(String code, String description, Integer sysrecord, Integer valid, String remark) {
        super(code, description, sysrecord, valid, remark);        
    }
   
}
