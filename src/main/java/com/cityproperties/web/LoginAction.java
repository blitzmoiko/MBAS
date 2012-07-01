package com.cityproperties.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class LoginAction extends ActionSupport implements SessionAware {

	// Fields
	private String username;
	private String password;

	// Session
	private Map<String, Object> session;
	private Client client;

	// DI via Spring
	private ClientDAO clientDao;

	public String execute() {
		client = clientDao.loadByUsernameAndPassword(username, password);
		if (client == null) {
			addActionError(getText("error.login"));
			return INPUT;
		} else {
			session.put(Constants.CLIENT, client);
			return SUCCESS;
		}
	}

	@RequiredStringValidator(message = "Username is required.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@RequiredStringValidator(message = "Password is required.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
