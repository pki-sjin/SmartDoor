package org.zero.db.entity.activity;

/**
 * AbstractSdExUserRelation entity provides the base persistence definition of
 * the SdExUserRelation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdExUserRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer exId;
	private Integer userId;
	private Integer joinId;
	private Long orderId;

	// Constructors

	/** default constructor */
	public AbstractSdExUserRelation() {
	}

	/** full constructor */
	public AbstractSdExUserRelation(Integer exId, Integer userId,
			Integer joinId, Long orderId) {
		this.exId = exId;
		this.userId = userId;
		this.joinId = joinId;
		this.orderId = orderId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExId() {
		return this.exId;
	}

	public void setExId(Integer exId) {
		this.exId = exId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getJoinId() {
		return this.joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}