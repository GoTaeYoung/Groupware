package com.groupware.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupware.dao.UserDao;
import com.groupware.vo.USER_TABLE_VO;

@Service
public class UserService {
	@Autowired
	UtilService utilService;
	@Autowired
	UserDao userDao;
	@Autowired
	HttpSession session;

	public String checkId(HttpServletRequest request) {
		String USER_ID = request.getParameter("USER_ID");
		return userDao.checkId(USER_ID) == 0 ? "사용 가능" : "사용 불가능";
	}

	public void setUSER_TABLE_VO(HttpSession session, USER_TABLE_VO user_table_vo) {
		session.setAttribute("USER_ID", user_table_vo.getUSER_ID());
		session.setAttribute("USER_PASSWORD", user_table_vo.getUSER_PASSWORD());
		session.setAttribute("USER_NAME", user_table_vo.getUSER_NAME());
		session.setAttribute("USER_MAIL", user_table_vo.getUSER_MAIL());
		session.setAttribute("USER_GENDER", user_table_vo.getUSER_GENDER());
	}

	public void getUSER_TABLE_VO(HttpSession session, USER_TABLE_VO user_table_vo) {
		user_table_vo.setUSER_ID((String) session.getAttribute("USER_ID"));
		user_table_vo.setUSER_PASSWORD((String) session.getAttribute("USER_PASSWORD"));
		user_table_vo.setUSER_NAME((String) session.getAttribute("USER_NAME"));
		user_table_vo.setUSER_MAIL((String) session.getAttribute("USER_MAIL"));
		user_table_vo.setUSER_GENDER((String) session.getAttribute("USER_GENDER"));
	}

	public void getUSER_TABLE_VO(HttpServletRequest request, USER_TABLE_VO user_table_vo) {
		user_table_vo.setUSER_ID((String) request.getParameter("USER_ID"));
		user_table_vo.setUSER_PASSWORD((String) request.getParameter("USER_PASSWORD"));
		user_table_vo.setUSER_NAME((String) request.getParameter("USER_NAME"));
		user_table_vo.setUSER_MAIL((String) request.getParameter("USER_MAIL"));
		user_table_vo.setUSER_GENDER((String) request.getParameter("USER_GENDER"));
	}

	public void insertUser(USER_TABLE_VO user_table_vo) {
		System.out.println("insert user " + user_table_vo.toString());
		userDao.insertUser(user_table_vo);
	}

	public void updateUser(USER_TABLE_VO user_table_vo) {
		System.out.println("update user " + user_table_vo.toString());
		userDao.updateUser(user_table_vo);
	}

	public USER_TABLE_VO selectUser(USER_TABLE_VO user_table_vo) {
		return userDao.selectUser(user_table_vo);
	}

	public boolean checkLogin(USER_TABLE_VO user_table_vo) {
		boolean flag = true;
		if (this.checkNull(user_table_vo)) {
			flag = false;
		} else if (this.selectUser(user_table_vo) == null) {
			System.out.println("None User");
			flag = false;
		}
		return flag;
	}

	public boolean checkNull(USER_TABLE_VO user_table_vo) {
		boolean flag = false;
		String userId = user_table_vo.getUSER_ID();
		String userPw = user_table_vo.getUSER_PASSWORD();
		if (userId == null || userId.equals("")) {
			System.out.println("ID is Null");
			flag = true;
		} else if (userPw == null || userPw.equals("")) {
			System.out.println("PW is Null");
			flag = true;
		}

		return flag;
	}

	public String setUser(HttpServletRequest request, HttpServletResponse response, String title, String pageName)
			throws IOException {
		USER_TABLE_VO user_table_vo = new USER_TABLE_VO();
		String prePageName = utilService.getPrePageName(request);
		session = request.getSession();

		this.getUSER_TABLE_VO(request, user_table_vo);

		if ("/sign_in".equals(prePageName)) {
			if (this.checkLogin(user_table_vo)) {
				user_table_vo = this.selectUser(user_table_vo);
				System.out.println("select user " + user_table_vo.toString());
			} else {
				response.sendRedirect("/sign_in");
			}
		} else if ("/sign_up".equals(prePageName)) {
			this.insertUser(user_table_vo);
		} else if ("/sign_edit".equals(prePageName)) {
			this.updateUser(user_table_vo);
		} else {
			this.getUSER_TABLE_VO(session, user_table_vo);
			if (!this.checkLogin(user_table_vo)) {
				response.sendRedirect("/sign_in");
			}
		}
		this.setUSER_TABLE_VO(session, user_table_vo);
		return utilService.movePage(request, title, pageName);
	}
}