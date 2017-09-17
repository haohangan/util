package utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	
	private static String USER_NAME = "name"; //
	private static String SMTP_HOST = "smtp.gmail.com"; //
	private static String PASSWORD = "password"; // GMail password
	private static String FROM = USER_NAME + "@gmail.com";
	private static String RECIPIENT = "目标@qq.com";//
	static Properties properties;

	static {
		properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "false");// 开启debug调试
		properties.setProperty("mail.smtp.auth", "true");// 发送服务器需要身份验证
		properties.setProperty("mail.host", SMTP_HOST);// 设置邮件服务器主机名
		properties.setProperty("mail.transport.protocol", "smtp");// 发送邮件协议名称
	}

	public static void main(String[] args) {
		try {
			sendFromGMail("测试啊", "asdasdasdasd", RECIPIENT);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	* 标题，内容，接收人
	*/
	private static void sendFromGMail(String subject, String content, String... recipient) throws MessagingException {
		if (recipient == null || recipient.length == 0) {
			return;
		}
		Address[] adds = new Address[recipient.length];
		for (int i = 0; i < recipient.length; i++) {
			adds[i] = new InternetAddress(recipient[i]);
		}
		Session session = Session.getInstance(properties);
		Message msg = new MimeMessage(session);// 创建邮件对象
		msg.setSubject(subject);//标题
		msg.setText(content);// 设置邮件内容
		msg.setFrom(new InternetAddress(FROM));// 设置发件人
		Transport transport = session.getTransport();
		transport.connect(USER_NAME, PASSWORD);// 连接邮件服务器
		transport.sendMessage(msg, adds);// 发送邮件
		transport.close();// 关闭连接
	}
}
