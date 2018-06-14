package com.groupware.vo;

public class USER_TABLE_VO {
	String USER_ID;
	String USER_PASSWORD;
	String USER_NAME;
	String USER_MAIL;
	String USER_GENDER;

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getUSER_PASSWORD() {
		return USER_PASSWORD;
	}

	public void setUSER_PASSWORD(String uSER_PASSWORD) {
		USER_PASSWORD = uSER_PASSWORD;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getUSER_MAIL() {
		return USER_MAIL;
	}

	public void setUSER_MAIL(String uSER_MAIL) {
		USER_MAIL = uSER_MAIL;
	}

	public String getUSER_GENDER() {
		return USER_GENDER;
	}

	public void setUSER_GENDER(String uSER_GENDER) {
		USER_GENDER = uSER_GENDER;
	}

	@Override
	public String toString() {
		return "USER_TABLE_VO [USER_ID=" + USER_ID + ", USER_PASSWORD=" + USER_PASSWORD + ", USER_NAME=" + USER_NAME
				+ ", USER_MAIL=" + USER_MAIL + ", USER_GENDER=" + USER_GENDER + "]";
	}

}
