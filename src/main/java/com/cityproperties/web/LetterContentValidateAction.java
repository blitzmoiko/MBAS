package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.LetterContentDAO;
import com.cityproperties.domain.LetterContent;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class LetterContentValidateAction 
		extends ActionSupport 
		implements SessionAware, Preparable {

	// Fields
	private Long letterContentId;
	private String name;
	private String content;
	
	// Session
	private Map<String, Object> session;
	private LetterContent letterContent;
	private List<LetterContent> letterContents;
	
	// DI via Spring
	private LetterContentDAO letterContentDao;
	
	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		if (session.containsKey(Constants.MODEL_LETTER_CONTENT) ) {
			letterContent = (LetterContent) session.get(Constants.MODEL_LETTER_CONTENT);
			setLetterContentId(letterContent.getLetterContentId());
			setName(letterContent.getName());
			setContent(letterContent.getContent());
		}
		
		if (session.containsKey(Constants.LETTER_CONTENTS)) {
			letterContents = (List<LetterContent>) session.get(Constants.LETTER_CONTENTS);		
		}  
	}

	public String execute() {
		if (letterContentId != null) {
			letterContent = (LetterContent) session.get(Constants.MODEL_LETTER_CONTENT);
		} else {
			letterContent = new LetterContent();
		}
		
		letterContent.setName(name);
		letterContent.setContent(content);
		
		session.put(Constants.MODEL_LETTER_CONTENT, letterContent);
		
		return SUCCESS;
	}
	
	public Long getLetterContentId() {
		return letterContentId;
	}

	public void setLetterContentId(Long letterContentId) {
		this.letterContentId = letterContentId;
	}
	
	@RequiredStringValidator(message="Name is required.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@RequiredStringValidator(message="Text template is required.")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LetterContent getLetterContent() {
		return letterContent;
	}

	public void setLetterContent(LetterContent letterContent) {
		this.letterContent = letterContent;
	}

	public List<LetterContent> getLetterContents() {
		return letterContents;
	}

	public void setLetterContents(List<LetterContent> letterContents) {
		this.letterContents = letterContents;
	}

	@Autowired
	public void setLetterContentDao(LetterContentDAO letterContentDao) {
		this.letterContentDao = letterContentDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
