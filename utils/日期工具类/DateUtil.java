package com.fin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 	�ַ���תDate����
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 	ʱ������ת�ַ���
	 * @param date
	 * @return
	 */
	public String date2Str(Date date) {
		return sdf.format(date);
	}
}
