package org.zero.db.entity.activity;



/**
 * SdExUserRelation entity. @author MyEclipse Persistence Tools
 */
public class SdExUserRelation extends AbstractSdExUserRelation implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public SdExUserRelation() {
    }

    
    /** full constructor */
    public SdExUserRelation(Integer exId, Integer userId, Integer joinId, Long orderId) {
        super(exId, userId, joinId, orderId);        
    }
   
}
