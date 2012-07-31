package com.cityproperties.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.SessionAware;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class LetterTemplateValidateAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Fields
    private Long letterTemplateId;
    private String name;
    private File templateImage;
    private String templateImageContentType;
    private byte[] template;
    private byte[] thumbnail;
    private String contentType;

    // Session
    private Map<String, Object> session;
    private LetterTemplate letterTemplate;
    private List<LetterTemplate> letterTemplates;

    // DI via Spring
    @Autowired
    private LetterTemplateDAO letterTemplateDao;

    public void setLetterTemplateDao(LetterTemplateDAO letterTemplateDao) {
        this.letterTemplateDao = letterTemplateDao;
    }

    @SuppressWarnings("unchecked")
    public void prepare() throws Exception {
        if (session.containsKey(Constants.MODEL_LETTER_TEMPLATE) ) {
            letterTemplate = (LetterTemplate) session.get(Constants.MODEL_LETTER_TEMPLATE);
            setLetterTemplateId(letterTemplate.getLetterTemplateId());
            setName(letterTemplate.getName());
            setTemplate(letterTemplate.getTemplate());
            setContentType(letterTemplate.getContentType());
        }

        if (session.containsKey(Constants.LETTER_TEMPLATES)) {
            letterTemplates = (List<LetterTemplate>) session.get(Constants.LETTER_TEMPLATES);
        }
    }

    public String execute() {
        if (letterTemplateId != null) {
            letterTemplate = (LetterTemplate) session.get(Constants.MODEL_LETTER_TEMPLATE);
        } else {
            letterTemplate = new LetterTemplate();
        }

        try {
            if (templateImage != null) {
                letterTemplate.setName(name);
                letterTemplate.setTemplate(FileCopyUtils.copyToByteArray(templateImage));
                letterTemplate.setThumbnail(createThumbnail(templateImage));
                letterTemplate.setContentType(templateImageContentType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }

        session.put(Constants.MODEL_LETTER_TEMPLATE, letterTemplate);

        return SUCCESS;
    }

    private byte[] createThumbnail(File image) throws IOException {
        BufferedImage img = ImageIO.read(templateImage);
        BufferedImage scaledImg = Scalr.resize(img, Method.QUALITY, 120, 80, Scalr.OP_ANTIALIAS);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledImg, "jpg", baos);
        baos.flush();

        return baos.toByteArray();
    }

    public Long getLetterTemplateId() {
        return letterTemplateId;
    }

    public void setLetterTemplateId(Long letterTemplateId) {
        this.letterTemplateId = letterTemplateId;
    }

    @RequiredStringValidator(message="Name is required.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getTemplate() {
        return template;
    }

    public void setTemplate(byte[] template) {
        this.template = template;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public File getTemplateImage() {
        return templateImage;
    }

    public void setTemplateImage(File templateImage) {
        this.templateImage = templateImage;
    }

    public String getTemplateImageContentType() {
        return templateImageContentType;
    }

    public void setTemplateImageContentType(String templateImageContentType) {
        this.templateImageContentType = templateImageContentType;
    }

    public LetterTemplate getLetterTemplate() {
        return letterTemplate;
    }

    public void setLetterTemplate(LetterTemplate letterTemplate) {
        this.letterTemplate = letterTemplate;
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
