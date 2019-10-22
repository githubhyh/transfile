package com.hyh.fileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * <p>完善date工具类以后直接调用</p>
 * */
public class DateUtil {
	public static String getModifiedTime(File file) {
		Calendar calendar = Calendar.getInstance();
		long lastModified = file.lastModified();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		calendar.setTimeInMillis(lastModified);
		String date = dateFormat.format(calendar.getTime());
		return date;
	}
	
	public static String dateToString(Date date, String patern) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(patern);
		calendar.setTime(date);
		String dateString = dateFormat.format(calendar.getTime());
		return dateString;
	}
}
