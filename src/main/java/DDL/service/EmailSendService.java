package DDL.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {
	@Autowired
	JavaMailSender mailSender;	//메일을 보내기 위한 API 객체
	public void mailSend(String fromEmail, String toEmail, String subject, String contents) {
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.setHeader("content-type", "text/html; charset=UTF-8");
			msg.setContent(contents, "text/html; charset=UTF-8");
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
			mailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
