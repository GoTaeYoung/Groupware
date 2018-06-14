package com.groupware.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupware.service.MailService;
import com.groupware.service.UserService;
import com.groupware.service.UtilService;
import com.groupware.vo.MAIL_TABLE_VO;

@Controller
public class MailController {
	@Autowired
	UtilService utilService;
	@Autowired
	MailService mailService;
	@Autowired
	UserService userService;
	String pageName = "";

	@RequestMapping("/mail_send")
	public String send(HttpServletRequest request, HttpServletResponse response) throws IOException {
		pageName = "/mail_send";
		mailService.sendMail(request);
		request.setAttribute("MAIL_LIST", mailService.selectMailList(request, "MAIL_BOX_NAME", pageName));
		mailService.setMailCount(request, pageName);
		return userService.setUser(request, response, "보낸메일함", pageName);
	}

	@RequestMapping("/mail_my")
	public String my(HttpServletRequest request) {
		pageName = "/mail_my";
		mailService.sendMail(request);
		request.setAttribute("MAIL_LIST", mailService.selectMailList(request, "MAIL_BOX_NAME", pageName));
		mailService.setMailCount(request, pageName);
		return utilService.movePage(request, "내게쓴메일함", pageName);
	}

	@RequestMapping("/mail_write")
	public String write(HttpServletRequest request) {
		pageName = "/mail_write";
		return utilService.movePage(request, "메일쓰기", pageName);
	}

	@RequestMapping("/mail_writeMe")
	public String writeMe(HttpServletRequest request) {
		pageName = "/mail_writeMe";
		return utilService.movePage(request, "내게쓰기", pageName);
	}

	@RequestMapping("/mail_garbage")
	public String garbage(HttpServletRequest request) {
		pageName = "/mail_garbage";
		request.setAttribute("MAIL_LIST", mailService.selectMailList(request, "MAIL_BOX_NAME", pageName));
		mailService.setMailCount(request, pageName);
		return utilService.movePage(request, "휴지통", pageName);
	}

	@RequestMapping("/mail_read")
	public String read(HttpServletRequest request) {
		pageName = "/mail_read";
		mailService.setMAIL_TABLE_VO(request);
		return utilService.movePage(request, "메일읽기", pageName);
	}

	@RequestMapping("/readMail")
	public String readMail(HttpServletRequest request) {
		pageName = "/readMail";
		request.setAttribute("MAIL_LIST", mailService.selectMailList(request, "MAIL_READ", "1"));
		return utilService.movePage(request, "읽은 메일", pageName);
	}

	@RequestMapping("/noReadMail")
	public String noReadMail(HttpServletRequest request) {
		pageName = "/noReadMail";
		request.setAttribute("MAIL_LIST", mailService.selectMailList(request, "MAIL_READ", "0"));
		return utilService.movePage(request, "읽지 않은 메일", pageName);
	}

	@RequestMapping("/attachMail")
	public String attachMail(HttpServletRequest request) {
		pageName = "/attachMail";
		request.setAttribute("MAIL_LIST", mailService.selectMailList(request, "MAIL_ATTACH", "1"));
		return utilService.movePage(request, "첨부파일 있는 메일", pageName);
	}

	@RequestMapping("/mail_order")
	@ResponseBody
	public List<MAIL_TABLE_VO> order(HttpServletRequest request) {
		return mailService.MailListOrder(request);
	}

	@RequestMapping("/mail_delete")
	@ResponseBody
	public void delete(HttpServletRequest request) {
		mailService.deleteMail(request);
	}

	@RequestMapping("/mail_rollback")
	@ResponseBody
	public void rollback(HttpServletRequest request) {
		mailService.rollbackMail(request);
	}

	@RequestMapping("/mail_changeRead")
	@ResponseBody
	public void changeRead(HttpServletRequest request) {
		mailService.changeReadMail(request);
	}
}
