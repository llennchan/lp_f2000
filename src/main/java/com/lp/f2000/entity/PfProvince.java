package com.lp.f2000.entity;

import java.util.List;

public class PfProvince {
	private int provinceId;
	private String provinceName;
	private List<PfCity> pfCities;
	
	
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public List<PfCity> getPfCities() {
		return pfCities;
	}
	public void setPfCities(List<PfCity> pfCities) {
		this.pfCities = pfCities;
	}
	
	
}
