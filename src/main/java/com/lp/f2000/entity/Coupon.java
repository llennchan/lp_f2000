package com.lp.f2000.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Coupon {
	private int id;
	private String name;
	private int couponType;
	private BigDecimal cutMoney;
	private float discountRate;
	private BigDecimal minCost;
	private BigDecimal maxDiscountPrice;
	
	private int num;
	private Timestamp receiveStartTime;
	private Timestamp receiveEndTime;
	private Timestamp useStartTime;
	private Timestamp useEndTime;
	private int validDayNum;
	private int personLimitNum;
	
	private boolean isValid;
	private Timestamp createTime;
	private Timestamp updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCouponType() {
		return couponType;
	}
	public void setCouponType(int couponType) {
		this.couponType = couponType;
	}
	public BigDecimal getCutMoney() {
		return cutMoney;
	}
	public void setCutMoney(BigDecimal cutMoney) {
		this.cutMoney = cutMoney;
	}
	public float getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(float discountRate) {
		this.discountRate = discountRate;
	}
	public BigDecimal getMinCost() {
		return minCost;
	}
	public void setMinCost(BigDecimal minCost) {
		this.minCost = minCost;
	}
	public BigDecimal getMaxDiscountPrice() {
		return maxDiscountPrice;
	}
	public void setMaxDiscountPrice(BigDecimal maxDiscountPrice) {
		this.maxDiscountPrice = maxDiscountPrice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Timestamp getReceiveStartTime() {
		return receiveStartTime;
	}
	public void setReceiveStartTime(Timestamp receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}
	public Timestamp getReceiveEndTime() {
		return receiveEndTime;
	}
	public void setReceiveEndTime(Timestamp receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}
	public Timestamp getUseStartTime() {
		return useStartTime;
	}
	public void setUseStartTime(Timestamp useStartTime) {
		this.useStartTime = useStartTime;
	}
	public Timestamp getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(Timestamp useEndTime) {
		this.useEndTime = useEndTime;
	}
	public int getValidDayNum() {
		return validDayNum;
	}
	public void setValidDayNum(int validDayNum) {
		this.validDayNum = validDayNum;
	}
	public int getPersonLimitNum() {
		return personLimitNum;
	}
	public void setPersonLimitNum(int personLimitNum) {
		this.personLimitNum = personLimitNum;
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
