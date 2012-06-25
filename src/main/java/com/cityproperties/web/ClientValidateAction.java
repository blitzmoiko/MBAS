package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.domain.Client;
import com.cityproperties.domain.ClientPrivilege;
import com.cityproperties.util.EncryptPassword;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class ClientValidateAction extends ActionSupport implements SessionAware {
	// Constants
	public static final String CLIENT = "client";
	
	// Fields
	private Long clientId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean zuper;
	private boolean active;
	private boolean view;
	private boolean insert;
	private boolean update;
	private boolean delete;
	
	// Session
	private Map<String, Object> session;
	private Client client = new Client();
	private List<Client> clients = new ArrayList<Client>();

	@SuppressWarnings("unchecked")
	public String execute() {
		
		if (session.containsKey("clients")) {
			
			clients = (List<Client>) session.get("clients");
			
		}
		
		// Encrypt password
		if (clientId == null) {
			client.setPassword(EncryptPassword.encrypt(password));
		}
		
		// Activate
		client.setActive(active);
		
		// Add Privileges
		ClientPrivilege clientPrivilege = new ClientPrivilege(client, view, insert, update, delete);
		
		client.setClientPrivilege(clientPrivilege);
		
		session.put(CLIENT, client);

		session.put("clients", clients);
		
		return SUCCESS;
		
	}
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	@RequiredStringValidator(message="First name is required.")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@RequiredStringValidator(message="Last name is required.")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@RequiredStringValidator(message="Username is required.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@RequiredStringValidator(message="Password is required.")
	@StringLengthFieldValidator(minLength="5", maxLength="10", message="Password must be from 5 to 10 characters.") 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isZuper() {
		return zuper;
	}

	public void setZuper(boolean zuper) {
		this.zuper = zuper;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
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
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
