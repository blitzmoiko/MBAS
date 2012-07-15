package com.cityproperties.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.cityproperties.domain.MailType;

public class MailTypeDAO_Impl 
		extends GenericDAOImpl<MailType, Serializable>
		implements MailTypeDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cityproperties.dao.MailTypeDAO#findByDescription(java.lang.String)
	 */
	public MailType findByDescription(String name) {

		Criteria crit = getSession().createCriteria(MailType.class);
		crit.add(Restrictions.eq("name", name));

		return (MailType) crit.uniqueResult();

	}

}
