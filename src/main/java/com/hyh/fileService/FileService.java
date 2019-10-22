package com.hyh.fileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hyh.entity.FileInfo;
import com.hyh.fileAPI.IFileTrans;
import com.hyh.fileUtil.Base64Util;
import com.hyh.fileUtil.DateUtil;

@Service
public class FileService implements IFileTrans {
	public List<FileInfo> listRoots(){
		List<FileInfo> files = new ArrayList<FileInfo>();
		File[] listRoots = File.listRoots();
		if (listRoots.length==0) {
			return null;
		}
		for (File f:listRoots) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setName(f);
			fileInfo.setIsFile(f.isDirectory());
			fileInfo.setDate(DateUtil.getModifiedTime(f));
			fileInfo.setAbPath(f.getAbsolutePath());
			fileInfo.setFileName(f.getName());
			fileInfo.setBasePath(Base64Util.encoder(f.getAbsolutePath().replace('\\', '/')));
			files.add(fileInfo);
		}
		return files;
	}
	
	public List<FileInfo> readSubFile(File file){
		List<FileInfo> files = new ArrayList<FileInfo>();
		if (file.isDirectory()) {
			File[] files2 = file.listFiles();
			for (File temp:files2) {
				FileInfo fileInfo = new FileInfo();
				fileInfo.setIsFile(temp.isDirectory());
				fileInfo.setName(temp);
				fileInfo.setDate(DateUtil.getModifiedTime(temp));
				fileInfo.setAbPath(temp.getAbsolutePath());
				fileInfo.setFileName(temp.getName());
				fileInfo.setBasePath(Base64Util.encoder(temp.getAbsolutePath().replace('\\', '/')));
				files.add(fileInfo);
			}
		}
		return files;
	}
}
