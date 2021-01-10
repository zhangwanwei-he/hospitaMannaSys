package com.offo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
//将日期转化成字符串
public static String dateString(Date dt) {
		return sdf.format(dt);
}
//将字符串转日期
public static Date stringDate(String str) {
	try {
		return sdf.parse(str);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
}
}
