package org.zero.db.entity.stage;

/**
 * SdStage entity. @author MyEclipse Persistence Tools
 */
public class SdStage extends AbstractSdStage implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdStage() {
	}

	/** minimal constructor */
	public SdStage(String code, Integer exId, String subject, String html,
			Integer sysrecord, Integer valid) {
		super(code, exId, subject, html, sysrecord, valid);
	}

	/** full constructor */
	public SdStage(String code, Integer exId, String subject, String html,
			Integer sysrecord, Integer valid, String remark) {
		super(code, exId, subject, html, sysrecord, valid, remark);
	}

}
