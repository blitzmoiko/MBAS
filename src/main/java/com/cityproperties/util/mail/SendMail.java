package com.cityproperties.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.cityproperties.domain.Client;
import com.cityproperties.util.encrypt.Encrypter;

/**
 * @author neil.juan
 *
 */
public class SendMail {

    private JavaMailSenderImpl mailSender;

    /**
     * Authenticate current Client before sending mail
     * @param client
     */
    public void authenticate(Client client) {
        Session session = null;
        final Client clientAuth = client;

        // If user is not admin, authenticate user using username and password
        if (!client.getZuper()) {
            session = Session.getDefaultInstance(
                mailSender.getJavaMailProperties(), new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                clientAuth.getUsername(),
                                Encrypter.decrypt(clientAuth.getPassword()));
                    }

                }
            );

            // Set authenticated session to mail sender
            mailSender.setSession(session);
        }
    }

    /**
     * Send mail
     * @param messagePreparator
     * @throws Exception
     */
    public void send(MimeMessagePreparator messagePreparator) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        messagePreparator.prepare(mimeMessage);

        mailSender.send(messagePreparator);
    }

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

}