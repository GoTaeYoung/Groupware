package com.groupware.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupware.service.UserService;
import com.groupware.service.UtilService;

@Controller
public class UserController {
	@Autowired
	UtilService utilService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/checkId", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String checkId(HttpServletRequest request) {
		return userService.checkId(request);
	}

	@RequestMapping("/sign_in")
	public String sign_in(HttpServletRequest request) {
		utilService.removeSession(request);
		return utilService.movePage(request, "로그인", "/sign_in");
	}

	@RequestMapping("/sign_up")
	public String sign_up(HttpServletRequest request) {
		return utilService.movePage(request, "회원가입", "/sign_up");
	}

	@RequestMapping("/sign_edit")
	public String utilEdit(HttpServletRequest request) {
		return utilService.movePage(request, "정보수정", "/sign_edit");
	}
}
