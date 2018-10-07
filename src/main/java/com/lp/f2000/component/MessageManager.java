package com.lp.f2000.component;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {
	private static MessageSource messageSource;

	public static String getMsg(String key) {
		String msg = null;
		if (key.length() > 2 && '{' == key.charAt(0) && '}' == key.charAt(key.length() - 1)) {
			key = key.substring(1, key.length() - 1);
			Locale locale = LocaleContextHolder.getLocale();
			msg = messageSource.getMessage(key, null, locale);
		}
		if (null == msg)
			return key;
		return msg;
	}

	public static String getMsg(String key, String... arg) {
		String msg = null;
		if (key.length() > 2 && '{' == key.charAt(0) && '}' == key.charAt(key.length() - 1)) {
			key = key.substring(1, key.length() - 1);
			Locale locale = LocaleContextHolder.getLocale();
			Object[] args = new Object[arg.length];
			for (int i = 0; i < arg.length; i++) {
				args[i] = arg[i];
			}
			msg = messageSource.getMessage(key, args, locale);
		}
		if (null == msg)
			return key;
		return msg;
	}

	@Autowired(required = true)
	public void setMessageSource(MessageSource messageSource) {
		MessageManager.messageSource = messageSource;
	}
}
