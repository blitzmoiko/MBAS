package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.LetterContentDAO;
import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.LetterContent;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.domain.MailType;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class MailTypeMultiTableAction
        extends ActionSupport
        implements SessionAware {

    // Session
    private Map<String, Object> session;
    private List<MailType> mailTypes;
    private List<LetterContent> letterContents;
    private List<LetterTemplate> letterTemplates;

    // DI via Spring
    @Autowired
    private MailTypeDAO mailTypeDao;

    public void setMailTypeDao(MailTypeDAO mailTypeDao) {
        this.mailTypeDao = mailTypeDao;
    }

    @Autowired
    private LetterContentDAO letterContentDao;

    public void setLetterContentDao(LetterContentDAO letterContentDao) {
        this.letterContentDao = letterContentDao;
    }

    @Autowired
    private LetterTemplateDAO letterTemplateDao;

    public void setLetterTemplateDao(LetterTemplateDAO letterTemplateDao) {
        this.letterTemplateDao = letterTemplateDao;
    }

    public String execute() {
        mailTypes = mailTypeDao.findAll();
        session.put(Constants.MAIL_TYPES, mailTypes);

        letterContents = letterContentDao.findAll();
        session.put(Constants.LETTER_CONTENTS, letterContents);

        letterTemplates = letterTemplateDao.findAll();
        session.put(Constants.LETTER_TEMPLATES, letterTemplates);

        return SUCCESS;
    }

    public List<MailType> getMailTypes() {
        return mailTypes;
    }

    public void setMailTypes(List<MailType> mailTypes) {
        this.mailTypes = mailTypes;
    }

    public List<LetterContent> getLetterContents() {
        return letterContents;
    }

    public void setLetterContents(List<LetterContent> letterContents) {
        this.letterContents = letterContents;
    }

    public List<LetterTemplate> getLetterTemplates() {
        return letterTemplates;
    }

    public void setLetterTemplates(List<LetterTemplate> letterTemplates) {
        this.letterTemplates = letterTemplates;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}