package com.lp.f2000.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lp.f2000.constant.ReturnCode;

public class TResponse<T> extends AbstractResponse {
	private T data;// 这是要返回的数据
	private transient Object pendingData; // 这是还需要处理的临时数据

	public TResponse() {
		super();
	}

	public TResponse(T data) {
		super();
		this.data = data;
	}

	public TResponse(int status, String msg) {
		super(status, msg);
	}

	public TResponse(int status, String msg, T data) {
		super(status, msg);
		this.data = data;
	}

	public TResponse(int status, String msg, T data, Object pendingData) {
		super(status, msg);
		this.data = data;
		this.pendingData = pendingData;
	}

	public static <T> TResponse<T> ofSuccess(T data) {
		return new TResponse<T>(ReturnCode.SUCCESS, "{success}", data);
	}

//	public static <T> TResponse<T> newResponse(T data) {
//		return new TResponse<>(data);
//	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonIgnore
	public Object getPendingData() {
		return pendingData;
	}

	public void setPendingData(Object pendingData) {
		this.pendingData = pendingData;
	}

}
