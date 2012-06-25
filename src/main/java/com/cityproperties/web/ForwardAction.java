package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.opensymphony.xwork2.ActionSupport;

public class ForwardAction extends ActionSupport implements SessionAware {
	// TODO Will this get the values of client and clients, debug please
	
	// Session
	private Map<String, Object> session;
	
	// Fields
	private Client client ;
	private List<Client> clients = new ArrayList<Client>();

	// DI via Spring
	private ClientDAO clientDao;
	
	public String execute() {
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
