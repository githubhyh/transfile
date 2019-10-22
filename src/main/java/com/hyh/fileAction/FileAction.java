package com.hyh.fileAction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hyh.entity.FileInfo;
import com.hyh.fileService.FileDownloadService;
import com.hyh.fileService.FileService;
import com.hyh.fileUtil.Base64Util;
import com.hyh.fileUtil.IOUtil;
import com.hyh.fileUtil.ZipUtil;

@Controller
public class FileAction {
	@Autowired
	private FileService fileService;
	@Autowired
	private FileDownloadService downloadService;
	
	@RequestMapping("/readFile")
	public String readFile(HttpServletRequest req) throws UnsupportedEncodingException {
		String pass = (String)req.getSession().getAttribute("auth");
		if (pass == null||"".equals(pass))return "redirect:/";
		req.setCharacterEncoding("utf-8");
		String filePath = req.getParameter("_file");
		String decoder = Base64Util.decoder(filePath);
		String path = decoder;
		List<FileInfo> subFile = fileService.readSubFile(new File(path));
		req.setAttribute("files", subFile);
		return "home";
	}
	
	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		String filePath = req.getParameter("_file");
		filePath = Base64Util.removeSpecialChar(filePath);
		String decoder = Base64Util.decoder(filePath);
		String path = decoder;
		ZipUtil.zip(path);
		downloadService.downloadByZip(path, res);;
	}
	
	@RequestMapping("/uploadFile")
	public String selectFile(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String destPath = req.getParameter("destPath");
		req.setAttribute("destPath", destPath);
		return "upload";
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String destPath = req.getParameter("destPath");
		String decoder = Base64Util.decoder(destPath);
		String path = decoder;
		MultipartRequest mutilReq = (MultipartRequest)req;
		MultiValueMap<String,MultipartFile> multiFileMap = mutilReq.getMultiFileMap();
		Set<Entry<String,List<MultipartFile>>> entrySet = multiFileMap.entrySet();
		for (Entry<String,List<MultipartFile>> entry:entrySet) {
			List<MultipartFile> files = entry.getValue();
			if (!files.isEmpty()) {
				for (MultipartFile file : files) {
					if (file.isEmpty())break;
					String originalFilename = file.getOriginalFilename();
					File destFile = new File(path+"/"+new String(originalFilename.getBytes("UTF-8"), "UTF-8"));
					if (destFile.exists())break;
					boolean createNewFile = destFile.createNewFile();
					if (!createNewFile)break;
					InputStream is = file.getInputStream();
					FileOutputStream os = new FileOutputStream(destFile);
					IOUtil.copyLarge(is, os);
					os.close();
					is.close();
				}
			}
		}
		return "redirect:/readFile?_file="+destPath;
	}
}
