package com.groupware.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.vo.ATTACH_LIST_TABLE_VO;
import com.groupware.vo.ATTACH_TABLE_VO;
import com.groupware.vo.MAIL_TABLE_VO;

@Mapper
public interface MailDao {
	public int getAllMailCount(String MAIL_BOX_NAME);

	public int getNoReadMailCount(String MAIL_BOX_NAME);

	public MAIL_TABLE_VO selectMail(String MAIL_PK);

	public List<MAIL_TABLE_VO> selectMailList(HashMap<String, String> hashMap);

	public void insertMail(MAIL_TABLE_VO MAIL_TABLE_VO);

	public void insertAttachFileList(ATTACH_LIST_TABLE_VO ATTACH_LIST_TABLE_VO);

	public void insertAttachFile(ATTACH_TABLE_VO ATTACH_TABLE_VO);

	public void deleteMail(String MAIL_PK);

	public void updateMail(HashMap<String, String> hashMap);

	public ATTACH_LIST_TABLE_VO selectAttachList(String MAIL_PK);

	public List<ATTACH_TABLE_VO> selectAttach(String ATTACH_LIST_PK);
}
