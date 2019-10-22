package com.hyh.test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Test {
	
	private static final String ENCODING = "UTF-8";
	     /**
	      * URLBase64加密
	      */
	     public static String encode(String data) throws UnsupportedEncodingException{
	         byte[] encodedByte = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
	         return new String(encodedByte, ENCODING);
	     }
	     /**
	      * URLBase64解密
	      */
	     public static String decode(String data) throws UnsupportedEncodingException{
	         byte[] decodedByte = Base64.decodeBase64(data.getBytes(ENCODING));
	         return new String(decodedByte, ENCODING);
	     }
	
	
	public static void main(String[] args) throws Exception {
		/*
		 * File file = new File("D:/aliyun/EDAS"); Base64 base64 = new Base64(); String
		 * encodeToString = base64.encodeToString("D:/aliyun/EDAS".getBytes()); String
		 * encodeAsString = base64.encodeAsString("D:/aliyun/EDAS".getBytes()); //byte[]
		 * decode = base64.decode(encodeToString); //System.out.println(new
		 * String(decode, "utf-8")); System.out.println(encodeToString);
		 * 
		 * byte[] decode = base64.decode(encodeAsString); System.out.println(new
		 * String(decode, "utf-8")); System.out.println(encodeAsString);
		 */
		
		//String string = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm");
		//System.out.println(string);
		//ZipUtil.zip("C:\\Users\\hu.yuhao\\Desktop\\框架学习项目", "C:\\\\Users\\\\hu.yuhao\\\\Desktop\\\\框架学习项目");
		/*File file = new File("C:\\Users\\hu.yuhao\\Desktop\\框架学习项目");
		String parent = file.getParent();
		System.out.println(parent);*/
		/*
		 * ZipUtil.zip("D:\\huyuhao\\云平台\\电子围栏_监控中心需求规格说明书_胡宇豪.docx"); File file = new
		 * File("D:\\huyuhao\\云平台\\电子围栏_监控中心需求规格说明书_胡宇豪.docx.zip"); String encoding =
		 * EncodeUtil.getEncoding(file.getName()); System.out.println(encoding);
		 */
		
		  //File zipFile = new File("D:\\huyuhao\\云平台\\电子围栏_监控中心需求规格说明书_胡宇豪.docx");
		  
		  //String absolutePath = zipFile.getAbsolutePath();
		  //System.out.println(absolutePath.replace('\\', '/')); byte[] base64urlSafe =
		  //byte[] base64urlSafe = Base64.encodeBase64URLSafe();
		  String code = "D:/huyuhao/云平台/电子围栏_监控中心需求规格说明书_胡宇豪.docx";
		  byte[] encodeBase64 = Base64.encodeBase64(code.getBytes("UTF-8"));
		  String string = new String (encodeBase64, "UTF-8");
		  System.out.println(string);
		  String base64urlSafe = Base64.encodeBase64URLSafeString(code.getBytes("UTF-8"));
		  //String encode = new String(base64urlSafe, "UTF-8");
		  System.out.println(base64urlSafe);
		  byte[] decodeBase64 = Base64.decodeBase64(base64urlSafe.getBytes("UTF-8"));
		  System.out.println(new String(decodeBase64, "UTF-8"));
		  //String encoder = new String(base64urlSafe, "UTF-8");
		  //byte[] decodeBase64 = Base64.decodeBase64(encoder.getBytes("UTF-8"));
		 // System.out.println(new String(decodeBase64, "utf-8"));
		  //System.out.println(encoder);
		 
		
		/*
		 * String encode = encode("D:\\huyuhao\\云平台\\电子围栏_监控中心需求规格说明书_胡宇豪.docx"); String
		 * decode = decode(encode); System.out.println(decode);
		 */
		
		//ZipUtil.zip("D:/huyuhao/云平台/电子围栏_监控中心需求规格说明书_胡宇豪.docx");
	}
}
