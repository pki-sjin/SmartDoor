package org.zero.db.entity.exhibition;

import java.sql.Timestamp;

/**
 * SdExhibition entity. @author MyEclipse Persistence Tools
 */
public class SdExhibition extends AbstractSdExhibition implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdExhibition() {
	}

	/** minimal constructor */
	public SdExhibition(String code, String subject, String html,
			Timestamp start, Timestamp end, Integer sysrecord, Integer valid) {
		super(code, subject, html, start, end, sysrecord, valid);
	}

	/** full constructor */
	public SdExhibition(String code, String subject, String html,
			Timestamp start, Timestamp end, Integer sysrecord, Integer valid,
			String remark) {
		super(code, subject, html, start, end, sysrecord, valid, remark);
	}

}
