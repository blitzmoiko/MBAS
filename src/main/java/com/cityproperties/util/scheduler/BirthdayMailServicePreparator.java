package com.cityproperties.util.scheduler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.cityproperties.domain.MailType;
import com.cityproperties.util.mail.SendException;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class BirthdayMailServicePreparator
        implements MimeMessagePreparator {

    private Map<String, String> model;
    private String templateName;
    private String from;
    private String to;
    private String subject;
    private String message;

    private BusinessAssociate businessAssociate;
    private MailType mailType;

    @Autowired
    private Configuration fmConfig;

    public void setFmConfig(Configuration fmConfig) {
        this.fmConfig = fmConfig;
    }

    @Autowired
    private MailTypeDAO mailTypeDao;

    public void setMailTypeDao(MailTypeDAO mailTypeDao) {
        this.mailTypeDao = mailTypeDao;
    }

    public BirthdayMailServicePreparator(BusinessAssociate businessAssociate) {
        this.businessAssociate = businessAssociate;
    }

    public void prepare(MimeMessage mimeMessage) throws Exception {
        // Use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Merge the model into the freemarker template
        final String result;

        model = new HashMap<String, String>();
        prepareModel(model, businessAssociate);

        if (!templateName.isEmpty()) {
            try {
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

        // Construct and send the message
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(result);

        mailType = mailTypeDao.findByDescription(templateName);
        if (mailType.getLetterTemplate() != null) {
            // helper.addInline("identifier1234", res); //TODO get MailType - LetterTemplate - getImage()
        }

    }

    private void prepareModel(Map<String, String> model, BusinessAssociate ba) {
        // Prepare model
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
