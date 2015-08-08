package org.zero.db.entity.sms;

import java.sql.Timestamp;

/**
 * SdValidation entity. @author MyEclipse Persistence Tools
 */
public class SdValidation extends AbstractSdValidation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdValidation() {
	}

	/** full constructor */
	public SdValidation(String sdValidationCell, String sdValidationCode,
			String sdValidationTag, Timestamp sdValidationCreate) {
		super(sdValidationCell, sdValidationCode, sdValidationTag,
				sdValidationCreate);
	}

}
