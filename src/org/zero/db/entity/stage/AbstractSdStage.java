package org.zero.db.entity.stage;

/**
 * AbstractSdStage entity provides the base persistence definition of the
 * SdStage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdStage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private Integer exId;
	private String subject;
	private String html;
	private Integer sysrecord;
	private Integer valid;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSdStage() {
	}

	/** minimal constructor */
	public AbstractSdStage(String code, Integer exId, String subject,
			String html, Integer sysrecord, Integer valid) {
		this.code = code;
		this.exId = exId;
		this.subject = subject;
		this.html = html;
		this.sysrecord = sysrecord;
		this.valid = valid;
	}

	/** full constructor */
	public AbstractSdStage(String code, Integer exId, String subject,
			String html, Integer sysrecord, Integer valid, String remark) {
		this.code = code;
		this.exId = exId;
		this.subject = subject;
		this.html = html;
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

	public Integer getExId() {
		return this.exId;
	}

	public void setExId(Integer exId) {
		this.exId = exId;
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