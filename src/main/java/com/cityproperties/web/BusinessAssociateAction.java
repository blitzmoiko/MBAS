package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BusinessAssociateAction extends ActionSupport implements SessionAware, Preparable {

	// Session
	private Map<String, Object> session;
	private BusinessAssociate businessAssociate;
	private List<BusinessAssociate> businessAssociates;

	// DI via Spring
	private BusinessAssociateDAO businessAssociateDao;

	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		if (session.containsKey(Constants.MODEL_BA)) {
			businessAssociate = (BusinessAssociate) session.get(Constants.MODEL_BA);		
		}
		
		if (session.containsKey(Constants.BAS)) {
			businessAssociates = (List<BusinessAssociate>) session.get(Constants.BAS);		
		}
		else {
			businessAssociates = businessAssociateDao.findAll();
			session.put(Constants.BAS, businessAssociates);
		}
	}

	public String execute() {
		return SUCCESS;
	}

	/**
	 * To save or update user.
	 * @return String
	 */
	public String saveOrUpdate() {
		businessAssociateDao.save(businessAssociate);
		return SUCCESS;
	}

	/**
	 * To list all users.
	 * @return String
	 */
	public String list() {
		businessAssociates = businessAssociateDao.findAll();
		session.put(Constants.BAS, businessAssociates);
		return SUCCESS;
	}

	/**
	 * To delete a user.
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		businessAssociateDao.removeById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	/**
	 * To list a single user by Id.
	 * @return String
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		businessAssociate = businessAssociateDao.find(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public BusinessAssociate getBusinessAssociate() {
		return businessAssociate;
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
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
