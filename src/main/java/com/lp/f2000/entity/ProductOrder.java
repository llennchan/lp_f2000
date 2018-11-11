package com.lp.f2000.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductOrder {
	private int id;
	private int pay_order_id;
	private int user_id;
	private int sku_id;
	private int num;
	private BigDecimal price;
	private int status;
	private int logistics_status;
	
	private boolean isValid;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPay_order_id() {
		return pay_order_id;
	}
	public void setPay_order_id(int pay_order_id) {
		this.pay_order_id = pay_order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getSku_id() {
		return sku_id;
	}
	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLogistics_status() {
		return logistics_status;
	}
	public void setLogistics_status(int logistics_status) {
		this.logistics_status = logistics_status;
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
