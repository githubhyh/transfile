package com.hyh.fileAction;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyh.entity.FileInfo;
import com.hyh.fileService.AuthorityService;
import com.hyh.fileService.FileService;
import com.hyh.fileUtil.MD5Util;

@Controller
public class AuthorityAction {
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private FileService fileService;
	
    @RequestMapping("/")
    public String index(){
        return "authority";
    }
    
    @RequestMapping("/checkAuth")
    public String checkAuth(HttpServletRequest req) throws UnsupportedEncodingException {
    	req.setCharacterEncoding("utf-8");
    	String pass = req.getParameter("text");
    	if (pass == null||"".equals(pass)) {
    		return "redirect:/";
    	}
    	boolean flag = authService.checkAuthority(pass);
    	if (flag) {
    		List<FileInfo> listRoots = fileService.listRoots();
    		req.getSession().setAttribute("auth", MD5Util.encrypt(pass));
    		req.setAttribute("files", listRoots);
    		return "home";
    	}else {
    		return "redirect:/";
    	}
    }
    
    @RequestMapping("/index")
    public String index1() {
    	return "NewFile";
    }
}
