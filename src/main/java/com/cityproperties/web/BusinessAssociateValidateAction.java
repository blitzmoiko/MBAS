package com.cityproperties.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.domain.BusinessAssociate;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class BusinessAssociateValidateAction extends ActionSupport implements SessionAware {
	// Constants
	private static final String BA = "businessAssociate";
	
	// Fields
	private Long businessAssociateId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String gender;
	private String homePhone;
	private String workPhone;
	private String mobilePhone;
	private Date birthDate;
	private String regToUser;
	private boolean supplier;
	
	// Session
	private Map<String, Object> session;
	private BusinessAssociate businessAssociate = new BusinessAssociate();
	private List<BusinessAssociate> businessAssociates = new ArrayList<BusinessAssociate>();
	
	public Long getBusinessAssociateId() {
		return businessAssociateId;
	}
	
	public void setBusinessAssociateId(Long businessAssociateId) {
		this.businessAssociateId = businessAssociateId;
	}
	
	@RequiredStringValidator(message="First name is required.")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@RequiredStringValidator(message="Last name is required.")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@RequiredStringValidator(message="E-mail is required.")
	@EmailValidator(message="E-mail is invalid.")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@RequiredStringValidator(message="Gender is required.")
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@RegexFieldValidator(expression = "![CDATA[\n{3}-\n{3}-\n{4}]]", message = "Home Phone number must be entered as 999-999-9999.")
	public String getHomePhone() {
		return homePhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	@RegexFieldValidator(expression = "![CDATA[\n{3}-\n{3}-\n{4}]]", message = "Work Phone number must be entered as 999-999-9999.")
	public String getWorkPhone() {
		return workPhone;
	}
	
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
	@RegexFieldValidator(expression = "![CDATA[\n{3}-\n{3}-\n{4}]]", message = "Mobile Phone number must be entered as 999-999-9999.")
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@RequiredStringValidator(message="Birthdate is required.")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getRegToUser() {
		return regToUser;
	}
	
	public void setRegToUser(String regToUser) {
		this.regToUser = regToUser;
	}
	
	public boolean isSupplier() {
		return supplier;
	}
	
	public void setSupplier(boolean supplier) {
		this.supplier = supplier;
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

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
