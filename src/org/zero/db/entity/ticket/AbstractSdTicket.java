package org.zero.db.entity.ticket;

import java.sql.Timestamp;


/**
 * AbstractSdTicket entity provides the base persistence definition of the SdTicket entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSdTicket  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String code;
     private Integer exId;
     private String name;
     private String description;
     private Double price;
     private Integer typeId;
     private Timestamp createTime;
     private Integer sysrecord;
     private Integer valid;
     private String remark;


    // Constructors

    /** default constructor */
    public AbstractSdTicket() {
    }

	/** minimal constructor */
    public AbstractSdTicket(String code, Integer exId, String name, String description, Double price, Integer typeId, Timestamp createTime, Integer sysrecord, Integer valid) {
        this.code = code;
        this.exId = exId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.typeId = typeId;
        this.createTime = createTime;
        this.sysrecord = sysrecord;
        this.valid = valid;
    }
    
    /** full constructor */
    public AbstractSdTicket(String code, Integer exId, String name, String description, Double price, Integer typeId, Timestamp createTime, Integer sysrecord, Integer valid, String remark) {
        this.code = code;
        this.exId = exId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.typeId = typeId;
        this.createTime = createTime;
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
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