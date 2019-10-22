package com.hyh.fileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.twmacinta.util.MD5;

/**
 * @author hu.yuhao
 *
 * <p>服务端设定所有值均不为空</p>
 * */
public class MD5Util {
    //加密，将密文放入指定文件
    /**
     * @param text <p>需要加密的明文</p>
     * @param path <p>密文存储文件路径</p>
     * */
    public static void encrypt(String text, String path){
        File tempFile = new File(path);
        if (!tempFile.exists()){
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MD5 md5 = new MD5();
        md5.Update(text);
        String hex = md5.asHex();
        try {
            FileWriter fw = new FileWriter(tempFile, false);
            fw.write(hex);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String encrypt(String code) {
    	MD5 md5 = new MD5();
        md5.Update(code);
        String hex = md5.asHex();
        return hex;
    }

    //内容比较，进行身份认证
    /**
     * @param pass <p>用户输入权限认证码</p>
     * @param path <p>文件路径</p>
     * @return <p>返回比较结果，true认证通过，false认证失败</p>
     * */
    @SuppressWarnings("resource")
	public static boolean checkAuth(String pass, String path) {
        File file = new File(path);
        if (!file.exists())return false;
        MD5 md5 = new MD5();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String readLine = bufferedReader.readLine();
            md5.Update(pass);
            String hex = md5.asHex();
            if (readLine.equals(hex)){
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
