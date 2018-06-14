package com.groupware.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
	@Autowired
	MailService mailService;
	@Autowired
	HttpSession session;

	public void mkdir(String dir_path) {
		File dir = new File(dir_path);
		if (!dir.exists()) // 폴더 존재 여부
			dir.mkdirs(); // 현재 폴더와 상위 폴더까지 생성
	}

	public String getRootPath(HttpServletRequest request) {
		String exactOs = System.getProperty("os.name"); // key = os.name, value = 현재 컴퓨터의 OS 이름
		String os = exactOs.split(" ")[0]; // 넘어온 이름값을 공백으로 잘랐을때 0번째 방의 값
		if ("Windows".equals(os)) {
			return request.getSession().getServletContext().getRealPath("/"); // C:\Users\GoTaeYoung\eclipse-workspace\Test1234\src\main\webapp\
		} else {
			return "";
		}
	}

	public String movePage(HttpServletRequest request, String title, String pageName) {
		request.setAttribute("title", title);
		return pageName;
	}

	public String getPrePageName(HttpServletRequest request) {
		String refererName = request.getHeader("referer");
		String prePageName = refererName.substring(refererName.lastIndexOf("/"));
		return prePageName;
	}

	public void removeSession(HttpServletRequest request) {
		session = request.getSession();
		session.invalidate();
	}

	public String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public String getEmailDomain(String email) {
		int start = email.indexOf("@") + 1;
		int end = email.length();
		String mail_domain = email.substring(start, end);
		return mail_domain;
	}

	public static String getNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.KOREA);
		Date date = new Date();
		return format.format(date);
	}
}
