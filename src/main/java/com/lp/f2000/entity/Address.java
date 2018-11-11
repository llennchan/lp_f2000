package com.lp.f2000.entity;

import java.sql.Timestamp;

public class Address {
	private int id;
	private int userId;
	private String recProvince;
	private String recCity;
	private String recArea;
	private String recAddress;
	private boolean is_default;
	private String contactName;
	private String contactPhone;
	
	private boolean isValid;
	private Timestamp createTime;
	private Timestamp updateTime;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRecProvince() {
		return recProvince;
	}
	public void setRecProvince(String recProvince) {
		this.recProvince = recProvince;
	}
	public String getRecCity() {
		return recCity;
	}
	public void setRecCity(String recCity) {
		this.recCity = recCity;
	}
	public String getRecArea() {
		return recArea;
	}
	public void setRecArea(String recArea) {
		this.recArea = recArea;
	}
	public String getRecAddress() {
		return recAddress;
	}
	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}
	public boolean isIs_default() {
		return is_default;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
