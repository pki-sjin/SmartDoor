package org.zero.db.entity.relation;

/**
 * SdExStageRelation entity. @author MyEclipse Persistence Tools
 */
public class SdExStageRelation extends AbstractSdExStageRelation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdExStageRelation() {
	}

	/** full constructor */
	public SdExStageRelation(Integer sdExId, Integer sdStageId) {
		super(sdExId, sdStageId);
	}

}
