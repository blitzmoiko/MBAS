package com.cityproperties.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class ForwardAction
        extends ActionSupport
        implements SessionAware {

    // Session
    private Map<String, Object> session;

    public String execute() {
        session.remove(Constants.MODEL_CLIENT);
        session.remove(Constants.CLIENTS);
        session.remove(Constants.MODEL_BA);
        session.remove(Constants.BAS);
        session.remove(Constants.MODEL_LETTER_CONTENT);
        session.remove(Constants.LETTER_CONTENTS);
        session.remove(Constants.MODEL_LETTER_TEMPLATE);
        session.remove(Constants.LETTER_TEMPLATES);
        return SUCCESS;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
