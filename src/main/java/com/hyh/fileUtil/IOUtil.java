package com.hyh.fileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author hu.yuhao
 * <p>文件流处理工具</p>
 * */
public class IOUtil {
	private static final byte[] MAX_BUFFER_SIZE = new byte[4096];
	
	@SuppressWarnings("unused")
	public static int copyLarge(InputStream is, OutputStream os) throws IOException {
		if (is == null || os == null)return -1;
		int read = 0;
		while(-1 != (read=is.read(MAX_BUFFER_SIZE))) {
			os.write(MAX_BUFFER_SIZE);
		}
		return 1;
	}
}
