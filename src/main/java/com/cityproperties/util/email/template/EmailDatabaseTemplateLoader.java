package com.cityproperties.util.email.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.cityproperties.dao.MailTypeDAO;
import com.cityproperties.domain.MailType;

import freemarker.cache.TemplateLoader;

public class EmailDatabaseTemplateLoader implements TemplateLoader {

	private MailTypeDAO mailTypeDao;

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

	public void closeTemplateSource(Object templateSource) throws IOException {}

	public MailTypeDAO getMailTypeDao() {
		return mailTypeDao;
	}

	public void setMailTypeDao(MailTypeDAO mailTypeDao) {
		this.mailTypeDao = mailTypeDao;
	}

}
