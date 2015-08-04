package org.zero.db.entity.user;

import java.sql.Timestamp;

/**
 * AbstractSdUser entity provides the base persistence definition of the SdUser
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdUser implements java.io.Serializable {

	// Fields

	private Integer sdUserId;
	private String sdUserName;
	private String sdUserPassword;
	private String sdUserDisplayname;
	private String sdUserCompany;
	private String sdUserPosition;
	private String sdUserTel;
	private String sdUserCell;
	private String sdUserFax;
	private String sdUserMail;
	private Integer sdUserTypeId;
	private Timestamp sdUserCreate;
	private Timestamp sdUserLastlogin;

	// Constructors

	/** default constructor */
	public AbstractSdUser() {
	}

	/** minimal constructor */
	public AbstractSdUser(String sdUserName, String sdUserPassword,
			String sdUserDisplayname, Integer sdUserTypeId,
			Timestamp sdUserCreate, Timestamp sdUserLastlogin) {
		this.sdUserName = sdUserName;
		this.sdUserPassword = sdUserPassword;
		this.sdUserDisplayname = sdUserDisplayname;
		this.sdUserTypeId = sdUserTypeId;
		this.sdUserCreate = sdUserCreate;
		this.sdUserLastlogin = sdUserLastlogin;
	}

	/** full constructor */
	public AbstractSdUser(String sdUserName, String sdUserPassword,
			String sdUserDisplayname, String sdUserCompany,
			String sdUserPosition, String sdUserTel, String sdUserCell,
			String sdUserFax, String sdUserMail, Integer sdUserTypeId,
			Timestamp sdUserCreate, Timestamp sdUserLastlogin) {
		this.sdUserName = sdUserName;
		this.sdUserPassword = sdUserPassword;
		this.sdUserDisplayname = sdUserDisplayname;
		this.sdUserCompany = sdUserCompany;
		this.sdUserPosition = sdUserPosition;
		this.sdUserTel = sdUserTel;
		this.sdUserCell = sdUserCell;
		this.sdUserFax = sdUserFax;
		this.sdUserMail = sdUserMail;
		this.sdUserTypeId = sdUserTypeId;
		this.sdUserCreate = sdUserCreate;
		this.sdUserLastlogin = sdUserLastlogin;
	}

	// Property accessors

	public Integer getSdUserId() {
		return this.sdUserId;
	}

	public void setSdUserId(Integer sdUserId) {
		this.sdUserId = sdUserId;
	}

	public String getSdUserName() {
		return this.sdUserName;
	}

	public void setSdUserName(String sdUserName) {
		this.sdUserName = sdUserName;
	}

	public String getSdUserPassword() {
		return this.sdUserPassword;
	}

	public void setSdUserPassword(String sdUserPassword) {
		this.sdUserPassword = sdUserPassword;
	}

	public String getSdUserDisplayname() {
		return this.sdUserDisplayname;
	}

	public void setSdUserDisplayname(String sdUserDisplayname) {
		this.sdUserDisplayname = sdUserDisplayname;
	}

	public String getSdUserCompany() {
		return this.sdUserCompany;
	}

	public void setSdUserCompany(String sdUserCompany) {
		this.sdUserCompany = sdUserCompany;
	}

	public String getSdUserPosition() {
		return this.sdUserPosition;
	}

	public void setSdUserPosition(String sdUserPosition) {
		this.sdUserPosition = sdUserPosition;
	}

	public String getSdUserTel() {
		return this.sdUserTel;
	}

	public void setSdUserTel(String sdUserTel) {
		this.sdUserTel = sdUserTel;
	}

	public String getSdUserCell() {
		return this.sdUserCell;
	}

	public void setSdUserCell(String sdUserCell) {
		this.sdUserCell = sdUserCell;
	}

	public String getSdUserFax() {
		return this.sdUserFax;
	}

	public void setSdUserFax(String sdUserFax) {
		this.sdUserFax = sdUserFax;
	}

	public String getSdUserMail() {
		return this.sdUserMail;
	}

	public void setSdUserMail(String sdUserMail) {
		this.sdUserMail = sdUserMail;
	}

	public Integer getSdUserTypeId() {
		return this.sdUserTypeId;
	}

	public void setSdUserTypeId(Integer sdUserTypeId) {
		this.sdUserTypeId = sdUserTypeId;
	}

	public Timestamp getSdUserCreate() {
		return this.sdUserCreate;
	}

	public void setSdUserCreate(Timestamp sdUserCreate) {
		this.sdUserCreate = sdUserCreate;
	}

	public Timestamp getSdUserLastlogin() {
		return this.sdUserLastlogin;
	}

	public void setSdUserLastlogin(Timestamp sdUserLastlogin) {
		this.sdUserLastlogin = sdUserLastlogin;
	}

}