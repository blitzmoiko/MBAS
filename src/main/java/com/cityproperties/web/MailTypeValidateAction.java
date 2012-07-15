package com.cityproperties.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.LetterContent;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.domain.MailType;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class MailTypeValidateAction 
		extends ActionSupport 
		implements SessionAware, Preparable {
	
	// Fields
	private Long mailTypeId;
	private String name;
	private LetterContent letterContent;
	private LetterTemplate letterTemplate;
	private Date revisionDate;
	
	// Session
	private Map<String, Object> session;
	private MailType mailType;
	private List<MailType> mailTypes;
	
	//DI via Spring
	private MailTypeDAO mailTypeDao;
	
	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		if (session.containsKey(Constants.MODEL_MAIL_TYPE) ) {
			mailType = (MailType) session.get(Constants.MODEL_MAIL_TYPE);
			setMailTypeId(mailType.getMailTypeId());
			setName(mailType.getName());
			setLetterContent(mailType.getLetterContent());
			setLetterTemplate(mailType.getLetterTemplate());
		}
		
		if (session.containsKey(Constants.MAIL_TYPES)) {
			mailTypes = (List<MailType>) session.get(Constants.MAIL_TYPES);		
		}  
	}

	public String execute() {
		mailType = new MailType();
		mailType.setName(name);
		mailType.setLetterContent(letterContent);
		mailType.setLetterTemplate(letterTemplate);
		mailType.setRevisionDate(new Date());
		
		session.put(Constants.MODEL_MAIL_TYPE, mailType);
		
		return SUCCESS;
	}

	public Long getMailTypeId() {
		return mailTypeId;
	}

	public void setMailTypeId(Long mailTypeId) {
		this.mailTypeId = mailTypeId;
	}

	@RequiredStringValidator(message="Template name is required.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@RequiredFieldValidator(message="Letter Content is required.")
	public LetterContent getLetterContent() {
		return letterContent;
	}

	public void setLetterContent(LetterContent letterContent) {
		this.letterContent = letterContent;
	}

	public LetterTemplate getLetterTemplate() {
		return letterTemplate;
	}

	public void setLetterTemplate(LetterTemplate letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	public MailType getMailType() {
		return mailType;
	}

	public void setMailType(MailType mailType) {
		this.mailType = mailType;
	}

	public List<MailType> getMailTypes() {
		return mailTypes;
	}

	public void setMailTypes(List<MailType> mailTypes) {
		this.mailTypes = mailTypes;
	}

	public void setMailTypeDao(MailTypeDAO mailTypeDao) {
		this.mailTypeDao = mailTypeDao;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
