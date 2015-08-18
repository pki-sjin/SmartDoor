package org.zero.db.entity.join;

/**
 * SdJoin entity. @author MyEclipse Persistence Tools
 */
public class SdJoin extends AbstractSdJoin implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdJoin() {
	}

	/** minimal constructor */
	public SdJoin(String code, String description, Integer sysrecord,
			Integer valid) {
		super(code, description, sysrecord, valid);
	}

	/** full constructor */
	public SdJoin(String code, String description, Integer sysrecord,
			Integer valid, String remark) {
		super(code, description, sysrecord, valid, remark);
	}

}
