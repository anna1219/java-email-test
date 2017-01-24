package com.java_email;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailHelper {

    public void sendMail(String toEmail, Session session) {
        try
        {
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("tester@javaemail.com", "JavaEmailTester"));
            msg.setReplyTo(InternetAddress.parse("tester@javaemail.com", false));
            msg.setSubject("Subject", "UTF-8");
            msg.setText("Hellow from Java Email Test", "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            System.out.println("Message is ready");

            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
