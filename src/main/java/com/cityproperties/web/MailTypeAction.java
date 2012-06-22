package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.dao.MailTypeDAO_Impl;
import com.cityproperties.domain.MailType;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MailTypeAction extends ActionSupport implements ModelDriven<MailType> {

	private MailType mailType = new MailType();
	private List<MailType> mailTypes = new ArrayList<MailType>();
	
	private MailTypeDAO mailTypeDao = new MailTypeDAO_Impl();
	//DI via Spring
	public void setMailTypeDao(MailTypeDAO mailTypeDao) {
		this.mailTypeDao = mailTypeDao;
	}

	public MailType getModel() {
		return mailType;
	}

	public String saveOrUpdate() {
		mailTypeDao.save(mailType);
		return Action.SUCCESS;
	}
	
	public String list() {
		mailTypes = mailTypeDao.findAll();
		return Action.SUCCESS;
	}
	
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		mailType = mailTypeDao.find(Long.parseLong(request.getParameter("id")));
		mailTypeDao.remove(mailType);
		return Action.SUCCESS;
	}
	
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		mailType = mailTypeDao.find(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
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

}
