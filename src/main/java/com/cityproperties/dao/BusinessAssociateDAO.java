package com.cityproperties.dao;

import java.io.Serializable;
import java.util.List;

import com.cityproperties.domain.BusinessAssociate;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface BusinessAssociateDAO
        extends GenericDAO<BusinessAssociate, Serializable> {

    /**
     * Find Business Associate object by email
     * @param email
     * @return
     */
    public BusinessAssociate findByEmail(String email);

    /**
     * Find Business Associate object by birthday
     * @return
     */
    public List<BusinessAssociate> findByBirthdayNow();

    /**
     * Find Business Associate object by anniversary
     * @return
     */
    public List<BusinessAssociate> findByAnniversaryNow();

}
