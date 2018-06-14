package com.groupware.vo;

public class EmailVO {

	String id;
	String pw;
	String mail_sender;
	String mail_receiver;
	String mail_title;
	String mail_date;
	String mail_content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMail_sender() {
		return mail_sender;
	}

	public void setMail_sender(String mail_sender) {
		this.mail_sender = mail_sender;
	}

	public String getMail_receiver() {
		return mail_receiver;
	}

	public void setMail_receiver(String mail_receiver) {
		this.mail_receiver = mail_receiver;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getMail_date() {
		return mail_date;
	}

	public void setMail_date(String mail_date) {
		this.mail_date = mail_date;
	}

	public String getMail_content() {
		return mail_content;
	}

	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}

	@Override
	public String toString() {
		return "EmailVO [id=" + id + ", pw=" + pw + ", mail_sender=" + mail_sender + ", mail_receiver=" + mail_receiver
				+ ", mail_title=" + mail_title + ", mail_date=" + ", mail_content=" + mail_content + "]";
	}

}
