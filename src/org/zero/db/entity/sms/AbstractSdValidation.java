package org.zero.db.entity.sms;

import java.sql.Timestamp;

/**
 * AbstractSdValidation entity provides the base persistence definition of the
 * SdValidation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdValidation implements java.io.Serializable {

	// Fields

	private Integer sdValidationId;
	private String sdValidationCell;
	private String sdValidationCode;
	private String sdValidationTag;
	private Timestamp sdValidationCreate;

	// Constructors

	/** default constructor */
	public AbstractSdValidation() {
	}

	/** full constructor */
	public AbstractSdValidation(String sdValidationCell,
			String sdValidationCode, String sdValidationTag,
			Timestamp sdValidationCreate) {
		this.sdValidationCell = sdValidationCell;
		this.sdValidationCode = sdValidationCode;
		this.sdValidationTag = sdValidationTag;
		this.sdValidationCreate = sdValidationCreate;
	}

	// Property accessors

	public Integer getSdValidationId() {
		return this.sdValidationId;
	}

	public void setSdValidationId(Integer sdValidationId) {
		this.sdValidationId = sdValidationId;
	}

	public String getSdValidationCell() {
		return this.sdValidationCell;
	}

	public void setSdValidationCell(String sdValidationCell) {
		this.sdValidationCell = sdValidationCell;
	}

	public String getSdValidationCode() {
		return this.sdValidationCode;
	}

	public void setSdValidationCode(String sdValidationCode) {
		this.sdValidationCode = sdValidationCode;
	}

	public String getSdValidationTag() {
		return this.sdValidationTag;
	}

	public void setSdValidationTag(String sdValidationTag) {
		this.sdValidationTag = sdValidationTag;
	}

	public Timestamp getSdValidationCreate() {
		return this.sdValidationCreate;
	}

	public void setSdValidationCreate(Timestamp sdValidationCreate) {
		this.sdValidationCreate = sdValidationCreate;
	}

}