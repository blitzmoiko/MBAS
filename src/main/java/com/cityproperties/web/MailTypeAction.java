package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.MailType;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class MailTypeAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Session
    private Map<String, Object> session;
    private MailType mailType;
    private List<MailType> mailTypes;

    //DI via Spring
    @Autowired
    private MailTypeDAO mailTypeDao;

    public void setMailTypeDao(MailTypeDAO mailTypeDao) {
        this.mailTypeDao = mailTypeDao;
    }

    @SuppressWarnings("unchecked")
    public void prepare() {
        if (session.containsKey(Constants.MODEL_MAIL_TYPE)) {
            mailType = (MailType) session.get(Constants.MODEL_MAIL_TYPE);
        }

        if (session.containsKey(Constants.MAIL_TYPES)) {
            mailTypes = (List<MailType>) session.get(Constants.MAIL_TYPES);
        }
    }

    public String execute() {
        return SUCCESS;
    }

    /**
     * To save or update mail type.
     * @return String
     */
    public String saveOrUpdate() {
        mailTypeDao.save(mailType);
        session.remove(Constants.MODEL_MAIL_TYPE);
        return SUCCESS;
    }

    /**
     * To list all mail types.
     * @return String
     */
    public String list() {
        mailTypes = mailTypeDao.findAll();
        session.put(Constants.MAIL_TYPES, mailTypes);
        return SUCCESS;
    }

    /**
     * To delete a mail type.
     * @return String
     */
    public String delete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        mailTypeDao.removeById(Long.parseLong(request.getParameter("id")));
        return SUCCESS;
    }

    /**
     * To delete a mail type.
     * @return String
     */
    public String edit() {
        HttpServletRequest request = ServletActionContext.getRequest();
        mailType = mailTypeDao.find(Long.parseLong(request.getParameter("id")));
        session.put(Constants.MODEL_MAIL_TYPE, mailType);
        return SUCCESS;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }

    public List<MailType> getMailTypes() {
        return mailTypes;
    }

    public void setMailTypes(List<MailType> mailTypes) {
        this.mailTypes = mailTypes;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
