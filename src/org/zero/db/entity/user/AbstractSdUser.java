package org.zero.db.entity.user;

import java.sql.Timestamp;

/**
 * AbstractSdUser entity provides the base persistence definition of the SdUser
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private Integer exId;
	private String name;
	private String password;
	private String displayname;
	private String country;
	private String province;
	private String city;
	private String company;
	private String address;
	private String postcode;
	private String web;
	private String position;
	private String tel;
	private String cell;
	private String fax;
	private String mail;
	private Integer typeId;
	private Timestamp createTime;
	private Timestamp lastlogin;
	private Integer sysrecord;
	private Integer valid;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSdUser() {
	}

	/** minimal constructor */
	public AbstractSdUser(String code, Integer exId, String name,
			String password, Integer typeId, Timestamp createTime,
			Timestamp lastlogin, Integer sysrecord, Integer valid) {
		this.code = code;
		this.exId = exId;
		this.name = name;
		this.password = password;
		this.typeId = typeId;
		this.createTime = createTime;
		this.lastlogin = lastlogin;
		this.sysrecord = sysrecord;
		this.valid = valid;
	}

	/** full constructor */
	public AbstractSdUser(String code, Integer exId, String name,
			String password, String displayname, String country,
			String province, String city, String company, String address,
			String postcode, String web, String position, String tel,
			String cell, String fax, String mail, Integer typeId,
			Timestamp createTime, Timestamp lastlogin, Integer sysrecord,
			Integer valid, String remark) {
		this.code = code;
		this.exId = exId;
		this.name = name;
		this.password = password;
		this.displayname = displayname;
		this.country = country;
		this.province = province;
		this.city = city;
		this.company = company;
		this.address = address;
		this.postcode = postcode;
		this.web = web;
		this.position = position;
		this.tel = tel;
		this.cell = cell;
		this.fax = fax;
		this.mail = mail;
		this.typeId = typeId;
		this.createTime = createTime;
		this.lastlogin = lastlogin;
		this.sysrecord = sysrecord;
		this.valid = valid;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getExId() {
		return this.exId;
	}

	public void setExId(Integer exId) {
		this.exId = exId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Integer getSysrecord() {
		return this.sysrecord;
	}

	public void setSysrecord(Integer sysrecord) {
		this.sysrecord = sysrecord;
	}

	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}