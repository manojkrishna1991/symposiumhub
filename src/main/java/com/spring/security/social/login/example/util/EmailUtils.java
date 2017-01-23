package com.spring.security.social.login.example.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

/**
 * 
 * @author manoj krishna
 * @project symposiumhub
 * @version 1.0
 */
public class EmailUtils {

	private static final Log logger = LogFactory.getLog(EmailUtils.class);

	private String from;

	private String to;

	private String content;

	private String subject;

	private String senderName;
	
	private String type;
	
	private Map<String,String> parameters;
	

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	private static String apiKey = "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";

	public EmailUtils(String from, String to, String subject) {
		this.from = from;
		this.to = to;
		this.subject = subject;

	}

	public boolean setContent(String template) {

		try (BufferedInputStream inputStream = (BufferedInputStream) this
				.getClass().getClassLoader().getResourceAsStream(template);) {

			String content = IOUtils.toString(inputStream);
			this.content = content;
			inputStream.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean sendMail() {

		Email from = new Email(this.from);
		String subject = this.subject;
		Email to = new Email(this.to);

		Content content = new Content("text/html", this.content);
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(EmailUtils.apiKey);
		Request request = new Request();
		try {
			request.method = Method.POST;
			request.endpoint = "mail/send";
			request.body = mail.build();
			Response response = sg.api(request);
			logger.debug(response.statusCode);

			return true;
		} catch (Exception e) {

			logger.debug("unable ro send mail");

			return false;
		}

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSender() {
		Properties properties = new Properties();
		try (BufferedInputStream inputStream = (BufferedInputStream) this
				.getClass().getClassLoader()
				.getResourceAsStream("application.properties");) {

			properties.load(inputStream);
			String sender = properties.getProperty("sender");
			return sender;
		} catch (IOException e) {

			logger.debug("Exception:: in getSender");
			return null;
		}
	}

	public void sendWelcomeEmail() {

		this.from = getSender();
		this.subject = EmailTemplates.WELCOME_SUBJECT;
		setContent(EmailTemplates.WELCOME_EMAIL);
		String content = this.content;
		this.content = subsituteVariable(content);
		boolean result = sendMail();
		if (result) {
			logger.debug("email successfully sent ");
		}
	}

	public void sendSymposiumRegistrationEmail(String no){
		this.from=getSender();
		String subject=EmailTemplates.SYM_REGEMAIL_SUBJECT;
	    subject=subject.replace("****", no);
	    subject=subject.replaceAll("####", getSenderName());
	    this.subject=subject;
		setContent(EmailTemplates.SYM_REGEMAIL);
		String content=this.content;
		content=subsituteVariable(content);
		this.content=subsituteRegNotifications(content, no);
		boolean result=sendMail();
		
		if (result) {
			logger.debug("email successfully sent ");
		}
		
	}
	public String subsituteVariable(String content) {

		if (getSenderName().isEmpty()) {

			return content;
		}
		Map valuesMap = new HashMap<String, String>();
		valuesMap.put("####", getSenderName());
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String subString = sub.replace(content);
		return subString;
	}

	public String subsituteRegNotifications(String content,String no) {

		
		Map valuesMap = new HashMap<String, String>();
		valuesMap.put("****", no);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String subString = sub.replace(content);
		return subString;
	}
}
