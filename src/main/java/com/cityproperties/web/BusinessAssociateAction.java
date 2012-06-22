package com.cityproperties.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BusinessAssociateAction extends ActionSupport implements ModelDriven<BusinessAssociate> {
	// Constants
	private final String BA = "businessAssociate";

	// Session
	private Map<String, Object> session;
	private BusinessAssociate businessAssociate = new BusinessAssociate();
	private List<BusinessAssociate> businessAssociates = new ArrayList<BusinessAssociate>();

	// DI via Spring
	private BusinessAssociateDAO businessAssociateDao;

	public BusinessAssociate getModel() {
		return businessAssociate;
	}

	public String execute() {
		session.put(BA, businessAssociate);
		return SUCCESS;
	}

	/**
	 * To save or update user.
	 * 
	 * @return String
	 */
	public String saveOrUpdate() {
		businessAssociateDao.save(businessAssociate);
		return SUCCESS;
	}

	/**
	 * To list all users.
	 * 
	 * @return String
	 */
	public String list() {
		businessAssociates = businessAssociateDao.findAll();
		return SUCCESS;
	}

	/**
	 * To delete a user.
	 * 
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		businessAssociate = businessAssociateDao.find(Long.parseLong(request.getParameter("id")));
		businessAssociateDao.remove(businessAssociate);
		return SUCCESS;
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		businessAssociate = businessAssociateDao.find(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public void setBusinessAssociate(BusinessAssociate businessAssociate) {
		this.businessAssociate = businessAssociate;
	}

	public List<BusinessAssociate> getBusinessAssociates() {
		return businessAssociates;
	}

	public void setBusinessAssociates(List<BusinessAssociate> businessAssociates) {
		this.businessAssociates = businessAssociates;
	}

	public void setBusinessAssociateDao(BusinessAssociateDAO businessAssociateDao) {
		this.businessAssociateDao = businessAssociateDao;
	}

}
