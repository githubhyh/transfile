package com.hyh.fileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.hyh.fileUtil.IOUtil;

/**
 * <p>文件上传服务，注意中文名的支持。目前只支持文件（可以多个单独文件）上传
 * 文件夹需要以打包的形式上传</p>
 * @author hu.yuhao
 * */
@Service
public class FileUploadService {
	/**
	 * 将多个单独文件上传到同一个目录下
	 * 暂时不考虑文件名冲突(多用户时)
	 * 单用户上传考虑文件重名
	 * 采用原生态写法
	 * @param destPath 远程目录，通过前段页面获取路径(必须是目录才能上传)
	 * @param srcPath 本地文件目录，可以是单独一个或多个单独文件
	 * @throws IOException 
	 * */
	public int uploadFile(String destPath, String srcPath) throws IOException {
		File srcFile = new File(srcPath);
		if (!srcFile.exists())return -1;
		String fileName = srcFile.getName();
		File destFile = new File(destPath+"/"+fileName);
		if (destFile.exists())return -1;
		FileInputStream inputStream = new FileInputStream(srcFile);
		FileOutputStream outputStream = new FileOutputStream(destFile);
		int flag = IOUtil.copyLarge(inputStream, outputStream);
		return flag;
	}
}
