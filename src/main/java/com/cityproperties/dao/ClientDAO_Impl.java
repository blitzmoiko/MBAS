package com.cityproperties.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.cityproperties.domain.Client;
import com.cityproperties.util.encrypt.EncryptPassword;

public class ClientDAO_Impl
        extends GenericDAOImpl<Client, Serializable>
        implements ClientDAO {

    /*
     * (non-Javadoc)
     *
     * @see
     * 	com.cityproperties.dao.ClientDAO#findByUsername(java.lang.String)
     */
    public Client findByUsername(String username) {

        Criteria crit = getSession().createCriteria(Client.class);
        crit.add(Restrictions.eq("username", username));

        return (Client) crit.uniqueResult();

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * 	com.cityproperties.dao.ClientDAO#findOldPasswordIfExists(com.cityproperties.domain.Client, java.lang.String)
     */
    public boolean findOldPasswordIfExists(Client client, String oldPassword) {

        if (EncryptPassword.checkPassword(oldPassword, client.getPassword())) {
            return true;
        }

        else {
            return false;
        }

    }

}
