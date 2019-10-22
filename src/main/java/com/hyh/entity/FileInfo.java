package com.hyh.entity;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileInfo {
	private File name;
	private boolean isFile;
	private String date;
	private String abPath;
	private String basePath;
	private String fileName;
	public File getName() {
		return name;
	}
	public void setName(File name) {
		this.name = name;
	}
	public boolean getIsFile() {
		return isFile;
	}
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAbPath() {
		return abPath;
	}
	public void setAbPath(String abPath) {
		this.abPath = abPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}	
}
