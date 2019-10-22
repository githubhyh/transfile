package com.hyh.fileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

/**
 * <p>将目录文件打包成zip格式，提供下载</p>
 * @author hu.yuhao
 * */
@Service
public class FileDownloadService {
	public void downloadByZip(String srcPath, HttpServletResponse res) throws Exception {
		File srcFile = new File(srcPath);
		String name = srcFile.getName();
		File zipFile = new File(srcFile.getParent()+"/"+ name +".zip");
		downloadByZip(zipFile, res);
	}
	
	public void downloadByZip(File zipFile, HttpServletResponse res) throws IOException {
		ServletOutputStream outputStream = null;
		FileInputStream fis = null;
		try {
			res.setCharacterEncoding("utf-8");
			res.setHeader("Content-Disposition", "attachment;filename="+new String(zipFile.getName().getBytes("UTF-8"), "ISO-8859-1"));
			res.setContentType("application/x-msdownload");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("download faile");
			e.printStackTrace();
		}
		outputStream = res.getOutputStream();
		fis = new FileInputStream(zipFile);
		IOUtils.copy(fis, outputStream);
		fis.close();
		outputStream.close();
		zipFile.delete();
	}
}
