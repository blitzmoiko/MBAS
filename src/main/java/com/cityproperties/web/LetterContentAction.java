package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.LetterContentDAO;
import com.cityproperties.domain.LetterContent;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LetterContentAction extends ActionSupport implements ModelDriven<LetterContent> {

	private LetterContent letterContent = new LetterContent();
	private List<LetterContent> letterContents = new ArrayList<LetterContent>();
	
	private LetterContentDAO letterContentDao;
	//DI via Spring
	public void setLetterContentDao(LetterContentDAO letterContentDao) {
		this.letterContentDao = letterContentDao;
	}

	public LetterContent getModel() {
		return letterContent;
	}

	public String saveOrUpdate() {
		letterContentDao.save(letterContent);
		return Action.SUCCESS;
	}
	
	public String list() {
		letterContents = letterContentDao.findAll();
		return Action.SUCCESS;
	}
	
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		letterContent = letterContentDao.find(Long.parseLong(request.getParameter("id")));
		letterContentDao.remove(letterContent);
		return Action.SUCCESS;
	}
	
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		letterContent = letterContentDao.find(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
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

}
