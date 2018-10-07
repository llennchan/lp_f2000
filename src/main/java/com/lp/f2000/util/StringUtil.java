package com.lp.f2000.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {
	public static ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {
		JSON_MAPPER = Jackson2ObjectMapperBuilder.json().serializationInclusion(Include.NON_NULL)
				.failOnUnknownProperties(false).build();
		// JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
		// JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
	}

	/**
	 * json转成pojo对象
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> valueType) {
		if (null == json) {
			return null;
		}
		try {
			T value = JSON_MAPPER.readValue(json, valueType);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * json转JsonNode
	 * 
	 * @param json
	 * @return null if not eixst
	 */
	public static JsonNode fromJson(String json) {
		if (null == json)
			return null;
		try {
			JsonNode node = JSON_MAPPER.readTree(json);
			return node;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param json
	 * @param nullable
	 *            json为空是否判定为合法 true合法
	 * @return
	 */
	public static boolean checkJson(String json, boolean nullable) {
		if (null == json) {
			return nullable;
		}
		try {
			JSON_MAPPER.readTree(json);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static String toJson(Object obj) {
		String json = null;
		try {
			json = JSON_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static Boolean isBlank(String val) {
		return (null != val && !"".equals(val)) ? false : true;
	}

	public static Boolean isNotBlank(String val) {
		return (null != val && !"".equals(val)) ? true : false;
	}

	public static String randomGUID() {
		UUID uuid = UUID.randomUUID();
		long mostSigBits = uuid.getMostSignificantBits();
		long leastSigBits = uuid.getLeastSignificantBits();
		return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4)
				+ digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12));
	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}

	public static String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 字符串压缩成gzip
	 * 
	 * @param str
	 * @param encoding
	 * @return
	 */
	public static byte[] compress(String str, String encoding) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encoding));
			gzip.close();
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * gzip解压缩
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] uncompress(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			GZIPInputStream ungzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	static class Res extends AbstractRes {
		public Map<String,Object> data;
		public Res() {
		}

		public Res(Map<String,Object> d) {
			this.data = d;
		}

		public Map<String,Object> getData() {
			return data;
		}

		public void setData(Map<String,Object> data) {
			this.data = data;
		}

		@Override
		public Object getObjectData() {
			return data;
		}

	}

	static class AbstractRes {

		public AbstractRes() {
		}

		@JsonIgnore
		public Object getObjectData() {
			return null;
		}
	}

}
