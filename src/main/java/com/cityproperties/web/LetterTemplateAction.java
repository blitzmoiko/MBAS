package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.domain.LetterTemplate;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LetterTemplateAction extends ActionSupport implements ModelDriven<LetterTemplate> {

	private LetterTemplate letterTemplate = new LetterTemplate();
	private List<LetterTemplate> letterTemplates = new ArrayList<LetterTemplate>();
	
	private LetterTemplateDAO letterTemplateDao;
	//DI via Spring
	public void setLetterTemplateDao(LetterTemplateDAO letterTemplateDao) {
		this.letterTemplateDao = letterTemplateDao;
	}

	public LetterTemplate getModel() {
		return letterTemplate;
	}

	public String saveOrUpdate() {
		letterTemplateDao.save(letterTemplate);
		return Action.SUCCESS;
	}
	
	public String list() {
		letterTemplates = letterTemplateDao.findAll();
		return Action.SUCCESS;
	}
	
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		letterTemplate = letterTemplateDao.find(Long.parseLong(request.getParameter("id")));
		letterTemplateDao.remove(letterTemplate);
		return Action.SUCCESS;
	}
	
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		letterTemplate = letterTemplateDao.find(Long.parseLong(request.getParameter("id")));
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

}
