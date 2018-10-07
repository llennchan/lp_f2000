package com.lp.f2000.common;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.lp.f2000.constant.Constant;
import com.lp.f2000.entity.User;
import com.lp.f2000.util.StringUtil;

public class Session implements Serializable {
	private static final long serialVersionUID = -719189565424841444L;
	private final static DateTimeFormatter fmt=DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
	private String key;
	private User user;

	public Session() {
		key = generateKey();
	}

	/**
	 * 生成新的sessionKey
	 * 
	 * @return
	 */
	private String generateKey() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(Constant.SESSION_KEY).append(StringUtil.randomGUID()).append(fmt.format(LocalDateTime.now()));
		return sb.toString();
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [key=" + key + ", user=" + user + "]";
	}
	
	public static void main(String[] args) {
		DateTimeFormatter fmt=DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
		System.out.println(fmt.format(LocalDateTime.now()));
	}

}
