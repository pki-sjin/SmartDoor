package org.zero.db.entity.relation;

/**
 * AbstractSdExStageRelation entity provides the base persistence definition of
 * the SdExStageRelation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdExStageRelation implements java.io.Serializable {

	// Fields

	private Long sdRelationId;
	private Integer sdExId;
	private Integer sdStageId;

	// Constructors

	/** default constructor */
	public AbstractSdExStageRelation() {
	}

	/** full constructor */
	public AbstractSdExStageRelation(Integer sdExId, Integer sdStageId) {
		this.sdExId = sdExId;
		this.sdStageId = sdStageId;
	}

	// Property accessors

	public Long getSdRelationId() {
		return this.sdRelationId;
	}

	public void setSdRelationId(Long sdRelationId) {
		this.sdRelationId = sdRelationId;
	}

	public Integer getSdExId() {
		return this.sdExId;
	}

	public void setSdExId(Integer sdExId) {
		this.sdExId = sdExId;
	}

	public Integer getSdStageId() {
		return this.sdStageId;
	}

	public void setSdStageId(Integer sdStageId) {
		this.sdStageId = sdStageId;
	}

}