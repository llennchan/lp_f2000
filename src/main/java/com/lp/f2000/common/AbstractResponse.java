package com.lp.f2000.common;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lp.f2000.component.MessageManager;
import com.lp.f2000.constant.ReturnCode;
import com.lp.f2000.util.StringUtil;

@JsonInclude(Include.NON_NULL)
public class AbstractResponse {
	private static Logger logger = LoggerFactory.getLogger(AbstractResponse.class);
	private int status;
	private String message;
	private String csrfToken;
	private String csrfTime;

	public AbstractResponse() {
		this.status = ReturnCode.SUCCESS;
		this.csrfTime = new Date().getTime()  + "";
		this.csrfToken = StringUtil.getCsrfToken(csrfTime);
		setMessage("{success}");
	}

	public AbstractResponse(int status, String message) {
		super();
		this.status = status;
		this.csrfTime = new Date().getTime()  + "";
		this.csrfToken = StringUtil.getCsrfToken(csrfTime);
		setMessage(message);
	}

	public void write2Response(HttpServletResponse response) {
		response.setHeader("content-type", "application/json;charset=UTF-8");
		try {
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(StringUtil.toJson(this).getBytes("UTF-8"));
		} catch (IOException e) {
			logger.warn("获取response流出错: " + e.getMessage());
			return;
		}
	}

	public static AbstractResponse ofSuccess() {
		return new AbstractResponse();
	}

	public static AbstractResponse ofError(int status, String msg) {
		return new AbstractResponse(status, msg);
	}

	/**
	 * 子类可以重写这个方法返回数据字段
	 * @return
	 */
	@JsonIgnore
	public Object getObjectData() {
		return null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = MessageManager.getMsg(message);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

	public String getcsrfTime() {
		return csrfTime;
	}

	public void setcsrfTime(String csrfTime) {
		this.csrfTime = csrfTime;
	}

	public boolean error() {
		return !success();
	}

	public boolean success() {
		return status == ReturnCode.SUCCESS;
	}

	@Override
	public String toString() {
		return "ResponseBase [status=" + status + ", csrfToken=" + csrfToken + ", csrfTime=" + csrfTime + ", message=" + message + "]";
	}
}
