package com.cityproperties.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.util.email.SendMail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class SendMailAction
        extends ActionSupport
        implements SessionAware {

    // Fields
    private String templateName;
    private String to;
    private String subject;
    private String message;

    // Session
    private Map<String, Object> session;

    // DI via Spring
    private SendMail sendMail;

    public String execute() {
        Map<String, String> model = new HashMap<String, String>();
        model.put("message", message); // FIXME Add a model creator that will populate credentials of BA

//		sendMail.setTemplateName(templateName); // FIXME this should be templateName
        sendMail.setTemplateName("simple");
        sendMail.setModel(model);
        sendMail.setTo(to);
        sendMail.setSubject(subject);

        try {
            sendMail.send();
        } catch (Exception e) {
            e.printStackTrace();
            return INPUT;
        }

        return SUCCESS;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @RequiredStringValidator(message="Please specify at least one recipient.")
    @EmailValidator(message="E-mail is invalid.")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSendMail(SendMail sendMail) {
        this.sendMail = sendMail;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
