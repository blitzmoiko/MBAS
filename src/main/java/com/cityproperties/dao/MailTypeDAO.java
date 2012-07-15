package com.cityproperties.dao;

import java.io.Serializable;

import com.cityproperties.domain.MailType;

public interface MailTypeDAO 
		extends GenericDAO<MailType, Serializable> {
	
	/**
	 * Find Mail Type object by name
	 * @param username
	 * @return
	 */
	public MailType findByDescription(String name);

}
