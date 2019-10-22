package com.hyh.fileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author hu.yuhao
 *         <p>
 *         web中文件夹上传下载采用压缩包格式
 *         </p>
 */
public class ZipUtil {
	public static boolean zip1(String srcPath, String destPath) {
		File srcFile = new File(srcPath);
		if (!srcFile.exists())
			return false;

		File destFile = new File(destPath + "/" + srcFile.getName() + ".zip");

		if (destFile.exists())
			return false;

		File[] listFiles = srcFile.listFiles();
		boolean flag = true;
		FileOutputStream fileOutputStream = null;
		ZipOutputStream zipOutputStream = null;
		FileInputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;

		if (listFiles.length < 1 || listFiles == null)
			return false;
		try {
			fileOutputStream = new FileOutputStream(destFile);
			zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
			byte[] buf = new byte[1024 * 10];

			for (int i = 0; i < listFiles.length; i++) {
				ZipEntry zipEntry = new ZipEntry(listFiles[i].getName());
				zipOutputStream.putNextEntry(zipEntry);

				inputStream = new FileInputStream(listFiles[i]);
				bufferedInputStream = new BufferedInputStream(inputStream, 1024 * 10);
				int read = 0;
				while ((read = bufferedInputStream.read(buf, 0, 1024 * 10)) != -1) {
					zipOutputStream.write(buf, 0, read);
				}
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			}
			if (zipOutputStream != null) {
				zipOutputStream.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
				if (fileOutputStream != null)
					fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static void zip(String srcPath, String destPath) {

		File sourceFile = new File(srcPath);
		File destFile = new File(destPath + "/" + sourceFile.getName() + ".zip");
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
			BufferedOutputStream bos = new BufferedOutputStream(out);
	
			compress(out, bos, sourceFile, sourceFile.getName());
	
			bos.close();
			out.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void zip(String srcPath) {
		File sourceFile = new File(srcPath);
		String destPath = sourceFile.getParent();
		try {
			File destFile = new File(destPath + "/" + new String((sourceFile.getName().getBytes()), "UTF-8") + ".zip");
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
			BufferedOutputStream bos = new BufferedOutputStream(out);
	
			compress(out, bos, sourceFile, sourceFile.getName());
	
			bos.close();
			out.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static File zipToCurrentPath(String srcPath) {
		File sourceFile = new File(srcPath);
		String destPath = sourceFile.getParent();
		File destFile = null;
		try {
			destFile = new File(destPath + "/" + new String((sourceFile.getName().getBytes()), "UTF-8") + ".zip");
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
			BufferedOutputStream bos = new BufferedOutputStream(out);
	
			compress(out, bos, sourceFile, sourceFile.getName());
	
			bos.close();
			out.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return destFile;
	}
	

	public static void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base) throws Exception {
		// 如果路径为目录（文件夹）
		if (sourceFile.isDirectory()) {

			// 取出文件夹中的文件（或子文件夹）
			File[] flist = sourceFile.listFiles();

			if (flist.length == 0)// 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
			{
				out.putNextEntry(new ZipEntry(base + "/"));
			} else// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
			{
				for (int i = 0; i < flist.length; i++) {
					compress(out, bos, flist[i], base + "/" + flist[i].getName());
				}
			}
		} else// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
		{
			out.putNextEntry(new ZipEntry(base));
			FileInputStream fos = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fos);

			int tag;
			// 将源文件写入到zip文件中
			while ((tag = bis.read()) != -1) {
				out.write(tag);
			}
			bis.close();
			fos.close();

		}
	}
}
