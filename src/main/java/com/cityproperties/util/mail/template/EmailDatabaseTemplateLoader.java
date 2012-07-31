package com.cityproperties.util.mail.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.MailType;

import freemarker.cache.TemplateLoader;

public class EmailDatabaseTemplateLoader
        implements TemplateLoader {

    @Autowired
    private MailTypeDAO mailTypeDao;

    public void setMailTypeDao(MailTypeDAO mailTypeDao) {
        this.mailTypeDao = mailTypeDao;
    }


    public Object findTemplateSource(String name) throws IOException {
        return mailTypeDao.findByDescription(name);
    }

    public long getLastModified(Object templateSource) {
        final MailType emailTemplate = (MailType) templateSource;
        return emailTemplate.getRevisionDate().getTime();
    }

    public Reader getReader(Object templateSource, String encoding)
            throws IOException {
        return new StringReader(((MailType) templateSource).getLetterContent().getContent());
    }

    public void closeTemplateSource(Object templateSource) throws IOException {

    }

}
