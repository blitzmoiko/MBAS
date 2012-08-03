package com.cityproperties.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.cityproperties.domain.Client;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.domain.MailType;
import com.cityproperties.util.Constants;
import com.cityproperties.util.mail.SendException;
import com.cityproperties.util.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class SendMailAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Fields
    private String from;
    private String to;
    private String subject;
    private String message;
    private String templateName;

    // Session
    private Map<String, Object> session;
    private Client client;
    private MailType mailType;

    @Autowired
    private Configuration fmConfig;

    public void setFmConfig(Configuration fmConfig) {
        this.fmConfig = fmConfig;
    }

    @Autowired
    private SendMail sendMail;

    public void setSendMail(SendMail sendMail) {
        this.sendMail = sendMail;
    }

    @Autowired
    private BusinessAssociateDAO businessAssociateDao;

    public void setBusinessAssociateDao(BusinessAssociateDAO businessAssociateDao) {
        this.businessAssociateDao = businessAssociateDao;
    }

    @Autowired
    private MailTypeDAO mailTypeDao;

    public void setMailTypeDao(MailTypeDAO mailTypeDao) {
        this.mailTypeDao = mailTypeDao;
    }

    public void prepare() {
        // Authenticate current client
        client = (Client) session.get(Constants.CLIENT);
        sendMail.authenticate(client);
    }

    public String execute() {
        // Prepare list of recipients
        List<BusinessAssociate> businessAssociates = populateBAFromRecipients(to);

        if (!businessAssociates.isEmpty()) {
            for (final BusinessAssociate businessAssociate: businessAssociates) {

                // Create message preparator
                MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

                    public void prepare(MimeMessage mimeMessage) throws Exception {
                        // Use the true flag to indicate you need a multipart message
                        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                        // Merge the model into the freemarker template
                        String result;
                        Map<String, String> model = null;

                        if (!templateName.isEmpty()) {
                            try {
                                // Add stuff to the model for inserting into the template
                                model = new HashMap<String, String>();
                                model.put("name", businessAssociate.getFirstName() + " " + businessAssociate.getLastName());
                                model.put("inlineImage", "<img src='cid:inlineImage'>");

                                result = FreeMarkerTemplateUtils.processTemplateIntoString(fmConfig.getTemplate(templateName), model);
                            } catch (IOException e) {
                                throw new SendException(
                                        "Unable to read or process freemarker configuration or template", e);
                            } catch (TemplateException e) {
                                throw new SendException(
                                        "Problem initializing freemarker or rendering template '" + templateName + "'", e);
                            }
                        } else {
                            result = message;
                        }

                        helper.setFrom(from);
                        helper.setTo(businessAssociate.getEmail());
                        helper.setSubject(subject);

                        // Use the true flag to indicate the text included is HTML
                        helper.setText(result, true);

                        mailType = mailTypeDao.findByDescription(templateName);
                        if (mailType != null) {
                            LetterTemplate template = mailType.getLetterTemplate();
                            if (template != null) {
                                // Add an inline image
                                helper.addInline("inlineImage", new ByteArrayResource(template.getTemplate()), template.getContentType());
                            }
                        }
                    }
                };

                try {
                    sendMail.send(messagePreparator);
                    addActionMessage("Message was sent.");
                    return SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    return INPUT;
                }
            }
        }

        return INPUT;
    }

    /**
     * Generate list of Business Associates from To address
     * @param to
     */
    private List<BusinessAssociate> populateBAFromRecipients(String to) {

        String[] tos = StringUtils.delimitedListToStringArray(to, ";");
        List<BusinessAssociate> businessAssociates = new ArrayList<BusinessAssociate>();

        for (String recipient : tos) {
            BusinessAssociate ba = businessAssociateDao.findByEmail(recipient);

            if (ba != null) {
                businessAssociates.add(ba);
            }

        }

        return businessAssociates;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @RequiredStringValidator(message="Please specify at least one recipient.")
    @EmailValidator(message="E-mail is invalid.")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @RequiredStringValidator(message="Please fill up subject.")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @RequiredStringValidator(message="Please fill up message.")
    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
