package com.cityproperties.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.util.Constants;
import com.cityproperties.util.encrypt.EncryptPassword;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class ChangePasswordAction 
		extends ActionSupport 
		implements SessionAware {

	// Fields
	private String oldPassword;
	private String newPassword;
	private String reNewPassword;

	// Session
	private Map<String, Object> session;
	private Client client;

	// DI via Spring
	private ClientDAO clientDao;

	public String execute() {

		/* TODO After validating the old password and the new password, it will
		 be able to confirm that it had successfully changed the password on the result page*/
		
		if (session.containsKey(Constants.CLIENT)) {
			client = (Client) session.get(Constants.CLIENT);
		}
		
		else {
			return INPUT;
		}

		if (!clientDao.findOldPasswordIfExists(client, oldPassword)) {
			addFieldError("oldPassword", getText("error.nonExistingPassword"));
		}

		else {
			if (oldPassword.equals(newPassword)) {
				addFieldError("newPassword", getText("error.samePassword"));
			}

			else if (!newPassword.equals(reNewPassword)) {
				addFieldError("reNewPassword", getText("error.unmatchedPassword"));
			}

			else {
				String encrypted = EncryptPassword.encrypt(newPassword);
				client.setPassword(encrypted);
				clientDao.save(client);

				session.put(Constants.CLIENT, client);
				
				return SUCCESS;
			}
		}
		
		return INPUT;
	}
	
	@RequiredStringValidator(message="Retype new password.")
	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	@RequiredStringValidator(message="Type old password.")
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@RequiredStringValidator(message="Type new password.")	
	@StringLengthFieldValidator(minLength="5", maxLength="10", message="Password must be from 5 to 10 characters.") 
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Autowired
	public void setClientDao(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
