package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class ForwardAction 
		extends ActionSupport 
		implements SessionAware {

	// Session
	private Map<String, Object> session;
	private Client client;
	private List<Client> clients;
	
	// DI via Spring
	private ClientDAO clientDao;
	
	public String execute() {
		session.remove(Constants.MODEL_CLIENT);
		session.remove(Constants.CLIENTS);
		session.remove(Constants.MODEL_BA);
		session.remove(Constants.BAS);
		session.remove(Constants.MODEL_LETTER_CONTENT);
		session.remove(Constants.LETTER_CONTENTS);
		session.remove(Constants.MODEL_LETTER_TEMPLATE);
		session.remove(Constants.LETTER_TEMPLATES);
		return SUCCESS;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
