package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.opensymphony.xwork2.ActionSupport;

public class ClientAction extends ActionSupport implements SessionAware {
	// Constants
	private static final String CLIENT = "client";
	
	// Session
	private Map<String, Object> session;
	private Client client;
	private List<Client> clients = new ArrayList<Client>();

	//DI via Spring
	private ClientDAO clientDao;
	
	public String execute() {
		session.put(CLIENT, client);
		session.put("clients", clients);
		return SUCCESS;
	}

	/**
	 * To save or update user.
	 * @return String
	 */
	public String saveOrUpdate() {	
		clientDao.save(client);
		return SUCCESS;
	}

	/**
	 * To list all users.
	 * @return String
	 */
	public String list() {
		clients = clientDao.findAll();
		return SUCCESS;
	}

	/**
	 * To delete a user.
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		clientDao.removeById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * To list a single user by Id.
	 * @return String
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		client = clientDao.find(Long.parseLong(request.getParameter("id")));
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
