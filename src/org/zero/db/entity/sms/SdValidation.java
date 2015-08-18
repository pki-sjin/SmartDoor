package org.zero.db.entity.sms;

import java.sql.Timestamp;

/**
 * SdValidation entity. @author MyEclipse Persistence Tools
 */
public class SdValidation extends AbstractSdValidation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdValidation() {
	}

	/** minimal constructor */
	public SdValidation(String cell, String code, String tag, String event,
			Timestamp createTime, Integer sysrecord, Integer valid) {
		super(cell, code, tag, event, createTime, sysrecord, valid);
	}

	/** full constructor */
	public SdValidation(String cell, String code, String tag, String event,
			Timestamp createTime, Integer sysrecord, Integer valid,
			String remark) {
		super(cell, code, tag, event, createTime, sysrecord, valid, remark);
	}

}
