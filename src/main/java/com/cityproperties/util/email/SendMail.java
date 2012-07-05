package com.cityproperties.util.email;

import java.io.IOException;
import java.util.Map;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class SendMail {

	private MailSender mailSender;
	private Configuration fmConfig;
	private String templateName;
	private Map<String, String> model;
	private String to;
	private String subject;
	private String message;

	public void send() throws Exception {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		// Merge the model into the template
		final String result;
		if (!templateName.isEmpty()) {
			try {
				result = FreeMarkerTemplateUtils.processTemplateIntoString(
						fmConfig.getTemplate(templateName), model);
			} catch (IOException e) {
				throw new SendException(
						"Unable to read or process freemarker configuration or template", e);
				// FIXME Add field error
			} catch (TemplateException e) {
				throw new SendException(
						"Problem initializing freemarker or rendering template '" + templateName + "'", e);
				// FIXME Add field error
			}
		} else {
			result = message;
		}

		// Construct and send the message
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(result);

		mailSender.send(mailMessage);
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setFmConfig(Configuration fmConfig) {
		this.fmConfig = fmConfig;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setModel(Map<String, String> model) {
		this.model = model;
	}
		
	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}