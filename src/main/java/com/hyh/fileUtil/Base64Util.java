package com.hyh.fileUtil;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * @author hu.yuhao
 * <p>敏感字符加密处理，传输文件路径</P>
 * */
public class Base64Util {
	private static final Base64 base64 = new Base64();
	
	/**
	 * <p>对字符串进行加密，默认是UTF-8编码</p>
	 * @param code 需要编码的字符串
	 * @return 返回重新编码的字符串，有效避免敏感字符
	 * */
	public static String encoder(String code) {
		if (code == null || "".equals(code))return "NULLERROR";
		byte[] encodeBase64;
		String encodeToString = null;
		try {
			encodeBase64 = Base64.encodeBase64(code.getBytes("UTF-8"));
			encodeToString = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodeToString;
	}
	
	/**
	 * <p>解密，就是反向重新编码</p>
	 * @param code 需要重新编码的字符串
	 * @param setCharacter 设置编码格式
	 * @return 返回指定的编码格式的字符串
	 * */
	public static String decoder(String code, String setCharacter) {
		if (code == null||"".equals(code))return "NULLERROR";
		byte[] decode = base64.decode(code);
		try {
			String decodeString = new String(decode, setCharacter);
			return decodeString;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	/**
	 * <p>默认解码方式</p>
	 * @param code 需要重新编码的字符串
	 * @return 采用默认编码方式解码的字符串
	 * */
	public static String decoder(String code) {
		if (code == null||"".equals(code))return "NULLERROR";
		byte[] decode = Base64.decodeBase64(code);
		try {
			String decodeString = new String(decode, "utf-8");
			return decodeString;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	
	public static String removeSpecialChar(String code) {
		String replace = code.replace(' ', '+');
		return replace;
	}
}
