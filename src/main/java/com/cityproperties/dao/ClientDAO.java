package com.cityproperties.dao;

import java.io.Serializable;

import com.cityproperties.domain.Client;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ClientDAO extends GenericDAO<Client, Serializable> {
	
	/**
	 * Find Client object by username
	 * @param username
	 * @return
	 */
	public Client findByUsername(String username);
	
	/**
	 * Find Client object by username and password
	 * @param username
	 * @param password
	 * @return
	 */
	public Client loadByUsernameAndPassword(String username, String password);
	
	/**
	 * Check if old password matches client's password
	 * @param client
	 * @param oldPassword
	 * @return
	 */
	public boolean findOldPasswordIfExists(Client client, String oldPassword);
	
}
