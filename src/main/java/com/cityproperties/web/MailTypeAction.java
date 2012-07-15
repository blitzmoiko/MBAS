package com.cityproperties.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.MailType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MailTypeAction extends ActionSupport {
	
	// Session
	private MailType mailType;
	private List<MailType> mailTypes;
	
	//DI via Spring
	private MailTypeDAO mailTypeDao;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * To save or update mail type.
	 * @return String
	 */
	public String saveOrUpdate() {
		mailTypeDao.save(mailType);
		return SUCCESS;
	}
	
	/**
	 * To list all mail types.
	 * @return String
	 */
	public String list() {
		mailTypes = mailTypeDao.findAll();
		return SUCCESS;
	}
	
	/**
	 * To delete a mail type.
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		mailType = mailTypeDao.find(Long.parseLong(request.getParameter("id")));
		mailTypeDao.remove(mailType);
		return SUCCESS;
	}
	
	/**
	 * To delete a mail type.
	 * @return String
	 */
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

	public void setMailTypeDao(MailTypeDAO mailTypeDao) {
		this.mailTypeDao = mailTypeDao;
	}

}
