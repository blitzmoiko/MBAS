package com.cityproperties.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.util.Constants;
import com.cityproperties.util.security.CustomClient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ForwardAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Session
    private Map<String, Object> session;

    @Autowired
    private ClientDAO clientDao;

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public void prepare() throws Exception {
        if (!session.containsKey(Constants.CLIENT)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomClient cclient = (CustomClient) auth.getPrincipal();
            Client client = clientDao.find(cclient.getClientId());
            session.put(Constants.CLIENT, client);
        }
    }

    public String execute() {
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
