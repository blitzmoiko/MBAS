package com.cityproperties.util.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.domain.BusinessAssociate;

public class AnniversaryMailService_Impl implements MailService {

    private static final Logger logger = Logger.getLogger(BirthdayMailService_Impl.class);

    @Autowired
    private BusinessAssociateDAO businessAssociateDao;

    /* (non-Javadoc)
     * @see com.cityproperties.util.scheduler.MailService#generateMail()
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void generateMail() {

        logger.info("Starting report at " + new Date(System.currentTimeMillis()));

        // Get all BA that birthday from today
        List<BusinessAssociate> bas = (ArrayList<BusinessAssociate>) businessAssociateDao.findByBirthdayNow();

        for (BusinessAssociate ba: bas) {

            ba.getFirstName();

        }

        // Give them the mail:

        // Log the email with the following reasons: Full name, event, date

        logger.info("Report sent at " + new Date(System.currentTimeMillis()));

    }

}
