package com.groupware.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.service.SmartEditorService;
import com.groupware.vo.SmartEditorVO;

@Controller
public class SmartEditorController {
	@Autowired
	SmartEditorService smartEditorService;

	@RequestMapping("/makePopup")
	public ModelAndView makePopup(HttpServletRequest request) {
		return smartEditorService.makePopup(request);
	}

	@RequestMapping("/photoUpload")
	public String photoUpload(HttpServletRequest request, SmartEditorVO vo) {
		return smartEditorService.photoUpload(request, vo);
	}
}
