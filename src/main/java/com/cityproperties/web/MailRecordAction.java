package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.MailRecordDAO;
import com.cityproperties.domain.MailRecord;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MailRecordAction extends ActionSupport implements ModelDriven<MailRecord> {

	private MailRecord mailRecord = new MailRecord();
	private List<MailRecord> mailRecords = new ArrayList<MailRecord>();
	
	private MailRecordDAO mailRecordDao;
	//DI via Spring
	public void setMailRecordDao(MailRecordDAO mailRecordDao) {
		this.mailRecordDao = mailRecordDao;
	}

	public MailRecord getModel() {
		return mailRecord;
	}

	public String saveOrUpdate() {
		mailRecordDao.save(mailRecord);
		return Action.SUCCESS;
	}
	
	public String list() {
		mailRecords = mailRecordDao.findAll();
		return Action.SUCCESS;
	}
	
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		mailRecord = mailRecordDao.find(Long.parseLong(request.getParameter("id")));
		mailRecordDao.remove(mailRecord);
		return Action.SUCCESS;
	}
	
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		mailRecord = mailRecordDao.find(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public MailRecord getMailRecord() {
		return mailRecord;
	}

	public void setMailRecord(MailRecord mailRecord) {
		this.mailRecord = mailRecord;
	}

	public List<MailRecord> getMailRecords() {
		return mailRecords;
	}

	public void setMailRecords(List<MailRecord> mailRecords) {
		this.mailRecords = mailRecords;
	}

}
