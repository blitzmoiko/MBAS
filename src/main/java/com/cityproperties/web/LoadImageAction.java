package com.cityproperties.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.domain.LetterTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class LoadImageAction 
		extends ActionSupport 
		implements SessionAware {

	// Fields
	private Long letterTemplateId;
	
	// Session
	private Map<String, Object> session;
	private LetterTemplate letterTemplate;
	private List<LetterTemplate> letterTemplates;
	
	// DI via Spring
	private LetterTemplateDAO letterTemplateDao;
	
	public String execute() {
		HttpServletResponse response = null;
		ServletOutputStream out = null;
		
		letterTemplate = letterTemplateDao.find(letterTemplateId);
		
		try {
			response = ServletActionContext.getResponse();
			response.setContentType(letterTemplate.getContentType());
			out = response.getOutputStream();
			out.write(letterTemplate.getTemplate());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return NONE;
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
