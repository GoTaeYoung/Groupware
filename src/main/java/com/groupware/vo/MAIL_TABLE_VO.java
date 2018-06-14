package com.groupware.vo;

public class MAIL_TABLE_VO {
	String MAIL_PK;
	String USER_ID;
	String MAIL_SENDER;
	String MAIL_RECEIVER;
	String MAIL_TITLE;
	String MAIL_DATE;
	String MAIL_CONTENT;
	String MAIL_BOX_NAME;
	String MAIL_READ;
	String MAIL_ATTACH;

	public String getMAIL_PK() {
		return MAIL_PK;
	}

	public void setMAIL_PK(String mAIL_PK) {
		MAIL_PK = mAIL_PK;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getMAIL_SENDER() {
		return MAIL_SENDER;
	}

	public void setMAIL_SENDER(String mAIL_SENDER) {
		MAIL_SENDER = mAIL_SENDER;
	}

	public String getMAIL_RECEIVER() {
		return MAIL_RECEIVER;
	}

	public void setMAIL_RECEIVER(String mAIL_RECEIVER) {
		MAIL_RECEIVER = mAIL_RECEIVER;
	}

	public String getMAIL_TITLE() {
		return MAIL_TITLE;
	}

	public void setMAIL_TITLE(String mAIL_TITLE) {
		MAIL_TITLE = mAIL_TITLE;
	}

	public String getMAIL_DATE() {
		return MAIL_DATE;
	}

	public void setMAIL_DATE(String mAIL_DATE) {
		MAIL_DATE = mAIL_DATE;
	}

	public String getMAIL_CONTENT() {
		return MAIL_CONTENT;
	}

	public void setMAIL_CONTENT(String mAIL_CONTENT) {
		MAIL_CONTENT = mAIL_CONTENT;
	}

	public String getMAIL_BOX_NAME() {
		return MAIL_BOX_NAME;
	}

	public void setMAIL_BOX_NAME(String mAIL_BOX_NAME) {
		MAIL_BOX_NAME = mAIL_BOX_NAME;
	}

	public String getMAIL_READ() {
		return MAIL_READ;
	}

	public void setMAIL_READ(String mAIL_READ) {
		MAIL_READ = mAIL_READ;
	}

	public String getMAIL_ATTACH() {
		return MAIL_ATTACH;
	}

	public void setMAIL_ATTACH(String mAIL_ATTACH) {
		MAIL_ATTACH = mAIL_ATTACH;
	}

	@Override
	public String toString() {
		return "MAIL_TABLE_VO [MAIL_PK=" + MAIL_PK + ", USER_ID=" + USER_ID + ", MAIL_SENDER=" + MAIL_SENDER
				+ ", MAIL_RECEIVER=" + MAIL_RECEIVER + ", MAIL_TITLE=" + MAIL_TITLE + ", MAIL_DATE=" + MAIL_DATE
				+ ", MAIL_CONTENT=" + MAIL_CONTENT + ", MAIL_BOX_NAME=" + MAIL_BOX_NAME + ", MAIL_READ=" + MAIL_READ
				+ ", MAIL_ATTACH=" + MAIL_ATTACH + "]";
	}

}
