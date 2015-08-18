package org.zero.db.entity.join;

/**
 * AbstractSdJoin entity provides the base persistence definition of the SdJoin
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdJoin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String description;
	private Integer sysrecord;
	private Integer valid;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSdJoin() {
	}

	/** minimal constructor */
	public AbstractSdJoin(String code, String description, Integer sysrecord,
			Integer valid) {
		this.code = code;
		this.description = description;
		this.sysrecord = sysrecord;
		this.valid = valid;
	}

	/** full constructor */
	public AbstractSdJoin(String code, String description, Integer sysrecord,
			Integer valid, String remark) {
		this.code = code;
		this.description = description;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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