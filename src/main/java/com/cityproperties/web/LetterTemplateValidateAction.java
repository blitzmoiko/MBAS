package com.cityproperties.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class LetterTemplateValidateAction 
		extends ActionSupport 
		implements SessionAware, Preparable {

	// Fields
	private Long letterTemplateId;
	private String name;
	private File templateImage;
	private String templateImageContentType;
	private byte[] template;
	private String contentType;
	
	// Session
	private Map<String, Object> session;
	private LetterTemplate letterTemplate;
	private List<LetterTemplate> letterTemplates;
	
	// DI via Spring
	private LetterTemplateDAO letterTemplateDao;
	
	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		if (session.containsKey(Constants.MODEL_LETTER_TEMPLATE) ) {
			letterTemplate = (LetterTemplate) session.get(Constants.MODEL_LETTER_TEMPLATE);
			setLetterTemplateId(letterTemplate.getLetterTemplateId());
			setName(letterTemplate.getName());
			setTemplate(letterTemplate.getTemplate());
			setContentType(letterTemplate.getContentType());
		}
		
		if (session.containsKey(Constants.LETTER_TEMPLATES)) {
			letterTemplates = (List<LetterTemplate>) session.get(Constants.LETTER_TEMPLATES);		
		}  
	}

	public String execute() {
		if (letterTemplateId != null) {
			letterTemplate = (LetterTemplate) session.get(Constants.MODEL_LETTER_TEMPLATE);
		} else {
			letterTemplate = new LetterTemplate();
		}
		
		try {
			if (templateImage != null) {
				letterTemplate.setName(name);
				byte[] fileArray = org.springframework.util.FileCopyUtils
						.copyToByteArray(templateImage);
				letterTemplate.setTemplate(fileArray);
				letterTemplate.setContentType(templateImageContentType);
			}
		} catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }
		
		session.put(Constants.MODEL_LETTER_TEMPLATE, letterTemplate);
		
		return SUCCESS;
	}
	
	public Long getLetterTemplateId() {
		return letterTemplateId;
	}

	public void setLetterTemplateId(Long letterTemplateId) {
		this.letterTemplateId = letterTemplateId;
	}
	
	@RequiredStringValidator(message="Name is required.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public byte[] getTemplate() {
		return template;
	}

	public void setTemplate(byte[] template) {
		this.template = template;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public File getTemplateImage() {
		return templateImage;
	}

	public void setTemplateImage(File templateImage) {
		this.templateImage = templateImage;
	}

	public String getTemplateImageContentType() {
		return templateImageContentType;
	}

	public void setTemplateImageContentType(String templateImageContentType) {
		this.templateImageContentType = templateImageContentType;
	}

	public LetterTemplate getLetterTemplate() {
		return letterTemplate;
	}

	public void setLetterTemplate(LetterTemplate letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	public List<LetterTemplate> getLetterTemplates() {
		return letterTemplates;
	}

	public void setLetterTemplates(List<LetterTemplate> letterTemplates) {
		this.letterTemplates = letterTemplates;
	}

	@Autowired
	public void setLetterTemplateDao(LetterTemplateDAO letterTemplateDao) {
		this.letterTemplateDao = letterTemplateDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
