package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class LetterTemplateAction 
		extends ActionSupport 
		implements SessionAware, Preparable {
	
	// Session
	private Map<String, Object> session;
	private LetterTemplate letterTemplate;
	private List<LetterTemplate> letterTemplates;
	
	//DI via Spring
	private LetterTemplateDAO letterTemplateDao;
	
	@SuppressWarnings("unchecked")
	public void prepare() {
		if (session.containsKey(Constants.MODEL_LETTER_TEMPLATE)) {
			letterTemplate = (LetterTemplate) session.get(Constants.MODEL_LETTER_TEMPLATE);
		}
		
		if (session.containsKey(Constants.LETTER_TEMPLATES)) {
			letterTemplates = (List<LetterTemplate>) session.get(Constants.LETTER_TEMPLATES);		
		}
	}
	
	public String execute() {
		return SUCCESS;
	}
	
	/**
	 * To save or update letter template.
	 * @return String
	 */
	public String saveOrUpdate() {
		letterTemplateDao.save(letterTemplate);
		return SUCCESS;
	}
	
	/**
	 * To list all letter templates.
	 * @return String
	 */	
	public String list() {
		letterTemplates = letterTemplateDao.findAll();
		return SUCCESS;
	}
	
	/**
	 * To delete a letter template.
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		letterTemplateDao.removeById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * To list a single letter template by Id.
	 * @return String
	 */
	public String edit()	{
		HttpServletRequest request = ServletActionContext.getRequest();
		letterTemplate = letterTemplateDao.find(Long.parseLong(request.getParameter("id")));
		session.put(Constants.MODEL_LETTER_TEMPLATE, letterTemplate);
		return SUCCESS;
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

	public void setLetterTemplateDao(LetterTemplateDAO letterTemplateDao) {
		this.letterTemplateDao = letterTemplateDao;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
