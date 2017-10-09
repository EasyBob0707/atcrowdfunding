package com.atguigu.atcrowdfunding.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间与字符串的格式转换
 * 
 * @Author SUNBO
 * @Date 2017年7月10日 下午8:42:25
 * @Version V1.0
 */
public class DateStringConvert {

	// 定义默认的时间转换格式(共19位数据, 符合数据库存储)
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 字符串转换为时间格式
	 * 
	 * @param str
	 *            时间字符串
	 * @return
	 */
	public static Date stringToDate(String str) {
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间转换为字符串
	 * 
	 * @param date 时间
	 * @return
	 */
	public static String dateToString(Date date) {
		return simpleDateFormat.format(date);
	}
}
