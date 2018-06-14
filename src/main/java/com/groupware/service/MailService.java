package com.groupware.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.groupware.dao.MailDao;
import com.groupware.vo.ATTACH_LIST_TABLE_VO;
import com.groupware.vo.ATTACH_TABLE_VO;
import com.groupware.vo.EmailVO;
import com.groupware.vo.MAIL_TABLE_VO;

@Service
public class MailService {
	@Autowired
	MailDao mailDao;
	@Autowired
	HttpSession session;
	@Autowired
	UtilService utilService;

	HashMap<String, String> hashMap;

	public int getAllMailCount(String mailBoxName) {
		return mailDao.getAllMailCount(mailBoxName);
	}

	public int getNoReadMailCount(String mailBoxName) {
		return mailDao.getNoReadMailCount(mailBoxName);
	}

	public void setMailCount(HttpServletRequest request, String mailBoxName) {
		request.setAttribute("ALL_MAIL_COUNT", this.getAllMailCount(mailBoxName));
		request.setAttribute("NO_READ_MAIL_COUNT", this.getNoReadMailCount(mailBoxName));
	}

	public String getEmail(String nameAndEmail) {
		int start = nameAndEmail.indexOf("(") + 1;
		int end = nameAndEmail.length();
		String email = nameAndEmail.substring(start, end).replace(")", "");

		return email;
	}

	public void setEmailVO(HttpServletRequest request) {
		session = request.getSession();
		String id = (String) session.getAttribute("USER_ID");
		String pw = (String) session.getAttribute("USER_PASSWORD");

		String mail_sender = request.getParameter("sender");
		String mail_receiver = request.getParameter("receiver");
		String mail_title = request.getParameter("mailTitle");
		String mail_content = request.getParameter("SEditor");

		emailVO = new EmailVO();
		emailVO.setId(id);
		emailVO.setPw(pw);
		emailVO.setMail_sender(this.getEmail(mail_sender));
		emailVO.setMail_receiver(this.getEmail(mail_receiver));
		emailVO.setMail_title(mail_title);
		emailVO.setMail_content(mail_content);
	}

	public void setMailFiles(HttpServletRequest request) {
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		List<MultipartFile> multipartFiles = multipart.getFiles(request.getParameter("files"));

		mail_files = new ArrayList<File>();
		sumFileSize = 0;

		String rootPath = utilService.getRootPath(request);
		String path = rootPath + File.separator + "temp" + File.separator + "mail_files" + File.separator
				+ UtilService.getNowTime() + File.separator;
		utilService.mkdir(path);

		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				continue;
			}
			File mail_file = new File(path + multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(mail_file);
				sumFileSize += mail_file.length();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mail_files.add(mail_file);
		}
	}

	public MAIL_TABLE_VO getMAIL_TABLE_VO(SendMail mail, String mailBoxName) {
		MAIL_TABLE_VO mail_table_vo = new MAIL_TABLE_VO();
		mail_table_vo.setUSER_ID(mail.emailVO.getId());
		mail_table_vo.setMAIL_SENDER(mail.emailVO.getMail_sender());
		mail_table_vo.setMAIL_RECEIVER(mail.emailVO.getMail_receiver());
		mail_table_vo.setMAIL_TITLE(mail.emailVO.getMail_title());
		mail_table_vo.setMAIL_DATE(mail.emailVO.getMail_date());
		mail_table_vo.setMAIL_BOX_NAME(mailBoxName);
		mail_table_vo.setMAIL_READ("1");

		String flag = mail_files.size() == 0 ? "0" : "1";
		mail_table_vo.setMAIL_ATTACH(flag);
		return mail_table_vo;
	}

	public ATTACH_LIST_TABLE_VO getATTACH_LIST_TABLE_VO(String mail_pk) {
		ATTACH_LIST_TABLE_VO attach_list_table_vo = new ATTACH_LIST_TABLE_VO();
		attach_list_table_vo.setMAIL_PK(mail_pk);
		attach_list_table_vo.setATTACH_SUM_SIZE(Integer.toString(sumFileSize));
		attach_list_table_vo.setATTACH_SUM_COUNT(Integer.toString(mail_files.size()));
		return attach_list_table_vo;
	}

	EmailVO emailVO = null;
	List<File> mail_files = null;
	int sumFileSize = 0;

	public void sendMail(HttpServletRequest request) {
		if (request.getParameter("receiver") != null) {
			this.setEmailVO(request);
			this.setMailFiles(request);

			SendMail mail = new SendMail(emailVO, mail_files);

			String prePageName = utilService.getPrePageName(request);
			String mailBoxName = "/mail_write".equals(prePageName) ? "/mail_send" : "/mail_my";
			this.insertMail(mail, mailBoxName, request);
		}
	}

	public List<MAIL_TABLE_VO> selectMailList(HttpServletRequest request, String WHERE_TYPE, String WHERE_VALUE) {
		String ORDER_TYPE = request.getParameter("ORDER_TYPE");
		hashMap = new HashMap<>();
		hashMap.put("WHERE_TYPE", WHERE_TYPE);
		hashMap.put("WHERE_VALUE", WHERE_VALUE);
		hashMap.put("ORDER_TYPE", ORDER_TYPE);
		List<MAIL_TABLE_VO> mailList = mailDao.selectMailList(hashMap);
		return mailList;
	}

	public void setMAIL_TABLE_VO(HttpServletRequest request) {
		String MAIL_PK = request.getParameter("MAIL_PK");
		MAIL_TABLE_VO mail = mailDao.selectMail(MAIL_PK);
		request.setAttribute("mail", mail);
		ATTACH_LIST_TABLE_VO attach_list = mailDao.selectAttachList(MAIL_PK);
		if (attach_list != null) {
			request.setAttribute("attach_list", attach_list);
			List<ATTACH_TABLE_VO> attach = mailDao.selectAttach(attach_list.getATTACH_LIST_PK());
			request.setAttribute("attach", attach);
		}
	}

	public void insertMail(SendMail mail, String mailBoxName, HttpServletRequest request) {
		MAIL_TABLE_VO mail_table_vo = this.getMAIL_TABLE_VO(mail, mailBoxName);
		mail_table_vo.setMAIL_CONTENT(request.getParameter("SEditor"));
		mailDao.insertMail(mail_table_vo);
		String mail_pk = mail_table_vo.getMAIL_PK();
		mail_table_vo = mailDao.selectMail(mail_pk);
		System.out.println("메일 정보 ::::: " + mail_table_vo.toString());
		if (mail_files.size() != 0) {
			this.insertAttachFileList(mail_pk);
		}
	}

	public void insertAttachFileList(String mail_pk) {
		ATTACH_LIST_TABLE_VO attach_list_table_vo = this.getATTACH_LIST_TABLE_VO(mail_pk);
		mailDao.insertAttachFileList(attach_list_table_vo);
		System.out.println("첨부파일 목록 ::::: " + attach_list_table_vo.toString());
		this.insertAttachFile(attach_list_table_vo.getATTACH_LIST_PK());
	}

	public void insertAttachFile(String attach_list_pk) {
		ATTACH_TABLE_VO attach_table_vo = new ATTACH_TABLE_VO();
		attach_table_vo.setATTACH_LIST_PK(attach_list_pk);
		for (File file : mail_files) {
			attach_table_vo.setATTACH_SIZE(Long.toString(file.length()));
			attach_table_vo.setATTACH_NAME(file.getName());
			attach_table_vo.setATTACH_ROUTE(file.getAbsolutePath());
			mailDao.insertAttachFile(attach_table_vo);
			System.out.println("첨부파일 ::::: " + attach_table_vo.toString());
		}
	}

	public List<MAIL_TABLE_VO> MailListOrder(HttpServletRequest request) {
		String prePage = utilService.getPrePageName(request);
		if ("/readMail".equals(prePage))
			return this.selectMailList(request, "MAIL_READ", "1");
		else if ("/noReadMail".equals(prePage))
			return this.selectMailList(request, "MAIL_READ", "0");
		else if ("/attachMail".equals(prePage))
			return this.selectMailList(request, "MAIL_ATTACH", "1");
		return this.selectMailList(request, "MAIL_BOX_NAME", prePage);
	}

	public void deleteMail(HttpServletRequest request) {
		String MAIL_PK = request.getParameter("MAIL_PK");
		MAIL_TABLE_VO mail_table_vo = mailDao.selectMail(MAIL_PK);
		String prePage = utilService.getPrePageName(request);
		if ("/mail_garbage".equals(prePage)) {
			mailDao.deleteMail(MAIL_PK);
			System.out.println("영구삭제 ::::: " + mail_table_vo.toString());
		} else {
			hashMap.put("UPDATE_TYPE", "MAIL_BOX_NAME");
			hashMap.put("UPDATE_VALUE", "/mail_garbage");
			hashMap.put("MAIL_PK", MAIL_PK);
			mailDao.updateMail(hashMap);
			System.out.println("휴지통  ::::: " + mail_table_vo.toString());
		}
	}

	public void rollbackMail(HttpServletRequest request) {
		String MAIL_PK = request.getParameter("MAIL_PK");
		MAIL_TABLE_VO mail_table_vo = mailDao.selectMail(MAIL_PK);
		hashMap.put("UPDATE_TYPE", "MAIL_BOX_NAME");
		hashMap.put("UPDATE_VALUE", "/mail_send");
		hashMap.put("MAIL_PK", MAIL_PK);
		mailDao.updateMail(hashMap);
		System.out.println("복원됨  ::::: " + mail_table_vo.toString());
	}

	public void changeReadMail(HttpServletRequest request) {
		String MAIL_PK = request.getParameter("MAIL_PK");
		String UPDATE_VALUE = request.getParameter("UPDATE_VALUE");
		hashMap.put("MAIL_PK", MAIL_PK);
		hashMap.put("UPDATE_TYPE", "MAIL_READ");
		hashMap.put("UPDATE_VALUE", UPDATE_VALUE);
		mailDao.updateMail(hashMap);
	}
}