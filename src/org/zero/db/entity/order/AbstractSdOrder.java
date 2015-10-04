package org.zero.db.entity.order;

import java.sql.Timestamp;

/**
 * AbstractSdOrder entity provides the base persistence definition of the
 * SdOrder entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdOrder implements java.io.Serializable {

	// Fields

	private Long id;
	private Double price;
	private Integer ticket;
	private Integer state;
	private String method;
	private Timestamp createTime;
	private Timestamp finish;
	private Integer sysrecord;
	private Integer valid;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSdOrder() {
	}

	/** minimal constructor */
	public AbstractSdOrder(Long id, Double price, Integer ticket,
			Integer state, String method, Timestamp createTime,
			Integer sysrecord, Integer valid) {
		this.id = id;
		this.price = price;
		this.ticket = ticket;
		this.state = state;
		this.method = method;
		this.createTime = createTime;
		this.sysrecord = sysrecord;
		this.valid = valid;
	}

	/** full constructor */
	public AbstractSdOrder(Long id, Double price, Integer ticket,
			Integer state, String method, Timestamp createTime,
			Timestamp finish, Integer sysrecord, Integer valid, String remark) {
		this.id = id;
		this.price = price;
		this.ticket = ticket;
		this.state = state;
		this.method = method;
		this.createTime = createTime;
		this.finish = finish;
		this.sysrecord = sysrecord;
		this.valid = valid;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getTicket() {
		return this.ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getFinish() {
		return this.finish;
	}

	public void setFinish(Timestamp finish) {
		this.finish = finish;
	}

	public Integer getSysrecord() {
		return this.sysrecord;
	}

	public void setSysrecord(Integer sysrecord) {
		this.sysrecord = sysrecord;
	}

	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}