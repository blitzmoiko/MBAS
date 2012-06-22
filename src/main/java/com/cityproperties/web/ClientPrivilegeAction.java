package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.ClientPrivilegeDAO;
import com.cityproperties.domain.ClientPrivilege;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClientPrivilegeAction extends ActionSupport implements ModelDriven<ClientPrivilege> {

	private ClientPrivilege clientPrivilege = new ClientPrivilege();
	private List<ClientPrivilege> clientPrivileges = new ArrayList<ClientPrivilege>();
	
	private ClientPrivilegeDAO clientPrivilegeDao;
	//DI via Spring
	public void setClientPrivilegeDao(ClientPrivilegeDAO clientPrivilegeDao) {
		this.clientPrivilegeDao = clientPrivilegeDao;
	}

	public ClientPrivilege getModel() {
		return clientPrivilege;
	}

	public String saveOrUpdate() {
		clientPrivilegeDao.save(clientPrivilege);
		return Action.SUCCESS;
	}
	
	public String list() {
		clientPrivileges = clientPrivilegeDao.findAll();
		return Action.SUCCESS;
	}
	
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		clientPrivilege = clientPrivilegeDao.find(Long.parseLong(request.getParameter("id")));
		clientPrivilegeDao.remove(clientPrivilege);
		return Action.SUCCESS;
	}
	
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
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
	
}
