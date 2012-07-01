package com.cityproperties.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.ClientPrivilegeDAO;
import com.cityproperties.domain.ClientPrivilege;
import com.opensymphony.xwork2.ActionSupport;

public class ClientPrivilegeAction extends ActionSupport  {

	// Session
	private ClientPrivilege clientPrivilege;
	private List<ClientPrivilege> clientPrivileges;
	
	// DI via Spring	
	private ClientPrivilegeDAO clientPrivilegeDao;
	
	public String execute() {
		return SUCCESS;
	}

	/**
	 * To save or update user.
	 * @return String
	 */
	public String saveOrUpdate() {
		clientPrivilegeDao.save(clientPrivilege);
		return SUCCESS;
	}
	
	/**
	 * To list all users.
	 * @return String
	 */	
	public String list() {
		clientPrivileges = clientPrivilegeDao.findAll();
		return SUCCESS;
	}
	
	/**
	 * To delete a user.
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		clientPrivilegeDao.removeById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * To list a single user by Id.
	 * @return String
	 */
	public String edit()	{
		HttpServletRequest request = ServletActionContext.getRequest();
		clientPrivilege = clientPrivilegeDao.find(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public ClientPrivilege getClientPrivilege() {
		return clientPrivilege;
	}

	public void setClientPrivilege(ClientPrivilege clientPrivilege) {
		this.clientPrivilege = clientPrivilege;
	}

	public List<ClientPrivilege> getClientPrivileges() {
		return clientPrivileges;
	}

	public void setClientPrivileges(List<ClientPrivilege> clientPrivileges) {
		this.clientPrivileges = clientPrivileges;
	}

	public void setClientPrivilegeDao(ClientPrivilegeDAO clientPrivilegeDao) {
		this.clientPrivilegeDao = clientPrivilegeDao;
	}
	
}
