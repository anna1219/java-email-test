package com.java_email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
public class MailClient {

    private MailHelper mailHelper;

    private String fromEmail;
    private String password;

    @Autowired
    public MailClient(MailHelper mailHelper,
                      @Value("${from.email.address}") String fromEmail,
                      @Value("${from.email.password}") String password) {
        this.mailHelper = mailHelper;
        this.fromEmail = fromEmail;
        this.password = password;
    }

    public void sendEmail(String toEmail) {
        System.out.println("TLSEmail Start");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        mailHelper.sendMail(toEmail, session);
    }


}
