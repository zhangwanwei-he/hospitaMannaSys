package com.offo.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Part;

public class uplodeUtils {
public static String fileUplode(Part part) {
	//获取文件名
	String filename=part.getSubmittedFileName();
	//设置文件路径
	String path="D:/Hospital-pic";
	File file=new File(path);
	//判断是否存在该文件
	if(!file.exists()) {
		file.mkdir();
	}
	//随机产生图片名称
	String img=UUID.randomUUID()+filename;
	try {
		part.write(path+"/"+img);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return img;
	
}
}
