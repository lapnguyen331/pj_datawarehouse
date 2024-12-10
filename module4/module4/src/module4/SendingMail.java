package module4;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class SendingMail {
	 public static void sendMail(String title, String content) {
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");//sử dụng TLS
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	       Authenticator authenticator = new Authenticator() {
	           @Override
	           protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication("webtmdttest@gmail.com","vdmmohpuwjmqiijg");
	           }
	       };
	       //Session
	        Session session = Session.getInstance(prop,authenticator);
	        //Tạo tin nhắn
	        MimeMessage mimeMessage = new MimeMessage(session);
	        try {
	            mimeMessage.addHeader("Content-Type","text/HTML; charset=UTF-8");
	        mimeMessage.setFrom(new InternetAddress("webtmdttest@gmail.com"));
	            mimeMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse("21130542@st.hcmuaf.edu.vn",false));
	            mimeMessage.setSubject(MimeUtility.encodeText(title, "UTF-8", "Q"));
	            mimeMessage.setContent(content,"text/HTML; charset=UTF-8");
	            mimeMessage.setSentDate(new Date());
	            Transport.send(mimeMessage);
	            System.out.println("oke roi");
	            System.out.println(content);
	        } catch (MessagingException e) {
	            System.out.println("loi roi ma");
	            System.out.println(e.getMessage().toString());
	            throw new RuntimeException(e);
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	public static void main(String[] args) {
		sendMail("hi", "aaa");
	    }


}
