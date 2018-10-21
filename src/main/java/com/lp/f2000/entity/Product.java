package com.lp.f2000.entity;

import java.sql.Timestamp;
import java.util.List;

public class Product {
	private int id;
	private String name;
	private String desc1;
	private String desc2;
	private String desc3;
	private boolean isValid;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	private int status;
	private int position;
	
	private List<Image> thumbImages;
	private List<Image> broadcastImages;
	private List<Image> smallImages;
	private List<Image> detailImages;
	
	
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
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
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
	public List<Image> getThumbImages() {
		return thumbImages;
	}
	public void setThumbImages(List<Image> thumbImages) {
		this.thumbImages = thumbImages;
	}
	public List<Image> getBroadcastImages() {
		return broadcastImages;
	}
	public void setBroadcastImages(List<Image> broadcastImages) {
		this.broadcastImages = broadcastImages;
	}
	public List<Image> getSmallImages() {
		return smallImages;
	}
	public void setSmallImages(List<Image> smallImages) {
		this.smallImages = smallImages;
	}
	public List<Image> getDetailImages() {
		return detailImages;
	}
	public void setDetailImages(List<Image> detailImages) {
		this.detailImages = detailImages;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

	
	
}
