package com.cityproperties.util.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.cityproperties.util.mail.SendMail;

@Service
public class BirthdayMailService_Impl
        implements MailService {

    private static final Logger logger = Logger.getLogger(BirthdayMailService_Impl.class);
    private static final String templateName = "birthday";
    private static final String subject = "Happy Birthday";

    @Autowired
    private BusinessAssociateDAO businessAssociateDao;

    public void setBusinessAssociateDao(BusinessAssociateDAO businessAssociateDao) {
        this.businessAssociateDao = businessAssociateDao;
    }

    @Autowired
    private SendMail sendMail;

    public void setSendMail(SendMail sendMail) {
        this.sendMail = sendMail;
    }

    /* (non-Javadoc)
     * @see com.cityproperties.util.scheduler.MailService#generateMail()
     */
    // Start task at 6:00 AM
    @Scheduled(cron = "0 0 6 * * ?")
    public void generateMail() {

        logger.info("Starting report at " + new Date(System.currentTimeMillis()));

        // Get all BA that birthday from today
        List<BusinessAssociate> bas = (ArrayList<BusinessAssociate>) businessAssociateDao.findByBirthdayNow();

        // Send them mail by Admin
        BirthdayMailServicePreparator mailPreparator = null;
        for (BusinessAssociate ba : bas) {

            mailPreparator = new BirthdayMailServicePreparator(ba);
            mailPreparator.setTemplateName(templateName);
            mailPreparator.setTo(ba.getEmail());
            mailPreparator.setSubject(subject);

            try {
                sendMail.send(mailPreparator);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // Log the email with the following reasons: Full name, event, date

        logger.info("Report sent at " + new Date(System.currentTimeMillis()));

    }

}
