package com.lp.f2000.entity;

import java.util.List;

public class PfCity {
	private int provinceId;
	private int cityId;
	private String cityName;
	private List<PfArea> pfAreas;
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<PfArea> getPfAreas() {
		return pfAreas;
	}
	public void setPfAreas(List<PfArea> pfAreas) {
		this.pfAreas = pfAreas;
	}
	
	
	
}
