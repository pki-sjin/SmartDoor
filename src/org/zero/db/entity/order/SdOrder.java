package org.zero.db.entity.order;

import java.sql.Timestamp;

/**
 * SdOrder entity. @author MyEclipse Persistence Tools
 */
public class SdOrder extends AbstractSdOrder implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdOrder() {
	}

	/** minimal constructor */
	public SdOrder(Long id, Double price, Integer ticket, Integer state,
			String method, Timestamp createTime, Integer sysrecord,
			Integer valid) {
		super(id, price, ticket, state, method, createTime, sysrecord, valid);
	}

	/** full constructor */
	public SdOrder(Long id, Double price, Integer ticket, Integer state,
			String method, Timestamp createTime, Timestamp finish,
			Integer sysrecord, Integer valid, String remark) {
		super(id, price, ticket, state, method, createTime, finish, sysrecord,
				valid, remark);
	}

}
