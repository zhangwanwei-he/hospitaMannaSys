package com.offo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
//������ת�����ַ���
public static String dateString(Date dt) {
		return sdf.format(dt);
}
//���ַ���ת����
public static Date stringDate(String str) {
	try {
		return sdf.parse(str);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
}
}
