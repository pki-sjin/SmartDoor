package org.zero.db.entity.exhibition;

import java.sql.Timestamp;

/**
 * AbstractSdExhibition entity provides the base persistence definition of the
 * SdExhibition entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdExhibition implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String subject;
	private String html;
	private Timestamp start;
	private Timestamp end;
	private Integer sysrecord;
	private Integer valid;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSdExhibition() {
	}

	/** minimal constructor */
	public AbstractSdExhibition(String code, String subject, String html,
			Timestamp start, Timestamp end, Integer sysrecord, Integer valid) {
		this.code = code;
		this.subject = subject;
		this.html = html;
		this.start = start;
		this.end = end;
		this.sysrecord = sysrecord;
		this.valid = valid;
	}

	/** full constructor */
	public AbstractSdExhibition(String code, String subject, String html,
			Timestamp start, Timestamp end, Integer sysrecord, Integer valid,
			String remark) {
		this.code = code;
		this.subject = subject;
		this.html = html;
		this.start = start;
		this.end = end;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Timestamp getStart() {
		return this.start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return this.end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
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