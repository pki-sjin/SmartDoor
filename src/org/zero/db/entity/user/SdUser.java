package org.zero.db.entity.user;

import java.sql.Timestamp;

import org.json.JSONObject;

/**
 * SdUser entity. @author MyEclipse Persistence Tools
 */
public class SdUser extends AbstractSdUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SdUser() {
	}

	/** minimal constructor */
	public SdUser(String sdUserName, String sdUserPassword,
			String sdUserDisplayname, Integer sdUserTypeId,
			Timestamp sdUserCreate, Timestamp sdUserLastlogin) {
		super(sdUserName, sdUserPassword, sdUserDisplayname, sdUserTypeId,
				sdUserCreate, sdUserLastlogin);
	}

	/** full constructor */
	public SdUser(String sdUserName, String sdUserPassword,
			String sdUserDisplayname, String sdUserCompany,
			String sdUserPosition, String sdUserTel, String sdUserCell,
			String sdUserFax, String sdUserMail, Integer sdUserTypeId,
			Timestamp sdUserCreate, Timestamp sdUserLastlogin) {
		super(sdUserName, sdUserPassword, sdUserDisplayname, sdUserCompany,
				sdUserPosition, sdUserTel, sdUserCell, sdUserFax, sdUserMail,
				sdUserTypeId, sdUserCreate, sdUserLastlogin);
	}

	public boolean needFilloutInfo() {
		return getSdUserCell() == null
				|| getSdUserCompany() == null
				|| getSdUserFax() == null
				|| getSdUserMail() == null
				|| getSdUserPosition() == null
				|| getSdUserTel() == null
				|| getSdUserDisplayname() == null
				||getSdUserCell().equalsIgnoreCase("") 
				|| getSdUserCompany().equalsIgnoreCase("")
				|| getSdUserFax().equalsIgnoreCase("")
				|| getSdUserMail().equalsIgnoreCase("")
				|| getSdUserPosition().equalsIgnoreCase("")
				|| getSdUserTel().equalsIgnoreCase("")
				|| getSdUserDisplayname().equalsIgnoreCase("");
	}
	
	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		json.put("username", getSdUserName());
		json.put("display", getSdUserDisplayname());
		json.put("company", getSdUserCompany());
		json.put("position", getSdUserPosition());
		json.put("tel", getSdUserTel());
		json.put("cell", getSdUserCell());
		json.put("fax", getSdUserFax());
		json.put("mail", getSdUserMail());
		json.put("last", getSdUserLastlogin());
		return json;
	}
}
