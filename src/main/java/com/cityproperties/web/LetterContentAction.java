package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.LetterContentDAO;
import com.cityproperties.domain.LetterContent;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class LetterContentAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Session
    private Map<String, Object> session;
    private LetterContent letterContent;
    private List<LetterContent> letterContents;

    //DI via Spring
    @Autowired
    private LetterContentDAO letterContentDao;

    public void setLetterContentDao(LetterContentDAO letterContentDao) {
        this.letterContentDao = letterContentDao;
    }

    @SuppressWarnings("unchecked")
    public void prepare() {
        if (session.containsKey(Constants.MODEL_LETTER_CONTENT)) {
            letterContent = (LetterContent) session.get(Constants.MODEL_LETTER_CONTENT);
        }

        if (session.containsKey(Constants.LETTER_CONTENTS)) {
            letterContents = (List<LetterContent>) session.get(Constants.LETTER_CONTENTS);
        }
    }

    public String execute() {
        return SUCCESS;
    }

    /**
     * To save or update letter content.
     * @return String
     */
    public String saveOrUpdate() {
        letterContentDao.save(letterContent);
        addActionMessage("Letter Template " + letterContent.getName() + " is saved.");
        return SUCCESS;
    }

    /**
     * To list all letter contents.
     * @return String
     */
    public String list() {
        letterContents = letterContentDao.findAll();
        return SUCCESS;
    }

    /**
     * To delete a letter content.
     * @return String
     */
    public String delete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        letterContentDao.removeById(Long.parseLong(request.getParameter("id")));
        return SUCCESS;
    }

    /**
     * To list a single letter content by Id.
     * @return String
     */
    public String edit() {
        HttpServletRequest request = ServletActionContext.getRequest();
        letterContent = letterContentDao.find(Long.parseLong(request.getParameter("id")));
        session.put(Constants.MODEL_LETTER_CONTENT, letterContent);
        return SUCCESS;
    }

    public LetterContent getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(LetterContent letterContent) {
        this.letterContent = letterContent;
    }

    public List<LetterContent> getLetterContents() {
        return letterContents;
    }

    public void setLetterContents(List<LetterContent> letterContents) {
        this.letterContents = letterContents;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
