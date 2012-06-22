package com.cityproperties.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.ClientDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.ConfigurationManager;

public class DefaultAction extends ActionSupport implements SessionAware {
	// Session
	private Map<String, Object> session;

	// DI via Spring
	private ClientDAO clientDao;
	
	public String execute() {
		new ConfigurationManager().destroyConfiguration();
		return SUCCESS;
	}

	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
