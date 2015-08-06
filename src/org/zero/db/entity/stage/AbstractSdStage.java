package org.zero.db.entity.stage;

/**
 * AbstractSdStage entity provides the base persistence definition of the
 * SdStage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdStage implements java.io.Serializable {

	// Fields

	private Integer sdStageId;
	private String sdStageSubject;
	private String sdStageHtml;

	// Constructors

	/** default constructor */
	public AbstractSdStage() {
	}

	/** full constructor */
	public AbstractSdStage(String sdStageSubject, String sdStageHtml) {
		this.sdStageSubject = sdStageSubject;
		this.sdStageHtml = sdStageHtml;
	}

	// Property accessors

	public Integer getSdStageId() {
		return this.sdStageId;
	}

	public void setSdStageId(Integer sdStageId) {
		this.sdStageId = sdStageId;
	}

	public String getSdStageSubject() {
		return this.sdStageSubject;
	}

	public void setSdStageSubject(String sdStageSubject) {
		this.sdStageSubject = sdStageSubject;
	}

	public String getSdStageHtml() {
		return this.sdStageHtml;
	}

	public void setSdStageHtml(String sdStageHtml) {
		this.sdStageHtml = sdStageHtml;
	}

}