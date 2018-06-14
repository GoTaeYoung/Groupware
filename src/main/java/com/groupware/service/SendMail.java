package com.groupware.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.groupware.service.UtilService;
import com.groupware.vo.EmailVO;

public class SendMail {

	MimeMessage message = null;
	String host;
	Properties prop;
	Authenticator auth;

	EmailVO emailVO;
	List<String> mail_images;
	List<File> mail_files;
	int imageCount;

	MimeMultipart mp = new MimeMultipart("related");

	public SendMail(EmailVO emailVO, List<File> mail_files) {
		emailVO.setMail_content(this.matchingImageOnContent(emailVO.getMail_content()));
		this.emailVO = emailVO;
		this.mail_files = mail_files;
		System.out.println("메일 정보 ::::: " + this.emailVO.toString());

		this.connectSMTP();
		this.createMail();
		this.sendMail();
	}

	public void connectSMTP() {
		this.setHost();
		this.setProp();
		this.setAuth();
		Session session = Session.getInstance(prop, auth);
		try {
			message = new MimeMessage(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMail() {
		try {

			MimeBodyPart mbp_html = new MimeBodyPart();
			mbp_html.setText(emailVO.getMail_content(), "UTF-8", "HTML");
			mp.addBodyPart(mbp_html);

			MimeBodyPart mbp_images = null;
			this.addImageOnMail(mbp_images);

			MimeBodyPart mbp_files = null;
			this.addFileOnMail(mbp_files);

			message.setSubject(emailVO.getMail_title());

			message.setContent(mp);

			message.setSentDate(new Date());
			emailVO.setMail_date(UtilService.getNowTime());

			// 보내는 사람
			message.setFrom(new InternetAddress(emailVO.getMail_sender()));

			// 단건 전송일 때는 사용 start
			// message.setRecipient(RecipientType.TO, new InternetAddress(""));
			// 단건 전송일 때는 사용 end

			// 복수 건 전송일 때는 사용 start
			InternetAddress[] receive_address = { new InternetAddress(emailVO.getMail_receiver()) };
			message.setRecipients(RecipientType.TO, receive_address);
			// 복수 건 전송일 때는 사용 end

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMail() {
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void setHost() {
		UtilService utilService = new UtilService();
		String mail_domain = utilService.getEmailDomain(emailVO.getMail_sender());

		switch (mail_domain) {
		case "naver.com":
			host = "smtp.naver.com";
			break;
		case "gmail.com":
			host = "smtp.gmail.com";
			break;
		default:
			host = "";
			break;
		}
	}

	public void setProp() {
		prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", host);
	}

	public void setAuth() {
		auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailVO.getId(), emailVO.getPw());
			};
		};
	}

	// html 이미지 태그의 src 경로를 가져온다
	public void setImageSrcList(String mail_content) {
		int start = 0;
		int end = 0;
		mail_images = new ArrayList<String>();
		while (true) {
			start = mail_content.indexOf("<img src=\"", start) + 10;
			if (start - 10 == -1) {
				break;
			}
			end = mail_content.indexOf("\">", start);

			String imageSrc = mail_content.substring(start, end);
			mail_images.add(imageSrc);

			start = end;
		}
	}

	// 기존 이미지 경로를 replace 해주는 메소드
	public String matchingImageOnContent(String mail_content) {
		this.setImageSrcList(mail_content);
		imageCount = 1;
		for (String imageSrc : mail_images) {
			mail_content = mail_content.replace(imageSrc, "cid:img" + imageCount);
			imageCount++;
		}
		return mail_content;
	}

	// 이미지 연결해주는 메소
	public void addImageOnMail(MimeBodyPart mbp_images) throws MessagingException {
		imageCount = 1;
		for (String imageSrc : mail_images) {
			mbp_images = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(
					"C:\\Users\\GoTaeYoung\\20180327_groupware\\Groupware\\src\\main\\webapp" + imageSrc);
			mbp_images.setDataHandler(new DataHandler(fds));
			mbp_images.setHeader("Content-ID", "<img" + imageCount + ">");
			mbp_images.setHeader("Content-Type", "image/*");

			mp.addBodyPart(mbp_images);
			imageCount++;
		}
	}

	// 첨부파일 달아주는 메소드
	public void addFileOnMail(MimeBodyPart mbp_files) throws Exception {
		for (File file : mail_files) {
			mbp_files = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(file);
			mbp_files.setDataHandler(new DataHandler(fds));
			mbp_files.setFileName(MimeUtility.encodeText(fds.getName()));
			mp.addBodyPart(mbp_files);
		}
	}
}