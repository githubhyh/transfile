package com.hyh.fileUtil;

public class InitAuth {
	public static void main(String[] args) {
		String pass = "123456";
		MD5Util.encrypt(pass, "C:/Users/hu.yuhao/filetransport/authority.txt");
		new FileUtil().init();
	}
}
