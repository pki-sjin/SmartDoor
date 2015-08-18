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
	public SdUser(String code, Integer exId, String name, String password,
			Integer typeId, Timestamp createTime, Timestamp lastlogin,
			Integer sysrecord, Integer valid) {
		super(code, exId, name, password, typeId, createTime, lastlogin,
				sysrecord, valid);
	}

	/** full constructor */
	public SdUser(String code, Integer exId, String name, String password,
			String displayname, String country, String province, String city,
			String company, String address, String postcode, String web,
			String position, String tel, String cell, String fax, String mail,
			Integer typeId, Timestamp createTime, Timestamp lastlogin,
			Integer sysrecord, Integer valid, String remark) {
		super(code, exId, name, password, displayname, country, province, city,
				company, address, postcode, web, position, tel, cell, fax,
				mail, typeId, createTime, lastlogin, sysrecord, valid, remark);
	}

	public boolean needFilloutInfo() {
		return getDisplayname() == null || getCountry() == null
				|| getProvince() == null || getCity() == null
				|| getCompany() == null || getAddress() == null
				|| getPostcode() == null || getWeb() == null
				|| getPosition() == null || getTel() == null
				|| getCell() == null || getFax() == null || getMail() == null
				|| getDisplayname().equalsIgnoreCase("")
				|| getCountry().equalsIgnoreCase("")
				|| getProvince().equalsIgnoreCase("")
				|| getCity().equalsIgnoreCase("")
				|| getCompany().equalsIgnoreCase("")
				|| getAddress().equalsIgnoreCase("")
				|| getPostcode().equalsIgnoreCase("")
				|| getWeb().equalsIgnoreCase("")
				|| getPosition().equalsIgnoreCase("")
				|| getTel().equalsIgnoreCase("")
				|| getCell().equalsIgnoreCase("")
				|| getFax().equalsIgnoreCase("")
				|| getMail().equalsIgnoreCase("");
	}

	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		json.put("username", getName());
		json.put("display", getDisplayname());
		json.put("country", getCountry());
		json.put("province", getProvince());
		json.put("city", getCity());
		json.put("company", getCompany());
		json.put("address", getAddress());
		json.put("postcode", getPostcode());
		json.put("web", getWeb());
		json.put("position", getPosition());
		json.put("tel", getTel());
		json.put("cell", getCell());
		json.put("fax", getFax());
		json.put("mail", getMail());
		json.put("last", getLastlogin());
		return json;
	}

}
