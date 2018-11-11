package com.lp.f2000.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sku {
	private int id;
	private int productId;
	private String name;
	private BigDecimal price;
	private BigDecimal discountPrice;
	private int num;
	private boolean isValid;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	private int restNum;
	
	public Sku() {
		super();
	}
	
	public Sku(int id, String name, int num, BigDecimal price, BigDecimal discountPrice) {
		this.id = id;
		this.name = name;
		this.num = num;
		this.price = price;
		this.discountPrice = discountPrice;
	}
	
	public Sku(String name, int num, BigDecimal price, BigDecimal discountPrice) {
		this.name = name;
		this.num = num;
		this.price = price;
		this.discountPrice = discountPrice;
	}
	
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public void setNum(int num) {
		this.num = num;
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

	public int getRestNum() {
		return restNum;
	}

	public void setRestNum(int restNum) {
		this.restNum = restNum;
	}
	
	
	
	
}
