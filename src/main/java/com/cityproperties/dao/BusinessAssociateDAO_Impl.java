package com.cityproperties.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.cityproperties.domain.BusinessAssociate;

public class BusinessAssociateDAO_Impl
        extends GenericDAOImpl<BusinessAssociate, Serializable>
        implements BusinessAssociateDAO {

    private static Date today = new Date();


    /* (non-Javadoc)
     * @see com.cityproperties.dao.BusinessAssociateDAO#findByEmail(java.lang.String)
     */
    public BusinessAssociate findByEmail(String email) {

        Criteria crit = getSession().createCriteria(BusinessAssociate.class);
        crit.add(Restrictions.eq("email", email));

        return (BusinessAssociate) crit.uniqueResult();

    }

    /* (non-Javadoc)
     * @see com.cityproperties.dao.BusinessAssociateDAO#findByBirthday()
     */
    @SuppressWarnings("unchecked")
    public List<BusinessAssociate> findByBirthdayNow() {

        Criteria crit = getSession().createCriteria(BusinessAssociate.class);
        crit.add(Restrictions.eq("birthDate", today));

        return (List<BusinessAssociate>) crit.list();

    }

    /* (non-Javadoc)
     * @see com.cityproperties.dao.BusinessAssociateDAO#findByAnniversary()
     */
    @SuppressWarnings("unchecked")
    public List<BusinessAssociate> findByAnniversaryNow() {

        Criteria crit = getSession().createCriteria(BusinessAssociate.class);
        crit.add(Restrictions.eq("anniversaryDate", today));

        return (List<BusinessAssociate>) crit.list();

    }


}
