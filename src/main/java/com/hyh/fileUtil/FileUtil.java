package com.hyh.fileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

/**
 * 获取计算机用户名，创建默认目录
 * */
public class FileUtil {

    @PostConstruct
    public void init(){
        StringBuffer sb = new StringBuffer("C:/Users/");
        Map<String, String> map = System.getenv();
        String username = map.get("USERNAME");
        sb.append(username+"/"+"filetransport/"+"authority.txt");
        File file = new File(sb.toString());
        File file1 = new File("C:/Users/"+username+"/"+"filetransport");
        if (!file.exists()){
        	if (!file1.exists()) {
        		file1.mkdir();
        	}
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Properties properties = new Properties();
        InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream("filePath.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.isEmpty());
        properties.setProperty("file.path", sb.toString());
        try {
			resourceAsStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static String getFilePath(){
        Properties properties = new Properties();
        InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream("filePath.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty("file.path");
        return property;
    }
}
