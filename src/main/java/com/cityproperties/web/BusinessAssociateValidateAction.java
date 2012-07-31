package com.cityproperties.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class BusinessAssociateValidateAction
        extends ActionSupport
        implements SessionAware, Preparable {

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
    private Date anniversaryDate;
    private Long regToUserId;
    private boolean supplier;

    // Session
    private Map<String, Object> session;
    private BusinessAssociate businessAssociate;
    private List<BusinessAssociate> businessAssociates;

    // DI via Spring
    @Autowired
    private ClientDAO clientDao;

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    @Autowired
    private BusinessAssociateDAO businessAssociateDao;

    public void setBusinessAssociateDao(BusinessAssociateDAO businessAssociateDao) {
        this.businessAssociateDao = businessAssociateDao;
    }

    @SuppressWarnings("unchecked")
    public void prepare() {
        if (session.containsKey(Constants.MODEL_BA)) {
            businessAssociate = (BusinessAssociate) session.get(Constants.MODEL_BA);
            setBusinessAssociateId(businessAssociate.getBusinessAssociateId());
            setFirstName(businessAssociate.getFirstName());
            setMiddleName(businessAssociate.getMiddleName());
            setLastName(businessAssociate.getLastName());
            setEmail(businessAssociate.getEmail());
            setGender(businessAssociate.getSex());
            setHomePhone(businessAssociate.getHomePhone());
            setWorkPhone(businessAssociate.getWorkPhone());
            setMobilePhone(businessAssociate.getMobilePhone());
            setBirthDate(businessAssociate.getBirthDate());
            setAnniversaryDate(businessAssociate.getAnniversaryDate());
            setRegToUserId(businessAssociate.getClient().getClientId());
            setSupplier(businessAssociate.getSupplier());
        }

        if (session.containsKey(Constants.BAS)) {
            businessAssociates = (List<BusinessAssociate>) session.get(Constants.BAS);
        }
    }

    public String execute() {

        if (businessAssociateId != null) {
            businessAssociate = (BusinessAssociate) session.get(Constants.MODEL_BA);
        } else {
            businessAssociate = new BusinessAssociate();
        }

        businessAssociate.setFirstName(firstName);
        businessAssociate.setMiddleName(middleName);
        businessAssociate.setLastName(lastName);
        businessAssociate.setSex(gender);
        businessAssociate.setEmail(email);
        businessAssociate.setHomePhone(homePhone);
        businessAssociate.setWorkPhone(workPhone);
        businessAssociate.setMobilePhone(mobilePhone);
        businessAssociate.setBirthDate(birthDate);
        businessAssociate.setAnniversaryDate(anniversaryDate);
        businessAssociate.setSupplier(supplier);
        businessAssociate.setClient(clientDao.find(regToUserId));

        session.put(Constants.MODEL_BA, businessAssociate);

        return SUCCESS;
    }

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

    @RegexFieldValidator(expression = "\\d{3}-\\d{3}-\\d{4}", message = "Home Phone number must be entered as 999-999-9999.")
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @RegexFieldValidator(expression = "\\d{3}-\\d{3}-\\d{4}", message = "Work Phone number must be entered as 999-999-9999.")
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @RegexFieldValidator(expression = "\\d{3}-\\d{3}-\\d{4}", message = "Mobile Phone number must be entered as 999-999-9999.")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @RequiredFieldValidator(message="Birth date is required.")
    @DateTimeFormat(pattern="dd-MM-yy")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @DateTimeFormat(pattern="dd-MM-yy")
    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public Long getRegToUserId() {
        return regToUserId;
    }

    public void setRegToUserId(Long regToUserId) {
        this.regToUserId = regToUserId;
    }

    public boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(boolean supplier) {
        this.supplier = supplier;
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
