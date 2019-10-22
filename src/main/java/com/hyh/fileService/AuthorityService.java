package com.hyh.fileService;

import com.hyh.fileUtil.FileUtil;
import com.hyh.fileUtil.MD5Util;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    public boolean checkAuthority(String authCode){
        boolean auth = MD5Util.checkAuth(authCode, FileUtil.getFilePath());
        return auth;
    }
}
