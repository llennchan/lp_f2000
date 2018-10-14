package com.lp.f2000.common;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lp.f2000.constant.ReturnCode;

public class Response<T> extends AbstractResponse {
	private T data;// 这是要返回的数据
	private transient Object pendingData; // 这是还需要处理的临时数据

	public Response() {
		super();
	}

	public Response(T data) {
		super();
		this.data = data;
	}

	public Response(int status, String msg) {
		super(status, msg);
	}

	public Response(int status, String msg, T data) {
		super(status, msg);
		this.data = data;
	}

	public Response(int status, String msg, T data, Object pendingData) {
		super(status, msg);
		this.data = data;
		this.pendingData = pendingData;
	}

	/**
	 * 返回一个成功状态的Response, 不附带数据
	 * 
	 * @return Response<Object>
	 */
	public static Response<Object> ofSuccess() {
		return new Response<Object>();
	}

	public static <T> Response<T> ofSuccess(Class<T> cls) {
		return new Response<T>();
	}

	/**
	 * 返回一个新的状态为success的Response, 并且附带数据data
	 * 
	 * @param data
	 * @return Response<T>
	 */
	public static <T> Response<T> ofSuccess(T data) {
		return new Response<T>(ReturnCode.SUCCESS, "{success}", data);
	}

	/**
	 * 返回一个错误的Response, 数据字段为空
	 * 
	 * @param status
	 * @param msg
	 * @return Response<Object>
	 */
	public static Response<Object> ofError(int status, String msg) {
		return new Response<Object>(status, msg);
	}

	public static Response<Object> ofParamError(String msg) {
		return new Response<Object>(ReturnCode.PARAM_ERROR, msg);
	}
	
	public static <T> Response<T> ofError(int status, String msg, Class<T> cls) {
		return new Response<T>(status, msg);
	}

	/**
	 * 
	 * @param status
	 * @param msg
	 * @return Response<Map<String, Object>>
	 */
	public static Response<Map<String, Object>> ofMapError(int status, String msg) {
		return new Response<Map<String, Object>>(status, msg);
	}

	/**
	 * 
	 * @param status
	 * @param msg
	 * @return Response<List<Map<String, Object>>>
	 */
	public static Response<List<Map<String, Object>>> ofListMapError(int status, String msg) {
		return new Response<List<Map<String, Object>>>(status, msg);
	}

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

	@Override
	@JsonIgnore
	public Object getObjectData() {
		return data;
	}

}
