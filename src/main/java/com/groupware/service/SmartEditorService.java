package com.groupware.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.vo.SmartEditorVO;

@Service
public class SmartEditorService {
	@Autowired
	UtilService utilService;

	public ModelAndView makePopup(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/photo_uploader");
		String textAreaName = request.getParameter("textAreaName");
		model.addObject("textAreaName", textAreaName);
		return model;
	}

	public String photoUpload(HttpServletRequest request, SmartEditorVO vo) {
		String textAreaName = request.getParameter("textAreaName");

		MultipartFile file = vo.getFiledata();
		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();

		String original_name = "";
		String file_result = "";
		try {
			if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
				original_name = file.getOriginalFilename();

				String rootPath = utilService.getRootPath(request);
				String path = "temp" + File.separator + textAreaName + File.separator;
				utilService.mkdir(rootPath + path);

				String time = UtilService.getNowTime();
				String ext = utilService.getFileExt(original_name);
				String real_name = time + "." + ext;

				file.transferTo(new File(rootPath + path + real_name));
				file_result += "&bNewLine=true&sFileName=" + original_name + "&sFileURL=" + File.separator + path
						+ real_name;
			} else {
				file_result += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("redirect:" + callback + "?callback_func=" + callback_func + file_result);
		return "redirect:" + callback + "?callback_func=" + callback_func + file_result;
	}
}
