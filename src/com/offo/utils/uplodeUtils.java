package com.offo.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Part;

public class uplodeUtils {
public static String fileUplode(Part part) {
	//��ȡ�ļ���
	String filename=part.getSubmittedFileName();
	//�����ļ�·��
	String path="D:/Hospital-pic";
	File file=new File(path);
	//�ж��Ƿ���ڸ��ļ�
	if(!file.exists()) {
		file.mkdir();
	}
	//�������ͼƬ����
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
