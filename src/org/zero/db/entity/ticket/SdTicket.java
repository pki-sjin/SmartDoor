package org.zero.db.entity.ticket;

import java.sql.Timestamp;

/**
 * SdTicket entity. @author MyEclipse Persistence Tools
 */
public class SdTicket extends AbstractSdTicket implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdTicket() {
	}

	/** minimal constructor */
	public SdTicket(String code, Integer exId, String name, String description,
			Double price, Integer typeId, Timestamp createTime,
			Integer sysrecord, Integer valid) {
		super(code, exId, name, description, price, typeId, createTime,
				sysrecord, valid);
	}

	/** full constructor */
	public SdTicket(String code, Integer exId, String name, String description,
			Double price, Integer typeId, Timestamp createTime,
			Integer sysrecord, Integer valid, String remark) {
		super(code, exId, name, description, price, typeId, createTime,
				sysrecord, valid, remark);
	}

}
