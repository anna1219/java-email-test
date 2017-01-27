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

    private String emailServerHost;
    private String emailServerPort;
    private String fromEmail;
    private String password;

    @Autowired
    public MailClient(MailHelper mailHelper,
                      @Value("${from.email.address}") String fromEmail,
                      @Value("${from.email.password}") String password,
                      @Value("${email.server.host}") String emailServerHost,
                      @Value("${email.server.port}") String emailServerPort) {
        this.mailHelper = mailHelper;
        this.fromEmail = fromEmail;
        this.password = password;
        this.emailServerHost = emailServerHost;
        this.emailServerPort = emailServerPort;
    }

    public void sendEmail(String toEmail) {
        System.out.println("TLSEmail Start");

        Properties props = new Properties();
        props.put("mail.smtp.host", emailServerHost);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        mailHelper.sendMail(toEmail, session);
    }


}
