package org.zero.db.entity.sms;

import java.sql.Timestamp;

/**
 * AbstractSdValidation entity provides the base persistence definition of the
 * SdValidation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdValidation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cell;
	private String code;
	private String tag;
	private String event;
	private Timestamp createTime;
	private Integer sysrecord;
	private Integer valid;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSdValidation() {
	}

	/** minimal constructor */
	public AbstractSdValidation(String cell, String code, String tag,
			String event, Timestamp createTime, Integer sysrecord, Integer valid) {
		this.cell = cell;
		this.code = code;
		this.tag = tag;
		this.event = event;
		this.createTime = createTime;
		this.sysrecord = sysrecord;
		this.valid = valid;
	}

	/** full constructor */
	public AbstractSdValidation(String cell, String code, String tag,
			String event, Timestamp createTime, Integer sysrecord,
			Integer valid, String remark) {
		this.cell = cell;
		this.code = code;
		this.tag = tag;
		this.event = event;
		this.createTime = createTime;
		this.sysrecord = sysrecord;
		this.valid = valid;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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